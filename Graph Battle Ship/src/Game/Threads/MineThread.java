
package Game.Threads;

import Client.Controller.ClientController;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MineThread extends Thread{

    private int iron;
    private ClientController controller; 
    private boolean running;
    
    public MineThread(ClientController controller) {
        this.iron = 0;
        this.controller = controller;
        this.running = true;
    }
        
    public int collectIron() {
        int collected = iron;
        this.iron = 0;
        controller.addActivity("You collected " + collected + " iron");
        return collected;
    }
    
    public void stopThread(){
        this.running = false;
    }
    
    public boolean readyToCollect(){
        return (iron != 0);
    }
    
    @Override
    public void run(){
        int counter = 0;
        while(running){
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TempleThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            counter++;
            if (counter == 60){
                counter = 0;
                iron += 50;
                controller.addActivity("Your iron is ready to collect");
            }
        }
    }
}
