import java.util.ArrayList;

public class Player {
    public final ArrayList<Country> countries;
    private final String name;

    public Player(String name) {
        this.name = name;
        countries = new ArrayList<>();
    }

    public void addCountry(Country country){
        countries.add(country);
    }

    public void print(){
        System.out.println("[" + name + "]");
        for (Country country: countries) {
            System.out.println(country.toString() + "  Troops: " + country.getTroops());
        }
    }
}