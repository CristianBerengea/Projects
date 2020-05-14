package DataAccessClasses;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class offers a generic implementation for common methods used in database management
 * @param <T>
 */
public class GenericDataAccessClass<T> {
    protected static final Logger LOGGER = Logger.getLogger(GenericDataAccessClass.class.getName());

    private  Class<T> type;

    @SuppressWarnings("unchecked")
    public GenericDataAccessClass() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectQuery(String field)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM warehouse.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE deleted <> 1 and " + field + " =?");
        return sb.toString();
    }

    private String createUpdateQuery(String setField,String whereField)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE warehouse.");
        sb.append(type.getSimpleName());
        sb.append(" SET " + setField + " =? ");
        sb.append(" WHERE " + whereField + " =?");
        return sb.toString();
    }

    private String createSelectAllQuery()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM warehouse.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE deleted <> 1");
        return sb.toString();
    }

    private String createSearchElementQuery()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        for(Field fild : type.getDeclaredFields()){
            if(fild.getName()!="id") {
                sb.append(fild.getName() + " =? AND ");
            }
        }
        sb.delete(sb.length()-5,sb.length()-1);
        return sb.toString();
    }

    private String createInsertQuery()
    {
        StringBuilder sb = new StringBuilder();
        StringBuilder aux = new StringBuilder();
        sb.append("Insert ");
        sb.append(" INTO warehouse." );
        sb.append(type.getSimpleName());
        sb.append(" ( ");
        for(Field fild : type.getDeclaredFields()){
            if(fild.getName()!="id") {
                sb.append(fild.getName() + " , ");
                aux.append(" ? , ");
            }
        }
        sb.delete(sb.length()-3,sb.length()-1);
        aux.delete(aux.length()-3,aux.length()-1);
        sb.append(" ) ");

        sb.append(" VALUES ( ");
        sb.append(aux);
        sb.append(" )");

        return sb.toString();
    }

    /**
     * return T object founded by an id
     * @param id
     * @return
     */
    public T findById(int id){
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String query = createSelectQuery("id");
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement(query);
            findStatement.setInt(1,id);
            rs = findStatement.executeQuery();
            return createObject(rs).get(0);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "GDAC: findById " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    /**
     * Select * query from T
     * @return
     */
    public List<T> SelectAll(){
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String query = createSelectAllQuery();
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement(query);
            rs = findStatement.executeQuery();
            return createObject(rs);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "GDAC: selectAll " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public T findByStringField(String fieldValue,String fieldName){
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String query = createSelectQuery(fieldName);
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement(query);
            findStatement.setString(1, fieldValue);
            rs = findStatement.executeQuery();
            List<T> l = createObject(rs);
            if(l.isEmpty()) return null;
            else return l.get(0);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "GDAC: findById " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public T findByIntField(int fieldValue,String fieldName){
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String query = createSelectQuery(fieldName);
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement(query);
            findStatement.setInt(1, fieldValue);
            rs = findStatement.executeQuery();
            List<T> l = createObject(rs);
            if(l.isEmpty()) return null;
            else return l.get(0);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "GDAC: findById " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public List<T> selectAllByIntField(int fieldValue,String fieldName){
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String query = createSelectQuery(fieldName);
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement(query);
            findStatement.setInt(1, fieldValue);
            rs = findStatement.executeQuery();
            List<T> l = createObject(rs);
            if(l.isEmpty()) return null;
            else return l;
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "GDAC: findById " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public void updateByIntIntField(int newValue,String setFieldName,int whereValue,String whereFieldName){
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        String query = createUpdateQuery(setFieldName,whereFieldName);
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement(query);
            findStatement.setInt(1, newValue);
            findStatement.setInt(2, whereValue);
            findStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "GDAC: findById " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public void updateByIntStringField(int newValue,String setFieldName,String whereValue,String whereFieldName){
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        String query = createUpdateQuery(setFieldName,whereFieldName);
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement(query);
            findStatement.setInt(1, newValue);
            findStatement.setString(2, whereValue);
            findStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "GDAC: findById " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public void updateByStringStringField(String newValue,String setFieldName,String whereValue,String whereFieldName){
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        String query = createUpdateQuery(setFieldName,whereFieldName);
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement(query);
            findStatement.setString(1, newValue);
            findStatement.setString(2, whereValue);
            findStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "GDAC: findById " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public void deleteByIntField(int value,String FieldName){
        updateByIntIntField(1,"deleted",value,FieldName);
    }
    public void deleteByStringField(String value,String FieldName){
        updateByIntStringField(1,"deleted",value,FieldName);
    }


    public void insertRow(T var){
        Connection dbConnection = null;
        PreparedStatement insertStatement = null;
        ResultSet rs = null;
        String query = createInsertQuery();
        try {
            dbConnection = ConnectionFactory.getConnection();
            insertStatement = dbConnection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            for(Field fild : var.getClass().getDeclaredFields()){
                fild.setAccessible(true);
                if(fild.getName()!="id") {
                    if(fild.getType().getSimpleName().equals("String")) insertStatement.setString(index , fild.get(var).toString());
                    else if(fild.getType().getSimpleName().equals("int")) insertStatement.setInt(index , fild.getInt(var));
                    else {
                        insertStatement.setFloat(index , fild.getFloat(var));
                    }
                    index++;
                }
            }
            insertStatement.executeUpdate();
        }
        catch (SQLException | IllegalAccessException e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "GDAC: insert " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public boolean searchElement(T var){
        Connection dbConnection = null;
        PreparedStatement searchStatement = null;
        ResultSet rs = null;
        String query = createSearchElementQuery();
        try {
            dbConnection = ConnectionFactory.getConnection();
            searchStatement = dbConnection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            for(Field fild : var.getClass().getDeclaredFields()){
                fild.setAccessible(true);
                if(fild.getName()!="id") {
                    if(fild.getType().getSimpleName().equals("String")) searchStatement.setString(index , fild.get(var).toString());
                    else if(fild.getType().getSimpleName().equals("int")) searchStatement.setInt(index , fild.getInt(var));
                    else {
                        searchStatement.setFloat(index , fild.getFloat(var));
                    }
                    index++;
                }
            }
            rs=searchStatement.executeQuery();
            if(rs.next()==false) return false;
            else return true;
        }
        catch (SQLException | IllegalAccessException e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "GDAC: insert " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(searchStatement);
            ConnectionFactory.close(dbConnection);
        }
        return false;
    }

    /**
     * Generate a List of T objects from  an executeQuery ResultSet
     * @param rs
     * @return
     */
    public List<T> createObject(ResultSet rs){
        List<T> list = new ArrayList<T>();
        try {
            while (rs.next())
            {
                T instance =  type.newInstance();
                for(Field fild : type.getDeclaredFields()){
                    Object value = rs.getObject(fild.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fild.getName(),type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance,value);
                }
                list.add(instance);
            }
            return list;
        }
        catch (Exception e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "GDAC: createObject " + e.getMessage());
        }
        return null;
    }
}
