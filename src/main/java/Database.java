import java.util.ArrayList;
import java.util.Collection;

public class Database {

    Filehandler filehandler = new Filehandler();

    ArrayList<Superhero> superheroList = getSuperheroListFromCVS();

    private boolean dataChanged = false;

   public ArrayList<Superhero> getSuperheroListFromCVS (){
        return filehandler.getSuperheroesInCVSList();
    }

    public Database() {
        this.superheroList = new ArrayList<>();
    }

    public void addSuperhero(String name, String realName, String superpower, int age, int strength, boolean isHuman) {
        superheroList.add(new Superhero(name, realName, superpower, age, strength, isHuman));
        dataChanged=true;
    }
    public void addSuperheroList(ArrayList<Superhero> heroListFromCVS) {
        superheroList.clear();
        superheroList.addAll(heroListFromCVS);
        dataChanged=true;
    }

    public void deleteSuperhero(String superheroName) {
        ArrayList<Superhero> superheroesToRemove = new ArrayList<>();

        for (Superhero superhero : superheroList) {
            if (superhero.getName().toLowerCase().contains(superheroName.toLowerCase())) {
                superheroesToRemove.add(superhero);
            }
        }
        dataChanged=true;
        superheroList.removeAll(superheroesToRemove);
    }


    public Superhero getSuperhero(int index) {
        return superheroList.get(index);
    }

    public ArrayList<Superhero> findSuperhero(String superheroName) {
        ArrayList<Superhero> superheroes = new ArrayList<>();

        for (Superhero superhero : superheroList) {
            if (superhero.getName().toLowerCase().contains(superheroName.toLowerCase()))
                superheroes.add(superhero);
            }
            return superheroes;
/*
                if (!superheroName.contains(superhero.getName())) {
                    superheroes.add(superhero);
                }

 */
        }

    public ArrayList<Superhero> getSuperheroList() {

        return superheroList;
    }

    //cvs
    public void saveToCVS (ArrayList<Superhero> superHeroListe) {

        if (dataChanged) {
            filehandler.saveSuperHeroToCVS(superHeroListe);
            dataChanged = false;
        }
    }
    public void loadFromCVS(){
        superheroList = filehandler.loadSuperheroesFromCVS();
    }


    }
