import java.util.Comparator;

public class ComparadorReferencia implements Comparator<Producto> {
    @Override
    public int compare(Producto p1, Producto p2) {
        return p1.getReferencia().compareTo(p2.getReferencia());
    }
}
