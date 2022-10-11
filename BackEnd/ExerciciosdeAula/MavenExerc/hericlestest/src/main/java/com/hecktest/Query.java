package com.hecktest;

public class Query {
    //delete and where statements
    private StringBuilder query;
    
    public Query delete(String table){
        query = new StringBuilder(); 
        query.append("DELETE FROM ");
        query.append(table);
        return this;//it returns the instance itself and it is user in chained statements
    }
    public Query where(String requirement){
        query.append(" WHERE ");
        query.append(requirement);
        return this;
    }
    public Query from (String table){
        query.append(" FROM ");
        query.append(table);
        return this;
    }
    //update and set
    public Query update(String table){
        query = new StringBuilder();
        query.append("UPDATE ");
        query.append(table);
        query.append(" SET ");
        return this;
    }
    public Query set (String[] columns){
        int count =  columns.length;
        if (count==0){
            throw new IllegalArgumentException("Invalid argument count");
        }
        for(String column : columns){
            query.append(column);
            query.append(" = ");
            query.append("?");
            query.append(",");
        }
        query.deleteCharAt(query.lastIndexOf(","));
        return this;
    }
    //insert and values
    public Query insert(String table){
        query = new StringBuilder();
        query.append("INSERT INTO ");    
        query.append(table);
        return this;
    }
    public Query values (Object[] params){
        query.append(" VALUES(");
        int count = params.length;
        if (count==0){
            throw new IllegalArgumentException("Invalid parameter count");
        }
        for (int i=0;i<count;i++){
            query.append("?,");
        }
        //remove the last comma
        query.deleteCharAt(query.lastIndexOf(","));
        query.append(");");
        return this;
    }
    //select and auxiliary methods
    public Query select(Object[] columns){
        query = new StringBuilder();
        query.append("SELECT ");
        if(columns != null){
            for(Object column : columns){
                query.append(column);
                query.append(",");
            }
            //removes the last question mark
            query.deleteCharAt(query.lastIndexOf(","));
        }
        else {
            query.append("*");
        }
        return this;
    }
    public String getQuery(){
        return query.toString();
    }
}
