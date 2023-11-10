import java.util.Comparator;

public class SuperpowerComparator implements Comparator<Superhero> {
    @Override
    public int compare(Superhero superhero1, Superhero superhero2) {
        return String.CASE_INSENSITIVE_ORDER.compare(superhero1.getSuperpower(), superhero2.getSuperpower());
    }
}
