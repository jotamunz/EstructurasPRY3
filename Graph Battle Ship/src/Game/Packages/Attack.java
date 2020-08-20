
package Game.Packages;

import Game.Coordinate;
import Game.WeaponType;
import java.util.ArrayList;

public class Attack implements IPackage{
    
    private ArrayList<Coordinate> coordinates;
    private WeaponType weapon;
    private int recieverId;
    private int playerId;

    public Attack(WeaponType weapon, ArrayList<Coordinate> coordinates, int playerId, int recieverId) {
        this.coordinates = coordinates;
        this.weapon = weapon;
        this.playerId = playerId;
        this.recieverId = recieverId;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public int getPlayerId() {
        return playerId;
    }
    
    public int getRecieverId(){
        return recieverId;
    }
    
    public WeaponType getAttackType() {
        return weapon;
    }

    @Override
    public PackageEnum getType() {
        return PackageEnum.Attack;
    }
    
    
    
}
