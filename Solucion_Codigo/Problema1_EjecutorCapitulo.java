/* 
@author = Jhojan Merino

Crea una estructura de clases que permita representar la información de un libro.
El libro está compuesto por capítulos.
A su vez, cada capítulo está formado por secciones.
Cada sección puede contener párrafos o figuras.
Y finalmente, cada párrafo está compuesto por palabras.
Se debe mostrar por pantalla la información del libro.
*/

class Contenido {

    protected String nombre;

    public Contenido(String nombre) {
        this.nombre = nombre;
    }

    public void mostrar() {
        System.out.println(nombre);
    }
}

class Parrafo extends Contenido {

    public Parrafo(String texto) {
        super(texto);
    }

    @Override
    public void mostrar() {
        System.out.println("Párrafo: " + nombre);
    }
}

class Figura extends Contenido {

    public Figura(String descripcion) {
        super(descripcion);
    }

    @Override
    public void mostrar() {
        System.out.println("Figura: " + nombre);
    }
}

class Seccion {

    private String titulo;
    private Contenido contenido1;
    private Contenido contenido2;

    public Seccion(String titulo) {
        this.titulo = titulo;
    }

    public void setContenido1(Contenido contenido1) {
        this.contenido1 = contenido1;
    }

    public void setContenido2(Contenido contenido2) {
        this.contenido2 = contenido2;
    }

    public void mostrar() {

        System.out.println("Sección: " + titulo);

        if (contenido1 != null) {
            contenido1.mostrar();
        }

        if (contenido2 != null) {
            contenido2.mostrar();
        }
    }
}

class Capitulo {

    private String titulo;
    private int numero;
    private Seccion seccion;

    public Capitulo(String titulo, int numero) {
        this.titulo = titulo;
        this.numero = numero;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public void mostrar() {

        System.out.println("Capítulo " + numero + ": " + titulo);

        if (seccion != null) {
            seccion.mostrar();
        }
    }
}

public class Problema1_EjecutorCapitulo {

    public static void main(String[] args) {

        Parrafo parrafo = new Parrafo("Hola mundo Java");

        Figura figura = new Figura("Esta es una figura de ejemplo");

        Seccion seccion = new Seccion("Introducción");

        seccion.setContenido1(parrafo);
        seccion.setContenido2(figura);

        Capitulo capitulo = new Capitulo("Programación en Java", 1);

        capitulo.setSeccion(seccion);

        capitulo.mostrar();
    }
}

/*
 * --------- Salida ---------
 * 
 * Capítulo 1: Programación en Java
 * Sección: Introducción
 * Párrafo: Hola mundo Java
 * Figura: Esta es una figura de ejemplo
 * 
 */