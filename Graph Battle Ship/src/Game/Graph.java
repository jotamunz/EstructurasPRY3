
package Game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph<T> implements Serializable{

    public class Vertex<T> implements Serializable{

        public T data;

        public Vertex(T data){
            this.data = data;
        }
        
        @Override
        public String toString(){
            return data.toString();
        }
    }

    public class Edge<T> implements Serializable{

        public T data;
        public double weight;

        public Edge(T data, double weight){
            this.data = data;
            this.weight = weight;
        }
        
        @Override
        public String toString(){
            return data.toString();
        }
    }

    public HashMap<Vertex, LinkedList<Edge>> map; 

    public Graph() {
        this.map = new HashMap<>();
    }
  
    public void addVertex(T vertex) 
    { 
        map.put(new Vertex<T>(vertex), new LinkedList<Edge>()); 
    } 
  
    public void connect(T source, T destination, int weight, boolean bidirectional) 
    { 
        if (this.hasVertex(source) && this.hasVertex(destination)){
            if (this.getEdge(source, destination) == null)
                this.getEdges(source).add(new Edge(destination, weight));
            if (bidirectional == true) { 
                if (this.getEdge(destination, source) == null)
                {
                    this.getEdges(destination).add(new Edge(source, weight)); 
                }
            }
        }
    } 
    
    public void disconnect(T source, T destination, boolean bidirectional)
    {
        if (this.areConnected(source, destination, bidirectional)){
            this.getEdges(source).remove(this.getEdge(source, destination));        
            if (bidirectional == true) { 
                this.getEdges(destination).remove(this.getEdge(destination, source)); 
            }
        }
    }
  
    public int getVertexCount() 
    { 
        return map.keySet().size();
    } 
  
    public int getEdgesCount(boolean bidirection) 
    { 
        int count = 0; 
        for (Vertex v : map.keySet()) { 
            count += map.get(v).size(); 
        } 
        if (bidirection == true) { 
            count = count / 2; 
        } 
        return count;
    } 
  
    public boolean hasVertex(T vertex) 
    {
        for (Vertex v : map.keySet()){
            if (v.data == vertex)
                return true;
        }
        return false;
    }
    
    public Vertex<T> getVertex(T vertex)
    {
        for (Vertex v : map.keySet()){
            if (v.data == vertex)
                return v;
        }
        return null; 
    }
  
    public boolean areConnected(T source, T destination, boolean bidirectional) 
    {
        boolean isSource = false;
        boolean isDest = false;
        if (hasVertex(source) && hasVertex(destination)){
            for (Edge e : this.getEdges(source)){
                if (e.data == destination)
                    isSource = true;
            }
            if (bidirectional){
                for (Edge e : this.getEdges(destination)){
                    if (e.data == source)
                         isDest = true;
                }
                return isSource && isDest;
            }
        }
        return isSource;
    }
    
    public Edge getEdge(T source, T edge) 
    {
        if (hasVertex(source)){
            for (Edge e : map.get(this.getVertex(source)))
                if (e.data == edge)
                    return e;
        }
        return null;
    }
    
    public LinkedList<Edge> getEdges(T source)
    {
        if (hasVertex(source))
            return map.get(this.getVertex(source));
        return null;
    }

    @Override
    public String toString() 
    { 
        StringBuilder builder = new StringBuilder(); 
        for (Vertex v : map.keySet()) { 
            builder.append(v.toString() + ": "); 
            for (Edge w : map.get(v)) { 
                builder.append(w.toString() + " "); 
            } 
            builder.append("\n"); 
        } 
        return (builder.toString()); 
    } 
    
}

