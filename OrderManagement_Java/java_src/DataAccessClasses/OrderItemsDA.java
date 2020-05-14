package DataAccessClasses;

import ModelClasses.Client;
import ModelClasses.OrderItems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
/**
 *This class contain the queries and the
 * database connection for the table which make conection beetwen Order and its Items
 *
 */
public class OrderItemsDA extends GenericDataAccessClass<OrderItems> {

    public OrderItems findByOrderIdAndProductId(int orderId,int productId)
    {
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement("SELECT * FROM warehouse.orderitems WHERE orderid = "+orderId+" AND productid = "+ productId +" AND deleted <> 1");
            rs = findStatement.executeQuery();
            List<OrderItems> c = createObject(rs);
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
    public void updateByOrderIdProductId(int orderId,int productId,int newQuantityValue)
    {
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement("UPDATE warehouse.orderitems SET quantity ="+ newQuantityValue +" WHERE orderid = "+orderId+" AND productid = "+ productId);
            findStatement.executeUpdate();

        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Client: updateByOrderIdProductId " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
