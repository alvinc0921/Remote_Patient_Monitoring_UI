import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
        public DBConnection() {
        }

        static Connection connectToDB() {
            Connection connection = null;
            try {
                String host = "ec2-54-77-182-219.eu-west-1.compute.amazonaws.com";
                String database = "d1b6klssq8ui5f";
                String user = "wzsnaeslyplxow";
                String password = "cc68b0d6b23a3330790b0bc95c5ee36f7cfc1837089baa3f3c75ab1dd42f2924";

                connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":5432/" + database,user,password);
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }finally {
                return connection;
            }
        }
    }
