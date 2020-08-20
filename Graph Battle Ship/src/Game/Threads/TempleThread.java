
package Game.Threads;

import Client.Controller.ClientController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TempleThread extends Thread{

    private ArrayList<Integer> shields;
    private ClientController controller; 
    private boolean running;

    public TempleThread(ClientController controller) {
        this.shields = new ArrayList<>();
        this.controller = controller;
        this.running = true;
    }

    public ArrayList<Integer> collectShields() {
        ArrayList<Integer> collected = shields;
        this.shields = new ArrayList<>();
        controller.addActivity("You collected " + collected.size() + " shields");
        return collected;
    }
    
    public void stopThread(){
        this.running = false;
    }
    
    public boolean readyToCollect(){
        return !shields.isEmpty();
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
            if (counter == 300){ 
                counter = 0;
                int rand = (int) ((Math.random()*100) % 4) + 2;
                shields.add(rand);
                controller.addActivity("Your shield is ready to collect");
            }
        }
    }
    
}
