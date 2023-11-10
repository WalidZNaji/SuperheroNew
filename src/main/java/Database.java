import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Database {

    Filehandler filehandler = new Filehandler();

    ArrayList<Superhero> superheroList = getSuperheroListFromCSV();

    private boolean dataChanged = false;

    public ArrayList<Superhero> getSuperheroListFromCSV() {
        return filehandler.getSuperheroesInCSVList();
    }

    public Database() {
        this.superheroList = new ArrayList<>();
    }

    public void addSuperhero(String name, String realName, String superpower, int age, int strength, boolean isHuman) {
        superheroList.add(new Superhero(name, realName, superpower, age, strength, isHuman));
        dataChanged = true;
    }

    public void addSuperheroList(ArrayList<Superhero> heroListFromCSV) {
        superheroList.clear();
        superheroList.addAll(heroListFromCSV);
        dataChanged = true;
    }

    public void deleteSuperhero(String superheroName) {

        ArrayList<Superhero> superheroesToRemove = new ArrayList<>();

        for (Superhero superhero : superheroList) {
            if (superhero.getName().toLowerCase().contains(superheroName.toLowerCase())) {
                superheroesToRemove.add(superhero);
            }
        }
        dataChanged = true;
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
    public void saveToCSV(ArrayList<Superhero> superHeroListe) {

        if (dataChanged) {
            filehandler.saveSuperHeroToCSV(superHeroListe);
            dataChanged = false;
        }
    }

    public void loadFromCSV() {
        superheroList = filehandler.loadSuperheroesFromCSV();
    }

    public ArrayList<Superhero> getSortedSuperheroesOneAttribute(int sortingchoice) {
        ArrayList<Superhero> superheroesOneAttribute = new ArrayList<>(superheroList);

        switch (sortingchoice) {
            case 1 -> Collections.sort(superheroesOneAttribute, Comparator.comparing(Superhero::getName));
            case 2 -> Collections.sort(superheroesOneAttribute, Comparator.comparing(Superhero::getRealName));
            case 3 -> Collections.sort(superheroesOneAttribute, Comparator.comparing(Superhero::getSuperpower));
            case 4 -> Collections.sort(superheroesOneAttribute, Comparator.comparing(Superhero::getAge));
            case 5 -> Collections.sort(superheroesOneAttribute, Comparator.comparing(Superhero::getStrength));
            case 6 -> Collections.sort(superheroesOneAttribute, Comparator.comparing(Superhero::isHuman));
            default -> {
                System.out.println("Invalid primary sorting choice. Defaulting to sorting by name.");
                Collections.sort(superheroesOneAttribute, Comparator.comparing(Superhero::getName));
            }
            }
            return superheroesOneAttribute;
        }

        public ArrayList<Superhero> getSortedSuperheroes ( int primarySortingChoice, int secondarySortingChoice){
            ArrayList<Superhero> superheroes = new ArrayList<>(superheroList);

            if (primarySortingChoice > 0) {
                switch (primarySortingChoice) {
                    case 1 -> Collections.sort(superheroes, Comparator.comparing(Superhero::getName));
                    case 2 -> Collections.sort(superheroes, Comparator.comparing(Superhero::getRealName));
                    case 3 -> Collections.sort(superheroes, Comparator.comparing(Superhero::getSuperpower));
                    case 4 -> Collections.sort(superheroes, Comparator.comparing(Superhero::getAge));
                    case 5 -> Collections.sort(superheroes, Comparator.comparing(Superhero::getStrength));
                    case 6 -> Collections.sort(superheroes, Comparator.comparing(Superhero::isHuman));
                    default -> {
                        System.out.println("Invalid primary sorting choice. Defaulting to sorting by name.");
                        Collections.sort(superheroes, Comparator.comparing(Superhero::getName));
                    }
                }

                if (secondarySortingChoice == 0) {
                    switch (secondarySortingChoice) {
                        case 1 -> Collections.sort(superheroes, Comparator.comparing(Superhero::getName));
                        case 2 -> Collections.sort(superheroes, Comparator.comparing(Superhero::getRealName));
                        case 3 -> Collections.sort(superheroes, Comparator.comparing(Superhero::getSuperpower));
                        case 4 -> Collections.sort(superheroes, Comparator.comparing(Superhero::getAge));
                        case 5 -> Collections.sort(superheroes, Comparator.comparing(Superhero::getStrength));
                        case 6 -> Collections.sort(superheroes, Comparator.comparing(Superhero::isHuman));
                        default -> System.out.println("Invalid secondary sorting choice. Ignoring secondary sorting.");
                    }
                }
            }
            return superheroes;
        }
    }
