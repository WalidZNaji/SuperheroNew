import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class DatabaseTest {

    Database database;

    @BeforeEach
    void setup() {
        database = new Database();
    }


    @Test
     void addSuperheroTest() { // Test metode for indsættelse af én superhelt
        int expected = database.superheroList.size() + 1;
        database.addSuperhero("Menig Hoijer", "Mads Teglskov", "Superstyrke, Stram", 24, 8, true);
        int actual = database.superheroList.size();
        assertEquals(expected, actual);
    }

    @Test
    void addMultipleSuperheroesTest() { // Test metode for indsættelse af flere superhelte.
        int expected = database.superheroList.size() + 3;
        database.addSuperhero("Menig Hoijer", "Mads Teglskov", "Superstyrke, Stram", 24, 8, true);
        database.addSuperhero("AC", "Anders kristensen", "Retard strength", 31, 6, true);
        database.addSuperhero("TS", "Tommy Skrudstrup", "Dårlig beslutningstager. Altid gør det modsatte af hvad han siger", 38, 8, true);
        int actual = database.superheroList.size();
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


}