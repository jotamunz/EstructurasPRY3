
package Client.Controller;

import Client.Model.Client;
import Game.Packages.*;
import Client.View.*;
import Game.Coordinate;
import Game.Factory;
import Game.Game;
import static Game.Game.DIMENSION;
import static Game.Game.SQUARE;
import Game.Graph;
import Game.Graph.Vertex;
import Game.Threads.MineThread;
import Game.Threads.TempleThread;
import Game.Token;
import Game.TokenType;
import static Game.TokenType.*;
import Game.Weapon;
import Game.WeaponType;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.text.DefaultCaret;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ClientController implements ActionListener, MouseListener{
   
    private Client client;
    private GameDisplay display;
    private NamePromptDisplay namePrompt;
    private ArmoryController armoryController;
    private MarketController marketController;
    private OfferController offerController;
    private int sourceX = -1;
    private int sourceY = -1;
    private WeaponType currentWeapon = null;
    private ArrayList<Coordinate> attackCords = new ArrayList<>();
    private int currentEnemy = 0;
    private boolean isReady = false;
    private boolean rotate = false;
    
    public ClientController(Client client, GameDisplay display, NamePromptDisplay namePrompt){
        this.client = client;
        this.display = display;
        this.namePrompt = namePrompt;
        this.armoryController = new ArmoryController(new ArmoryDisplay(), this, client);
        this.marketController = new MarketController(new MarketDisplay(), this, client);
        this.offerController = new OfferController(new OfferDisplay(), this, client);
        this.innit();
    }
    
    private void innit(){
        //MOUSE
        display.jPanel_BGBoard.addMouseListener(this);
        display.jPanel_BGEnemy.addMouseListener(this);
        
        //COMBO BOX
        for(TokenType token : TokenType.values()){
            if (token != Whirlpool)
                this.display.jComboBox_Tokens.addItem(token.toString());
        }
        
        //BUTTONS
        DefaultCaret caret = (DefaultCaret) display.jTextArea_Chat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        DefaultCaret caret2 = (DefaultCaret) display.jTextArea_Activity.getCaret();
        caret2.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        display.jButton_Send.addActionListener(this);
        display.jButton_Ready.addActionListener(this);
        display.jButton_Refresh.addActionListener(this);
        display.jButton_Rotate.addActionListener(this);
        display.jButton_Info.addActionListener(this);
        namePrompt.jButton_Go.addActionListener(this);
        display.jButton_Enemy1.addActionListener(this);
        display.jButton_Enemy2.addActionListener(this);
        display.jButton_Enemy3.addActionListener(this);
        display.jButton_Enemy4.addActionListener(this);
        display.jButton_Shield.addActionListener(this);
        display.jButton_Enemy1.hide();
        display.jButton_Enemy2.hide();
        display.jButton_Enemy3.hide();
        display.jButton_Enemy4.hide();
        
        //BOARD
        display.jPanelBG.setSize(1600, 940);
        display.jPanel_BGBoard.setSize(SQUARE*DIMENSION, SQUARE*DIMENSION);
        for (int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                display.displayBoardPlayer[i][j] = new JLabel();
                display.displayBoardPlayer[i][j].setOpaque(true);
                display.displayBoardPlayer[i][j].setBackground(Color.getColor("#006372")); 
                try {
                    BufferedImage img_Water = ImageIO.read(new File("Assets/water.png"));
                    ImageIcon icon_Water = new ImageIcon(img_Water);
                    display.displayBoardPlayer[i][j].setIcon(icon_Water);
                } catch (IOException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
                display.displayBoardPlayer[i][j].setText("");
                display.displayBoardPlayer[i][j].setBounds(SQUARE*i, SQUARE*j, SQUARE, SQUARE);
                display.displayBoardPlayer[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153))); 
                display.jPanel_BGBoard.add(display.displayBoardPlayer[i][j]);
            }
        }
        display.jPanel_BGEnemy.setSize(SQUARE*DIMENSION, SQUARE*DIMENSION);
        for (int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                display.displayBoardEnemy[i][j] = new JLabel();
                display.displayBoardEnemy[i][j].setOpaque(true);
                display.displayBoardEnemy[i][j].setBackground(Color.GRAY);
                try {
                    BufferedImage img_Water = ImageIO.read(new File("Assets/cloud.png"));
                    ImageIcon icon_Water = new ImageIcon(img_Water);
                    display.displayBoardEnemy[i][j].setIcon(icon_Water);
                } catch (IOException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
                display.displayBoardEnemy[i][j].setText("");
                display.displayBoardEnemy[i][j].setBounds(SQUARE*i, SQUARE*j, SQUARE, SQUARE);
                display.displayBoardEnemy[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153))); 
                display.jPanel_BGEnemy.add(display.displayBoardEnemy[i][j]);
            }
        }
        this.updateEnemyHeader();
        display.setResizable(false);
        display.setLocationRelativeTo(null);
        namePrompt.setLocationRelativeTo(null); 
        namePrompt.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(namePrompt.jButton_Go)){
            if (!"".equals(namePrompt.jTextField_Name.getText())){
                client.connect(namePrompt.jTextField_Name.getText());
                namePrompt.hide();
                display.setVisible(true);
                this.drawGraph(this.client.clientThread.player.graph, false);
            }
        }
        else if (e.getSource().equals(display.jButton_Send)){
            if (!"".equals(display.jTextField_Chat.getText())){
                client.clientThread.sendPackage(new Message(client.clientThread.player.getName(), display.jTextField_Chat.getText()));
                display.jTextField_Chat.setText("");
            }
        }
        else if (e.getSource().equals(display.jButton_Ready)){
            if (!isReady){
                client.clientThread.sendPackage(new Board(client.clientThread.player.graph, client.clientThread.player.getId()));
                client.clientThread.sendPackage(new Turn());
                isReady = true;
                display.jButton_Ready.setEnabled(false);
            }
        }
        else if (e.getSource().equals(display.jButton_Shield)){
            if (!client.clientThread.player.hasShield() && client.clientThread.player.activateShield()){
                client.clientThread.sendPackage(new Shield(client.clientThread.player.getId(), client.clientThread.player.getCurrentShield()));
                this.updateShield();
            }
        }
        else if (e.getSource().equals(display.jButton_Refresh)){
            this.drawGraph(client.clientThread.player.graph, false);
            if (client.clientThread.player.enemyGraphs.containsKey(currentEnemy))
                this.drawGraph(client.clientThread.player.enemyGraphs.get(currentEnemy), true);
        }
        else if (e.getSource().equals(display.jButton_Enemy1)){
            if (client.clientThread.player.enemyGraphs.get(1) != null)
                this.drawGraph(client.clientThread.player.enemyGraphs.get(1), true);
            this.currentEnemy = 1;
            this.updateEnemyHeader();
        }
        else if (e.getSource().equals(display.jButton_Enemy2)){
            if (client.clientThread.player.enemyGraphs.get(2) != null)
                this.drawGraph(client.clientThread.player.enemyGraphs.get(2), true);
            this.currentEnemy = 2;
            this.updateEnemyHeader();
        }
        else if (e.getSource().equals(display.jButton_Enemy3)){
            if (client.clientThread.player.enemyGraphs.get(3) != null)
                this.drawGraph(client.clientThread.player.enemyGraphs.get(3), true);
            this.currentEnemy = 3;
            this.updateEnemyHeader();
        }   
        else if (e.getSource().equals(display.jButton_Enemy4)){
            if (client.clientThread.player.enemyGraphs.get(4) != null)
                this.drawGraph(client.clientThread.player.enemyGraphs.get(4), true);
            this.currentEnemy = 4;
            this.updateEnemyHeader();
        }   
        else if (e.getSource().equals(display.jButton_Rotate)){
            this.rotate = !rotate;
        }
        else if (e.getSource().equals(display.jButton_Info)){
            System.out.println("My Graph");
            System.out.println(client.clientThread.player.graph.toString());
            System.out.println("Shields");
            System.out.println(client.clientThread.player.shields);
            System.out.println("Weapons");
            System.out.println(client.clientThread.player.weapons);
        }
    }
    
    public void addMessage(String name, String message){
        display.jTextArea_Chat.append(name + ": " + message + "\n");
    }
    
    public void addActivity(String activity){
        display.jTextArea_Activity.append(activity + "\n");
    }
    
    public void welcomePlayer(int id){
        display.setTitle("Player " + id);
        display.jTextArea_Chat.append(client.clientThread.player.getName() + " welcome to the chat room\n");
    }
    
    public void updateMoney(){
        display.jLabel_Money.setText("$" + client.clientThread.player.getMoney());
    }
    
    public void updateIron(){
        display.jLabel_Iron.setText("€" + client.clientThread.player.getIron());
    }
    
    public void updateWeapon(){
        if (!client.clientThread.player.weapons.isEmpty()){
            try {
                String imgStr = getWeaponImage(client.clientThread.player.weapons.peek());
                BufferedImage img = ImageIO.read(new File(imgStr));
                ImageIcon icon = new ImageIcon(img);
                display.jLabel_Weapon.setIcon(icon);
            } catch (IOException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        else
            try {
                BufferedImage img_None = ImageIO.read(new File("Assets/Weapons/none.png"));
                ImageIcon icon_None = new ImageIcon(img_None);
                display.jLabel_Weapon.setIcon(icon_None);
            } catch (IOException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void updateShield(){
        display.jLabel_CurrentShield.setText(String.valueOf(client.clientThread.player.getCurrentShield()));
    }
    
    public void setCurrentEnemy(int id){
        this.currentEnemy = id;
        this.updateEnemyHeader();
    }
    
    public void updateEnemyHeader(){
        if (currentEnemy == 0)
            display.jLabel_EnemyHeader.setText("No player selected");
        else
            display.jLabel_EnemyHeader.setText("Player #" + currentEnemy);
    }
    
    public void recieveOffer(Offer offer){
        offerController.showDisplay(offer);
    }
    
    public void drawGraph(Graph<Token> graph, boolean enemy){
        if (!enemy){ // PLAYER GRAPH
            for (Vertex vertex : graph.map.keySet()) {
                Token token = (Token) vertex.data;
                ArrayList<Coordinate> coordinates = token.getCoordinates();
                for (int i = 0; i < coordinates.size(); i++){
                    if (coordinates.get(i).hit){
                        display.displayBoardPlayer[coordinates.get(i).positionX][coordinates.get(i).positionY].setBackground(Color.RED);
                        try {
                            BufferedImage img = ImageIO.read(new File("Assets/fire.png"));
                            ImageIcon icon = new ImageIcon(img);
                            display.displayBoardPlayer[coordinates.get(i).positionX][coordinates.get(i).positionY].setIcon(icon);
                        } catch (IOException ex) {
                            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                        }                     
                    }
                    else{
                        display.displayBoardPlayer[coordinates.get(i).positionX][coordinates.get(i).positionY].setBackground(Color.GRAY);
                        try {
                            String imgStr = getTokenImage(token);
                            BufferedImage img = ImageIO.read(new File(imgStr));
                            ImageIcon icon = new ImageIcon(img);
                            display.displayBoardPlayer[coordinates.get(i).positionX][coordinates.get(i).positionY].setIcon(icon);
                        } catch (IOException ex) {
                            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                        }                 
                    }
                }
            }
            this.paintEdges(graph);
        }
        else{ // ENEMY GRAPH
            clearEnemyPanel();
            for (Vertex vertex : graph.map.keySet()) {
                Token token = (Token) vertex.data;
                ArrayList<Coordinate> coordinates = token.getCoordinates();
                for (int i = 0; i < coordinates.size(); i++){
                    if (coordinates.get(i).hit){
                        display.displayBoardEnemy[coordinates.get(i).positionX][coordinates.get(i).positionY].setBackground(Color.RED);
                        try {
                            BufferedImage img = ImageIO.read(new File("Assets/fire.png"));
                            ImageIcon icon = new ImageIcon(img);
                            display.displayBoardEnemy[coordinates.get(i).positionX][coordinates.get(i).positionY].setIcon(icon);
                        } catch (IOException ex) {
                            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                        }                    
                    }
                    else if (!coordinates.get(i).connected || coordinates.get(i).spied.get(client.clientThread.player.getId())){
                        display.displayBoardEnemy[coordinates.get(i).positionX][coordinates.get(i).positionY].setBackground(Color.DARK_GRAY);
                        try {
                            String imgStr = getTokenImage(token);
                            BufferedImage img = ImageIO.read(new File(imgStr));
                            ImageIcon icon = new ImageIcon(img);
                            display.displayBoardEnemy[coordinates.get(i).positionX][coordinates.get(i).positionY].setIcon(icon);
                        } catch (IOException ex) {
                            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                    }
                }
            }
        }
    }
    
    public void clearEnemyPanel(){
        for (int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                display.displayBoardEnemy[i][j].setBackground(Color.GRAY);
                try {
                    BufferedImage img_Water = ImageIO.read(new File("Assets/cloud.png"));
                    ImageIcon icon_Water = new ImageIcon(img_Water);
                    display.displayBoardEnemy[i][j].setIcon(icon_Water);
                } catch (IOException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public String getTokenImage(Token token){
        String imgStr = "";
        switch(token.getTokenType()){ 
            case Power:
                imgStr = "Assets/Power.png";
                break;
            case Connector:
                imgStr = "Assets/connector.png";
                break;
            case Market:
                imgStr = "Assets/market.png";
                break;
            case Mine:
                imgStr = "Assets/mine.png";
                break;
            case Armory:
                imgStr = "Assets/armory.png";
                break;
            case Temple:
                imgStr = "Assets/temple.png";
                break;
            case Whirlpool:
                imgStr = "Assets/whirlpool.png";
                break;
        }
        return imgStr;
    }
    
    public String getWeaponImage(Weapon weapon){
        String imgStr = "";
        switch(weapon.getWeaponType()){
            case Torpedo:
                imgStr = "Assets/Weapons/torpedo.png";
                break;
            case MultiShot:
                imgStr = "Assets/Weapons/multishot.png";
                break;
            case Bomb:
                imgStr = "Assets/Weapons/bomb.png";
                break;
            case Spy:
                imgStr = "Assets/Weapons/spy.png";
                break;
            case Trumpedo:
                imgStr = "Assets/Weapons/trumpedo.png";
                break;
        }
        return imgStr;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // PLAYER PANEL
        if (e.getSource().equals(display.jPanel_BGBoard)){
            if (client.clientThread.player.isTurn() || !isReady){
                if (this.display.jCheckBox_Connect.isSelected() == false && this.display.jCheckBox_Disconnect.isSelected() == false && this.display.jCheckBox_Place.isSelected() == true){
                    TokenType type = this.getComboBoxToken();
                    ArrayList<Coordinate> coordinates = Game.getTokenCoordinates(type, e.getX()/ SQUARE, e.getY()/ SQUARE, rotate);
                    if (coordinates != null && !Game.graphIsOccupied(coordinates, client.clientThread.player.graph)){
                        if (this.addTokenToPlayer(type, coordinates)){
                            this.drawGraph(client.clientThread.player.graph, false);
                            this.updateMoney();
                        }
                        else
                            this.addActivity("Not enough money");
                    }
                    else
                        this.addActivity("Invalid placement");
                }
                else if (this.display.jCheckBox_Connect.isSelected() == true && this.display.jCheckBox_Disconnect.isSelected() == false && this.display.jCheckBox_Place.isSelected() == false)
                    connectEdges(e);
                else if (this.display.jCheckBox_Connect.isSelected() == false && this.display.jCheckBox_Disconnect.isSelected() == true && this.display.jCheckBox_Place.isSelected() == false)
                    disconnectEdges(e);
                else if (this.display.jCheckBox_Connect.isSelected() == false && this.display.jCheckBox_Disconnect.isSelected() == false && this.display.jCheckBox_Place.isSelected() == false){
                    Token token = Game.getTokenAt(new Coordinate(e.getX()/ SQUARE, e.getY()/ SQUARE), client.clientThread.player.graph);
                    if (token != null && !Game.slotDestroyed(token))
                        this.doTokenAction(token);
                }
            }
            else if (this.display.jCheckBox_Connect.isSelected() == false && this.display.jCheckBox_Disconnect.isSelected() == false && this.display.jCheckBox_Place.isSelected() == false){
                Token token = Game.getTokenAt(new Coordinate(e.getX()/ SQUARE, e.getY()/ SQUARE), client.clientThread.player.graph);
                if (token != null && !Game.slotDestroyed(token))
                    this.doTokenAction(token);
            }
            else
                this.addActivity("Its not your turn!");
        }
        // ENEMY PANEL
        else if(e.getSource().equals(display.jPanel_BGEnemy) && currentEnemy != 0){
            if (client.clientThread.player.isTurn()){
                if (currentWeapon != null){
                    Coordinate coord = new Coordinate(e.getX()/ SQUARE, e.getY()/ SQUARE);
                    attackCords.add(coord);
                    if (Game.readyToFire(currentWeapon, attackCords)){
                        client.clientThread.sendPackage(new Board(client.clientThread.player.graph, client.clientThread.player.getId()));
                        client.clientThread.sendPackage(new Attack(currentWeapon, attackCords, client.clientThread.player.getId(), currentEnemy));
                        client.clientThread.player.setTurn(false);
                        this.currentWeapon = null;
                        this.attackCords.clear();       
                        this.updateWeapon();
                    }
                }
                else if (!client.clientThread.player.weapons.isEmpty()){
                    currentWeapon = client.clientThread.player.weapons.pop().getWeaponType();
                    Coordinate coord = new Coordinate(e.getX()/ SQUARE, e.getY()/ SQUARE);
                    attackCords.add(coord);
                    if (Game.readyToFire(currentWeapon, attackCords)){
                        client.clientThread.sendPackage(new Board(client.clientThread.player.graph, client.clientThread.player.getId()));
                        client.clientThread.sendPackage(new Attack(currentWeapon, attackCords, client.clientThread.player.getId(), currentEnemy));
                        client.clientThread.player.setTurn(false);
                        this.currentWeapon = null;
                        this.attackCords.clear();
                        this.updateWeapon();
                    }
                }
                else
                    this.addActivity("No weapons available");
            }
            else{
                this.addActivity("Its not your turn!");
            }
        }
    }
    
    public void connectEdges(MouseEvent e){
        if (Game.graphIsLinkable(new Coordinate(e.getX()/ SQUARE, e.getY()/ SQUARE), client.clientThread.player.graph)){
            if (sourceX == -1 && sourceY == -1){
                sourceX = e.getX();
                sourceY = e.getY();
                this.display.jCheckBox_Connect.setEnabled(false);
                this.display.jCheckBox_Disconnect.setEnabled(false);
                this.display.jCheckBox_Place.setEnabled(false);
            } 
            else if(sourceX != -1 && sourceY != -1) {
                if(Game.connectTokens(e.getX()/SQUARE, sourceX/SQUARE, e.getY()/SQUARE, sourceY/SQUARE, client.clientThread.player.graph)){
                    paintEdges(client.clientThread.player.graph);
                    this.display.jCheckBox_Connect.setEnabled(true);
                    this.display.jCheckBox_Disconnect.setEnabled(true);
                    this.display.jCheckBox_Place.setEnabled(true);
                    sourceX = -1;
                    sourceY = -1;
                }
            }
        }
        else{
            this.display.jCheckBox_Connect.setEnabled(true);
            this.display.jCheckBox_Disconnect.setEnabled(true);
            this.display.jCheckBox_Place.setEnabled(true);
            sourceX = -1;
            sourceY = -1;
        }
    }
    
    public void disconnectEdges(MouseEvent e){
        if (Game.graphIsLinkable(new Coordinate(e.getX()/ SQUARE, e.getY()/ SQUARE), client.clientThread.player.graph)){
            if (sourceX == -1 && sourceY == -1){
                sourceX = e.getX();
                sourceY = e.getY();
                this.display.jCheckBox_Connect.setEnabled(false);
                this.display.jCheckBox_Disconnect.setEnabled(false);
            } 
            else if(sourceX != -1 && sourceY != -1) {
                if(Game.disconnectTokens(e.getX()/SQUARE, sourceX/SQUARE, e.getY()/SQUARE, sourceY/SQUARE, client.clientThread.player.graph)){
                    paintEdges(client.clientThread.player.graph);
                    this.display.jCheckBox_Connect.setEnabled(true);
                    this.display.jCheckBox_Disconnect.setEnabled(true);
                    sourceX = -1;
                    sourceY = -1;
                }
            }
        }
        else{
            this.display.jCheckBox_Connect.setEnabled(true);
            this.display.jCheckBox_Disconnect.setEnabled(true);
            this.display.jCheckBox_Place.setEnabled(true);
            sourceX = -1;
            sourceY = -1;
        }
    }

    public TokenType getComboBoxToken(){
         switch(this.display.jComboBox_Tokens.getSelectedIndex()){       
             case 0:
                 return Power;
             case 1:
                 return Connector;
             case 2:
                 return Market;
             case 3:
                 return Mine;
             case 4:
                 return Armory;
             case 5:
                 return Temple;
            default:
                 System.out.println("Incorrect token type");
         }
         return null;
    }
    
    public void paintEdges(Graph<Token> graph){      
        Graphics g = this.display.jPanel_BGBoard.getGraphics();
        this.display.jPanel_BGBoard.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        for (Map.Entry<Vertex, LinkedList<Graph.Edge>> entry : graph.map.entrySet()) {
            Token source = (Token) entry.getKey().data;
            LinkedList<Graph.Edge> value = entry.getValue();               
            if(!value.isEmpty()){                                           
                for(int i = 0; i < value.size(); i ++){
                    Token destination = (Token) value.get(i).data;
                    g2.drawLine(source.getCoordinates().get(0).positionX * SQUARE + (SQUARE/2), source.getCoordinates().get(0).positionY * SQUARE + (SQUARE/2), destination.getCoordinates().get(0).positionX * SQUARE + (SQUARE/2), destination.getCoordinates().get(0).positionY * SQUARE + (SQUARE/2));
                }
            }
        }
    }
    
    public boolean addTokenToPlayer(TokenType type, ArrayList<Coordinate> coordinates){
         switch(type){       
             case Power:
                 return client.clientThread.player.addToken(Factory.createToken(Power, coordinates));
             case Connector:
                 return client.clientThread.player.addToken(Factory.createToken(Connector, coordinates));
             case Market:
                 return client.clientThread.player.addToken(Factory.createToken(Market, coordinates));
             case Mine:
                 if (client.clientThread.player.addToken(Factory.createToken(Mine, coordinates))){
                     Pair pair = new Pair<>(coordinates.get(0).positionX, coordinates.get(0).positionY);
                     client.clientThread.player.mines.put(pair, new MineThread(this));
                     client.clientThread.player.mines.get(pair).start();
                     return true;
                 }
             case Armory:
                 return client.clientThread.player.addToken(Factory.createToken(Armory, coordinates));
             case Temple:
                 if (client.clientThread.player.addToken(Factory.createToken(Temple, coordinates))){
                     Pair pair = new Pair<>(coordinates.get(0).positionX, coordinates.get(0).positionY);
                     client.clientThread.player.temples.put(pair, new TempleThread(this));
                     client.clientThread.player.temples.get(pair).start();
                     return true;                     
                 }
            default:
                 System.out.println("Incorrect token type");
         }
         return false;
    }
    
    public void doTokenAction(Token token){
         if (client.clientThread.player.lostGame())
             return;
         switch(token.getTokenType()){       
             case Market:
                 marketController.showDisplay();
                 return;
             case Mine:
                 for (MineThread mine : client.clientThread.player.mines.values()){
                     if (mine.readyToCollect())
                        client.clientThread.player.addIron(mine.collectIron());
                 }
                 this.updateIron();
                 return;
             case Armory:
                 armoryController.showDisplay();
                 return;
             case Temple:
                 for (TempleThread temple : client.clientThread.player.temples.values()){
                     if (temple.readyToCollect()){
                         ArrayList<Integer> collected = temple.collectShields();
                         for (int i = 0; i < collected.size(); i++)
                             client.clientThread.player.addShield(collected.get(i));
                     }
                 }
         }        
    }
    
    public void showEnemyButtons(int playerAmount){
        JButton[] enemyButtons = {display.jButton_Enemy1, display.jButton_Enemy2, display.jButton_Enemy3, display.jButton_Enemy4};
        for (int i = 0; i < playerAmount; i++){
            if(i != client.clientThread.player.getId() - 1)
                enemyButtons[i].show();
        }
    }
    
    public void gameOver(){
        display.jButton_Shield.setEnabled(false);
        display.jCheckBox_Connect.setEnabled(false);
        display.jCheckBox_Disconnect.setEnabled(false);
        display.jCheckBox_Place.setEnabled(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    

   
}
