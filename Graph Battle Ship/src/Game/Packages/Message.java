
package Game.Packages;

public class Message implements IPackage {
    
    private String message;
    private String name;
    
    public Message(String name, String message){
        this.name = name;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    @Override
    public PackageEnum getType() {
        return PackageEnum.Message;
    }
    
}
