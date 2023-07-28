package i2i;

import java.sql.*;

public class SubscriberSpring {

    private Connection connection;

    public SubscriberSpring() throws SQLException {
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "berkay", "123");
    }

    public void insertSubscriber(int subscId, String subscName, String subscSurname, String msisdn, int tariffId, Date startDate) throws SQLException {
        String sql = "INSERT INTO SUBSCRIBER (SUBSC_ID, SUBSC_NAME, SUBSC_SURNAME, MSISDN, TARIFF_ID, START_DATE) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, subscId);
        statement.setString(2, subscName);
        statement.setString(3, subscSurname);
        statement.setString(4, msisdn);
        statement.setInt(5, tariffId);
        statement.setDate(6, startDate);
        statement.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
        SubscriberSpring cust = new SubscriberSpring();
        cust.insertSubscriber(1, "John Doe", "Doe", "1234567890", 1, new Date(0));
        cust.insertSubscriber(2, "Berkay", "A", "14493654", 1, new Date(0));
        cust.insertSubscriber(3, "Ahmet", "A", "14746876", 2, new Date(0));
        cust.insertSubscriber(4, "Seyhun", "A", "17546863", 3, new Date(0));
        cust.insertSubscriber(4, "i2i", "A", "1765462", 1, new Date(0));
    }
}

