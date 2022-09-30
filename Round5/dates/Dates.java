
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.time.temporal.ChronoUnit;

public class Dates{
    
    public Dates() {
    }

    public static class DateDiff {
        private String start;
        private String end;
        private int diff;
  
        private DateDiff(String start, String end, int daydiff) {
            this.start = start;
            this.end = end;
            this.diff = daydiff;    
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }

        public int getDiff() {
            return diff;
        }
        
        private String changeString(String s){
            return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
        }
        
        public String toString(){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyy");
            // Convert string start and end to LocalDate objects
            LocalDate startdate = LocalDate.parse(start);
            LocalDate enddate = LocalDate.parse(end);
            //Format day string with formatter
            String start_f = startdate.format(formatter);
            String end_f = enddate.format(formatter);
            String startweekday = changeString(startdate.getDayOfWeek().toString());
            String endweekday = changeString(enddate.getDayOfWeek().toString());
            
            return String.format("%s %s --> %s %s: %d days", 
                startweekday, start_f, endweekday, end_f, diff) ;
        }
         catch(DateTimeException e){
            System.out.println("DateTimeException found");
        }
            return null;
        }

    }
    
    public static DateDiff[] dateDiffs(String ...dateStrs){
        if(dateStrs.length < 2){
            return new DateDiff[0];
        }     
        else{
            ArrayList<LocalDate> valid_dates = new  ArrayList<>();
            int MIN_YEARS = 1000; int MAX_YEARS = 9999;
                
            for (String dateStr : dateStrs) {
                String delimiter = "\\.";
                // If day is in for day.month.year
                String[] dateparts = dateStr.split("\\.");
                // If day is in form year-day-month
                String[] dateparts2 = {};
                if (dateparts.length == 1) {
                    dateparts2 = dateStr.split("-");
                    delimiter = "-";
                }
                try {
                    if("-".equals(delimiter)){
          
                        LocalDate localdate2 = LocalDate.of(Integer.parseInt(dateparts2[0]),
                            Integer.parseInt(dateparts2[1]), Integer.parseInt(dateparts2[2]));
                        
                        if(Integer.parseInt(dateparts2[0]) < MIN_YEARS ||
                                Integer.parseInt(dateparts2[0]) > MAX_YEARS
                                || dateparts2[1].length() < 2 || dateparts2[2].length()< 2){
                            throw new DateTimeException("The date \"" + dateStr + "\" is illegal!");
                        }
                        valid_dates.add(localdate2);                     
                    }
                    else{ 
                        if(Integer.parseInt(dateparts[2]) < MIN_YEARS || Integer.parseInt(dateparts[2]) > MAX_YEARS){
                            throw new DateTimeException("The date \"" + dateStr + "\" is illegal!");
                        }
                        LocalDate localdate = LocalDate.of(Integer.parseInt(dateparts[2]),
                            Integer.parseInt(dateparts[1]), Integer.parseInt(dateparts[0]));
                        valid_dates.add(localdate);
                    } 
                } catch (DateTimeException e) {
                        System.out.format("The date \"%s\" is illegal!%n", dateStr);
                }
            }
            
            Collections.sort(valid_dates);
            ArrayList<DateDiff> diff_array = new ArrayList<>();
            DateDiff[] datediffs = {};
            for(int i = 0; i < valid_dates.size()-1; ++i){
                long daydiff = valid_dates.get(i).until(valid_dates.get(i+1), ChronoUnit.DAYS);
                diff_array.add(new DateDiff(valid_dates.get(i).toString(),
                        valid_dates.get(i+1).toString(), (int)daydiff));
            }
            datediffs = diff_array.toArray(datediffs);
            return datediffs;
        }     
    }
}
  