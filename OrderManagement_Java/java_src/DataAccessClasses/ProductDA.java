package DataAccessClasses;

import ModelClasses.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * This class contain the queries and the
 * database connection for Product
 */

public class ProductDA extends GenericDataAccessClass<Product>{
    public void updatePrice(float newValue,int id){
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement("UPDATE warehouse.Product SET price = "+ newValue + "  WHERE id ="+id);
            findStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "ProductDa updatePrice " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
