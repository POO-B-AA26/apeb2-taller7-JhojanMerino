import java.util.Arrays;

/**
 * Problema 02: Un videoclub dispone de una serie de películas que pueden estar
 * en DVD (con capacidad en Gb.) o en VHS (una sola cinta por película y con
 * cierto tipo de cinta magnética). De las películas interesa guardar el título,
 * el autor, el año de edición y el idioma (o los idiomas, en caso de DVD).
 * El precio de alquiler de las películas varía en función del tipo de película.
 * Las DVD siempre son 10% más caras que las de VHS.
 *
 * @author Jhojan Merino
 * @version 1.0
 */

class Pelicula {

    private String titulo;
    private String autor;
    private int anio;

    public Pelicula(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    public void mostrar() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Año: " + anio);
    }
}

class Soporte {

    protected Pelicula pelicula;
    protected double precio;

    public Soporte(Pelicula pelicula, double precio) {
        this.pelicula = pelicula;
        this.precio = precio;
    }

    public double calcularAlquiler() {
        return precio;
    }

    public void mostrar() {
        pelicula.mostrar();
        System.out.println("Precio alquiler: $" + calcularAlquiler());
    }
}

class Dvd extends Soporte {

    private String[] idiomas;

    public Dvd(Pelicula pelicula, double precio, String[] idiomas) {
        super(pelicula, precio);
        this.idiomas = idiomas;
    }

    @Override
    public double calcularAlquiler() {
        return precio * 1.10;
    }

    @Override
    public void mostrar() {
        System.out.println("=== DVD ===");
        super.mostrar();
        System.out.println("Idiomas: " + Arrays.toString(idiomas));
    }
}

class Vhs extends Soporte {

    private String idioma;

    public Vhs(Pelicula pelicula, double precio, String idioma) {
        super(pelicula, precio);
        this.idioma = idioma;
    }

    @Override
    public void mostrar() {
        System.out.println("=== VHS ===");
        super.mostrar();
        System.out.println("Idioma: " + idioma);
    }
}

public class Problema_2_EjecutoVideoClub {

    public static void main(String[] args) {

        Pelicula pelicula = new Pelicula("El Mundial", "Mathew", 2026);

        String[] idiomas = { "Español", "Inglés" };

        Dvd dvd = new Dvd(pelicula, 20, idiomas);

        Vhs vhs = new Vhs(pelicula, 20, "Español");

        dvd.mostrar();

        System.out.println();

        vhs.mostrar();
    }
}

/**
 * RUN:
 *
 * === DVD ===
 * Título: El Mundial
 * Autor: Mathew
 * Año: 2026
 * Precio alquiler: $22.0
 * Idiomas: [Español, Inglés]
 *
 * === VHS ===
 * Título: El Mundial
 * Autor: Mathew
 * Año: 2026
 * Precio alquiler: $20.0
 * Idioma: Español
 *
 * BUILD SUCCESSFUL
 */