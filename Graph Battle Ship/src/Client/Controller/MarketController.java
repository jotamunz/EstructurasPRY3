
package Client.Controller;

import Client.Model.Client;
import Client.View.MarketDisplay;
import Game.Factory;
import Game.Packages.Offer;
import Game.WeaponType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class MarketController implements ActionListener{

    private MarketDisplay display;
    private ClientController controller;
    private Client client;
    private Offer offer;

    public MarketController(MarketDisplay display, ClientController controller, Client client) {
        this.display = display;
        this.controller = controller;
        this.client = client;
        this.offer = new Offer();
        this.innit();
    }
    
    private void innit(){
        display.jButton_SellIron.addActionListener(this);
        display.jButton_SellTorpedo.addActionListener(this);
        display.jButton_SellMultiShot.addActionListener(this);
        display.jButton_SellBomb.addActionListener(this);
        display.jButton_SellSpy.addActionListener(this);
        display.jButton_SellTrumpedo.addActionListener(this);
        display.jButton_addIron.addActionListener(this);
        display.jButton_addTorpedo.addActionListener(this);
        display.jButton_addMultiShot.addActionListener(this);
        display.jButton_addBomb.addActionListener(this);
        display.jButton_addSpy.addActionListener(this);
        display.jButton_addTrumpedo.addActionListener(this);
        display.jButton_SendOffer.addActionListener(this);
        display.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        display.setLocationRelativeTo(null);
    }
    
    public void showDisplay(){
        offer.setPlayerId(client.clientThread.player.getId());
        display.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_SellIron)){
            int amount = (int) display.jSpinner_Iron.getValue();
            if (client.clientThread.player.getIron() >= amount){
                client.clientThread.player.subtractIron(amount);
                client.clientThread.player.addMoney(amount*10);
                controller.updateMoney();
                controller.updateIron();
            }
        }
        else if (e.getSource().equals(display.jButton_SellTorpedo)){
            int amount = (int) display.jSpinner_Torpedo.getValue();
            sellToBank(WeaponType.Torpedo, amount, 2500);
        }
        else if (e.getSource().equals(display.jButton_SellMultiShot)){
            int amount = (int) display.jSpinner_MultiShot.getValue();
            sellToBank(WeaponType.MultiShot, amount, 5000);
        }
        else if (e.getSource().equals(display.jButton_SellBomb)){
            int amount = (int) display.jSpinner_Bomb.getValue();
            sellToBank(WeaponType.Bomb, amount, 10000);
        }
        else if (e.getSource().equals(display.jButton_SellSpy)){
            int amount = (int) display.jSpinner_Spy.getValue();
            sellToBank(WeaponType.Spy, amount, 12500);
        }
        else if (e.getSource().equals(display.jButton_SellTrumpedo)){
            int amount = (int) display.jSpinner_Trumpedo.getValue();
            sellToBank(WeaponType.Trumpedo, amount, 25000);
        }
        else if (e.getSource().equals(display.jButton_addIron)){
            int amount = (int) display.jSpinner_Iron.getValue();
            if (client.clientThread.player.getIron() >= amount){
                client.clientThread.player.subtractIron(amount);
                offer.addIron(amount);
                display.jTextArea_Offer.append(amount + " Iron");
                controller.updateIron();
            }
        }
        else if (e.getSource().equals(display.jButton_addTorpedo)){
            int amount = (int) display.jSpinner_Torpedo.getValue();
            addToOffer(WeaponType.Torpedo, amount);
        }
        else if (e.getSource().equals(display.jButton_addMultiShot)){
            int amount = (int) display.jSpinner_MultiShot.getValue();
            addToOffer(WeaponType.MultiShot, amount);
        }
        else if (e.getSource().equals(display.jButton_addBomb)){
            int amount = (int) display.jSpinner_Bomb.getValue();
            addToOffer(WeaponType.Bomb, amount);
        }
        else if (e.getSource().equals(display.jButton_addSpy)){
            int amount = (int) display.jSpinner_Spy.getValue();
            addToOffer(WeaponType.Spy, amount);
        }
        else if (e.getSource().equals(display.jButton_addTrumpedo)){
            int amount = (int) display.jSpinner_Trumpedo.getValue();
            addToOffer(WeaponType.Trumpedo, amount);
        }
        else if (e.getSource().equals(display.jButton_Cancel)){
            client.clientThread.player.returnOffer(offer);
            controller.updateIron();
            controller.updateWeapon();
            this.offer = new Offer();
            offer.setPlayerId(client.clientThread.player.getId());
        }
        else if (e.getSource().equals(display.jButton_SendOffer)){
            int price = (int) display.jSpinner_Price.getValue();
            int recieverId = (int) display.jSpinner_Player.getValue();
            if (recieverId <= client.playerAmount && recieverId != client.clientThread.player.getId()){
                offer.setRecieverId(recieverId);
                offer.setPrice(price);
                client.clientThread.sendPackage(offer);
                this.offer = new Offer();
                offer.setPlayerId(client.clientThread.player.getId());
            }
        }
    }
    
    public void sellToBank(WeaponType type, int amount, int price){
        if (client.clientThread.player.hasWeapon(type, amount)){
            client.clientThread.player.removeWeapon(type, amount);
            client.clientThread.player.addMoney(amount*price);
            controller.updateMoney();
            controller.updateWeapon();
        }
    }
    
    public void addToOffer(WeaponType type, int amount){
        if (client.clientThread.player.hasWeapon(type, amount)){
            client.clientThread.player.removeWeapon(type, amount);
            for (int i = 0; i < amount; i++)
                offer.addWeapon(Factory.createWeapon(type));
            display.jTextArea_Offer.append(amount + " " + type.toString() + "s\n");
            controller.updateWeapon();
        }
    }
}
