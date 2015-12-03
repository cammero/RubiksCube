package com.cameo;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String JDBCDriver = "com.mysql.jdbc.Driver";
    static String DBConnectionURL = "jdbc:mysql://localhost:3306/rubiksCubeRecords";
    //static String DBName = "rubiksCubeRecords";
    static final String USER = "Cameo";
    static final String PASSWORD = "Mil@Vi0l3t";
    static Statement statement = null;
    static Connection conn = null;
    static ResultSet rs = null;

    public static void main(String[] args) throws Exception{

        Class.forName(JDBCDriver);

        conn = DriverManager.getConnection(DBConnectionURL, USER, PASSWORD);
        statement = conn.createStatement();

        statement.execute("CREATE TABLE IF NOT EXISTS Cube (nameOfRubiksCubeContestant varchar(50), timeTaken DOUBLE)");
        statement.execute("INSERT INTO Cube VALUES ('Cubestormer II robot', 5.270)");
        statement.execute("INSERT INTO Cube VALUES ('Fakhri Raihaan (using his feet)', 27.93)");
        statement.execute("INSERT INTO Cube VALUES ('Ruxin Liu (age 3)', 99.33)");
        statement.execute("INSERT INTO Cube VALUES ('Mats Valk', 6.27)");

        Scanner s = new Scanner(System.in);

        System.out.println("\nEnter the name of the contestant whose time you would like to update");
        String contestantName = s.nextLine();
        System.out.println("Enter the new time of the contestant");
        Double newTime = s.nextDouble();
        statement.executeUpdate("UPDATE cube SET timeTaken = " + newTime + "WHERE nameOfRubiksCubeContestant = '" + contestantName + "'");


        System.out.println("\nEnter Y if you would like to enter a new time");
        String response = s.nextLine();
        if (response.equalsIgnoreCase("y")){
            System.out.println("What is the name of the contestant?");
            String name = s.nextLine();
            System.out.println("What is the time?");
            double timeSolved = s.nextDouble();
            statement.execute("INSERT INTO Cube VALUES ('"+ name + "' , " + timeSolved + ")");
        }

        rs = statement.executeQuery("SELECT * from cube");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " solved the Rubik's cube in " +rs.getDouble(2)+ " seconds.");
        }

        statement.executeUpdate("DROP TABLE Cube");
        rs.close();
        statement.close();
        conn.close();
    }
}

