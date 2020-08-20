
package Client.Model;

import Game.Packages.*;
import Client.Controller.ClientController;
import Game.Coordinate;
import Game.Graph.Vertex;
import Game.Player;
import Game.Token;
import Game.TokenType;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

public class ClientThread extends Thread{
    
    private ClientController controller;
    private Socket clientSocket;
    private Client client;
    public Player player;
    private boolean running;
    
    public ClientThread(Socket clientSocket, Client client, ClientController controller, Player player) {
        this.controller = controller;
        this.clientSocket = clientSocket;
        this.client = client;
        this.player = player;
        this.running = true;
    }
    
    @Override
    public void run(){
        while (running) {
            try {
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                IPackage pack = (IPackage) input.readObject();
                switch (pack.getType()) {
                    case Message:
                        Message msg = (Message) pack;
                        controller.addMessage(msg.getName(), msg.getMessage());
                        break;
                    case Activity:
                        Activity act = (Activity) pack;
                        controller.addActivity(act.getMessage());
                        break;
                    case Board:
                        Board board = (Board) pack;
                        setBoard(board);
                        break;
                    case Turn:
                        player.setTurn(true);
                        controller.addActivity("Its your turn to atack!");
                        break;
                    case Shield:
                        player.reduceShield();
                        controller.addActivity("Your shield has blocked an attack");
                        controller.updateShield();
                        break;
                    case Offer:
                        Offer offer = (Offer) pack;
                        evaluateOffer(offer);
                        break;
                    case Notification:
                        Notification not = (Notification) pack;
                        evaluateNotification(not);
                        break;
                }
            }
            catch(IOException | ClassNotFoundException e) { 
                 System.out.println(e); 
            }
        }
    }
    
    public void sendPackage(IPackage pack){
        try {
            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
            output.writeObject(pack);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setBoard(Board board){
        if (board.getPlayerId() == this.player.getId()){
            this.player.graph = board.getGraph();
            this.checkBuildingThreads();
            controller.drawGraph(player.graph, false);
        }
        else{
            if (player.enemyGraphs.replace(board.getPlayerId(), board.getGraph()) == null)
                player.enemyGraphs.put(board.getPlayerId(), board.getGraph());
            controller.drawGraph(player.enemyGraphs.get(board.getPlayerId()), true);
            controller.setCurrentEnemy(board.getPlayerId());
        }
    }
    
    public void checkBuildingThreads(){
        for (Vertex vertex : player.graph.map.keySet()) { 
            Token token = (Token) vertex.data;
            if (token.getTokenType() == TokenType.Mine){
                ArrayList<Coordinate> coordinates = token.getCoordinates();
                boolean isDestroyed = true;
                for (int i = 0; i < coordinates.size(); i++){
                    if (!coordinates.get(i).hit)
                        isDestroyed = false;  
                }   
                if (isDestroyed)
                    this.player.mines.get(new Pair<>(token.getCoordinates().get(0).positionX, token.getCoordinates().get(0).positionY)).stopThread();
            }
            else if (token.getTokenType() == TokenType.Temple){
                ArrayList<Coordinate> coordinates = token.getCoordinates();
                boolean isDestroyed = true;
                for (int i = 0; i < coordinates.size(); i++){
                    if (!coordinates.get(i).hit)
                        isDestroyed = false;  
                }   
                if (isDestroyed)
                    this.player.temples.get(new Pair<>(token.getCoordinates().get(0).positionX, token.getCoordinates().get(0).positionY)).stopThread();
            }
        }          
    }
    
    public void evaluateOffer(Offer offer){
        if (offer.getPlayerId() == player.getId()){
            if (offer.isAccepted())
                player.addMoney(offer.getPrice());
            else
                player.returnOffer(offer);
        }
        else{
            controller.recieveOffer(offer);
        }
    }
    
    public void evaluateNotification(Notification not){
        if (not.isGameOver()){
            controller.gameOver();
            player.loseGame();
        }
        else{
            controller.addActivity("You destroyed a power source and earned " + not.getMoney());
            player.addMoney(not.getMoney());
            controller.updateMoney();
        }
    }

}
