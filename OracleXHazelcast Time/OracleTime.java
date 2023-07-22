import java.sql.*;
import java.util.Random;

//Java kodu, Oracle veritabanına 20.000 ve 100.000 rastgele değer girer.
//Program, her işlem için derleme süresini konsola yazdırır.


public class OracleTime {

	// Oracle veritabanına bağlanmak için kullanılan URL'yi tanımlar.
    private static final String URL = "jdbc:oracle:thin:@localhost:1521";
    private static final String USER = "SYS";
    private static final String PASSWORD = "123123";

 // Programın ana işlevini tanımlar.
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        Random random = new Random();

        long start_time = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            int value = random.nextInt(1000000);
         // Sayıları veritabanına eklemek için SQL sorgusunu oluşturur.
            String sql = "INSERT INTO random_values (value) VALUES (?)";
         // Sorguyu yürütmek için hazırlanan bir ifade oluşturur.
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, value);
            statement.executeUpdate();
        }
        long end_time = System.currentTimeMillis();
        System.out.println("Calculated time calculation for Oracle");
        System.out.println("Calculated average time (insert-select) for 20000 random number: " + (end_time - start_time)+ " ms");

        start_time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            int value = random.nextInt(1000000);
            String sql = "INSERT INTO random_values (value) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, value);
            statement.executeUpdate();
        }
        end_time = System.currentTimeMillis();
        System.out.println("Calculated average time (insert-select) for 100000 random number: " + (end_time - start_time)+ " ms");

     // Bağlantıyı kapatır.
        connection.close();
    }
}
