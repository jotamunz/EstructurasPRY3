
package Server.Model;

import Game.Game;
import Game.Packages.Board;
import Game.Packages.Turn;
import Game.Player;
import Server.Controller.ServerController;
import Server.View.ServerDisplay;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread{
    
    private ServerController controller;
    private boolean active;
    public HashMap<Integer, ServerThread> clients; 
    public int playerAmount;
    public int playerOrder[];
    public int currentTurn;
    public boolean[] playersReady;
    
    public Server(){
        this.controller = new ServerController(this, new ServerDisplay());
        this.clients = new HashMap<>();
        this.active = false;
    }
    
    @Override
    public void run(){
        this.active = true;
        this.playersReady = new boolean[playerAmount];
        this.playerOrder = new int[playerAmount];
        try {
            ServerSocket server = new ServerSocket(5000);
            controller.addStatus("Server Active: Waiting for Clients...");
            for (int i = 1; i <= playerAmount; i++){
                Socket client = server.accept();
                ServerThread serverThread = new ServerThread(client, this, controller, new Player(i, "Player " + i));
                clients.put(i, serverThread);
                this.sendInt(i, client);
                this.sendInt(playerAmount, client);
                serverThread.start();
                controller.addStatus("Client Connected");
                playersReady[i-1] = false;
                playerOrder[i-1] = i;
            }
            controller.addStatus("Server Full");
            while (!playersAreReady()){
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            orderPlayers();
            currentTurn = 0;
            sendGraphs();
            controller.addStatus("Sending frist turn to: " + playerOrder[currentTurn]);
            clients.get(playerOrder[currentTurn]).sendPackage(new Turn(), clients.get(playerOrder[currentTurn]));
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isActive(){
        return active;
    }

    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
    }
    
    public boolean playersAreReady(){
        for (int i = 0; i < playerAmount; i++){
            if (playersReady[i] == false)
                return false;
        }
        return true;
    }
    
    public void orderPlayers(){
        Random rgen = new Random();
        for (int i = 0; i < playerOrder.length; i++) {
            int randomPosition = rgen.nextInt(playerOrder.length);
            int temp = playerOrder[i];
            playerOrder[i] = playerOrder[randomPosition];
            playerOrder[randomPosition] = temp;
        }
    }
    
    public void sendInt(int id, Socket client) {
        try {
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            output.writeInt(id);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendGraphs(){
        for (ServerThread serverThread : clients.values()){
            for (int i = 1; i <= playerAmount; i++){
                serverThread.sendPackage(new Board(clients.get(i).player.graph, clients.get(i).player.getId()), serverThread);
            }
        }
    }

}   
