
package fi.tuni.prog3.round7.xmlcountries;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class CountryData {
    
    public static List<Country> readFromXmls(String areaFile,
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
            File file1 = new File(areaFile);
            SAXBuilder sax = new SAXBuilder();
            Document doc1 = sax.build(file1);
            // Read file
            Element root = doc1.getRootElement();
            List<Element> datas = root.getChildren("data");
            
            for(Element data : datas){
                
                List<Element> records = data.getChildren("record");
                for(Element record : records){
                    
                List<Element> fields = record.getChildren();
                String name = " ";
                for(Element field : fields){
                    if(field.getAttribute("name").getValue().equals("Country or Area")){
                        name = field.getContent(0).getValue();
                        list.add(new Country(name));
                    }
                    
                    if(field.getAttribute("name").getValue().equals("Value")){
                        
                        for (Country c : list) {
                                if (name.equals(c.getName())) {
                                    c.setArea(Double.parseDouble(field.getContent(0).getValue()));
                                }
                        }
                    }
                }
            }                   
            }            
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(CountryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void readPopulationFile(String populationFile, List<Country> list){
        try {
            File file2 = new File(populationFile);
            SAXBuilder sax = new SAXBuilder();
            Document doc2 = sax.build(file2);
            
            Element root = doc2.getRootElement();
            List<Element> datas = root.getChildren("data");
            for(Element data : datas){
                
                List<Element> records = data.getChildren("record");
                for(Element record : records){
                    
                List<Element> fields = record.getChildren();
                String name = " ";
                for(Element field : fields){
                    if(field.getAttribute("name").getValue().equals("Country or Area")){
                        boolean exists = false;
                        name = field.getContent(0).getValue();
                        for(Country c : list){
                            if(name.equals(c.getName())){
                                exists = true;
                                break;
                            }
                        }
                        if(!exists){
                            list.add(new Country(name));
                        }               
                    }
                    
                    if(field.getAttribute("name").getValue().equals("Value")){
                        
                        for (Country c : list) {
                                if (name.equals(c.getName())) {
                                    c.setPopulation(Long.parseLong(field.getContent(0).getValue()));
                                }
                        }
                    }
                }
            }
            }
            
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(CountryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     private static void readGdpFile(String gdpFile, List<Country> list){
        try {
            File file3 = new File(gdpFile);
            SAXBuilder sax = new SAXBuilder();
            Document doc3 = sax.build(file3);
            Element root = doc3.getRootElement();
            List<Element> datas = root.getChildren("data");
            for (Element data : datas) {

                List<Element> records = data.getChildren("record");
                for (Element record : records) {

                    List<Element> fields = record.getChildren();
                    String name = " ";

                    for (Element field : fields) {

                        if (field.getAttribute("name").getValue().equals("Country or Area")) {
                            boolean exists = false;
                            name = field.getContent(0).getValue();
                            for (Country c : list) {
                                if (name.equals(c.getName())) {
                                    exists = true;
                                    break;
                                }
                            }
                            if (!exists) {
                                list.add(new Country(name));
                            }
                        }
                        if (field.getAttribute("name").getValue().equals("Value")) {

                            for (Country c : list) {

                                if (name.equals(c.getName())) {
                                    c.setGDP(Double.parseDouble(field.getContent(0).getValue()));
                                }

                            }
                        }
                    }
                }
            }           
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(CountryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void writeToXml(List<Country> countries,
                                String countryFile){
        try {
            SAXBuilder sax = new SAXBuilder();
            Document doc = sax.build(new File(countryFile));
            doc.setRootElement(new Element("countries"));
            for(Country c: countries){
                Element country = new Element("country");
                country.addContent(new Element("name").setText(c.getName()));
                country.addContent(new Element("area").setText("" + c.getArea()));
                country.addContent(new Element("population").setText("" + c.getPopulation()));
                country.addContent(new Element("gdp").setText("" + c.getGdp()));
                doc.getRootElement().addContent(country);
            }
            XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
            xout.output(doc, System.out);
            
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(CountryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
