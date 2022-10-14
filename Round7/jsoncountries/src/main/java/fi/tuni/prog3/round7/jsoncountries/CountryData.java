
package fi.tuni.prog3.round7.jsoncountries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    
        try {
            File input = new File(areaFile);
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            
            JsonArray records = fileObject.get("records").getAsJsonArray();
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
            }   } catch (FileNotFoundException ex) {
            Logger.getLogger(CountryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    
} 

    
    private static void readPopulationFile(String populationFile, List<Country> list){
        
        try {
            File input = new File(populationFile);
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            
            JsonArray records = fileObject.get("records").getAsJsonArray();
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
            }   } catch (FileNotFoundException ex) {
            Logger.getLogger(CountryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void readGdpFile(String gdpFile, List<Country> list){
        try {
            File input = new File(gdpFile);
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray records = fileObject.get("records").getAsJsonArray();
            
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
            }   } catch (FileNotFoundException ex) {
            Logger.getLogger(CountryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void writeToJson(List<Country> countries, String countryFile){
        Writer writer = null;
        try {
            // Create Gson instance
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // Create a writer
            writer = Files.newBufferedWriter(Paths.get(countryFile));
            gson.toJson(countries, writer);
            
        } catch (IOException ex) {
            Logger.getLogger(CountryData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(CountryData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
