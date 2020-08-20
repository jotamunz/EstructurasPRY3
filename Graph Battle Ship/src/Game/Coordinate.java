
package Game;

import java.io.Serializable;
import java.util.HashMap;

public class Coordinate implements Serializable{
    public int positionX;
    public int positionY;
    public boolean hit;
    public boolean connected;
    public HashMap<Integer, Boolean> spied;

    public Coordinate(int x, int y){
        this.positionX = x;
        this.positionY = y;
        this.hit = false;
        this.connected = false;
        this.spied = new HashMap<>();
        spied.put(1, false);
        spied.put(2, false);
        spied.put(3, false);
        spied.put(4, false);
    }
    
    @Override
    public String toString(){
        return "(" + positionX + ", " + positionY + ") ";
    }
}
