
package Server.Controller;

import Server.Model.Server;
import Server.View.ServerDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.DefaultCaret;

public class ServerController implements ActionListener {
    
    private Server server;
    private ServerDisplay display;
    
    public ServerController(Server server, ServerDisplay display){
        this.server = server;
        this.display = display;
        this.innit();
    }
    
    private void innit(){
        DefaultCaret caret = (DefaultCaret) display.jTextArea_Status.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        display.jButton_Run.addActionListener(this);
        display.setLocationRelativeTo(null);
        display.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Run) && !server.isActive()){
            server.setPlayerAmount((int) display.jSpinner_Players.getValue());
            server.start();
        }
    }
    
    public void addStatus(String message){
        display.jTextArea_Status.append(message + "\n");
    }
}
