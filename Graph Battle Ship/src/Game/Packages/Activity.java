
package Game.Packages;

public class Activity implements IPackage{

    private String message;

    public Activity(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    } 
    
    @Override
    public PackageEnum getType() {
        return PackageEnum.Activity;
    }
    
}
