import java.util.*;

public class UserInterface {

    private final Controller controller;
    private final Scanner scan;

    public UserInterface() {
        controller = new Controller();
        scan = new Scanner(System.in);
    }

    public void startProgram() {
        loadHeroesFromCSV();

        String runAgain = "y";

        printWelcomeMessage();

        while ("y".equalsIgnoreCase(runAgain)) {
            printStartMenu();
            int startInput = getStartInput();

            switch (startInput) {
                case 1 -> createSuperhero();
                case 2 -> showSuperheroList();
                case 3 -> searchForSuperhero();
                case 4 -> findAndEditSuperhero();
                case 5 -> deleteSuperhero();
                case 9 -> runAgain = "n";
            }
            if ("n".equalsIgnoreCase(runAgain)) {
                break;
            }
            backToMenuMessage();
            runAgain = scan.next();
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
            break;
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

        saveHeroToCSV();

    }
    private void showSuperheroList() {
    String runAgain = "y";
    while (runAgain.equalsIgnoreCase("y")) {
        System.out.println("1. Sorter ved navn.\n2. Sorter ved én attribut" +
                "\n3. Sorter ved primær og sekundær attribut \n" +
                "4. Afslut sorterings menu");
        System.out.print("Indtast [1/2/3/4]: ");
        int choice = scan.nextInt();

        switch (choice) {
            case 1 ->
                System.out.println(controller.getSortedHeroes(1, 0));

            case 2 -> {
                System.out.println("Vælg sorterings parameter");
                System.out.println("1. Superhero name\n2. Real name\n3. Superpower\n4. Age\n" +
                        "5. Strength\n6. Hero is Human");
                int sorteringsParameter = scan.nextInt();

                System.out.println(controller.getSortedHeroesOneAttribute(sorteringsParameter));

            }
            case 3 -> {
                System.out.println("Vælg primær attribut sortering af superheltene:");
                System.out.println("1. Superhero name\n2. Real name\n3. Superpower\n4. Age\n" +
                        "5. Strength\n6. Hero is Human");
                int primærSortering = scan.nextInt();


                System.out.println("Vælg sekundær attribut sortering af superheltene:");
                System.out.println("1. Superhero name\n2. Real name\n3. Superpower\n4. Age\n" +
                        "5. Strength\n6. Humanity");

                int sekundærSortering = scan.nextInt();


                System.out.println(controller.getSortedHeroes(primærSortering,
                        sekundærSortering));
            }
            case 4 -> {
                System.out.println("Afslutter sorterings menu");
                runAgain = "n";
            }
            default -> System.out.println("Ugyldigt svar. Prøv igen.");
        }
    }

    }
    private void searchForSuperhero() {
        System.out.print("Indtast superhelte navn: ");
        String søgning = scan.next();

        ArrayList<Superhero> result = controller.findSuperhero(søgning);

        for (Superhero superhero:result) {
            System.out.println(superhero.getName() + ", " +
                    superhero.getRealName() + ", " +
                    superhero.getSuperpower() + ", " +
                    superhero.getAge() + " år, " +
                    superhero.getStrength() + " powerlevel, " +
                    superhero.isHuman());
        }
    }
    private void findAndEditSuperhero() {

        System.out.println("Indtast nummer for superhelt");
        String brugerInput = scan.nextLine();

        // Find person(er)
        ArrayList<Superhero> søgeResultat = controller.findSuperhero(brugerInput);
        Superhero superheroToEdit = null;

        // Søgning finder ingen personer
        if (søgeResultat.isEmpty()) {
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
                System.out.println("Opdateret superhelt:\n" + superheroToEdit);

            }
            saveHeroToCSV();
        }

    }
    private void backToMenuMessage() {
        System.out.print("Tilbage til start menuen? [y/n] ");
    }
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
        saveHeroToCSV();
    }
    private void showSuperheroListByName() {
        ArrayList<Superhero> superheroes = controller.getSuperheroList();
        System.out.println("Her er alle superhelte i databasen:");
        for (Superhero superhero : superheroes) {
            System.out.println(superhero.getName());
        }

    }

    private void loadHeroesFromCSV() {
        controller.loadFromCSV();
    }
    public void saveHeroToCSV() {
        String runAgain = "y";
        String input;
        System.out.println("Vil du opdatere csv filen/databasen? [y/n]");
        input = scan.next();
        while(runAgain.equalsIgnoreCase("y")) {

            if (input.equalsIgnoreCase("y")) {
                controller.saveToCSV(controller.getSuperheroList());
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
    public int getStartInput() {
        int startInput = 0;
        while (true) {
            try {
                startInput = scan.nextInt();
                if (startInput >= 1 && startInput <= 9)
                    break;
                else {
                    System.out.print("Indtast et tal mellem 1-9: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Indtast et tal mellem 1-9: ");
                scan.nextLine();
            }
        }
        return startInput;
    }


}
