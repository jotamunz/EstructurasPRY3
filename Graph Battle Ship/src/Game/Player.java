
package Game;

import static Game.Game.DIMENSION;
import Game.Packages.Offer;
import Game.Threads.MineThread;
import Game.Threads.SpyThread;
import Game.Threads.TempleThread;
import static Game.TokenType.*;
import static Game.WeaponType.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javafx.util.Pair;

public class Player {
    
    private int money;
    private int id;
    private int iron;    
    private boolean shieldActive;
    private int currentShield;
    private String name;
    private boolean turn;
    private boolean lost;
    public Graph<Token> graph;
    public Stack<Weapon> weapons;
    public Queue<Integer> shields;
    public HashMap<Integer, Graph<Token>> enemyGraphs;
    public HashMap<Pair<Integer, Integer>, MineThread> mines;
    public HashMap<Pair<Integer, Integer>, TempleThread> temples;
    public ArrayList<SpyThread> spyBoats;
    
    public Player(int id, String name){
        this.id = id;
        this.name = name;
        this.turn = false;
        this.lost = false;
        this.shields = new LinkedList<>();
        this.shieldActive = false;
        this.currentShield = 0;
        this.graph = new Graph<>();
        this.addWhirlpool();
        this.addWhirlpool();;
        this.enemyGraphs = new HashMap<>();
        this.weapons = new Stack(); 
        this.mines = new HashMap<>();
        this.temples = new HashMap<>();
        this.spyBoats = new ArrayList<>();
        this.money = 16000;
        this.iron = 10000; 
    }
    
    public void setTurn(boolean turn){
        this.turn = turn;
    }
    
    public void loseGame(){
        this.lost = true;
    }
    
    public boolean lostGame(){
        return lost;
    }
    
    public boolean isTurn(){
        return turn;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public int getMoney() {
        return money;
    }
    
    public int getIron(){
        return iron;
    }
    
    public void addMoney(int money){
        this.money += money;
    }
    
    public void addIron(int iron){
        this.iron += iron;
    }
    
    public void subtractMoney(int money){
        this.money -= money;
    }
    
    public void subtractIron(int iron){
        this.iron -= iron;
    }
    
    public boolean hasShield(){
        return shieldActive;
    }
    
    public int getCurrentShield(){
        return currentShield;
    }
    
    public void setCurrentShield(int shieldAmount){
        this.shieldActive = true;
        this.currentShield = shieldAmount;
    }
    
    public void addShield(int shieldAmount){
        this.shields.add(shieldAmount);
    }
    
    public boolean activateShield(){
        if (!shields.isEmpty()){
            this.shieldActive = true;
            this.currentShield = shields.remove();
            return true;
        }
        return false;
    }
    
    public void reduceShield(){
        this.currentShield -= 1;
        if (currentShield <= 0)
            this.shieldActive = false;
    }
    
    public boolean addWeapon(Weapon weapon){
        if(weapon.getWeaponType() == Torpedo && iron > weapon.getPrice()) {
            this.weapons.push(weapon);
            this.iron -= weapon.getPrice();
            return true;
        }
        else if(weapon.getWeaponType() == MultiShot && iron > weapon.getPrice()) {
            this.weapons.push(weapon);
            this.iron -= weapon.getPrice();
            return true;
        }
        else if(weapon.getWeaponType()  == Bomb && iron > weapon.getPrice())  {
            this.weapons.push(weapon);
            this.iron -= weapon.getPrice();
            return true;
        }
        else if(weapon.getWeaponType() == Trumpedo && iron > weapon.getPrice())  {
            this.weapons.push(weapon);
            this.iron -= weapon.getPrice();
            return true;
        }
        else if(weapon.getWeaponType() == Spy && money > weapon.getPrice()) {
            this.weapons.push(weapon);
            this.money -= weapon.getPrice();
            return true;
        }
        return false;
    }
    
    public boolean addToken(Token token) {
        if(token.getTokenType() == Market && money > token.getPrice()) {
            this.graph.addVertex(token);
            this.money -= token.getPrice();
            return true;
        }
        else if(token.getTokenType() == Armory && money > token.getPrice()) {
            this.graph.addVertex(token);
            this.money -= token.getPrice();
            return true;
        }
        else if(token.getTokenType() == Mine && money > token.getPrice())  {
            this.graph.addVertex(token);
            this.money -= token.getPrice();
            return true;
        }
        else if(token.getTokenType() == Temple && money > token.getPrice())  {
            this.graph.addVertex(token);
            this.money -= token.getPrice();
            return true;
        }
        else if(token.getTokenType() == Power && money > token.getPrice())  {
            this.graph.addVertex(token);
            this.money -= token.getPrice();
            return true;
        }
        else if(token.getTokenType() == Connector && money > token.getPrice())  {
            this.graph.addVertex(token);
            this.money -= token.getPrice();
            return true;
        }
        return false;
    }
    
    public void addWhirlpool(){
        int x;
        int y;
        do{
            x = (int) (Math.random()*100) % DIMENSION;
            y = (int) (Math.random()*100) % DIMENSION;
        } while (x >= DIMENSION || y >= DIMENSION || Game.getTokenAt(new Coordinate(x, y), graph) != null);
        this.graph.addVertex(Factory.createToken(Whirlpool, new ArrayList<>(Arrays.asList(new Coordinate(x,y)))));
    }

    public boolean hasWeapon(WeaponType type, int amount){
        int weaponCount = 0;
        for (int i = 0; i < weapons.size(); i++){
            if (weapons.get(i).getWeaponType() == type){
                weaponCount++;
            }
        }
        return weaponCount >= amount;
    }
    
    public void removeWeapon(WeaponType type, int amount){
        int count = 0;
        while (count < amount){
            for (int i = 0; i < weapons.size(); i++){
                if (weapons.get(i).getWeaponType() == type){
                    weapons.remove(i);
                    count++;
                    break;
                }
            }
        }
    }
    
    public void returnOffer(Offer offer){
        this.addIron(offer.getIron());
        for (int i = 0; i < offer.getWeapons().size(); i++){
            this.addWeapon(offer.getWeapons().get(i));
        }
    }
}