
package Game.Packages;

public class Notification implements IPackage{
    
    private boolean gameOver;
    private int money;

    public Notification(int price) {
        this.money = price;
        this.gameOver = false;
    }
    
    public Notification(){
        this.gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getMoney() {
        return money;
    }
    
    @Override
    public PackageEnum getType() {
        return PackageEnum.Notification;
    }
    
}
