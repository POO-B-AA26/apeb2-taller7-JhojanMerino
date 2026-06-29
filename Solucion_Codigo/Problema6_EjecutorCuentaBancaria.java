/*@author Jhojan Merino 
  *fecha: 27/06/2026
  *version: 1.0
  El banco UN BANCO mantiene las cuentas de varios clientes. Los datos que describen a cada una de las cuentas consisten en el número de cuenta, el nombre del cliente y el balance actual. Escriba una clase para implementar dicha cuenta bancaria. El método constructor debe aceptar como parámetros el número de cuenta y el nombre. Debe proporcionarse métodos para depositar o retirar una cantidad de dinero y obtener el balance actual.

El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS. Una cuenta de cheques puede sobregirarse (el balance puede ser menor que cero), pero una cuenta de ahorros no. Al final de cada mes, se calcula el interés sobre la cantidad que tenga la cuenta de ahorros. Este interés se suma al balance. Escriba clases para describir cada uno de estos tipos de cuentas, haciendo un máximo uso de la herencia. La clase de la cuenta de ahorros debe proporcionar un método que sea invocado para calcular el interés. Además, el banco está pensando en implementar una cuenta PLATINO que viene siendo similar a los otros dos tipos anteriores de cuentas bancarias, ésta tiene el interés del 10%, sin cargos ni castigos por sobregiro.


  */
class Cuenta {
    private String nroCuenta;
    private String nombCliente;
    private double saldo;
    private double deposito;
    private double retiro;
    private double balanceAct;

    public Cuenta(String nroCuenta, String nombCliente, double saldo, double deposito, double retiro) {
        this.nroCuenta = nroCuenta;
        this.nombCliente = nombCliente;
        this.saldo = saldo;
        this.deposito = deposito;
        this.retiro = retiro;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public String getNombCliente() {
        return nombCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getDeposito() {
        return deposito;
    }

    public double getRetiro() {
        return retiro;
    }

    public double getBalanceAct() {
        return balanceAct;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double calcularBalance() {
        balanceAct = saldo;
        saldo = saldo + deposito - retiro;
        return saldo;
    }

    public double calcularInteres() {
        return saldo * 0.02;

    }

}

class cuentaCheques extends Cuenta {

    public cuentaCheques(String nroCuenta, String nombCliente, double saldo, double deposito, double retiro) {
        super(nroCuenta, nombCliente, saldo, deposito, retiro);
    }

    public void sobreCargo(double monto) {
        if (this.getSaldo() < monto) {
            setSaldo(getSaldo() - monto - (0.02 * monto));
        }

    }
}

class CuentaAhorros extends Cuenta {
    public CuentaAhorros(String nroCuenta, String nombCliente, double saldo, double deposito, double retiro) {
        super(nroCuenta, nombCliente, saldo, deposito, retiro);
    }

    public double calcularBalance() {
        double balance = super.calcularBalance();
        balance += calcularInteres();
        super.setSaldo(balance);
        return balance;
    }

}

class CuentaPlatino extends Cuenta {
    public CuentaPlatino(String nroCuenta, String nombCliente, double saldo, double deposito, double retiro) {
        super(nroCuenta, nombCliente, saldo, deposito, retiro);
    }

    @Override
    public double calcularInteres() {
        return getSaldo() * 0.10;
    }

    public double calcularBalance() {
        double balance = super.calcularBalance();
        balance += calcularInteres();
        super.setSaldo(balance);
        return balance;
    }

}

public class Problema6_EjecutorCuentaBancaria {
    public static void main(String[] args) {
        CuentaAhorros cuentaAhorros = new CuentaAhorros("123456789", "Jhojan Merino", 1000, 500, 200);
        System.out.println("Número de Cuenta: " + cuentaAhorros.getNroCuenta());
        System.out.println("Nombre del Cliente: " + cuentaAhorros.getNombCliente());
        System.out.println("Saldo: " + cuentaAhorros.getSaldo());
        System.out.println("Depósito: " + cuentaAhorros.getDeposito());
        System.out.println("Retiro: " + cuentaAhorros.getRetiro());
        System.out.println("Balance Actual: " + cuentaAhorros.calcularBalance());

        cuentaCheques cuentaCheques = new cuentaCheques("123456789", "Allysson Sanchez", 2000, 600, 300);
        System.out.println("Número de Cuenta: " + cuentaCheques.getNroCuenta());
        System.out.println("Nombre del Cliente: " + cuentaCheques.getNombCliente());
        System.out.println("Saldo: " + cuentaCheques.getSaldo());
        System.out.println("Depósito: " + cuentaCheques.getDeposito());
        System.out.println("Retiro: " + cuentaCheques.getRetiro());
        System.out.println("Balance Actual: " + cuentaCheques.calcularBalance());

        CuentaPlatino cuentaPlatino = new CuentaPlatino("123456789", "Jhojan Merino", 1000, 500, 200);
        System.out.println("Número de Cuenta: " + cuentaPlatino.getNroCuenta());
        System.out.println("Nombre del Cliente: " + cuentaPlatino.getNombCliente());
        System.out.println("Saldo: " + cuentaPlatino.getSaldo());
        System.out.println("Depósito: " + cuentaPlatino.getDeposito());
        System.out.println("Retiro: " + cuentaPlatino.getRetiro());
        System.out.println("Balance Actual: " + cuentaPlatino.calcularBalance());

    }

}
/*
 * Número de Cuenta: 123456789
 * Nombre del Cliente: Jhojan Merino
 * Saldo: 1000.0
 * Depósito: 500.0
 * Retiro: 200.0
 * Balance Actual: 1326.0
 * Número de Cuenta: 123456789
 * Nombre del Cliente: Allysson Sanchez
 * Saldo: 2000.0
 * Depósito: 600.0
 * Retiro: 300.0
 * Balance Actual: 2300.0
 */