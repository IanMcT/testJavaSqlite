//added org.xerial:sqlite-jdbc package

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;


class Main {
      
  public static void main(String[] args) {
     Connection connection = null;
        try
        {
          // create a database connection
          connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);  // set timeout to 30 sec.

          statement.executeUpdate("drop table if exists contacts");
          statement.executeUpdate("create table contacts (id integer PRIMARY KEY, name string, address string)");
          statement.executeUpdate("insert into contacts (name, address) values('McT', 'Huntsville')");
          statement.executeUpdate("insert into contacts (name, address) values('Suzy', 'Port Sydney')");
          ResultSet rs = statement.executeQuery("select * from contacts");
          while(rs.next())
          {
            // read the result set
            System.out.println("name = " + rs.getString("name"));
            System.out.println("id = " + rs.getInt("id"));
            System.out.println("ADDRESS = " + rs.getString("address"));
          }
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }
      }
    }