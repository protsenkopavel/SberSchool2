import java.util.List;

public interface Source {
    void saveData(String key, List<Integer> result);
    List<Integer> getData(String key);
}
