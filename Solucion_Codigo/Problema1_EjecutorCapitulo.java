/* @author = Jhojan Merino
Crea una estructura de clases que permita representar la información de un libro.
El libro está compuesto por capítulos.
A su vez, cada capítulo está formado por secciones.
Cada sección puede contener párrafos o figuras.
Y finalmente, cada párrafo está compuesto por palabras.
Se debe mostrar por pantalla la información del libro.
*/

class Palabra {

    String texto;

    public Palabra(String texto) {
        this.texto = texto;
    }

    public String toString() {
        return texto;
    }
}

class Sentencia {

    java.util.List<Palabra> palabras = new java.util.ArrayList<>();

    public void agregarPalabra(Palabra p) {
        palabras.add(p);
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Palabra p : palabras) {
            sb.append(p).append(" ");
        }

        return sb.toString().trim();
    }
}

class Parrafo {

    java.util.List<Sentencia> sentencias = new java.util.ArrayList<>();

    public void agregarSentencia(Sentencia s) {
        sentencias.add(s);
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Sentencia s : sentencias) {
            sb.append(s).append("\n");
        }

        return sb.toString();
    }
}

class Figura {

    String urlImagen;
    String pieDeFoto;

    public Figura(String url, String pie) {
        this.urlImagen = url;
        this.pieDeFoto = pie;
    }

    public String toString() {

        return "[Figura]\n"
                + "URL: " + urlImagen
                + "\nPie: " + pieDeFoto;
    }
}

class Seccion {

    String titulo;

    java.util.List<Object> componentes = new java.util.ArrayList<>();

    public Seccion(String titulo) {
        this.titulo = titulo;
    }

    public void agregarComponente(Object comp) {
        componentes.add(comp);
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Sección: ")
                .append(titulo)
                .append("\n");

        for (Object c : componentes) {
            sb.append(c).append("\n");
        }

        return sb.toString();
    }
}

class Capitulo {

    String titulo;
    int numero;

    java.util.List<Seccion> secciones = new java.util.ArrayList<>();

    public Capitulo(String titulo, int numero) {

        this.titulo = titulo;
        this.numero = numero;
    }

    public void agregarSeccion(Seccion s) {

        secciones.add(s);
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Capítulo ")
                .append(numero)
                .append(": ")
                .append(titulo)
                .append("\n\n");

        for (Seccion s : secciones) {
            sb.append(s);
        }

        return sb.toString();
    }
}

public class Problema1_EjecutorCapitulo {


    public static void main(String[] args) {

        Palabra p1 = new Palabra("Hola");
        Palabra p2 = new Palabra("mundo");
        Palabra p3 = new Palabra("Java");

        Sentencia sentencia = new Sentencia();

        sentencia.agregarPalabra(p1);
        sentencia.agregarPalabra(p2);
        sentencia.agregarPalabra(p3);

        Parrafo parrafo = new Parrafo();

        parrafo.agregarSentencia(sentencia);

        Figura figura = new Figura(
                "imagen.jpg",
                "Esta es una figura de ejemplo");

        Seccion seccion = new Seccion("Introducción");

        seccion.agregarComponente(parrafo);

        seccion.agregarComponente(figura);

        Capitulo capitulo = new Capitulo(
                "Programación en Java",
                1);

        capitulo.agregarSeccion(seccion);

        System.out.println(capitulo);
    }
}
/*
--- Estructura del Libro ---

Capítulo 1: Programación en Java

Sección: Introducción

Hola mundo Java 

[Figura]
URL: imagen.jpg
Pie: Esta es una figura de ejemplo


*/