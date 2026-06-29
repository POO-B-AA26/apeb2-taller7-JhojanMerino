/* @author: Jhojan Merino
 * @version: 1.0
 * 
 * Se desea gestionar la venta de entradas para un espectáculo en un teatro.
 * Las entradas pueden ser normales, reducidas o abonadas.
 * Cada entrada tiene identificador, zona y comprador.
 */

class Zona {

    private String nombre;
    private double precioNormal;
    private double precioAbonado;

    public Zona(String nombre, double precioNormal, double precioAbonado) {

        this.nombre = nombre;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioNormal() {
        return precioNormal;
    }

    public double getPrecioAbonado() {
        return precioAbonado;
    }

}

class Entrada {

    protected int id;
    protected Zona zona;
    protected String comprador;

    public Entrada(int id, Zona zona, String comprador) {

        this.id = id;
        this.zona = zona;
        this.comprador = comprador;
    }

    public double calcularPrecio() {

        return zona.getPrecioNormal();
    }

    public String mostrar() {

        return "ID: " + id +
                " | Comprador: " + comprador +
                " | Zona: " + zona.getNombre() +
                " | Precio: $" + calcularPrecio();
    }

}

class EntradaReducida extends Entrada {

    public EntradaReducida(int id, Zona zona, String comprador) {

        super(id, zona, comprador);
    }

    @Override
    public double calcularPrecio() {

        return zona.getPrecioNormal() * 0.85;
    }

}

class EntradaAbonado extends Entrada {

    public EntradaAbonado(int id, Zona zona, String comprador) {

        super(id, zona, comprador);
    }

    @Override
    public double calcularPrecio() {

        return zona.getPrecioAbonado();
    }

}

public class Problema5_EjecutorTeatro {

    public static void main(String[] args) {

        Zona principal = new Zona(
                "Principal",
                25,
                17.5);

        Zona central = new Zona(
                "Central",
                20,
                14);

        Zona palco = new Zona(
                "PalcoB",
                70,
                40);

        Entrada entrada1 = new Entrada(
                1,
                principal,
                "Juan Perez");

        Entrada entrada2 = new EntradaReducida(
                2,
                central,
                "Maria Garcia");

        Entrada entrada3 = new EntradaAbonado(
                3,
                palco,
                "Carlos Lopez");

        System.out.println("=== ENTRADAS ===");

        System.out.println(entrada1.mostrar());

        System.out.println(entrada2.mostrar());

        System.out.println(entrada3.mostrar());

        double ingresos = entrada1.calcularPrecio()
                + entrada2.calcularPrecio()
                + entrada3.calcularPrecio();

        System.out.println("\nIngresos totales: $" + ingresos);

    }

}

/*
 * RUN:
 * 
 * === ENTRADAS ===
 * 
 * ID: 1 | Comprador: Juan Perez | Zona: Principal | Precio: $25.0
 * 
 * ID: 2 | Comprador: Maria Garcia | Zona: Central | Precio: $17.0
 * 
 * ID: 3 | Comprador: Carlos Lopez | Zona: PalcoB | Precio: $40.0
 * 
 * 
 * Ingresos totales: $82.0
 * 
 * BUILD SUCCESSFUL
 */