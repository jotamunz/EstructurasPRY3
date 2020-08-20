
package Game.Packages;

import Game.Weapon;
import java.util.ArrayList;

public class Offer implements IPackage{

    private int iron;
    private ArrayList<Weapon> weapons;
    private int price;
    private int playerId;
    private int recieverId;
    private boolean accepted;

    public Offer() {
        this.weapons = new ArrayList<>();
        this.iron = 0;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getRecieverId() {
        return recieverId;
    }

    public int getIron() {
        return iron;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public int getPrice() {
        return price;
    }

    public void addIron(int iron) {
        this.iron += iron;
    }

    public void addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRecieverId(int recieverId) {
        this.recieverId = recieverId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    
    @Override
    public PackageEnum getType() {
        return PackageEnum.Offer;
    }
    
}
