import java.util.ArrayList;

public class Controller {

    private Database database;



    Controller() {
        this.database = new Database();

    }

    public void addSuperhero(String name, String realName, String superpower, int age, int strength, boolean isHuman) {
        database.superheroList.add(new Superhero(name, realName, superpower, age, strength, isHuman));
    }

    public void deleteSuperhero(String superheroName) {
        ArrayList<Superhero> superheroesToRemove = new ArrayList<>();

        for (Superhero superhero : database.superheroList) {
            if (superhero.getName().toLowerCase().contains(superheroName.toLowerCase())) {
                superheroesToRemove.add(superhero);
            }
        }
        database.superheroList.removeAll(superheroesToRemove);
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
}
