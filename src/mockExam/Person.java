package mockExam;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Person {
    private String nombre;
    private LocalDate fechaNacimiento;
    private int edad;
    private double euros;
    private int id;

    public Person(String nombre, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = calcularEdad();
        this.euros=randomEuros();
        this.id= 0;
    }

    public int calcularEdad() {
        int edad;
        LocalDate fechaActual = LocalDate.now();
        edad = Period.between(fechaNacimiento,fechaActual).getYears();
        return edad;
    }

    public double randomEuros() {
        double euros;
        Random random = new Random();
        return euros = random.nextDouble(1001);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getEuros() {
        return euros;
    }

    public void setEuros(double euros) {
        this.euros = euros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Person> listaPersonas() {
        ArrayList<Person> coleccion = new ArrayList<>();
        Random random = new Random();
        int year = random.nextInt(1930, 2021);
        int mes = random.nextInt(1, 13);
        int dia = random.nextInt(1, 29);
        LocalDate fecha = LocalDate.of(year, mes, dia);

        Person persona1 = new Person("Beatriz", fecha);
        Person persona2 = new Person("Elene", fecha);
        Person persona3 = new Person("Javier", fecha);
        Person persona4 = new Person("Rocío", fecha);
        Person persona5 = new Person("Jose", fecha);

        coleccion.add(persona1);
        coleccion.add(persona3);
        coleccion.add(persona2);
        coleccion.add(persona5);
        coleccion.add(persona4);

        return coleccion;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", edad=" + edad +
                ", euros=" + euros +
                ", id=" + id +
                '}';
    }
}
