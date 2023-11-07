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
        ArrayList<Superhero> superheroes = new ArrayList<>();

        for (Superhero superhero : database.superheroList) {
            if (superhero.getName().toLowerCase().contains(superheroName.toLowerCase())) {

                if (!superheroName.contains(superhero.getName())) {
                    superheroes.add(superhero);
                }
            }
        } return superheroes;
    }

    public ArrayList<Superhero> getSuperheroList() {
        return database.superheroList;
    }
    public void saveToCVS (ArrayList<Superhero> superHeroListe){
        database.saveToCVS(superHeroListe);
    }

    public void loadFromCVS(){
        database.loadFromCVS();

    }
    public void addSuperheroFromCVS(ArrayList<Superhero> heroListFromCVS){
        database.superheroList.clear();
        database.addSuperheroList(heroListFromCVS);
    }

    /*public ArrayList<Superhero> getSuperheroListFromCVS() {
    return database.getSuperheroListFromCVS();
    }*/


}
