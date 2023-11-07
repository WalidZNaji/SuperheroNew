import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Filehandler {
    PrintStream fileWriter;

    public ArrayList<Superhero> getSuperheroesInCVSList() {
        return superheroesInCVSList;
    }

    ArrayList<Superhero> superheroesInCVSList = new ArrayList<>();

    File file = new File("Superheroes.cvs");

        public void saveSuperHeroToCVS (ArrayList<Superhero> superheroList) {
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

        public ArrayList<Superhero> loadSuperheroesFromCVS() {
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

                    superheroesInCVSList.add(superhero);

                } fileReader.close();
                return superheroesInCVSList;

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);

            }


        }

    }
