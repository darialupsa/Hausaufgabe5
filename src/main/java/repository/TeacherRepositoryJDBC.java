package repository;

import entities.Course;
import entities.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryJDBC implements ICrudRepo<Teacher> {
    final String DB_URL = "jdbc:mysql://root@localhost:3306/University";
    final String USER = "root";
    final String PASS = "Daria2001";

    Connection connection;

    public TeacherRepositoryJDBC() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL, USER, PASS);}

    @Override
    public Teacher find(long Id) throws SQLException {
        String Find = "SELECT * FROM Teacher WHERE teacherId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(Find); // transformam Stringul Find in comanda de SQL
        preparedStatement.setLong(1,Id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Teacher teacher = new Teacher();

        if (resultSet != null){

            while (resultSet.next())
            {
                teacher.setTeacherId(resultSet.getLong("teacherId"));
                teacher.setFirstName(resultSet.getString("firstName"));
                teacher.setLastName(resultSet.getString("lastName"));

            }
        }
        return teacher;
    }

    @Override
    public List<Teacher> getAll() throws SQLException {
        String findAll = "SELECT * FROM Teacher";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll); // transformam Stringul findAll in comanda de SQL
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Teacher> teachers = new ArrayList<>();
        if (resultSet != null){
            while (resultSet.next()){
                Teacher teacher = new Teacher();
                teacher.setTeacherId(resultSet.getLong("teacherId"));
                teacher.setFirstName(resultSet.getString("firstName"));
                teacher.setLastName(resultSet.getString("lastName"));
                teachers.add(teacher);
            }
        }
        return teachers;

    }

    @Override
    public Teacher update(Teacher obj) throws SQLException {

        return null;
    }

    @Override
    public void delete(long Id) throws SQLException {
        String delete = "DELETE FROM Teacher WHERE teacherId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setLong(1,Id);
        preparedStatement.executeUpdate();
    }
}
