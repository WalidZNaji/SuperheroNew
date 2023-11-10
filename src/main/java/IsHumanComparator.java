import java.util.Comparator;

public class IsHumanComparator implements Comparator<Superhero> {
    @Override
    public int compare(Superhero superhero1, Superhero superhero2) {
        if(superhero1.isHuman()==superhero2.isHuman()){
            return 0;
        }else if(superhero1.isHuman() && superhero2.isHuman()){
            return 1;
        }else{
            return 2;
        }
    }
}
