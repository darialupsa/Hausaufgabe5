package repository;

import entities.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PersonRepositoryJDBC {
    final String DB_URL = "jdbc:mysql://root@localhost:3306/University";
    final String USER = "root";
    final String PASS = "Daria2001";

    Connection connection;

    public PersonRepositoryJDBC() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL, USER, PASS);}
}
