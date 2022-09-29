
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class WordGame {
    private WordGameState gamestate;
    private final ArrayList<String> game_words;
    private ArrayList<Character> guessed_chars = new ArrayList<>();
    private String word_to_guess;
    
    public WordGame(String wordFilename) {
        ArrayList<String> game_words = new ArrayList<>();
        try{
            Scanner myReader = new Scanner(new File(wordFilename));
            while(myReader.hasNextLine()){
                String word = myReader.nextLine();
                game_words.add(word);
            }
            myReader.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        } 
        this.game_words = game_words;
    }
    
    public void initGame(int wordIndex, int mistakeLimit){
        this.gamestate = new WordGameState();
        
        int N = game_words.size(); // Number of words
        String word = game_words.get(wordIndex%N);
        this.word_to_guess = word;
        
        //Add the word as it is represented to the player
        gamestate.word_guess = "_".repeat(word.length());
        gamestate.mistake_limit = mistakeLimit;
        gamestate.missing_chars = word.length();
    }
    
    public boolean isGameActive(){
        return gamestate != null;
    }
    
    public WordGameState getGameState() throws GameStateException{
        if(gamestate == null){
            throw new GameStateException("There is currently no active word game!");
        }
        return gamestate; 
    }
    
    public WordGameState guess(char c) throws GameStateException{
        if(gamestate == null){
            throw new GameStateException("There is currently no active word game!");
        }
        boolean is_char_in_word = false;
        char c2 = Character.toLowerCase(c); 
        char[] word_guess = gamestate.word_guess.toCharArray();
        
        // If char has already been guessed
        if(guessed_chars.contains(c2)){
            gamestate.mistakes += 1;
            return gamestate;
        }
        for(int i = 0; i < word_to_guess.length(); i++){
            if(word_to_guess.charAt(i) == c2){
                is_char_in_word = true;
                guessed_chars.add(c2);
                word_guess[i] = c2;
                gamestate.missing_chars -= 1;
            }
        }
        if(!is_char_in_word){  
            if(gamestate.mistakes == gamestate.mistake_limit){
                gamestate.word_guess = word_to_guess;
                gamestate.mistakes += 1;  
            }
            else{
               gamestate.mistakes += 1;   
            }
        }
        else if(is_char_in_word){
            gamestate.word_guess = String.valueOf(word_guess);
        }
        return gamestate;
    }
    
    public WordGameState guess(String word) throws GameStateException{
        if(gamestate == null){
            throw new GameStateException("There is currently no active word game!");
        }
        
        if(word.equals(word_to_guess)){
            gamestate.word_guess = word;
            gamestate.missing_chars = 0;
            gamestate = null;
        }
        else{    
            if(gamestate.mistakes == gamestate.mistake_limit){ 
                gamestate.word_guess = word_to_guess;
                gamestate.mistakes += 1;
            }
            else{
                gamestate.mistakes += 1;
            }
        }
        return gamestate;
    }
    

    public static class WordGameState {
        private String word_guess = " ";
        private int mistakes = 0;
        private int mistake_limit = 0;
        private int missing_chars = 0;
        
        public String getWord(){
            return word_guess;
        }
        
        public int getMistakes(){
            return mistakes;
        }
        
        public int getMistakeLimit(){
            return mistake_limit;
        }
        
        public int getMissingChars(){
            return missing_chars;
        }
    }
    
}
