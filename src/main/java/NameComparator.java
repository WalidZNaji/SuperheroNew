import java.util.Comparator;

public class NameComparator implements Comparator<Superhero> {
    @Override
    public int compare(Superhero superhero1, Superhero superhero2) {
        return String.CASE_INSENSITIVE_ORDER.compare(superhero1.getName(), superhero2.getName());
    }
}
