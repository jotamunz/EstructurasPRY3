
package Client.Controller;

import Client.Model.Client;
import Client.View.ArmoryDisplay;
import Game.Factory;
import Game.WeaponType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ArmoryController implements ActionListener{
    
    private ArmoryDisplay display;
    private ClientController controller;
    private Client client;

    public ArmoryController(ArmoryDisplay display, ClientController controller, Client client) {
        this.display = display;
        this.controller = controller;
        this.client = client;
        this.innit();
    }
    
    private void innit(){
        display.jButton_Torpedo.addActionListener(this);
        display.jButton_MultiShot.addActionListener(this);
        display.jButton_Spy.addActionListener(this);
        display.jButton_Bomb.addActionListener(this);
        display.jButton_Trumpedo.addActionListener(this);
        display.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        display.setLocationRelativeTo(null);
    }
    
    public void showDisplay(){
        display.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Torpedo)){
            if (client.clientThread.player.addWeapon(Factory.createWeapon(WeaponType.Torpedo))){
                controller.updateIron();
                controller.updateWeapon();
            }
            else
                controller.addActivity("Not enough iron");
        }
        if (e.getSource().equals(display.jButton_MultiShot)){
            if (client.clientThread.player.addWeapon(Factory.createWeapon(WeaponType.MultiShot))){
                controller.updateIron();
                controller.updateWeapon();
            }
            else
                controller.addActivity("Not enough iron");
        }
        
        if (e.getSource().equals(display.jButton_Bomb)){
            if (client.clientThread.player.addWeapon(Factory.createWeapon(WeaponType.Bomb))){
                controller.updateIron();
                controller.updateWeapon();
            }
            else
                controller.addActivity("Not enough iron");
        }
        
        if (e.getSource().equals(display.jButton_Trumpedo)){
            if (client.clientThread.player.addWeapon(Factory.createWeapon(WeaponType.Trumpedo))){
                controller.updateIron();
                controller.updateWeapon();
            }
            else
                controller.addActivity("Not enough iron");
        }
        
        if (e.getSource().equals(display.jButton_Spy)){
            if (client.clientThread.player.addWeapon(Factory.createWeapon(WeaponType.Spy))){
                controller.updateIron();
                controller.updateWeapon();
            }
            else
                controller.addActivity("Not enough iron");
        }
        
    }
    
}
