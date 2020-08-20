
package Game;

import java.io.Serializable;

public class Weapon implements Serializable{
    
    private WeaponType type;
    private int price;
    
    public Weapon(WeaponType type, int price){
        this.price = price;
        this.type = type;
    }
    
    public WeaponType getWeaponType() {
        return type;
    }
    
    public int getPrice(){
        return price;
    }
    
    @Override
    public String toString(){
        return type.toString();
    }
}
