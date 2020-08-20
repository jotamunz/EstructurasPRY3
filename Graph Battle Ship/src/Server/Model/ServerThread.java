
package Server.Model;

import Game.Coordinate;
import Game.Game;
import static Game.Game.DIMENSION;
import Game.Graph;
import Game.Packages.*;
import java.net.*; 
import java.io.*; 
import Game.Player;
import Game.Threads.SpyThread;
import Game.Token;
import Game.WeaponType;
import Server.Controller.ServerController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread{
    
    private ServerController controller;
    private Socket clientSocket;
    private Server server;
    public Player player;
    private boolean running;

    public ServerThread(Socket clientSocket, Server server, ServerController controller, Player player) {
        this.controller = controller;
        this.clientSocket = clientSocket;
        this.server = server;
        this.player = player;
        this.running = true;
    }
    
    @Override
    public void run() {
        while (running) {
            try {
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                IPackage pack = (IPackage) input.readObject();
                switch (pack.getType()) {
                    case Message:
                        for (ServerThread serverThread : server.clients.values())
                            this.sendPackage(pack, serverThread);
                        break;
                    case Board:
                        Board board = (Board) pack;
                        server.clients.get(board.getPlayerId()).player.graph = Game.markConnectedGraph(board.getGraph());
                        break;
                    case Attack:
                        Attack attack = (Attack) pack;
                        doAttack(attack);
                        sendUpdatedGraph();
                        passTurn();
                        break;
                    case Turn:
                        server.playersReady[player.getId()-1] = true;
                        controller.addStatus("Player " + player.getId() + " is ready");
                        break;
                    case Shield:
                        Shield shield = (Shield) pack;
                        activateShield(shield);
                        break;
                    case Offer:
                        Offer offer = (Offer) pack;
                        sendPackage(offer, server.clients.get(offer.getRecieverId()));
                        break;
                }
            }
            catch(IOException | ClassNotFoundException e) { 
                 System.out.println(e); 
            }
        }
    }
    
    public void sendPackage(IPackage pack, ServerThread serverThread) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(serverThread.clientSocket.getOutputStream());
            output.writeObject(pack);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void passTurn(){
        if (server.currentTurn == server.playerAmount-1)
            server.currentTurn = 0;
        else
            server.currentTurn++;
        if (!server.clients.get(server.playerOrder[server.currentTurn]).player.lostGame())
            sendPackage(new Turn(), server.clients.get(server.playerOrder[server.currentTurn]));
        else {
            int playersLeft = 0;
            for (int i = 1; i <= server.playerAmount; i++){
                if (!server.clients.get(i).player.lostGame())
                    playersLeft++;
            }
            if (playersLeft <= 1){
                for (int i = 1; 1 <= server.playerAmount; i++){
                    if (!server.clients.get(i).player.lostGame()){
                        sendPackage(new Activity("You win!!"), server.clients.get(i));
                        break;
                    }
                }
            }  
            else
                passTurn();
        }
    }
    
    public void sendUpdatedGraph(){
        for (ServerThread serverThread : server.clients.values()){
            sendPackage(new Board(player.graph, player.getId()), serverThread);
        }
    }
    
    public void doAttack(Attack attack){
        AtomicInteger whirlpoolAmount = new AtomicInteger();
        AtomicInteger powerSourceAmount = new AtomicInteger();
        boolean hit = false;
        StringBuffer shotCords = new StringBuffer();
        StringBuffer hitCords = new StringBuffer();
        Graph<Token> graph = server.clients.get(attack.getRecieverId()).player.graph;
        Player reciever = server.clients.get(attack.getRecieverId()).player;
        Player attacker = server.clients.get(attack.getPlayerId()).player;
        switch(attack.getAttackType()){
            case MultiShot:
                for (int i = 0; i < attack.getCoordinates().size(); i++){
                    if (!reciever.hasShield()){
                        if (Game.multiShotAttack(graph, attack.getCoordinates().get(i), attacker, whirlpoolAmount, hitCords, powerSourceAmount, shotCords))
                            hit = true;
                    }
                    else{
                        reciever.reduceShield();
                        sendPackage(new Shield(), server.clients.get(attack.getRecieverId()));
                    }
                }
                break;    
            case Bomb:
                for (int i = 0; i < attack.getCoordinates().size(); i++){
                    if (!reciever.hasShield()){
                        if (Game.bombAttack(graph, attack.getCoordinates().get(i), attacker, whirlpoolAmount, hitCords, powerSourceAmount, shotCords))
                            hit = true;
                    }
                    else{
                        reciever.reduceShield();
                        sendPackage(new Shield(), server.clients.get(attack.getRecieverId()));
                    }
                }
                break;     
            case Spy:
                player.spyBoats.add(new SpyThread(attack.getPlayerId(), attack.getRecieverId(), attack.getCoordinates().get(0), server));
                break;
            default:
                for (int i = 0; i < attack.getCoordinates().size(); i++){
                    if (!reciever.hasShield()){
                        if (Game.torpedoAttack(graph, attack.getCoordinates().get(i), attacker, whirlpoolAmount, hitCords, powerSourceAmount, shotCords))
                            hit = true;
                    }
                    else{
                        reciever.reduceShield();
                        sendPackage(new Shield(), server.clients.get(attack.getRecieverId()));
                    }
                }
                break;
        }
        sendPackage(new Activity("You shot: " + shotCords.toString()), server.clients.get(attack.getPlayerId()));
        if (hit){
            server.clients.get(attack.getRecieverId()).player.graph = Game.markConnectedGraph(graph);
            for (ServerThread serverThread : server.clients.values()){
                sendPackage(new Board(graph, attack.getRecieverId()), serverThread);
            }
            sendPackage(new Activity("You hit: " + hitCords.toString()), server.clients.get(attack.getPlayerId()));
            sendPackage(new Activity("You got hit at: " + hitCords.toString()), server.clients.get(attack.getRecieverId()));
            if (Game.playerLost(graph)){
                server.clients.get(attack.getRecieverId()).player.loseGame();
                sendPackage(new Activity("You Lost"), server.clients.get(attack.getRecieverId()));
                sendPackage(new Notification(), server.clients.get(attack.getRecieverId()));
            }
        }
        for (int i = 0; i < powerSourceAmount.intValue(); i++){
            sendPackage(new Notification(12000), server.clients.get(attack.getPlayerId()));
        }
        for (int i = 0; i < whirlpoolAmount.intValue(); i++){
            ArrayList<Coordinate> coordinates = new ArrayList<>();
            for (int j = 0; j < 3; j++){
                int x = (int) (Math.random()*100) % DIMENSION;
                int y = (int) (Math.random()*100) % DIMENSION;  
                coordinates.add(new Coordinate(x,y));
            }
            sendPackage(new Activity("Whirlpool:"), server.clients.get(attack.getRecieverId()));
            this.doAttack(new Attack(WeaponType.Torpedo, coordinates, attack.getRecieverId(), attack.getPlayerId()));
        }
    }
    
    public void activateShield(Shield shield){
        player.setCurrentShield(shield.getShieldAmount());
        for (ServerThread serverThread : server.clients.values()){
           if (serverThread != this)
               sendPackage(new Activity("Player " + shield.getPlayerId() + " has activated a shield"), serverThread);
        }
    }

} 

