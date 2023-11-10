import java.util.Comparator;

public class RealNameComparator implements Comparator<Superhero> {

    public int compare(Superhero superhero1, Superhero superhero2){
        return String.CASE_INSENSITIVE_ORDER.compare(superhero1.getRealName(), superhero2.getRealName());
    }
}
