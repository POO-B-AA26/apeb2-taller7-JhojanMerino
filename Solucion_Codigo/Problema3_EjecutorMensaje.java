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

    protected String remitente;
    protected String destinatario;

    public Mensaje(String remitente, String destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    public void enviarMensaje() {
        System.out.println("Mensaje enviado.");
    }

    public void visualizarMensaje() {
        System.out.println("Remitente: " + remitente);
        System.out.println("Destinatario: " + destinatario);
    }
}

class SMS extends Mensaje {

    private String texto;

    public SMS(String remitente, String destinatario, String texto) {
        super(remitente, destinatario);
        this.texto = texto;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("SMS enviado.");
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("=== SMS ===");
        super.visualizarMensaje();
        System.out.println("Mensaje: " + texto);
    }
}

class MMS extends Mensaje {

    private String imagen;

    public MMS(String remitente, String destinatario, String imagen) {
        super(remitente, destinatario);
        this.imagen = imagen;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("MMS enviado.");
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("=== MMS ===");
        super.visualizarMensaje();
        System.out.println("Imagen: " + imagen);
    }
}

public class Problema3_EjecutorMensaje {

    public static void main(String[] args) {

        SMS sms = new SMS(
                "0987654321",
                "0991234567",
                "Hola Ana");

        MMS mms = new MMS(
                "0987654321",
                "0998887777",
                "foto.png");

        sms.enviarMensaje();
        sms.visualizarMensaje();

        System.out.println();

        mms.enviarMensaje();
        mms.visualizarMensaje();
    }
}

/*
 * RUN:
 * 
 * SMS enviado.
 * === SMS ===
 * Remitente: 0987654321
 * Destinatario: 0991234567
 * Mensaje: Hola Ana
 * 
 * MMS enviado.
 * === MMS ===
 * Remitente: 0987654321
 * Destinatario: 0998887777
 * Imagen: foto.png
 * 
 * BUILD SUCCESSFUL
 */