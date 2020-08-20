
package Game;

import Game.Graph.Edge;
import Game.Graph.Vertex;
import static Game.TokenType.*;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    
    public static int DIMENSION = 20;
    public static int SQUARE = 35;
    
    public static boolean graphIsOccupied(ArrayList<Coordinate> coordinateList, Graph<Token> graph){
        for (int i = 0; i < coordinateList.size(); i++){
            if(graph.map.isEmpty())
                return false;
            for(Vertex vertex : graph.map.keySet()) {
                Token token = (Token) vertex.data;
                ArrayList<Coordinate> coordinates = token.getCoordinates();
                for(int j = 0; j < coordinates.size(); j++) {
                    if(coordinates.get(j).positionX == coordinateList.get(i).positionX && coordinates.get(j).positionY == coordinateList.get(i).positionY)
                        return true;
                }
            }
        }
        return false;
    }
    
    public static boolean graphIsLinkable(Coordinate coordinate, Graph<Token> graph){
        if(graph.map.isEmpty())
            return false;
        for(Vertex vertex : graph.map.keySet()) {
            Token token = (Token) vertex.data;
            if (token.getTokenType() != TokenType.Whirlpool){
                ArrayList<Coordinate> coordinates = token.getCoordinates();
                for(int j = 0; j < coordinates.size(); j++) {
                    if(coordinates.get(j).positionX == coordinate.positionX && coordinates.get(j).positionY == coordinate.positionY)
                        return true;
                }
            }
        }
        return false;
    }
    
    public static Token getTokenAt(Coordinate coordinate, Graph<Token> graph){
        if(graph.map.isEmpty())
            return null;
        for(Vertex vertex : graph.map.keySet()) {
            Token token = (Token) vertex.data;
            ArrayList<Coordinate> coordinates = token.getCoordinates();
            for(int j = 0; j < coordinates.size(); j++) {
                if(coordinates.get(j).positionX == coordinate.positionX && coordinates.get(j).positionY == coordinate.positionY)
                    return token;
            }
        }
        return null;
    }
    
    public static boolean slotDestroyed(Token slot){
        boolean isDestroyed = true;
        ArrayList<Coordinate> slotCords = slot.getCoordinates();
        for (int i = 0; i < slotCords.size(); i++){
            if (!slotCords.get(i).hit)
                isDestroyed = false;
        }
        return isDestroyed;
    }
    
    public static boolean connectTokens(int x1, int x2, int y1, int y2, Graph<Token> graph){
        Coordinate source = new Coordinate(x1,y1);
        Coordinate destination = new Coordinate(x2,y2);
        
        int x = Math.abs(x1 - x2);
        int y = Math.abs(y1 - y2);
        int hypo = 0;
        
        boolean source_coordinate = false;
        boolean destination_coordinate = false;
        
        Token sourceToken = null;
        Token destinationToken = null;

        if(source != destination && !graph.map.isEmpty()) {     
            hypo = (int) Math.hypot(x, y) * 1000;          
            for(Map.Entry<Graph.Vertex, LinkedList<Graph.Edge>> entry : graph.map.entrySet()) {
                Token vertex = (Token) entry.getKey().data;
                for(int j = 0; j < vertex.getCoordinates().size(); j++) {
                    if(vertex.getCoordinates().get(j).positionX == source.positionX && vertex.getCoordinates().get(j).positionY == source.positionY){
                        sourceToken = vertex;                       
                        source_coordinate = true;
                    }
                    else if(vertex.getCoordinates().get(j).positionX == destination.positionX && vertex.getCoordinates().get(j).positionY == destination.positionY){
                        destinationToken = vertex;
                        destination_coordinate = true;
                    }
                }
            }
        }
        if(source_coordinate == true && destination_coordinate == true && (sourceToken.getTokenType() == Connector ^ destinationToken.getTokenType() == Connector)) {                   
            graph.connect(sourceToken, destinationToken, hypo, true);
            return true;
        }
        return false;
    }
    
    public static boolean disconnectTokens(int x1, int x2, int y1, int y2, Graph<Token> graph){
        Coordinate source = new Coordinate(x1,y1);
        Coordinate destination = new Coordinate(x2,y2);
        
        boolean source_coordinate = false;
        boolean destination_coordinate = false;
        
        Token sourceToken = null;
        Token destinationToken = null;
        
        if(source != destination && !graph.map.isEmpty()){
            for(Map.Entry<Graph.Vertex, LinkedList<Graph.Edge>> entry : graph.map.entrySet()) {
                Token vertex = (Token) entry.getKey().data;
                for(int j = 0; j < vertex.getCoordinates().size(); j++) {
                    if(vertex.getCoordinates().get(j).positionX == source.positionX && vertex.getCoordinates().get(j).positionY == source.positionY){
                        sourceToken = vertex;                       
                        source_coordinate = true;
                    }
                    else if(vertex.getCoordinates().get(j).positionX == destination.positionX && vertex.getCoordinates().get(j).positionY == destination.positionY){
                        destinationToken = vertex;
                        destination_coordinate = true;
                    }
                }
            }
        }
        if(source_coordinate == true && destination_coordinate == true && graph.areConnected(sourceToken, destinationToken, true)) {
            graph.disconnect(sourceToken, destinationToken, true);
            return true;
        }
        return false;
    }
    
    public static void isolateToken(Coordinate coordinate, Graph<Token> graph){
        for(Map.Entry<Graph.Vertex, LinkedList<Graph.Edge>> entry : graph.map.entrySet()) {
            Token vertex = (Token) entry.getKey().data;
            for(int i = 0; i < vertex.getCoordinates().size(); i++) {
                if(vertex.getCoordinates().get(i).positionX == coordinate.positionX && vertex.getCoordinates().get(i).positionY == coordinate.positionY){
                    LinkedList<Edge> connections = entry.getValue();
                    while (!connections.isEmpty()){
                        Token connection = (Token) connections.get(0).data;
                        graph.disconnect(vertex, connection, true);
                    }
                    return;
                }
            }
        }
    }
    
    public static ArrayList<Coordinate> getTokenCoordinates(TokenType type, int x, int y, boolean rotate)
    {
        switch (type){
            case Power:
                if (x < DIMENSION - 1 && y < DIMENSION - 1)
                    return new ArrayList<>(Arrays.asList(new Coordinate(x,y), new Coordinate(x+1,y), new Coordinate(x+1,y+1), new Coordinate(x,y+1)));
                break;
            case Connector:
                return new ArrayList<>(Arrays.asList(new Coordinate(x,y)));
            default:
                if (rotate && x < DIMENSION - 1)
                    return new ArrayList<>(Arrays.asList(new Coordinate(x,y), new Coordinate(x+1,y)));
                else if (!rotate && y < DIMENSION - 1)
                    return new ArrayList<>(Arrays.asList(new Coordinate(x,y), new Coordinate(x,y+1)));
        }
        return null;
    }
    
    public static Graph<Token> markConnectedGraph(Graph<Token> graph){
        for (Vertex vertex : graph.map.keySet()){
            Token token = (Token) vertex.data;
            for (int i = 0; i < token.getCoordinates().size(); i++)
                token.getCoordinates().get(i).connected = false;
        }
        for (Vertex vertex : graph.map.keySet()){
            Token token = (Token) vertex.data;
            if (token.getTokenType() == Power){
                markConnectedGraph_Aux(token, graph);
            }
            else if (token.getTokenType() == Whirlpool){
                for (int i = 0; i < token.getCoordinates().size(); i++)
                    token.getCoordinates().get(i).connected = true;
            }
        }
        return graph;
    }
    
    public static void markConnectedGraph_Aux(Token token, Graph<Token> graph){
        if (!token.getCoordinates().get(0).connected){
            for (int i = 0; i < token.getCoordinates().size(); i++){
                token.getCoordinates().get(i).connected = true;
            }
            LinkedList<Edge> edges = graph.getEdges(token);
            for (int i = 0; i < edges.size(); i++){
                Token connection = (Token) edges.get(i).data;
                markConnectedGraph_Aux(connection, graph);
            }
        }
    }
    
    public static boolean readyToFire(WeaponType weapon, ArrayList<Coordinate> coordinates)
    {
        switch (weapon){
            case Bomb:
                return coordinates.size() == 3;
            case Trumpedo:
                return coordinates.size() == 10;
            default:
                return true;
        }
    }
    
    public static boolean torpedoAttack(Graph<Token> graph, Coordinate coordinate, Player atacker, AtomicInteger whirlpoolAmount, StringBuffer hitCords, AtomicInteger powerSourceAmount, StringBuffer shotCords){
        Token slot = getTokenAt(coordinate, graph);
        shotCords.append(coordinate.toString());
        if (slot != null){
            if (slot.getTokenType() != Whirlpool){
                ArrayList<Coordinate> slotCords = slot.getCoordinates();
                for (int i = 0; i < slotCords.size(); i++){
                    if (slotCords.get(i).positionX == coordinate.positionX && slotCords.get(i).positionY == coordinate.positionY && !slotCords.get(i).hit){
                        slotCords.get(i).hit = true;
                        hitCords.append(slotCords.get(i).toString());
                        if (slotDestroyed(slot)){
                            if (slot.getTokenType() == TokenType.Power)
                                powerSourceAmount.incrementAndGet();
                            isolateToken(coordinate, graph);
                            generateRipple(slotCords.get(i), graph, hitCords, powerSourceAmount);
                        }
                        return true;
                    }        
                }
            }
            else
                whirlpoolAmount.incrementAndGet(); 
        }
        return false;
    }
    
    public static boolean multiShotAttack(Graph<Token> graph, Coordinate coordinate, Player atacker, AtomicInteger whirlpoolAmount, StringBuffer hitCords, AtomicInteger powerSourceAmount, StringBuffer shotCords){
        Token slot = getTokenAt(coordinate, graph);
        shotCords.append(coordinate.toString());
        if (slot != null){
            if (slot.getTokenType() != Whirlpool){
                ArrayList<Coordinate> slotCords = slot.getCoordinates();
                for (int i = 0; i < slotCords.size(); i++){
                    if (slotCords.get(i).positionX == coordinate.positionX && slotCords.get(i).positionY == coordinate.positionY && !slotCords.get(i).hit){
                        slotCords.get(i).hit = true;
                        hitCords.append(slotCords.get(i).toString());
                        for(int j = 0; j < 4; j++){
                            int x = (int) (Math.random()*100) % DIMENSION;
                            int y = (int) (Math.random()*100) % DIMENSION;
                            torpedoAttack(graph, new Coordinate(x,y), atacker, whirlpoolAmount, hitCords, powerSourceAmount, shotCords);
                        }
                        if (slotDestroyed(slot)){
                            if (slot.getTokenType() == TokenType.Power)
                                powerSourceAmount.incrementAndGet();
                            isolateToken(coordinate, graph);
                            generateRipple(slotCords.get(i), graph, hitCords, powerSourceAmount);
                        }
                        return true;
                    }        
                }
            }
            else
               whirlpoolAmount.incrementAndGet(); 
        }
        return false;
    }
    
    public static boolean bombAttack(Graph<Token> graph, Coordinate coordinate, Player atacker, AtomicInteger whirlpoolAmount, StringBuffer hitCords, AtomicInteger powerSourceAmount, StringBuffer shotCords){
        int random = (int) (Math.random()*100) % 100;
        if (random < 50){
            int x = coordinate.positionX + 1;
            if (x >= DIMENSION)
                x = coordinate.positionX - 1;
            int y = coordinate.positionY;
            boolean attack1 = torpedoAttack(graph, coordinate, atacker, whirlpoolAmount, hitCords, powerSourceAmount, shotCords);
            boolean attack2 = torpedoAttack(graph, new Coordinate(x,y), atacker, whirlpoolAmount, hitCords, powerSourceAmount, shotCords);
            if (attack1 || attack2)
                return true;
        }
        else{
            int y = coordinate.positionY + 1;
            if (y >= DIMENSION)
                y = coordinate.positionY - 1;
            int x = coordinate.positionX;
            boolean attack1 = torpedoAttack(graph, coordinate, atacker, whirlpoolAmount, hitCords, powerSourceAmount, shotCords);
            boolean attack2 = torpedoAttack(graph, new Coordinate(x,y), atacker, whirlpoolAmount, hitCords, powerSourceAmount, shotCords);
            if (attack1 || attack2)
                return true;
        }
        return false;
    }
    
    public static void generateRipple(Coordinate source, Graph<Token> graph, StringBuffer hitCords, AtomicInteger powerSourceAmount){
        int posX = source.positionX;
        int posY = source.positionY;
        if(graph.map.isEmpty())
            return;
        for(Vertex vertex : graph.map.keySet()) {
            Token token = (Token) vertex.data;
            if (token.getTokenType() != TokenType.Whirlpool){
                ArrayList<Coordinate> coordinates = token.getCoordinates();
                for(int i = 0; i < coordinates.size(); i++) {
                    if (!coordinates.get(i).hit && abs(coordinates.get(i).positionX - posX) <= 2 && abs(coordinates.get(i).positionY - posY) <= 2){
                        coordinates.get(i).hit = true;
                        hitCords.append("R/").append(coordinates.get(i).toString());
                        if (slotDestroyed(token)){
                            if (token.getTokenType() == TokenType.Power)
                                powerSourceAmount.incrementAndGet();
                            isolateToken(coordinates.get(i), graph);
                            generateRipple(coordinates.get(i), graph, hitCords, powerSourceAmount);
                        }
                    }
                }
            }
        }
    }
    
    public static Graph<Token> spyBoatAttack(Coordinate position, Graph<Token> graph, int playerId){
        int posX = position.positionX;
        int posY = position.positionY;
        for(Vertex vertex : graph.map.keySet()) {
            Token token = (Token) vertex.data;
            ArrayList<Coordinate> coordinates = token.getCoordinates();
            for(int i = 0; i < coordinates.size(); i++) {
                if (abs(coordinates.get(i).positionX - posX) <= 3 && abs(coordinates.get(i).positionY - posY) <= 3){
                    coordinates.get(i).spied.replace(playerId, true);
                }
            }
        }
        return graph;
    }
    
    public static boolean playerLost(Graph<Token> graph){
        for (Vertex vertex : graph.map.keySet()){
            Token token = (Token) vertex.data;
            if (token.getTokenType() != TokenType.Whirlpool){
                ArrayList<Coordinate> coordinates = token.getCoordinates();
                for (int i = 0; i < coordinates.size(); i++){
                    if (!coordinates.get(i).hit)
                        return false;
                }
            }
        }
        return true;
    }
}

