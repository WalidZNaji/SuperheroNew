import java.util.ArrayList;

public class Controller {

    private Database database;



    public Controller() {
        this.database = new Database();

    }

    public void addSuperhero(String name, String realName, String superpower, int age, int strength, boolean isHuman) {
        database.addSuperhero(name,realName,superpower, age, strength, isHuman);
    }

    public void deleteSuperhero(String superheroName) {
        database.deleteSuperhero(superheroName);
    }


    public Superhero getSuperhero(int index) {
        return database.superheroList.get(index);
    }

    public ArrayList<Superhero> findSuperhero(String superheroName) {
        return database.findSuperhero(superheroName);
        }

    public ArrayList<Superhero> getSuperheroList() {
        return database.superheroList;
    }
    public void saveToCSV (ArrayList<Superhero> superHeroListe){
        database.saveToCSV(superHeroListe);
    }

    public ArrayList<Superhero> loadFromCSV(){
        database.loadFromCSV();
        return null;
    }

    public ArrayList<Superhero> getSortedHeroes(int primaryAttribute, int secondaryAttribute) {
        return database.getSortedSuperheroes(primaryAttribute, secondaryAttribute);
    }
    public ArrayList<Superhero> getSortedHeroesOneAttribute(int sortingChoice){
        return database.getSortedSuperheroesOneAttribute(sortingChoice);
    }
}
