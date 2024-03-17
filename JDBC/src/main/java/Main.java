import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
            Source source = new DBConnection();
            Calculator calculator = new Calculator(source);

            calculator.fibonachi(1);
            calculator.fibonachi(2);
            calculator.fibonachi(3);
            calculator.fibonachi(4);
    }
}
