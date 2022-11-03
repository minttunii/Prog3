
package fi.tuni.prog3.standings;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * A class for maintaining team statistics and standings. 
 * Team standings are determined by the following rules: 
 * <ul>
 *      <li>Primary rule: points total. Higher points come first.</li>
 *      <li>Secondary rule: goal difference (scored minus allowed). Higher difference comes first.</li>
 *      <li>Tertiary rule: number of goals scored. Higher number comes first.</li>
 *      <li>Last rule: natural String order of team names.</li>
 * </ul>
 */

public class Standings {
    private final HashMap<String, Team> Teams;
    
    /**
     * A class for storing statistics of a single team. The class offers only 
     * public getter functions. The enclosing class Standings is responsible for
     * setting and updating team statistics.
     */
    public static class Team{
        private final String name;
        private int played_games = 0;
        private int wins = 0;
        private int ties = 0;
        private int losses = 0;
        private int scored = 0;
        private int allowed = 0;
        private int points = 0;
    
    /**
     * Constructs a Team object for storing statistics of the named team.
     * @param name - the name of the team whose statistics the new team object stores.
     */
    public Team(String name){this.name = name;}
    
    /**
     * Returns the name of the team.
     * @return the name of the team.
     */
    public String getName(){return name;}
    
    /**
     * Returns the number of wins of the team.
     * @return he number of wins of the team.
     */
    public int getWins(){return wins;}
    
    /**
     * Returns the number of ties of the team.
     * @return the number of ties of the team.
     */
    public int getTies(){return ties;}
    
    /**
     * Returns the number of losses of the team.
     * @return the number of losses of the team.
     */
    public int getLosses(){return losses;}
    
    /**
     * Returns the number of goals scored by the team.
     * @return the number of goals scored by the team.
     */
    public int getScored(){return scored;}
    
    /**
     * Returns the number of goals allowed (conceded) by the team.
     * @return the number of goals allowed (conceded) by the team.
     */
    public int getAllowed(){return allowed;}
    
    /**
     * Returns the overall number of points of the team.
     * @return the overall number of points of the team.
     */
    public int getPoints(){return points;}
    
    private void print(int longestName) {
        int teamNameLength = this.name.length();
        String j = scored +"-"+ allowed;
        System.out.format("%s %"+(longestName-teamNameLength+3)+"d %3d %3d %3d %6s %3d%n",
                this.name, this.played_games, this.wins, this.ties, this.losses, j, this.points);
        }
    }
    
    /**
     * Constructs an empty Standings object.
     */ 
    public Standings(){
        this.Teams = new HashMap<>();
    }
    
    /**
     * Constructs a Standings object that is initialized with the game data 
     * read from the specified file. The result is identical to first 
     * constructing an empty Standing object and then calling
     * {@link #readMatchData(java.lang.String) readMatchData(filename)}.
     * @param filename - the name of the game data file to read.
     * @throws IOException - if there is some kind of an IO error (e.g. if the
     * specified file does not exist).
     */
    public Standings(String filename) throws IOException{
        this.Teams = new HashMap<>();
        this.readMatchData(filename);
    }
    
    /**
     * Reads game data from the specified file and updates the team statistics
     * and standings accordingly. 
     * <p>
     * The match data file is expected to contain lines of form
     * "teamNameA\tgoalsA-goalsB\tteamNameB". Note that the '\t' are tabulator
     * characters.
     * </p>
     * <p>
     * E.g. the line "Iceland\t3-2\tFinland" would describe a match between 
     * Iceland and Finland where Iceland scored 3 and Finland 2 goals.
     * </p>
     * @param filename - the name of the game data file to read.
     * @throws IOException - if there is some kind of an IO error (e.g. if the specified file does not exist).
     */
    public final void readMatchData(String filename) throws IOException{
        try{
            Scanner myReader = new Scanner(new File(filename));
            while(myReader.hasNextLine()){
                String line =  myReader.nextLine();
                String[] splitline = line.split("\t");
                String[] goals = splitline[1].split("-");
                    
                // If teams are new
                Teams.computeIfAbsent(splitline[0], tn -> new Team(splitline[0]));
                Teams.computeIfAbsent(splitline[2], tn -> new Team(splitline[2]));
                    
                // Add points to teams
                this.addMatchResult(splitline[0], Integer.parseInt(goals[0]),
                Integer.parseInt(goals[1]), splitline[2]);  
                }
            myReader.close();
            }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        
    }
    
    /**
     * Updates the team statistics and standings according to the match result 
     * described by the parameters.
     * @param teamNameA - the name of the first ("home") team.
     * @param goalsA - the number of goals scored by the first team.
     * @param goalsB - the number of goals scored by the second team.
     * @param teamNameB - the name of the second ("away") team.
     */
    public void addMatchResult(String teamNameA, int goalsA, int goalsB,
            String teamNameB){
        Teams.get(teamNameA).played_games += 1;
        Teams.get(teamNameB).played_games += 1;
        Teams.get(teamNameA).scored += goalsA;
        Teams.get(teamNameB).scored += goalsB;
        Teams.get(teamNameA).allowed += goalsB;
        Teams.get(teamNameB).allowed += goalsA;
            
        if(goalsA > goalsB){
            Teams.get(teamNameA).wins += 1;
            Teams.get(teamNameA).points += 3;
            Teams.get(teamNameB).losses += 1;
        }
        else if(goalsB > goalsA){
            Teams.get(teamNameB).wins += 1;
            Teams.get(teamNameB).points += 3;
            Teams.get(teamNameA).losses += 1;
        }
        else{
            Teams.get(teamNameA).ties += 1;
            Teams.get(teamNameA).points += 1;
            Teams.get(teamNameB).ties += 1;
            Teams.get(teamNameB).points += 1;
        }
    }
    
    /**
     * Returns a list of the teams in the same order as they would appear in a standings table.
     * @return a list of the teams in the same order as they would appear in a standings table.
     */
    public List<Team> getTeams(){
        ArrayList<Team> teamlist = new ArrayList<>();
        for (String key : Teams.keySet()){
            teamlist.add(Teams.get(key));
        }
        
        teamlist.sort((team1, team2) -> {

            if (team1.getPoints() < team2.getPoints()) {
                return 1;
            } 
            else if (team1.getPoints() > team2.getPoints()) {
                return -1;
            } 
            else {
                if (team1.getScored() - team1.getAllowed() < team2.getScored() - team2.getAllowed()) {
                    return 1;
                } 
                else if (team1.getScored() - team1.getAllowed() > team2.getScored() - team2.getAllowed()) {
                    return -1;
                } 
                else {
                    if (team1.getScored() < team2.getScored()) {
                        return 1;
                    } 
                    else if (team1.getScored() > team2.getScored()) {
                        return -1;
                    } 
                    else {
                        return team1.getName().compareTo(team2.getName());
                    }
                }
            }
        });
        return teamlist;
    }
    
    /**
     * Prints a formatted standings table to the provided output stream.
     * @param out - the output stream to use when printing the standings table.
     */
    public void printStandings(PrintStream out){
        List<Team> teamlist = this.getTeams();
        int longestname = 0;
        for(String teamname: Teams.keySet()){
            if(teamname.length() > longestname){
                longestname = teamname.length();
            }
        }
        
        for(Team team : teamlist){
            team.print(longestname);
        }
        
    }
}
