
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class MovieAnalytics {
    private ArrayList<Movie> movies;
    private Comparator<Movie> comparator = 
            Comparator.comparing(Movie::getReleaseYear).thenComparing(Movie::getTitle);

    public MovieAnalytics() {
        this.movies = new ArrayList<>();
    }
    
    public static Consumer<Movie> showInfo(){
        Consumer<Movie> display = m -> System.out.format("%s (By %s, %d)%n",
                                    m.getTitle(), m.getDirector(), m.getReleaseYear());
        return display;
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
    
    public Stream<Movie> moviesAfter(int year){
        Movie[] movielist = {};
        ArrayList<Movie> moviearr = new ArrayList<>();
        for(Movie m : movies){
            if(m.getReleaseYear() >= year){
                moviearr.add(m);
            }
        }
        movielist = moviearr.toArray(movielist);
        return Arrays.stream(movielist).sorted(comparator);
    }
    
    public Stream<Movie> moviesBefore(int year){
        Movie[] movielist = {};
        ArrayList<Movie> moviearr = new ArrayList<>();
        for(Movie m : movies){
            if(m.getReleaseYear() <= year){
                moviearr.add(m);
            }
        }
        movielist = moviearr.toArray(movielist);
        return Arrays.stream(movielist).sorted(comparator);
    }
    
    public Stream<Movie> moviesBetween(int yearA, int yearB){
        Movie[] movielist = {};
        ArrayList<Movie> moviearr = new ArrayList<>();
        for(Movie m : movies){
            if(m.getReleaseYear() >= yearA && m.getReleaseYear() <= yearB){
                moviearr.add(m);
            }
        }
        movielist = moviearr.toArray(movielist);
        return Arrays.stream(movielist).sorted(comparator);
    }
    
    public Stream<Movie> moviesByDirector(String director){
        Movie[] movielist = {};
        ArrayList<Movie> moviearr = new ArrayList<>();
        for(Movie m : movies){
            if(m.getDirector().equals(director)){
                moviearr.add(m);
            }
        }
        movielist = moviearr.toArray(movielist);
        return Arrays.stream(movielist).sorted(comparator);
    }
}
