import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// TODO Fjern premade, lav vis super til at loade fra cvs med det samme.
public class UserInterface {

    private final Controller controller;
    private final Scanner scan;

    public UserInterface() {

        controller = new Controller();
        scan = new Scanner(System.in);

    }

    public void startProgram() {
        loadHeroesFromCVS();

        String runAgain = "y";

        printWelcomeMessage();

        while (runAgain.equalsIgnoreCase("y")) {

            printStartMenu();

            while (!scan.hasNextInt()) {
                scan.nextLine();
                System.out.print("Indtast et tal mellem 1-9: ");
            }

            int startInput = scan.nextInt();

            if (startInput > 9) {
                System.out.println("Indtast et tal mellem 1-9: ");
            }

            if (startInput == (1)) {

                createSuperhero();

                backToMenuMessage();

                runAgain = scan.next();

            } else if (startInput == 2) {

                showSuperheroList();

                backToMenuMessage();

                runAgain = scan.next();

            } else if (startInput == 3) {
                searchForSuperhero();

                backToMenuMessage();

                runAgain = scan.next();

            } else if (startInput == 4) {
                findAndEditSuperhero();

                backToMenuMessage();

            } else if (startInput == 5) {
                deleteSuperhero();

                backToMenuMessage();

                runAgain = scan.next();

            } else if (startInput == 6) {

                saveHeroToCVS();
                System.out.println("Superheltene er nu gemt til cvs filen");

            }else if (startInput==7){

                System.out.println("Superheltene er nu loaded");
                loadHeroesFromCVS();

            }
            else if (startInput == 9) {
                runAgain = "n";
            }
        }
        System.out.println("Programmet er afsluttet.");
    }
    private void printStartMenu() {
        System.out.println("---Start menu---");
        System.out.println("Tryk 1: Opret ny superhelt\n" +
                "Tryk 2: Vis superhelte liste\n" +
                "Tryk 3: Søg efter superhelt\n" +
                "Tryk 4: Rediger superhelt\n" +
                "Tryk 5: Fjern superhelt\n" +
                "Tryk 6: Gem dine superhelte\n" +
                "Tryk 7: Load dine superhelte\n" +
                "Tryk 9: Afslut programmet");
    }
    private void createSuperhero() {
        System.out.print("Indtast navn på superhelten: ");
        String nameSuperheroxd = scan.nextLine(); //  Scanner bug. Virker når jeg har en overflødig scanner.
        String nameSuperhero = scan.nextLine();

        System.out.print("Indtast superheltens rigtige navn: ");
        String realNameSuperhero = scan.nextLine();

        System.out.print("Indtast superheltens superkraft: ");
        String superpowerSuperhero = scan.nextLine();


        int ageSuperhero = 0;
        while (true) {    // Infinite loop. stopper kun ved 'break'.
            try {
                System.out.print("Indtast superheltens alder: ");
                ageSuperhero = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ugyldigt svar. Indtast superheltens alder fx. '35'");
                scan.nextLine();  // fanger og skiller sig af med det forkerte input.
            }
        }

        int strengthSuperhero = 0;
        while (true) {
            try {
                System.out.print("Indtast superheltens styrkeniveau 1-9999: ");
                strengthSuperhero = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ugyldigt svar. Indtast styrkeniveau fx. '2300'");
                scan.nextLine();
            }
        }

        System.out.print("Er superhelten et menneske? [y/n] ");

        String userInput = scan.next();
        boolean isHumanSuperhero = false; /* Lokale variabler skal have en værdi, derfor gives
                                                  "false" som default */
        if (userInput.equalsIgnoreCase("y")) {
            isHumanSuperhero = true;
        } else if (userInput.equalsIgnoreCase("n")) {
            isHumanSuperhero = false;
        }

        controller.addSuperhero(nameSuperhero, realNameSuperhero,
                superpowerSuperhero, ageSuperhero, strengthSuperhero,
                isHumanSuperhero);

        saveHeroToCVS();

    }
    private void showSuperheroList() {
      //  controller.getSuperheroList().clear();
      //  controller.loadFromCVS();
        System.out.println(controller.getSuperheroList());
    }
    private void searchForSuperhero() {
        System.out.print("Indtast søgning: ");
        String søgning = scan.next();
        System.out.println(controller.findSuperhero(søgning));
    }
    private void findAndEditSuperhero() {
        //Her kan delvist navn indtastes
        System.out.println("Indtast søgekriterium for superhelt");
        String brugerInput = scan.nextLine();

        // Find person(er)
        ArrayList<Superhero> søgeResultat = controller.findSuperhero(brugerInput);
        Superhero superheroToEdit = null;

        // Søgning finder ingen personer
        if (søgeResultat.size() == 0) {
            System.out.println("Der findes ingen superhelte der opfylder søgekriterium");

        } else if (søgeResultat.size() > 1) {
            // Vælg en person i søgeresultat med flere personer
            System.out.println("Vælg superhelt");
            int tæller = 1;
            for (Superhero superhero : søgeResultat) {
                System.out.println(tæller++ + ". " +
                        superhero.getName() + ", " +
                        superhero.getRealName() + ", " +
                        superhero.getSuperpower() + ", " +
                        superhero.getAge() + "år, " +
                        superhero.getStrength() + " powerlevel, " +
                        superhero.isHuman());
            }
            int superheroPick = 0;
            while (true) {
                try {
                    superheroPick = scan.nextInt();
                    scan.nextLine(); // Håndterer Scanner Bug
                    if (superheroPick > 0 && superheroPick <= søgeResultat.size()) {
                        superheroToEdit = søgeResultat.get(superheroPick - 1);
                        break;
                    }   else {
                        System.out.print("Tal ikke inde for rækkevidde. Prøv igen: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.print("Ugyldigt input. Indtast et tal fx. '1': ");
                    scan.nextLine();
                }
            }

        }
        //Søgning finder én person
        else {
            superheroToEdit = søgeResultat.get(0);
        }

        // Redigering af valgte personer
        if (superheroToEdit != null) {
            System.out.println("Rediger superhelt information. Tryk ENTER hvis information ikke skal redigeres.");
            String nyVærdi;
            System.out.println("navn: " + superheroToEdit.getName());
            nyVærdi = scan.nextLine();
            if (!nyVærdi.isEmpty()) {
                superheroToEdit.setName(nyVærdi);
            }
            System.out.println("Rigtige navn: " + superheroToEdit.getRealName());
            nyVærdi = scan.nextLine();
            if (!nyVærdi.isEmpty()) {
                superheroToEdit.setRealName((nyVærdi));
            }
            {
                System.out.println("Superkraft: " + superheroToEdit.getSuperpower());
                nyVærdi = scan.nextLine();
                if (!nyVærdi.isEmpty()) {
                    superheroToEdit.setSuperpower((nyVærdi));
                }
                System.out.println("Alder: " + superheroToEdit.getAge());
                nyVærdi = scan.nextLine();
                if (!nyVærdi.isEmpty()) {
                    superheroToEdit.setAge(Integer.parseInt(nyVærdi));
                }
                System.out.println("Styrke niveau: " + superheroToEdit.getStrength());
                nyVærdi = scan.nextLine();
                if (!nyVærdi.isEmpty()) {
                    superheroToEdit.setStrength(Integer.parseInt(nyVærdi));
                }
                System.out.println(superheroToEdit + " er opdateret.");
            }
        }
    }
    private void backToMenuMessage() {
        System.out.print("Tilbage til start menuen? [y/n] ");
    }
    /*private void premadeSuperheroes() {
        controller.addSuperhero("Rico", "Victor Thy", "Skifter personlighed ved indtagelse af alkohol", 23, 9, true);
        controller.addSuperhero("Menig Hoijer", "Mads Teglskov", "Superstyrke", 24, 8, true);
        controller.addSuperhero("AC", "Anders kristensen", "Retard strength", 31, 6, true);
        controller.addSuperhero("TS", "Tommy Skrudstrup", "Dårlig beslutningstager. Altid gør det modsatte af hvad han siger", 38, 8, true);
    }
     */
    private void printWelcomeMessage() {
        System.out.println("-----Velkommen til superhelte databasen-----");
    }
    private void deleteSuperhero() {

        showSuperheroListByName();

        System.out.print("Indtast superhelte navn på superhelten du vil fjerne" +
                " fra databasen: ");
        scan.nextLine();
        String superheroToRemoveInput = scan.nextLine();

        controller.deleteSuperhero(superheroToRemoveInput);

        System.out.println("Superhelte listen er nu opdateret.");
        showSuperheroListByName();
    }
    private void showSuperheroListByName() {
        ArrayList<Superhero> superheroes = controller.getSuperheroList();
        System.out.println("Her er alle superhelte i databasen:");
        for (Superhero superhero : superheroes) {
            System.out.println(superhero.getName());
        }

    }

    private void loadHeroesFromCVS() {
        controller.loadFromCVS();

    }
    public void saveHeroToCVS() {
        String runAgain = "y";
        String input;
        System.out.println("Vil du gemme superhelten til cvs filen/databasen? [y/n]");
        input = scan.next();
        while(runAgain.equalsIgnoreCase("y")) {

            if (input.equalsIgnoreCase("y")) {
                controller.saveToCVS(controller.getSuperheroList());
                runAgain="n";
            } else if (input.equalsIgnoreCase("n")) {
                System.out.println("Superhelten blev ikke gemt.");
                runAgain="n";
            } else {
                System.out.println("Ugyldigt input. Prøv igen? [y/n]");
                runAgain = scan.next();
            }

        }
    }
    public void clearSuperheroList() {
        controller.getSuperheroList().clear();
    }
}
