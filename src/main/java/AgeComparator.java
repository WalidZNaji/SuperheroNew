import java.util.Comparator;

public class AgeComparator implements Comparator<Superhero> {
    @Override
    public int compare(Superhero superhero1, Superhero superhero2) {
        return Integer.compare(superhero1.getAge(), superhero2.getAge());
    }
}
