
package fi.tuni.prog3.round7.jsoncountries;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;


public class CountryData {
    public static List<Country> readFromJsons(String areaFile,
                                            String populationFile,
                                            String gdpFile){
        List<Country> countrylist = new ArrayList<>();
        readAreaFile(areaFile, countrylist);
        readPopulationFile(populationFile, countrylist);
        readGdpFile(gdpFile, countrylist);
        return countrylist;
    }
    
    private static void readAreaFile(String areaFile, List<Country> list){
        
    JsonObject jsonObject = new JsonParser().parse(areaFile).getAsJsonObject();
    JsonArray records = jsonObject.get("records").getAsJsonArray();
    for(JsonElement record : records){
        JsonArray fields = record.getAsJsonObject().get("field").getAsJsonArray();
        for(JsonElement field : fields){
            JsonObject fieldobject = field.getAsJsonObject();
            String name = fieldobject.get("attributes").getAsJsonObject().get("name").getAsString();
            String countryname = " ";
            if("Country or Area".equals(name)){
                countryname = fieldobject.get("value").getAsString();
                list.add(new Country(countryname));
            }
            if("Value".equals(name)){
                for(Country c : list){
                    if(countryname.equals(c.getName())){
                        double area = fieldobject.get("value").getAsDouble();
                        c.setArea(area);
                    }
                }
            }
        }
    }
    
} 

    
    private static void readPopulationFile(String populationFile, List<Country> list){
        
    JsonObject jsonObject = new JsonParser().parse(populationFile).getAsJsonObject();
    JsonArray records = jsonObject.get("records").getAsJsonArray();
    for(JsonElement record : records){
        JsonArray fields = record.getAsJsonObject().get("field").getAsJsonArray();
        for(JsonElement field : fields){
            JsonObject fieldobject = field.getAsJsonObject();
            String name = fieldobject.get("attributes").getAsJsonObject().get("name").getAsString();
            String countryname = " ";
            if("Country or Area".equals(name)){
                countryname = fieldobject.get("value").getAsString();
                boolean exists = false;    
                    for(Country c : list){
                        if(countryname.equals(c.getName())){
                            exists = true;
                            break;
                        }
                    }
                    if(!exists){
                        list.add(new Country(countryname));
                        }            
                
            }
            if("Value".equals(name)){
                for(Country c : list){
                    if(countryname.equals(c.getName())){
                        long population = fieldobject.get("value").getAsLong();
                        c.setPopulation(population);
                    }
                }
            }
        }
    }
    }
    
    private static void readGdpFile(String gdpFile, List<Country> list){
    JsonObject jsonObject = new JsonParser().parse(gdpFile).getAsJsonObject();
    JsonArray records = jsonObject.get("records").getAsJsonArray();
    for(JsonElement record : records){
        JsonArray fields = record.getAsJsonObject().get("field").getAsJsonArray();
        for(JsonElement field : fields){
            JsonObject fieldobject = field.getAsJsonObject();
            String name = fieldobject.get("attributes").getAsJsonObject().get("name").getAsString();
            String countryname = " ";
            if("Country or Area".equals(name)){
                countryname = fieldobject.get("value").getAsString();
                boolean exists = false;    
                    for(Country c : list){
                        if(countryname.equals(c.getName())){
                            exists = true;
                            break;
                        }
                    }
                    if(!exists){
                        list.add(new Country(countryname));
                        }            
                
            }
            if("Value".equals(name)){
                for(Country c : list){
                    if(countryname.equals(c.getName())){
                        double gdp = fieldobject.get("value").getAsDouble();
                        c.setGDP(gdp);
                    }
                }
            }
        }
    }
    }
    
    public static void writeToJson(List<Country> countries, String countryFile){
        
    }
}
