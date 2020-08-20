
package Client.Controller;

import Client.Model.Client;
import Client.View.OfferDisplay;
import Game.Packages.Offer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class OfferController implements ActionListener{
    
    private OfferDisplay display;
    private ClientController controller;
    private Client client;
    private Offer offer;
    
    public OfferController(OfferDisplay display, ClientController controller, Client client) {
        this.display = display;
        this.controller = controller;
        this.client = client;
        this.innit();
    }
    
    private void innit(){
        display.jButton_Accept.addActionListener(this);
        display.jButton_Reject.addActionListener(this);
        display.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        display.setLocationRelativeTo(null);
    }
    
    public void showDisplay(Offer offer){
        display.jButton_Accept.setEnabled(true);
        display.jButton_Reject.setEnabled(true);
        this.offer = offer;
        display.jLabel_Offer.setText("Player " + offer.getPlayerId() + " has made you an offer");
        display.jTextArea_Offer.setText("");
        display.jTextArea_Offer.append("Iron: " + offer.getIron() + "\n");
        for (int i = 0; i < offer.getWeapons().size(); i++){
            display.jTextArea_Offer.append(offer.getWeapons().get(i).getWeaponType().toString() + "\n");
        }
        display.jTextArea_Offer.append("Price: " + offer.getPrice());
        display.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Reject)){
            offer.setAccepted(false);
            client.clientThread.sendPackage(offer);
            display.jButton_Accept.setEnabled(false);
            display.jButton_Reject.setEnabled(false);
        }
        else if (e.getSource().equals(display.jButton_Accept)){
            if (client.clientThread.player.getMoney() >= offer.getPrice()){
                client.clientThread.player.subtractMoney(offer.getPrice());
                client.clientThread.player.returnOffer(offer);
                controller.updateMoney();
                controller.updateWeapon();
                offer.setAccepted(true);
                client.clientThread.sendPackage(offer);
                display.jButton_Accept.setEnabled(false);
                display.jButton_Reject.setEnabled(false);
            }
        }
    }
}
