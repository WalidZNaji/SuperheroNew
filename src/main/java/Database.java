import java.util.ArrayList;

public class Database {

    Filehandler filehandler = new Filehandler();

    ArrayList<Superhero> superheroList = getSuperheroListFromCSV();

    private boolean dataChanged = false;

   public ArrayList<Superhero> getSuperheroListFromCSV (){
        return filehandler.getSuperheroesInCSVList();
    }

    public Database() {
        this.superheroList = new ArrayList<>();
    }

    public void addSuperhero(String name, String realName, String superpower, int age, int strength, boolean isHuman) {
        superheroList.add(new Superhero(name, realName, superpower, age, strength, isHuman));
        dataChanged=true;
    }
    public void addSuperheroList(ArrayList<Superhero> heroListFromCSV) {
        superheroList.clear();
        superheroList.addAll(heroListFromCSV);
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
        }

    public ArrayList<Superhero> getSuperheroList() {

        return superheroList;
    }

    //csv
    public void saveToCSV (ArrayList<Superhero> superHeroListe) {
            filehandler.saveSuperHeroToCSV(superHeroListe);
            dataChanged = false;
    }
    public void loadFromCSV(){
        superheroList = filehandler.loadSuperheroesFromCSV();
    }


    }
