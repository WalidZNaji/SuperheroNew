import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    private Database database;



    Controller() {
        this.database = new Database();

    }

    public void addSuperhero(String name, String realName, String superpower, int age, int strength, boolean isHuman) {
        database.superheroList.add(new Superhero(name, realName, superpower, age, strength, isHuman));
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

    public void loadFromCSV(){
        database.loadFromCSV();

    }
    public void addSuperheroFromCSV(ArrayList<Superhero> heroListFromCSV){
        database.superheroList.clear();
        database.addSuperheroList(heroListFromCSV);
    }

    /*public ArrayList<Superhero> getSuperheroListFromCSV() {
    return database.getSuperheroListFromCSV();
    }*/

}
