import java.util.ArrayList;
import java.util.Collection;

public class Database {

    Filehandler filehandler = new Filehandler();

    ArrayList<Superhero> superheroList = getSuperheroListFromCVS();

   public ArrayList<Superhero> getSuperheroListFromCVS (){
        return filehandler.getSuperheroesInCVSList();
    }

    public Database() {
        this.superheroList = new ArrayList<>();
    }

    public void addSuperhero(String name, String realName, String superpower, int age, int strength, boolean isHuman) {
        superheroList.add(new Superhero(name, realName, superpower, age, strength, isHuman));
    }
    public void addSuperheroList(ArrayList<Superhero> heroListFromCVS) {
        superheroList.clear();
        superheroList.addAll(heroListFromCVS);
    }

    public void deleteSuperhero(String superheroName) {
        ArrayList<Superhero> superheroesToRemove = new ArrayList<>();

        for (Superhero superhero : superheroList) {
            if (superhero.getName().toLowerCase().contains(superheroName.toLowerCase())) {
                superheroesToRemove.add(superhero);
            }
        }
        superheroList.removeAll(superheroesToRemove);
    }


    public Superhero getSuperhero(int index) {
        return superheroList.get(index);
    }

    public ArrayList<Superhero> findSuperhero(String superheroName) {
        ArrayList<Superhero> superheroes = new ArrayList<>();

        for (Superhero superhero : superheroList) {
            if (superhero.getName().toLowerCase().contains(superheroName.toLowerCase())) {

                if (!superheroName.contains(superhero.getName())) {
                    superheroes.add(superhero);
                }
            }
        } return superheroes;
    }

    public ArrayList<Superhero> getSuperheroList() {

        return superheroList;
    }

    //cvs
    public void saveToCVS (ArrayList<Superhero> superHeroListe){
        filehandler.saveSuperHeroToCVS(superHeroListe);
    }

    public void loadFromCVS(){
        superheroList = filehandler.loadSuperheroesFromCVS();
    }


    }
