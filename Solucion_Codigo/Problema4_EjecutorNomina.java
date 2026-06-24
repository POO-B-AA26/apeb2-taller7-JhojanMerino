/* @author = Jhojan Merino
 * La empresa "Solución Total" requiere un sistema para gestionar las nóminas de sus empleados.
 * Los empleados pueden ser de cuatro tipos:
 * 1. Jefe: Tiene un sueldo fijo.
 * 2. Trabajador Fijo: Tiene un sueldo mensual fijo.
 * 3. Trabajador Comisionista: Su sueldo depende de las ventas que realiza (un porcentaje de ellas).
 * 4. Trabajador por Horas: Su sueldo depende de las horas trabajadas. Si trabaja más de 40 horas, las extras se pagan al doble.
 * Se requiere mostrar por pantalla los datos de cada trabajador y su sueldo.
 */

public class Problema4_EjecutorNomina {

    static class Trabajador {
        protected String nombre;
        protected String apellidos;
        protected String direccion;
        protected String dni;

        public Trabajador(String nombre, String apellidos, String direccion, String dni) {
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.direccion = direccion;
            this.dni = dni;
        }

        public double calcularSueldo() {
            return 0.0;
        }

        @Override
        public String toString() {
            return "Trabajador{" +
                    "nombre='" + nombre + '\'' +
                    ", apellidos='" + apellidos + '\'' +
                    ", direccion='" + direccion + '\'' +
                    ", dni='" + dni + '\'' +
                    '}';
        }
    }

    static class Jefe extends Trabajador {
        private double sueldoFijo;

        public Jefe(String nombre, String apellidos, String direccion,
                String dni, double sueldoFijo) {
            super(nombre, apellidos, direccion, dni);
            this.sueldoFijo = sueldoFijo;
        }

        @Override
        public double calcularSueldo() {
            return sueldoFijo;
        }

        @Override
        public String toString() {
            return "Jefe{" + super.toString()
                    + ", sueldoFijo=" + sueldoFijo + '}';
        }
    }

    static class TrabajadorFijo extends Trabajador {
        private double sueldoMensual;

        public TrabajadorFijo(String nombre, String apellidos,
                String direccion, String dni,
                double sueldoMensual) {
            super(nombre, apellidos, direccion, dni);
            this.sueldoMensual = sueldoMensual;
        }

        @Override
        public double calcularSueldo() {
            return sueldoMensual;
        }

        @Override
        public String toString() {
            return "TrabajadorFijo{" + super.toString()
                    + ", sueldoMensual=" + sueldoMensual + '}';
        }
    }

    static class TrabajadorComisionista extends Trabajador {
        private double ventasRealizadas;
        private double porcentajeComision;

        public TrabajadorComisionista(String nombre, String apellidos,
                String direccion, String dni,
                double porcentajeComision) {
            super(nombre, apellidos, direccion, dni);
            this.porcentajeComision = porcentajeComision;
            this.ventasRealizadas = 0.0;
        }

        public void setVentasRealizadas(double ventas) {
            this.ventasRealizadas = ventas;
        }

        @Override
        public double calcularSueldo() {
            return ventasRealizadas * (porcentajeComision / 100);
        }

        @Override
        public String toString() {
            return "TrabajadorComisionista{" + super.toString()
                    + ", ventasRealizadas=" + ventasRealizadas
                    + ", porcentajeComision=" + porcentajeComision + '}';
        }
    }

    static class TrabajadorPorHoras extends Trabajador {
        private int horasTrabajadas;
        private double precioHoraNormal;

        public TrabajadorPorHoras(String nombre, String apellidos,
                String direccion, String dni,
                double precioHoraNormal) {
            super(nombre, apellidos, direccion, dni);
            this.precioHoraNormal = precioHoraNormal;
            this.horasTrabajadas = 0;
        }

        public void setHorasTrabajadas(int horas) {
            this.horasTrabajadas = horas;
        }

        @Override
        public double calcularSueldo() {
            if (horasTrabajadas <= 40) {
                return horasTrabajadas * precioHoraNormal;
            } else {
                int horasExtras = horasTrabajadas - 40;
                return (40 * precioHoraNormal)
                        + (horasExtras * precioHoraNormal * 2);
            }
        }

        @Override
        public String toString() {
            return "TrabajadorPorHoras{" + super.toString()
                    + ", horasTrabajadas=" + horasTrabajadas
                    + ", precioHoraNormal=" + precioHoraNormal + '}';
        }
    }

    public static void main(String[] args) {

        Jefe jefe = new Jefe(
                "Carlos",
                "García",
                "Calle Principal 123",
                "12345678A",
                2500.0);

        TrabajadorFijo fijo = new TrabajadorFijo(
                "Ana",
                "López",
                "Av. Central 45",
                "87654321B",
                1800.0);

        TrabajadorComisionista comisionista = new TrabajadorComisionista(
                "Luis",
                "Martínez",
                "Calle Sol 67",
                "11223344C",
                12.5);

        comisionista.setVentasRealizadas(8500);

        TrabajadorPorHoras porHoras = new TrabajadorPorHoras(
                "María",
                "Rodríguez",
                "Plaza Mayor 8",
                "55667788D",
                15.0);

        porHoras.setHorasTrabajadas(48);

        System.out.println(jefe);
        System.out.println("Sueldo: " + jefe.calcularSueldo() + " €\n");

        System.out.println(fijo);
        System.out.println("Sueldo: " + fijo.calcularSueldo() + " €\n");

        System.out.println(comisionista);
        System.out.println("Sueldo: " + comisionista.calcularSueldo() + " €\n");

        System.out.println(porHoras);
        System.out.println("Sueldo: " + porHoras.calcularSueldo() + " €");
    }
}