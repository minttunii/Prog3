import java.util.ArrayList;
import java.util.Iterator;

public class ArrayNode extends Node implements Iterable<Node> {
    private ArrayList<Node> arraynode;

    public ArrayNode() {
        arraynode = new ArrayList<>();
    }
    
    public void add(Node node){
        arraynode.add(node);
    }
    
    public int size(){
        return arraynode.size();
    }
    
    @Override
    public Iterator<Node> iterator(){
        return arraynode.iterator();
    }
   
}
