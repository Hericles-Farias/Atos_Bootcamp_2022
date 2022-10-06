package heck_db_conn;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DatabaseMetaData;

//executeUpdate-> ALterar
//executeQuery->Buscar
public class Db {
    //Conneection to the db
    protected Connection connection;
    //Query Class instance
    protected Query query;
    private String currentWorkingTable=null;

    public Db(String url, String user, String password) throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://localhost/"+url, user, password);
    }

    private int query(String query, Object[] params)throws SQLException{
        //first checks if the table has an auto increment Pk, so we will need to change the
        //syntaxe in case of insert operation
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet rs = null;
        ResultSet rsx = null;
        //Boolean hasAInc=false;
        String columnName="";
        rs = meta.getPrimaryKeys(null, null, currentWorkingTable);
        rsx = meta.getColumns(null,null,currentWorkingTable,null);
        StringBuilder rsxstr  = new StringBuilder();
        rsxstr.append("(");
        while (rsx.next()) {
            String name = rsx.getString("COLUMN_NAME");
            //String type = rsx.getString("TYPE_NAME");
            //int size = rsx.getInt("COLUMN_SIZE");
            rsxstr.append(name);
            rsxstr.append(",");
        }
        //remove last comma
        rsxstr.deleteCharAt(rsxstr.lastIndexOf(","));
        rsxstr.append(")");
        //System.out.println(rsxstr);
        //List list = new ArrayList();
        //before
        System.out.println(query);
        //String queryori = query;
        //now
        query=query.replace(currentWorkingTable,currentWorkingTable+rsxstr);
        //System.out.println(query);
        if (rs!=null){//it means this table has an autoimcrement column
            //hasAInc=true;
            while (rs.next()) {
                columnName = rs.getString("COLUMN_NAME");
                //System.out.println("getPrimaryKeys(): columnName=" + columnName);
              }
              query = query.replace(columnName+",","");
              //System.out.println(query);
        }
        PreparedStatement ps = connection.prepareStatement(query);
        if (params!=null){
            int index =1;
            for (Object param : params){
                ps.setObject(index, param);
                index++;
            }
        }
        return ps.executeUpdate();
    }
    public int delete(String table, String requirement, Object[] param) throws SQLException {
        query = new Query();
        query.delete(table).where(requirement);
        return query(query.getQuery(),param);
    }
    public int insert(String table, Object[] params) throws SQLException {
        currentWorkingTable = table;
        query = new Query();
        query.insert(table).values(params);
        return query(query.getQuery(),params);
    }
    public int update(String table, String[] columns, String requirement, Object[] params) throws SQLException {
        query = new Query();
        query.update(table)
            .set(columns)
            .where(requirement);
        return query(query.getQuery(),params);
    }
    public ResultSet select(String table, Object[] columns, Object[] params) throws SQLException{
        return this.select(table, columns, "", params);
    }

    public ResultSet select(String table, Object[] columns, String requirement, Object[] params) throws SQLException{
        query = new Query();
        query.select(columns)
             .from(table)
             .where(requirement);

        PreparedStatement ps = connection.prepareStatement(query.getQuery());
        if(params != null){
            int index = 1;
            for(Object param : params){
            ps.setObject(index, param);
            index++;
         }
        }

        ResultSet rs = ps.executeQuery();
        return rs;
    }
}
