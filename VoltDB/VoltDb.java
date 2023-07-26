package i2i;

import java.sql.ResultSet;

public class VoltDb {

    public static void main(String[] args){
        // Create a VoltDB connection
        VoltDBClient client = new VoltDBClient("localhost", 32768); // 21212

        // Create the SUBSCRIBER table
        String sql = "CREATE TABLE SUBSCRIBER(SUBSC_ID NUMBER, SUBSC_NAME VARCHAR(100), SUBSC_SURNAME VARCHAR(100), MSISDN VARCHAR(100), TARIFF_ID NUMBER, START_DATE DATE)";
        client.callProcedure("sys.sql", sql);

        // Insert mock records into the SUBSCRIBER table
        sql = "INSERT INTO SUBSCRIBER (SUBSC_ID, SUBSC_NAME, SUBSC_SURNAME, MSISDN, TARIFF_ID, START_DATE) VALUES (1, 'John', 'Doe', '1234567890', 1, '2023-01-01')";
        client.callProcedure("sys.sql", sql);
        sql = "INSERT INTO SUBSCRIBER (SUBSC_ID, SUBSC_NAME, SUBSC_SURNAME, MSISDN, TARIFF_ID, START_DATE) VALUES (2, 'Jane', 'Doe', '9876543210', 2, '2023-02-01')";
        client.callProcedure("sys.sql", sql);

        // Select and print the records from the SUBSCRIBER table
        sql = "SELECT * FROM SUBSCRIBER";
        ResultSet resultSet = client.callProcedure("sys.sql", sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("SUBSC_ID") + ", " + resultSet.getString("SUBSC_NAME") + ", " + resultSet.getString("SUBSC_SURNAME") + ", " + resultSet.getString("MSISDN") + ", " + resultSet.getInt("TARIFF_ID") + ", " + resultSet.getDate("START_DATE"));
        }
        // Close the VoltDB connection
        client.close();
    }
}
