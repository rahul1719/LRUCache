package com.custom.cache;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    static int counter=0;
    public List<Vehicle> getAllVehicleInfo(){
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        // This will load the MySQL driver, each DB has its own driver
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connect = DriverManager
                    // Setup the connection with the DB
                    .getConnection("jdbc:mysql://localhost:3306/bigdata?"
                            + "user=root&password=password123" );
            // Statements allow to issue SQL queries to the database
           Statement statement = connect.createStatement();
            // Result set get the result of the SQL query
           ResultSet resultSet = statement
                    .executeQuery("select * from bigdata.vehicleinfo");
            vehicles.add(writeResultSet(resultSet));
            statement.close();
            connect.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return vehicles;
    }


    public Vehicle getVehicleInfoById(int Id){
        Vehicle vehicle=null;
        // This will load the MySQL driver, each DB has its own driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String query= "select * from bigdata.vehicleinfo where id = ?";

            Connection connect = DriverManager
                    // Setup the connection with the DB
                    .getConnection("jdbc:mysql://localhost:3306/bigdata?"
                            + "user=root&password=password123" );
            // Statements allow to issue SQL queries to the database

            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, Id);
            // Result set get the result of the SQL query
            ResultSet resultSet = ps.executeQuery();
            vehicle=writeResultSet(resultSet);
            ps.close();
            connect.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
return vehicle;
    }

    private Vehicle writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set

        Vehicle vehicle = null;
        while (resultSet.next()) {
            vehicle = new Vehicle();
            counter++;
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            Integer id = resultSet.getInt("id");
            vehicle.setId(id);
            String make = resultSet.getString("make");
            vehicle.setMake(make);
            String model = resultSet.getString("model");
            vehicle.setModel(model);
            int year = resultSet.getInt("year");
            vehicle.setYear(year);
            String drive = resultSet.getString("drive");
            vehicle.setDrive(drive);


        }
        System.out.println("total DB hit==="+counter);
        return vehicle;
    }

}
