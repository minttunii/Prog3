
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;


public class MovieAnalytics2 {
    private ArrayList<Movie> movies;
 
    public MovieAnalytics2() {
        this.movies = new ArrayList<>();
    }
    
    public void populateWithData(String fileName) throws FileNotFoundException, IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
           movies = reader.lines()
                   .map(line -> line.split(";"))
                   .map(parts -> new Movie(parts[0], Integer.parseInt(parts[1]),
                                    Integer.parseInt(parts[2]), parts[3], 
                                    Double.parseDouble(parts[4]), parts[5]))
                   .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        }
    }
    
    public void printCountByDirector(int n){
        // Collect directors and their occurencies into a map
        var x = movies.stream().collect(Collectors.groupingBy(Movie::getDirector,
                Collectors.collectingAndThen(Collectors.counting(), f -> f.intValue())));
        // Sort map first by value in decending orger the by name
        // Only n first items are printed
        x.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey())).limit(n).
                forEach(a -> System.out.format("%s: %d movies%n", a.getKey(), a.getValue()));
        
    }
    
    public void printAverageDurationByGenre(){
        // Collect all genres and their movies average duration into a map
        var x = movies.stream().collect(Collectors.groupingBy(Movie::getGenre,
                Collectors.averagingInt(Movie::getDuration)));
        // Sort map by average and then by genre
        x.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue()
                .thenComparing(Map.Entry.comparingByKey())).
                forEach(a -> System.out.format("%s: %.2f%n", a.getKey(), a.getValue()));
        
  
    }
    
    public void printAverageScoreByGenre(){
        // Collect all genres and their average score into a map
        var x = movies.stream().collect(Collectors.groupingBy(Movie::getGenre,
                Collectors.averagingDouble(Movie::getScore)));
        // Sort map by average then by genre
        x.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey())).
                forEach(a -> System.out.format("%s: %.2f%n", a.getKey(), a.getValue()));
        
    }
    
}
