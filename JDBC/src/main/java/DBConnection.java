import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBConnection implements Source{

    private final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private final String USER = "postgres";
    private final String PASSWORD = "123";

    public DBConnection() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS cache (key TEXT PRIMARY KEY, result INTEGER[])");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveData(String key, List<Integer> result) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String insertQuery = "INSERT INTO cache" + "(key, result) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, key);
            preparedStatement.setArray(2, connection.createArrayOf("integer", result.toArray()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getData(String key) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String selectQuery = "SELECT result FROM cache" + " WHERE key = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, key);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSetToList(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Integer> resultSetToList(ResultSet resultSet) throws SQLException {
        Array array = null;

        if (resultSet.next()) {
            array = resultSet.getArray("result");
        }
        List<Integer> set = new ArrayList<>();

        if (array != null) {
            Integer[] result = (Integer[]) array.getArray();

            set = new ArrayList<>(Arrays.asList(result));
        }

        return set;
    }
}
