/* @author = Jhojan Merino
 * La empresa "Solución Total" requiere un sistema para gestionar las nóminas de sus empleados.
 * Los empleados pueden ser de cuatro tipos:
 * 1. Jefe: Tiene un sueldo fijo.
 * 2. Trabajador Fijo: Tiene un sueldo mensual fijo.
 * 3. Trabajador Comisionista: Su sueldo depende de las ventas que realiza (un porcentaje de ellas).
 * 4. Trabajador por Horas: Su sueldo depende de las horas trabajadas. Si trabaja más de 40 horas,
 * las extras se pagan al doble.
 * Se requiere mostrar por pantalla los datos de cada trabajador y su sueldo.
 */

class Trabajador {

    protected String nombre;
    protected String dni;

    public Trabajador(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public double calcularSueldo() {
        return 0;
    }

    public void mostrar() {
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
    }
}

class Jefe extends Trabajador {

    private double sueldo;

    public Jefe(String nombre, String dni, double sueldo) {
        super(nombre, dni);
        this.sueldo = sueldo;
    }

    @Override
    public double calcularSueldo() {
        return sueldo;
    }
}

class TrabajadorFijo extends Trabajador {

    private double sueldoMensual;

    public TrabajadorFijo(String nombre, String dni, double sueldoMensual) {
        super(nombre, dni);
        this.sueldoMensual = sueldoMensual;
    }

    @Override
    public double calcularSueldo() {
        return sueldoMensual;
    }
}

class TrabajadorComisionista extends Trabajador {

    private double ventas;
    private double comision;

    public TrabajadorComisionista(String nombre, String dni, double ventas, double comision) {
        super(nombre, dni);
        this.ventas = ventas;
        this.comision = comision;
    }

    @Override
    public double calcularSueldo() {
        return ventas * (comision / 100);
    }
}

class TrabajadorPorHoras extends Trabajador {

    private int horas;
    private double precioHora;

    public TrabajadorPorHoras(String nombre, String dni, int horas, double precioHora) {
        super(nombre, dni);
        this.horas = horas;
        this.precioHora = precioHora;
    }

    @Override
    public double calcularSueldo() {

        if (horas <= 40) {
            return horas * precioHora;
        } else {
            int extras = horas - 40;

            return (40 * precioHora) + (extras * precioHora * 2);
        }
    }
}

public class Problema4_EjecutorNomina {

    public static void main(String[] args) {

        Jefe jefe = new Jefe(
                "Carlos",
                "123456",
                2500);

        TrabajadorFijo fijo = new TrabajadorFijo(
                "Ana",
                "654321",
                1800);

        TrabajadorComisionista comisionista = new TrabajadorComisionista(
                "Luis",
                "456789",
                8000,
                10);

        TrabajadorPorHoras horas = new TrabajadorPorHoras(
                "Maria",
                "987654",
                45,
                15);

        System.out.println("=== JEFE ===");
        jefe.mostrar();
        System.out.println("Sueldo: " + jefe.calcularSueldo());

        System.out.println("\n=== TRABAJADOR FIJO ===");
        fijo.mostrar();
        System.out.println("Sueldo: " + fijo.calcularSueldo());

        System.out.println("\n=== COMISIONISTA ===");
        comisionista.mostrar();
        System.out.println("Sueldo: " + comisionista.calcularSueldo());

        System.out.println("\n=== POR HORAS ===");
        horas.mostrar();
        System.out.println("Sueldo: " + horas.calcularSueldo());

    }
}

/*
 * RUN:
 * 
 * === JEFE ===
 * Nombre: Carlos
 * DNI: 123456
 * Sueldo: 2500.0
 * 
 * === TRABAJADOR FIJO ===
 * Nombre: Ana
 * DNI: 654321
 * Sueldo: 1800.0
 * 
 * === COMISIONISTA ===
 * Nombre: Luis
 * DNI: 456789
 * Sueldo: 800.0
 * 
 * === POR HORAS ===
 * Nombre: Maria
 * DNI: 987654
 * Sueldo: 775.0
 * 
 * BUILD SUCCESSFUL
 */