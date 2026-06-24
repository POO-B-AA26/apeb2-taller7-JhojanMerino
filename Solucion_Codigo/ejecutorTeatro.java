/* @author: Jhojan Merino
 * @version: 1.0
 * @fecha: 2024-06-10
 *
Se desea gestionar la venta de entradas para un espectáculo en un teatro. El patio de butacas del teatro se divide en varias zonas, cada una identificada por su nombre. Los datos de las zonas son:
    NOMBRE ZONA   NÚMERO DE LOCALIDADES   PRECIO NORMAL   PRECIO ABONADO
    Principal     200                    25.0$           17.5$
    PalcoB        40                     70.0$           40.0$
    Central       400                    20.0$           14.0$
    Lateral       100                    15.5$           10.0$

Para comprar una entrada, el espectador indica la zona y presenta documento que justifique descuento (estudiante, abonado o pensionista). El vendedor crea la entrada del tipo apropiado y le asigna un identificador entero único. 
Una entrada tiene: identificador, zona y nombre del comprador.
Precios:
 - Normal: precio normal de la zona.
 - Reducida: 15% de descuento sobre el precio normal.
 - Abonado: precio abonado de la zona.
Operaciones: vender entrada, consultar entrada por id, listar entradas vendidas y calcular ingresos.
*/

class Zona {
    private String nombre;
    private int capacidad;
    private int localidadesVendidas;
    private double precioNormal;
    private double precioAbonado;

    public Zona(String nombre, int capacidad, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.localidadesVendidas = 0;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }

    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public int getLocalidadesVendidas() { return localidadesVendidas; }
    public double getPrecioNormal() { return precioNormal; }
    public double getPrecioAbonado() { return precioAbonado; }

    public boolean estaCompleta() {
        return localidadesVendidas >= capacidad;
    }

    public boolean venderLocalidad() {
        if (estaCompleta()) return false;
        localidadesVendidas++;
        return true;
    }
}

class Entrada {
    private int id;
    private String comprador;
    private Zona zona;
    protected String tipo; // "normal", "reducida", "abonado"

    public Entrada(int id, Zona zona, String comprador, String tipo) {
        this.id = id;
        this.zona = zona;
        this.comprador = comprador;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public Zona getZona() { return zona; }
    public String getComprador() { return comprador; }
    public String getTipo() { return tipo; }

    public double calcularPrecio() {
        return 0; // impl. en subclases
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + comprador + " | Zona: " + zona.getNombre() + " | Tipo: " + tipo + " | Precio: $" + calcularPrecio();
    }
}

class EntradaNormal extends Entrada {
    public EntradaNormal(int id, Zona zona, String comprador) {
        super(id, zona, comprador, "normal");
    }

    @Override
    public double calcularPrecio() {
        return getZona().getPrecioNormal();
    }
}

class EntradaReducida extends Entrada {
    private String tipoDescuento; // p.e. "estudiante", "pensionista"

    public EntradaReducida(int id, Zona zona, String comprador, String tipoDescuento) {
        super(id, zona, comprador, "reducida");
        this.tipoDescuento = tipoDescuento;
    }

    public String getTipoDescuento() { return tipoDescuento; }

    @Override
    public double calcularPrecio() {
        return getZona().getPrecioNormal() * 0.85;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | " + getComprador() + " | Zona: " + getZona().getNombre()
            + " | Tipo: " + getTipo() + " (" + tipoDescuento + ")" + " | Precio: $" + calcularPrecio();
    }
}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(int id, Zona zona, String comprador) {
        super(id, zona, comprador, "abonado");
    }

    @Override
    public double calcularPrecio() {
        return getZona().getPrecioAbonado();
    }
}

class Teatro {
    private String nombre;
    private Zona[] zonas;
    private Entrada[] entradas;
    private int numEntradas;
    private int numZonas;

    public Teatro(String nombre) {
        this.nombre = nombre;
        this.zonas = new Zona[4];
        this.entradas = new Entrada[1000];
        this.numEntradas = 0;
        this.numZonas = 0;
        inicializarZonas();
    }

    private void inicializarZonas() {
        zonas[0] = new Zona("Principal", 200, 25.0, 17.5);
        zonas[1] = new Zona("PalcoB", 40, 70.0, 40.0);
        zonas[2] = new Zona("Central", 400, 20.0, 14.0);
        zonas[3] = new Zona("Lateral", 100, 15.5, 10.0);
        numZonas = 4;
    }

    private Zona buscarZona(String nombreZona) {
        for (int i = 0; i < numZonas; i++) {
            if (zonas[i].getNombre().equalsIgnoreCase(nombreZona)) return zonas[i];
        }
        return null;
    }

    // según UML: venderEntrada(zona: String, comprador: String, tipo: String)
    public void venderEntrada(String nombreZona, String comprador, String tipo) {
        Zona z = buscarZona(nombreZona);
        if (z == null) {
            System.out.println("Zona no encontrada: " + nombreZona);
            return;
        }
        if (z.estaCompleta()) {
            System.out.println("No hay localidades disponibles en zona " + z.getNombre());
            return;
        }

        int id = numEntradas + 1;
        Entrada e = null;
        switch (tipo.toLowerCase()) {
            case "normal":
                e = new EntradaNormal(id, z, comprador);
                break;
            case "reducida":
                // sin detalle, ponemos "estudiante" por defecto si no se indica otro
                e = new EntradaReducida(id, z, comprador, "estudiante");
                break;
            case "abonado":
                e = new EntradaAbonado(id, z, comprador);
                break;
            default:
                System.out.println("Tipo de entrada no válido: " + tipo);
                return;
        }

        if (z.venderLocalidad()) {
            entradas[numEntradas] = e;
            numEntradas++;
            System.out.println("Entrada vendida: " + e);
        } else {
            System.out.println("Error al vender localidad en zona " + z.getNombre());
        }
    }

    public void consultarEntrada(int id) {
        for (int i = 0; i < numEntradas; i++) {
            if (entradas[i].getId() == id) {
                System.out.println("Consulta: " + entradas[i]);
                return;
            }
        }
        System.out.println("Entrada con ID " + id + " no encontrada.");
    }

    public void mostrarEntradas() {
        System.out.println("\n=== Entradas vendidas en " + nombre + " ===");
        for (int i = 0; i < numEntradas; i++) System.out.println(entradas[i]);
    }

    public double calcularIngresos() {
        double total = 0;
        for (int i = 0; i < numEntradas; i++) total += entradas[i].calcularPrecio();
        return total;
    }
}

public class ejecutorTeatro {
    public static void main(String[] args) {
        Teatro miTeatro = new Teatro("Teatro Nacional");

        miTeatro.venderEntrada("Principal", "Juan Pérez", "normal");
        miTeatro.venderEntrada("Central", "María García", "reducida");
        miTeatro.venderEntrada("PalcoB", "Carlos López", "abonado");
        miTeatro.venderEntrada("Lateral", "Ana Martínez", "normal");
        miTeatro.venderEntrada("Principal", "Pedro Sánchez", "reducida");

        miTeatro.consultarEntrada(3); // ejemplo de consulta
        miTeatro.mostrarEntradas();
        System.out.println("\nIngresos totales: $" + miTeatro.calcularIngresos());
    }
}
/*Entrada vendida: ID: 1 | Juan Pérez | Zona: Principal | Tipo: normal | Precio: $25.0
Entrada vendida: ID: 2 | María García | Zona: Central | Tipo: reducida (estudiante) | Precio: $17.0
Entrada vendida: ID: 3 | Carlos López | Zona: PalcoB | Tipo: abonado | Precio: $40.0
Entrada vendida: ID: 4 | Ana Martínez | Zona: Lateral | Tipo: normal | Precio: $15.5
Entrada vendida: ID: 5 | Pedro Sánchez | Zona: Principal | Tipo: reducida (estudiante) | Precio: $21.25
Consulta: ID: 3 | Carlos López | Zona: PalcoB | Tipo: abonado | Precio: $40.0  

=== Entradas vendidas en Teatro Nacional ===
ID: 1 | Juan Pérez | Zona: Principal | Tipo: normal | Precio: $25.0
ID: 2 | María García | Zona: Central | Tipo: reducida (estudiante) | Precio: $17.0
ID: 3 | Carlos López | Zona: PalcoB | Tipo: abonado | Precio: $40.0
ID: 4 | Ana Martínez | Zona: Lateral | Tipo: normal | Precio: $15.5
ID: 5 | Pedro Sánchez | Zona: Principal | Tipo: reducida (estudiante) | Precio: $21.25

Ingresos totales: $118.75
PS C:\Users\USER\OneDrive\Documentos\GitHub\apeb2-taller7-JhojanMerino>  */