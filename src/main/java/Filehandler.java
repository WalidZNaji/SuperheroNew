import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Filehandler {
    PrintStream fileWriter;

    public ArrayList<Superhero> getSuperheroesInCSVList() {
        return superheroesInCSVList;
    }

    ArrayList<Superhero> superheroesInCSVList = new ArrayList<>();

    File file = new File("Superheroes.csv");

        public void saveSuperHeroToCSV (ArrayList<Superhero> superheroList) {
            try {
                fileWriter = new PrintStream(file);
                for (Superhero superhero : superheroList) {
                   String linje = superhero.getName() + "," +  superhero.getRealName() + "," +
                           superhero.getSuperpower() + "," + superhero.getAge() + "," + superhero.getStrength() +
                           "," + superhero.isHuman();
                    fileWriter.println(linje);
                }
                fileWriter.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public ArrayList<Superhero> loadSuperheroesFromCSV() {
            try {
                Scanner fileReader = new Scanner(file);
                fileReader.nextLine(); // Header
                while (fileReader.hasNext()) {
                    String linje = fileReader.nextLine();
                    String[] attributes = linje.split(",");

                    Superhero superhero = new Superhero(
                            (attributes[0].trim()),
                            (attributes[1].trim()),
                            (attributes[2].trim()),
                            Integer.parseInt(attributes[3].trim()),
                            Integer.parseInt(attributes[4].trim()),
                            Boolean.parseBoolean(attributes[5].trim()));

                    superheroesInCSVList.add(superhero);

                } fileReader.close();
                return superheroesInCSVList;

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);

            }


        }

    }
