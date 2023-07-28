package i2i;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class SubscriberTableIgnite {
    public static void main(String[] args) {
    	
        // Start Ignite and connect to the cluster
    	try (Connection conn = DriverManager.getConnection("jdbc:ignite:thin://localhost/", "berkay","1234")){
            // Execute a SQL query to select all records from the SUBSCRIBER table
            SqlFieldsQuery query = new SqlFieldsQuery("SELECT * FROM SUBSCRIBER");

            // Fetch and print the results
            List<List<?>> results = ignite.cache("SQL_PUBLIC_SUBSCRIBER").query(query).getAll();
            for (List<?> row : results) {
                System.out.println("SUBSC_ID: " + row.get(0) +
                        ", SUBSC_NAME: " + row.get(1) +
                        ", SUBSC_SURNAME: " + row.get(2) +
                        ", MSISDN: " + row.get(3) +
                        ", TARIFF_ID: " + row.get(4) +
                        ", START_DATE: " + row.get(5));
            }

            // Call the procedure
            Ignite.compute().call("i2i.SubscriberTableIgnite.printSubscriberCount");
        }
    }
    
// 127.0.0.1
    public static void printSubscriberCount() {
        int subscriberCount = Ignite.cache("SQL_PUBLIC_SUBSCRIBER").size();
        System.out.println("The number of subscribers is: " + subscriberCount);
    }
}