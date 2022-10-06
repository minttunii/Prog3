

public class ValueNode extends Node {
    private double number;
    private boolean bool;
    private boolean boolchanged = false;
    private String string;
    
    ValueNode(double value){  
        this.number = value;        
    }
    
    ValueNode(boolean value){ 
        this.bool = value;
        this.boolchanged = true;
    }
    
    ValueNode(String value){
        this.string = value;
    }
    
    public boolean isNumber(){
        return number != 0.0d;
    }
    
    public boolean isString(){
        return string != null;
    }
    
    public boolean isNull(){
        return string == null;
    }
    
    public boolean isBoolean(){
        return boolchanged == true || bool == true;
    }
    
    public double getNumber(){
        return number;
    }
    
    public String getString(){
        return string;
    }
    
    public boolean getBoolean(){
        return bool;
    }
   
}
