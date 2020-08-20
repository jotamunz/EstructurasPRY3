
package Game.Threads;

import Game.Coordinate;
import Game.Game;
import Game.Packages.Activity;
import Game.Packages.Board;
import Server.Model.Server;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpyThread extends Thread{
    
    private int playerId;
    private int recieverId;
    private Coordinate position;
    private Server server;

    public SpyThread(int playerId, int recieverId, Coordinate position, Server server) {
        this.playerId = playerId;
        this.recieverId = recieverId;
        this.position = position;
        this.server = server;
        this.start();
    }
    
    @Override
    public void run(){
        int timer = 0;
        while(timer < 90){
            try {
                sleep(1000);
                timer++;
            } catch (InterruptedException ex) {
                Logger.getLogger(SpyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        server.clients.get(recieverId).player.graph = Game.spyBoatAttack(position, server.clients.get(recieverId).player.graph, playerId);
        server.clients.get(playerId).sendPackage(new Board(server.clients.get(recieverId).player.graph, recieverId), server.clients.get(playerId));
        server.clients.get(playerId).sendPackage(new Activity("Your spy boat has returned"), server.clients.get(playerId));
        server.clients.get(recieverId).sendPackage(new Board(server.clients.get(recieverId).player.graph, recieverId), server.clients.get(recieverId));
    } 
}
