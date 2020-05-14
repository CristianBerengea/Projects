package DataAccessClasses;

import ModelClasses.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

/**
 * This class contain the queries and the
 * database connection for Client
 */

public class ClientDA extends GenericDataAccessClass<Client>{

    public Client findByFullName(String firstName, String lastName){
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement("SELECT * FROM warehouse.client WHERE first_name = \'"+firstName+"\' AND last_name = \'"+lastName +"\' AND deleted <> 1");
            rs = findStatement.executeQuery();
            List<Client> c = createObject(rs);
            if(c.isEmpty()) return null;
            else return c.get(0);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Client: findByFullName " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public List<Client> findAllByFullName(String firstName, String lastName){
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement("SELECT * FROM warehouse.client WHERE first_name = \'"+firstName+"\' AND last_name = \'"+lastName +"\' AND deleted <> 1");
            rs = findStatement.executeQuery();
            return createObject(rs);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Client: findByFullName " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

}
