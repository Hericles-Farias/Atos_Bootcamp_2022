package com.br.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class db {
    String password = "";

    public void put_data(String Username) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", this.password);
        Statement stmt1 = con.createStatement();
        stmt1.executeUpdate("CREATE DATABASE IF NOT EXISTS heckjpa;");
        stmt1.executeUpdate("USE heckjpa;");
        stmt1.executeQuery("SELECT DATABASE();");
        /*
         * Set Username as promary key so that no two users with same username register.
         */
        String query1 = ("CREATE TABLE IF NOT EXISTS usuario(" + "Id INT(5) NOT NULL PRIMARY KEY,"
                + "Nome VARCHAR(30) NOT NULL);");
        stmt1.executeUpdate(query1);
        //PreparedStatement stmt2;
        /* NULLIF is to return NULL if both parameters are same */
        //stmt2 = con.prepareStatement("INSERT INTO Usuario VALUES" + "NULLIF(?,'');");
        //stmt2.setString(Username);
        System.out.println(Username);
        String query2 = ("INSERTO INTO usuario (`Nome`) VALUES ('Hericles21')");
        Statement stmt2 = con.createStatement();
        stmt2.executeUpdate(query2);
        con.close();
    }
}