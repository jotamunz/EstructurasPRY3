
package Game;

import static Game.TokenType.*;
import static Game.WeaponType.*;
import java.util.ArrayList;

public class Factory {
    
     public static Token createToken(TokenType type, ArrayList<Coordinate> coordinates)
     {
         switch(type){
             
             case Market:
                 return new Token(Market,2000,coordinates);
             case Temple:
                 return new Token(Temple,2500,coordinates);
             case Power:
                 return new Token(Power,12000,coordinates);
             case Connector:
                 return new Token(Connector,100,coordinates);
             case Mine:
                 return new Token(Mine,1000,coordinates);
             case Armory:
                 return new Token(Armory,1500,coordinates);   
             case Whirlpool:
                 return new Token(Whirlpool,0,coordinates);
            default:
                System.out.println("Incorrect token type");
                return null;
         }
     }
     
     public static Weapon createWeapon(WeaponType type)
     {
         switch (type){
             
             case Torpedo:
                 return new Weapon(Torpedo,500);
             case MultiShot:
                 return new Weapon(MultiShot,2000);
             case Bomb:
                 return new Weapon(Bomb,2000);
             case Trumpedo:
                 return new Weapon(Trumpedo,5000);
             case Spy:
                 return new Weapon(Spy,2500);
             default:
                System.out.println("Incorrect token type");
                return null;      
         }
     }
    
}
