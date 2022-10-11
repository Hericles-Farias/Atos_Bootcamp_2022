package com.hecktest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
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
    private String currentOp="";

    public Db(String url, String user, String password) throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://localhost/"+url, user, password);
    }

    private int query(String query, Object[] params)throws SQLException{
        //first checks if the table has an auto increment Pk, so we will need to change the
        //syntaxe in case of insert operation
        if (currentOp=="INSERT"){//AUTO INCREMENT CHECKER
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet rsx = null;
            String AIncCol="";
            rsx = meta.getColumns(null,null,currentWorkingTable,null);
            StringBuilder rsxstr  = new StringBuilder();
            rsxstr.append("(");
            while (rsx.next()) {
                if(rsx.getString("IS_AUTOINCREMENT").equals("YES")){
                    AIncCol= rsx.getString("COLUMN_NAME"); 
                }
                String name = rsx.getString("COLUMN_NAME");           
                rsxstr.append(name);
                rsxstr.append(",");
            }    
            rsxstr.deleteCharAt(rsxstr.lastIndexOf(","));
            rsxstr.append(")");
            System.out.println("SQL Before: "+query);
            query=query.replace(currentWorkingTable,currentWorkingTable+rsxstr);
            System.out.println("SQL Now: "+query);
            if (AIncCol!=""){//it means this table has an autoimcrement column
                //and then we need to take them out of the query!
                  query = query.replace(AIncCol+",","");    
                  System.out.println("SQL After Removing the Auto Increment Column: ");
                  System.out.println(query);
                }
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
        currentOp = "INSERT";
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
