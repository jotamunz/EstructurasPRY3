
package Client.Model;

import Client.Controller.ClientController;
import Client.View.GameDisplay;
import Client.View.NamePromptDisplay;
import Game.Player;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    
    private ClientController controller;
    public ClientThread clientThread;
    public int playerAmount;
    
    public Client(){
        this.controller = new ClientController(this, new GameDisplay(), new NamePromptDisplay());
    }
    
    public void connect(String clientName){
        try {
            Socket client = new Socket("localhost", 5000);
            int id = this.recieveInt(client);
            playerAmount = this.recieveInt(client);
            clientThread = new ClientThread(client, this, controller, new Player(id, clientName));
            clientThread.start();
            controller.welcomePlayer(id);
            controller.showEnemyButtons(playerAmount);
            controller.updateIron();
            controller.updateMoney();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int recieveInt(Socket client){
        try {
            DataInputStream input = new DataInputStream(client.getInputStream());
            return input.readInt();
        } catch(IOException e) { 
            System.out.println(e); 
            return 0;
        }
    }
    
}
