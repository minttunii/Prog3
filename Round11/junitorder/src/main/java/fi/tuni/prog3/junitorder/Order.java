
package fi.tuni.prog3.junitorder;

import java.util.List;
import java.util.NoSuchElementException;

public class Order {
    
    public static class Entry{
        public Entry(Order.Item item, int count) throws IllegalArgumentException{
            
        }
        
        public String getItemName(){
            return " ";
        }
        
        public double getUnitPrice(){
            return 0;
        }
        
        public Item getItem(){
            return null;
        }
        
        public int getCount(){
            return 0;
        }
        
        @Override
        public String toString(){
            return " ";
        } 
    }
    
    public static class Item{
        public Item(String name, double price) throws IllegalArgumentException{
            
        }
        
        public String getName(){
            return " ";
        }
        
        public double getPrice(){
            return 0;
        }
        
        @Override
        public String toString(){
            return " ";
        }
        
        public boolean equals(Item other){
            return true;
        }
    }
    
    
    public Order(){
        
    }
    
    public boolean addItems(Item item, int count) 
            throws IllegalArgumentException {
        return true;
    }
    
    public boolean addItems(String name, int count)
                throws IllegalArgumentException, NoSuchElementException {
        return true;
    }
    
    public List<Entry> getEntries(){
        return null;
    }
    
    public int getEntryCount(){
        return 0;
    }
    
    public int getItemCount(){
        return 0;
    }
    
    public double getTotalPrice(){
        return 0;
    }
    
    public boolean isEmpty(){
        return true;
    }
    
    public boolean removeItems(String name, int count)
                    throws IllegalArgumentException, NoSuchElementException{
        return true;
    }
    
}
