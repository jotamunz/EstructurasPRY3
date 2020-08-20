
package Game.Packages;

import Game.Graph;
import Game.Token;

public class Board implements IPackage{

    private Graph<Token> graph;
    private int playerId;

    public Board(Graph<Token> graph, int playerId) {
        this.graph = graph;
        this.playerId = playerId;
    }
    
    public int getPlayerId(){
        return playerId;
    }
    
    public Graph<Token> getGraph(){
        return graph;
    }
    
    @Override
    public PackageEnum getType() {
        return PackageEnum.Board;
    }
    
}
