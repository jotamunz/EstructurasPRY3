
package Game.Packages;

public class Shield implements IPackage{
    
    private int playerId;
    private int shieldAmount;

    public Shield(int playerId, int shieldAmount) {
        this.playerId = playerId;
        this.shieldAmount = shieldAmount;
    }
    
    public Shield(){
        
    }

    public int getShieldAmount() {
        return shieldAmount;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public PackageEnum getType() {
        return PackageEnum.Shield;
    }
    
}
