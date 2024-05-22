package fuckingExam;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/personas";
    private static final String USSER = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    public DataBaseManager() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USSER, PASSWORD);
    }

    public void closeDB() throws SQLException {
        if (connection.isClosed() && connection != null) {
            connection.close();
        }
    }

    public void insertPersonas(Person persona) throws SQLException {
        String query = "INSERT INTO personas (nombre, fechaNacimiento, edad, euros) VALUES (?,?,?,?);";
        PreparedStatement st = connection.prepareStatement(query);
        String fecha = String.valueOf(persona.getFechaNacimiento());
        st.setString(1, persona.getNombre());
        st.setString(2, fecha);
        st.setInt(3, persona.getEdad());
        st.setDouble(4, persona.getEuros());
        int personaInsertada = st.executeUpdate();
        if (personaInsertada > 0) {
            System.out.println("Persona insertada en la base de datos");
        }
    }

    public void eliminarPersona(int id) throws SQLException {
        String query = "DELETE FROM personas WHERE id=?;";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, id);
        int personaeliminada = st.executeUpdate();
        if (personaeliminada > 0) {
            System.out.println("Persona eliminada con exito");
        }
    }

    public ArrayList<Person> getPersonas() throws SQLException {
        ArrayList<Person> listaPersonas = new ArrayList<>();
        String query = "SELECT * FROM personas;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            LocalDate fechaNacimiento = LocalDate.parse(rs.getString("fechaNacimiento"));
            //int edad=rs.getInt("edad");
            double euros = rs.getDouble("euros");
            Person persona = new Person(nombre, fechaNacimiento);
            persona.setEuros(euros);
            persona.setId(id);
            listaPersonas.add(persona);
        }

        return listaPersonas;
    }
}
