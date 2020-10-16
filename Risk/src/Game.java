import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    final ArrayList<Player> players;
    Player currentPlayer;

    private static final int BEGINNING_TROOPS = 20;

    /**
     * Default constructor for game.
     */
    public Game() {
        currentPlayer = null;
        players = new ArrayList<>();
    }

    public Game(ArrayList<Player> players) {
        currentPlayer = null;
        this.players = players;
    }

    /**
     * Generates a game as long as two players are present.
     */
    private void generateGame() {
        //GetMap
        if(players.size() < 2){
            System.out.println("Add players to start a game");
            return;
        }
        //Choose a random player to start the game
        currentPlayer = players.get(ThreadLocalRandom.current().nextInt(0, players.size()));
        ArrayList<Country> map = generateMap();

        //Randomly Assign countries
        Country randomCountry;
        while(map.size()>0){
            //Find a random Country
            randomCountry = map.get(ThreadLocalRandom.current().nextInt(0,map.size()));
            //Give it to a player
            currentPlayer.addCountry(randomCountry);
            //Remove it from the available countries
            map.remove(randomCountry);
            //Go to the next player
            nextPlayer();
        }

        //Randomly Assign troops to countries

        for (Player player:players) {
            ArrayList<Country> countryArrayList= player.countries;
            //To stop to many troops from being assigned to a single country we set a max number of troops on one country
            //The maximum should be at least 4
            int maxTroops = Math.max(BEGINNING_TROOPS/countryArrayList.size() + 2, 4);
            int random;
            for(int assigned = countryArrayList.size(); assigned<BEGINNING_TROOPS; assigned++){
                random = ThreadLocalRandom.current().nextInt(0,countryArrayList.size());
                if(countryArrayList.get(random).getTroops()<maxTroops){
                    countryArrayList.get(random).addTroop(1);
                    assigned++;
                }
            }
        }
        printState();
    }



    public void addPlayer(Player player){
        players.add(player);
    }

    public void nextPlayer(){
        if(players.indexOf(currentPlayer) != players.size() -1){
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        }else{
            currentPlayer = players.get(0);
        }

    }


    public void printState(){
        for(Player player: players){
            player.print();
        }
    }

    /**
     * TEST ONLY
     * Temporary test function
     * TODO: Replace this with the real map function
     * @return a list of  countries used for testing ONLY.
     */
    private ArrayList<Country> generateMap(){
        ArrayList<Country> map = new ArrayList<>();
        map.add(new Country("HELLO"));
        map.add(new Country("second"));
        map.add(new Country("third"));

        return map;
    }


    public static void  main(String[] args){
        Game test = new Game();
        test.addPlayer(new Player("Player1"));
        test.addPlayer(new Player("Player2"));
        test.generateGame();

    }


}
