
import java.util.TreeMap;
import java.util.Iterator;


public class ObjectNode extends Node implements Iterable<String>{
    private TreeMap<String, Node> jsonObject;

    public ObjectNode() {
        this.jsonObject = new TreeMap<>();
    }
    
    public Node get(String key){
        for(String map_key : jsonObject.keySet()){
            if(map_key.equals(key)){
                return jsonObject.get(key);
            }
        }
        return null;
    }
    
    public void set(String key, Node node){
        jsonObject.put(key, node);
    }
    
    public int size(){
        return 0;
    }
    
    @Override
    public Iterator<String> iterator() {
        return jsonObject.keySet().iterator();
    }

}
