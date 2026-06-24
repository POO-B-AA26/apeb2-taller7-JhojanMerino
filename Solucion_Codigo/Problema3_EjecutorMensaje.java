
/* @author = Jhojan Merino
 * Un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes
 * que se pueden enviar entre móviles, mensajes de texto (SMS) y mensajes que contienen imágenes
 * (MMS). Por un lado, los mensajes de texto contienen un mensaje en caracteres que se desea
 * enviar de un móvil a otro. Por otro lado, los mensajes que contienen imágenes almacenan
 * información sobre la imagen a enviar, la cual se representará por el nombre del fichero
 * que la contiene. Independientemente del tipo de mensaje, cada mensaje tendrá asociado
 * un remitente de dicho mensaje y un destinatario. Ambos estarán definidos obligatoriamente
 * por un número de móvil, y opcionalmente se podrá guardar información sobre su nombre.
 * Además, los métodos enviarMensaje y visualizarMensaje deben estar definidos.
 */

class Mensaje {

    protected String remitenteNumero;
    protected String remitenteNombre;
    protected String destinatarioNumero;
    protected String destinatarioNombre;

    public Mensaje(String remitenteNumero, String remitenteNombre,
            String destinatarioNumero, String destinatarioNombre) {

        this.remitenteNumero = remitenteNumero;

        this.remitenteNombre = (remitenteNombre == null || remitenteNombre.trim().isEmpty())
                ? "Sin Nombre"
                : remitenteNombre;

        this.destinatarioNumero = destinatarioNumero;

        this.destinatarioNombre = (destinatarioNombre == null || destinatarioNombre.trim().isEmpty())
                ? "Sin Nombre"
                : destinatarioNombre;
    }

    public String enviarMensaje() {

        return "Mensaje enviado correctamente";
    }

    public String visualizarMensaje() {

        return "Remitente: " + remitenteNumero
                + " (" + remitenteNombre + ") -> Destinatario: "
                + destinatarioNumero + " (" + destinatarioNombre + ")";
    }

    @Override
    public String toString() {

        return String.format(
                "Remitente: %s (%s) -> Destinatario: %s (%s)",
                remitenteNumero,
                remitenteNombre,
                destinatarioNumero,
                destinatarioNombre);
    }
}

class SMS extends Mensaje {

    private String mensajeText;

    public SMS(String remitenteNumero, String remitenteNombre,
            String destinatarioNumero, String destinatarioNombre,
            String mensajeText) {

        super(remitenteNumero, remitenteNombre,
                destinatarioNumero, destinatarioNombre);

        this.mensajeText = mensajeText;
    }

    @Override
    public String enviarMensaje() {

        return "SMS: Mensaje enviado con éxito de "
                + remitenteNumero + " a "
                + destinatarioNumero + ".";
    }

    @Override
    public String visualizarMensaje() {

        return String.format(
                "--- Mensaje de Texto (SMS) ---\n%s\nContenido: %s\n-----------------------------",
                super.toString(),
                mensajeText);
    }

    @Override
    public String toString() {

        return super.toString()
                + " | Tipo: SMS | Mensaje: \""
                + mensajeText + "\"";
    }
}

class MMS extends Mensaje {

    private String nombreFichero;

    public MMS(String remitenteNumero, String remitenteNombre,
            String destinatarioNumero, String destinatarioNombre,
            String nombreFichero) {

        super(remitenteNumero, remitenteNombre,
                destinatarioNumero, destinatarioNombre);

        this.nombreFichero = nombreFichero;
    }

    @Override
    public String enviarMensaje() {

        return "MMS: Mensaje multimedia enviado con éxito de "
                + remitenteNumero + " a "
                + destinatarioNumero + ".";
    }

    @Override
    public String visualizarMensaje() {

        return String.format(
                "--- Mensaje Multimedia (MMS) ---\n%s\nFichero Imagen: %s.png\n---------------------------------",
                super.toString(),
                nombreFichero);
    }

    @Override
    public String toString() {

        return super.toString()
                + " | Tipo: MMS | Fichero: \""
                + nombreFichero + "\"";
    }
}

public class Problema3_EjecutorMensaje {

    public static void main(String[] args) {

        SMS sms = new SMS(
                "0987654321",
                "Jhojan Merino",
                "0991234567",
                "Ana Lopez",
                "Hola Ana, ya terminé el taller.");

        MMS mms = new MMS(
                "0987654321",
                "Jhojan Merino",
                "0998887777",
                null,
                "Mensajes");

        System.out.println(mms.enviarMensaje());

        System.out.println();

        System.out.println(sms.visualizarMensaje());

        System.out.println();

        System.out.println(mms.visualizarMensaje());
    }
}
/*
 * --- Mensaje de Texto (SMS) ---
 * Remitente: 0987654321 (Jhojan Merino) -> Destinatario: 0991234567 (Ana Lopez)
 * Contenido: Hola Ana, ya terminé el taller.
 * -----------------------------
 * 
 * --- Mensaje Multimedia (MMS) ---
 * Remitente: 0987654321 (Jhojan Merino) -> Destinatario: 0998887777 (Sin
 * Nombre)
 * Fichero Imagen: Mensajes.png
 * ---------------------------------
 */