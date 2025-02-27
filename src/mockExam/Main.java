package mockExam;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws SQLException, IOException, InvalidDataException {
        Scanner teclado = new Scanner(System.in);
        int opcionMenu = printMenu();
        DataBaseManager db = new DataBaseManager();
        while (opcionMenu > 0 && opcionMenu < 11) {
            switch (opcionMenu) {
                case 1:
                    verListaPorNombre();
                    opcionMenu = printMenu();
                    break;
                case 2:
                    verListaPorEdad();
                    opcionMenu = printMenu();
                    break;
                case 3:
                    //
                    opcionMenu = printMenu();
                    break;
                case 4:
                    anhadirPersona();
                    opcionMenu = printMenu();
                    break;
                case 5:
                    eliminarPersona();
                    opcionMenu = printMenu();
                    break;
                case 6:
                    edadMedia();
                    opcionMenu = printMenu();
                    break;
                case 7:
                    imprimirFichero();
                    opcionMenu = printMenu();
                    break;
                case 8:
                    mayoresDeEdad();
                    opcionMenu = printMenu();
                    break;
                case 9:
                    dineroTotalMayores();
                    opcionMenu = printMenu();
                    break;
                case 10:
                    dineroTotalMayoresFicheros();
                    opcionMenu = printMenu();
                    break;

            }
        }
        if (opcionMenu == 11) {
            System.out.println("Gracias por haber usado nuestros servicios, ¡hasta pronto!");
        }
    }

    public static int printMenu() {
        Scanner teclado = new Scanner(System.in);
        int opcionMenu;
        System.out.println("Bienvenide a nuestro menú, indica que acción quieres realizar" + '\n' +
                "1. Ver lista de personas ordenada por nombre" + '\n' +
                "2. Ver lista de personas ordenada por edad" + '\n' +
                "3. Suma total del dinero de todas las personas mayores de edad" + '\n' +//desde el fichero
                "4. Añadir persona" + '\n' +
                "5. Eliminar persona" + '\n' +
                "6. Mostrar la edad media de todas las personas" + '\n' +
                "7. Mostrar por pantalla personas_dinero.txt" + '\n' +
                "8. Mostrar personas mayores de 18 años con streams" + '\n' +
                "9. Mostar y calcular suma total de dinero de personas mayores de 18 años con streams" + '\n' +
                "10. Mostar y calcular suma total de dinero de personas mayores de 18 años con streams" + '\n' +
                "11. Salir");
        opcionMenu = teclado.nextInt();
        return opcionMenu;
    }

    public static void anhadirPersona() throws SQLException, InvalidDataException {
        Scanner teclado = new Scanner(System.in);
        DataBaseManager db = new DataBaseManager();
        System.out.println("Dime el nombre de la persona a añadir:");
        String nombre = teclado.next();
        System.out.println("Ahora dime la fecha de nacimiento en el siguiente formato (aaaa-mm-dd):");

        LocalDate fechaNacimiento;
        fechaNacimiento= LocalDate.parse(teclado.next());

        Person persona = new Person(nombre, fechaNacimiento);
        db.insertPersonas(persona);
        //TODO: ACTUALIZAR FICHERO
    }

    public static void eliminarPersona() throws SQLException {
        Scanner teclado = new Scanner(System.in);
        DataBaseManager db = new DataBaseManager();
        System.out.println("Dime el nombre de la persona a eliminar:");
        int id = teclado.nextInt();
        db.eliminarPersona(id);
        //TODO: ACTUALIZAR FICHERO
    }

    public static void verListaPorNombre() throws SQLException {
        DataBaseManager db = new DataBaseManager();
        ArrayList<Person> listaPersonas = db.getPersonas();
        List<Person> personasOrdenadas = listaPersonas.stream()
                .sorted((p1, p2) -> p1.getNombre().compareTo(p2.getNombre()))
                .toList();
        for (Person person : listaPersonas) {
            System.out.println(person.toString());
        }
    }

    public static void verListaPorEdad() throws SQLException {
        DataBaseManager db = new DataBaseManager();
        ArrayList<Person> listaPersonas = db.getPersonas();
        List<Person> listaOrganizada = listaPersonas.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getEdad(), p1.getEdad()))
                .collect(Collectors.toList());
        for (Person person : listaOrganizada) {
            System.out.println(person.toString());
        }
    }

    public static void edadMedia() throws SQLException {
        DataBaseManager db = new DataBaseManager();
        ArrayList<Person> listaPersonas = db.getPersonas();
        int sumaEdades = 0;
        int mediaEdad;

        for (Person persona : listaPersonas) {
            sumaEdades = sumaEdades + persona.getEdad();
        }
        mediaEdad = sumaEdades / listaPersonas.size();
        System.out.println("La edad media de todas las personas es: " + mediaEdad);
    }

    public static void imprimirFichero() throws SQLException, IOException {
        ListaPersonas fichero = new ListaPersonas();
        ArrayList<Person> lista = fichero.listaPersonas();
        fichero.crearFichero(lista);
        fichero.imprimirFichero();
    }
    public static void mayoresDeEdad() throws SQLException {
        DataBaseManager db = new DataBaseManager();
        ArrayList<Person> listaPersonas = db.getPersonas();
        List<Person> listaMayores= listaPersonas.stream().filter(person -> person.getEdad()>18).toList();
        for (Person persona:listaMayores){
            System.out.println(persona.toString());
        }
    }
    public static void dineroTotalMayores() throws SQLException {
        DataBaseManager db = new DataBaseManager();
        ArrayList<Person> listaPersonas = db.getPersonas();
        double totalEuros=0;
        List<Person> listaMayores= listaPersonas.stream().filter(person -> person.getEdad()>18).toList();
        for (Person persona:listaMayores){
            totalEuros=totalEuros+ persona.getEuros();
        }
        System.out.println(totalEuros+ " euros en total");
    }
    public static void dineroTotalMayoresFicheros() throws SQLException, IOException {
        DataBaseManager db = new DataBaseManager();
        ArrayList<Person> listaPersonas = db.getPersonas();
        ListaPersonas lista=new ListaPersonas();
        double totalEuros=0;
        List<Person> listaMayores=listaPersonas.stream().filter(person -> person.getEdad()>18).toList();
        ArrayList<Person> listaPutoFinal = new ArrayList<>(listaMayores);
        lista.crearFichero(listaPutoFinal);
        ArrayList<Double> euros=lista.leerFicheroEuros();
        for (Double euro : euros) {
            totalEuros = totalEuros + euro;
        }
        System.out.println("El total de euros de los mayores de 18 años es: "+totalEuros);
    }



}
