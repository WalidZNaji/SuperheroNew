import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


class DatabaseTest {

    Database database;

    @BeforeEach
    void setup() {
        database = new Database();
    }

    private void createTestSuperheroes() {
        database.addSuperhero("Menig Hoijer", "Mads Teglskov", "Superstyrke", 24, 8, true);
        database.addSuperhero("AC", "Anders kristensen", "Retard strength", 31, 6, true);
        database.addSuperhero("TS", "Tommy Skrudstrup", "Dårlig beslutningstager. Altid gør det modsatte af hvad han siger", 38, 8, true);
    }

    @Test
     void addSuperheroTest() { // Test metode for indsættelse af én superhelt
        int expected = database.getSuperheroList().size() + 1;
        database.addSuperhero("Menig Hoijer", "Mads Teglskov", "Superstyrke, Stram", 24, 8, true);
        int actual = database.getSuperheroList().size();
        assertEquals(expected, actual);
    }

    @Test
    void addMultipleSuperheroesTest() { // Test metode for indsættelse af flere superhelte.
        int expected = database.getSuperheroList().size() + 3;
        database.addSuperhero("Menig Hoijer", "Mads Teglskov", "Superstyrke, Stram", 24, 8, true);
        database.addSuperhero("AC", "Anders kristensen", "Retard strength", 31, 6, true);
        database.addSuperhero("TS", "Tommy Skrudstrup", "Dårlig beslutningstager. Altid gør det modsatte af hvad han siger", 38, 8, true);
        int actual = database.getSuperheroList().size();
        assertEquals(expected, actual);
    }

    @Test
    void findSuperhero() { // Test metoden hvor søgeresultatet er 0.
        String findHero = "Mikkel";
        int expected = 0;
        ArrayList<Superhero> actual = database.findSuperhero(findHero);
        assertEquals(expected, actual.size());
    }

    @Test
    void findSingleSuperheroesBySingleLetter() { // Test metoden hvor søgeresultatet er 1.
        database.addSuperhero("AC", "Anders kristensen", "Retard strength", 31, 6, true);
        String findHero = "A";
        int expected = 1;
        ArrayList<Superhero> actual = database.findSuperhero(findHero);
        assertEquals(expected, actual.size());
    }

    @Test
    void findMultipleSuperheroesByLetter() { // Test metoden hvor søgeresultat er 2 el. flere.
        database.addSuperhero("Peter", "Morten", "ingen superkraft", 10, 5, true);
        database.addSuperhero("Peter", "Ole", "ingen superkraft", 12, 3, true);
        int expected = 2;
        ArrayList<Superhero> actual = database.findSuperhero("Pe");
        assertEquals(expected, actual.size());


    }

    @Test
    void DeleteSuperhero() {
        database.addSuperhero("AC", "Anders kristensen", "Retard strength", 31, 6, true);
        database.addSuperhero("Peter", "Ole", "ingen superkraft", 12, 3, true);
        database.addSuperhero("Peter", "Morten", "ingen superkraft", 10, 5, true);
        database.getSuperheroList().remove(2); // første helt er på index 0.
        int expected = 2;
        int actual = database.getSuperheroList().size();
        assertEquals(expected, actual);
    }


       /*Sortering af navn ved brug af getSuperheroesOneAttribute, derefter manuel sortering af
       superheltelisten, efter navn, derefter en verificering af at de stemmer overens med hinanden. */
    @Test
    void testGetSuperheroesOneAttribute() {
        int sortingChoice = 1;

        ArrayList<Superhero> sortedList = database.getSortedSuperheroesOneAttribute(sortingChoice);

        ArrayList<Superhero> originalList = new ArrayList<>(database.getSuperheroList());
        switch (sortingChoice) {
            case 1 -> Collections.sort(originalList, Comparator.comparing(Superhero::getName));
            case 2 -> Collections.sort(originalList, Comparator.comparing(Superhero::getRealName));
        }
        assertEquals(originalList, sortedList);
    }

    @Test
    void testGetSortedSuperheroes() {
        int primarySortingChoice = 1;
        int secondarySortingChoice = 2;

        ArrayList<Superhero> sortedList = database.getSortedSuperheroes(primarySortingChoice, secondarySortingChoice);

        ArrayList<Superhero> originalList = new ArrayList<>(database.getSuperheroList());

        // Manuel sortering af den primære attribut
        switch (primarySortingChoice) {
            case 1 -> Collections.sort(originalList, Comparator.comparing(Superhero::getName));
            case 2 -> Collections.sort(originalList, Comparator.comparing(Superhero::getRealName));
            case 3 -> Collections.sort(originalList, Comparator.comparing(Superhero::getSuperpower));
            case 4 -> Collections.sort(originalList, Comparator.comparing(Superhero::getAge));
            case 5 -> Collections.sort(originalList, Comparator.comparing(Superhero::getStrength));
            case 6 -> Collections.sort(originalList, Comparator.comparing(Superhero::isHuman));
            default -> {
                System.out.println("Invalid primary sorting choice. Defaulting to sorting by name.");
                Collections.sort(originalList, Comparator.comparing(Superhero::getName));
            }
        }

        // Manuel sortering af den sekundære attribut
        switch (secondarySortingChoice) {
            case 1 -> Collections.sort(originalList, Comparator.comparing(Superhero::getName));
            case 2 -> Collections.sort(originalList, Comparator.comparing(Superhero::getRealName));
            case 3 -> Collections.sort(originalList, Comparator.comparing(Superhero::getSuperpower));
            case 4 -> Collections.sort(originalList, Comparator.comparing(Superhero::getAge));
            case 5 -> Collections.sort(originalList, Comparator.comparing(Superhero::getStrength));
            case 6 -> Collections.sort(originalList, Comparator.comparing(Superhero::isHuman));
            default -> System.out.println("Invalid secondary sorting choice. Ignoring secondary sorting.");
        }

        // Assert that the sorted list matches the manually sorted list
        assertEquals(originalList, sortedList);
    }
}
