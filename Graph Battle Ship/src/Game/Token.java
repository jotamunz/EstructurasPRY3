
package Game;

import java.io.Serializable;
import java.util.ArrayList;

public class Token implements Serializable{
    
    private ArrayList<Coordinate> coordinates;
    private TokenType type;
    private int price;
    
    public Token(TokenType type, int price, ArrayList<Coordinate> coordinates){
        this.coordinates = coordinates;
        this.price = price;
        this.type = type;
    }
    
    public TokenType getTokenType() {
        return type;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }
    
    public int getPrice(){
        return price;
    }
    
    @Override
    public String toString(){
        String str = type.toString();
        for (int i = 0; i < coordinates.size(); i++)
            str += " (" + coordinates.get(i).positionX + ", " + coordinates.get(i).positionY +  ", " + coordinates.get(i).hit + ", " + coordinates.get(i).connected + ")";
        return str;
    }
}
