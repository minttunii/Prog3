
package fi.tuni.prog3.round7.xmlcountries;


public class Country implements Comparable<Country>{
    String name;
    double area;
    long population;
    double GDP;

    public Country(String name) {
        this.name = name;       
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setGDP(double GDP) {
        this.GDP = GDP;
    }
    
    
    @Override
    public int compareTo(Country other){
        return name.compareTo(other.name);
    }
    
    @Override
    public String toString(){
 
        return String.format("%s%n"
                + "  Area: %.1f km2%n"
                + "  Population: %d%n"
                + "  GDB: %.1f (2015 USD)",
                name, area, population, GDP);
    }
    
    public String getName(){
        return name;
    }
    
    public double getArea(){
        return area;
    }

    public long getPopulation() {
        return population;
    }

    public double getGdp() {
        return GDP;
    }
    
    
    
}