package fuckingExam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaPersonas {
    public final Scanner teclado = new Scanner(System.in);

    public ArrayList<Person> listaPersonas() throws SQLException {
        DataBaseManager db = new DataBaseManager();
        ArrayList<Person> listaPersonas = db.getPersonas();
        return listaPersonas;
    }

    public void crearFichero(ArrayList<Person> listaPersonas) throws IOException {
        String rutaFichero = "src/data/personas_dinero.txt";
        Path path = Paths.get(rutaFichero);
        Files.deleteIfExists(path);
        Files.createFile(path);

        String cadenaTexto = "";
        for (Person persona : listaPersonas) {
            int id = persona.getId();
            double euros = persona.getEuros();
            cadenaTexto += id + "," + euros + System.lineSeparator();

        }
        try {
            Files.write(path, cadenaTexto.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void imprimirFichero() throws IOException {
        String rutaFichero = "src/data/personas_dinero.txt";
        Path path = Paths.get(rutaFichero);
        System.out.println(Files.readString(path));
    }

    public ArrayList<Double> leerFicheroEuros() throws IOException {
        String rutaFichero = "src/data/personas_dinero.txt";
        Path path = Paths.get(rutaFichero);
        ArrayList<Double> listaEuros = new ArrayList<>();

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            List<String> lineas = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String linea : lineas) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                double euros = Double.parseDouble(datos[1]);
                listaEuros.add(euros);
            }
        }
        return listaEuros;
    }

}
