import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Repository<T> {

    private List<T> items = new ArrayList<>();

    public void add(T item) {
        this.items.add(item);
    }

    public void remove(T item) {
        this.items.removeAll(List.of(item));
    }

    public List<T> getAll() {
        return items.stream().toList();
    }

    public T findByIndex(int index) {
        try {
            return this.items.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

}
