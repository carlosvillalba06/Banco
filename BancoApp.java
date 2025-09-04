import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Cuenta {
    private String numero;
    private double saldo;

    public Cuenta(String numero) {
        this.numero = numero;
        this.saldo = 0.0;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean depositar(double monto) {
        if (monto <= 0) return false;
        saldo += monto;
        return true;
    }

    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo) return false;
        saldo -= monto;
        return true;
    }
}

public class BancoApp {
    private static Map<String, Cuenta> cuentas = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Cuenta cuentaActual;

    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema bancario");
        iniciarSesion();
        while (true) {
            mostrarMenu();
            int opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    depositarPropio();
                    break;
                case 2:
                    retirarPropio();
                    break;
                case 3:
                    depositarOtraCuenta();
                    break;
                case 4:
                    mostrarSaldo();
                    break;
                case 5:
                    System.out.println("Gracias por usar el sistema. Adiós!");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private static void iniciarSesion() {
        System.out.print("Ingrese su número de cuenta: ");
        String numero = scanner.nextLine();
        cuentaActual = cuentas.get(numero);
        if (cuentaActual == null) {
            cuentaActual = new Cuenta(numero);
            cuentas.put(numero, cuentaActual);
        }
        System.out.println("Sesión iniciada en la cuenta " + cuentaActual.getNumero());
    }

    private static void mostrarMenu() {
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Depositar a mi cuenta");
        System.out.println("2. Retirar de mi cuenta");
        System.out.println("3. Depositar a otra cuenta");
        System.out.println("4. Consultar saldo");
        System.out.println("5. Salir");
    }

    private static int leerOpcion() {
        System.out.print("Opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void depositarPropio() {
        System.out.print("Ingrese el monto a depositar: ");
        double monto = leerMonto();
        if (cuentaActual.depositar(monto)) {
            System.out.println("Depósito exitoso. Nuevo saldo: " + cuentaActual.getSaldo());
        } else {
            System.out.println("Monto inválido. Debe ser mayor a cero.");
        }
    }

    private static void retirarPropio() {
        System.out.print("Ingrese el monto a retirar: ");
        double monto = leerMonto();
        if (cuentaActual.retirar(monto)) {
            System.out.println("Retiro exitoso. Nuevo saldo: " + cuentaActual.getSaldo());
        } else {
            System.out.println("Monto inválido o fondos insuficientes. Saldo actual: " + cuentaActual.getSaldo());
        }
    }

    private static void depositarOtraCuenta() {
        System.out.print("Ingrese el número de cuenta destino: ");
        String destino = scanner.nextLine();
        Cuenta cuentaDestino = cuentas.get(destino);
        if (cuentaDestino == null) {
            cuentaDestino = new Cuenta(destino);
            cuentas.put(destino, cuentaDestino);
        }
        System.out.print("Ingrese el monto a depositar: ");
        double monto = leerMonto();
        if (cuentaDestino.depositar(monto)) {
            System.out.println("Depósito exitoso a la cuenta " + destino + ". Nuevo saldo: " + cuentaDestino.getSaldo());
        } else {
            System.out.println("Monto inválido. Debe ser mayor a cero.");
        }
    }

    private static void mostrarSaldo() {
        System.out.println("Saldo actual: " + cuentaActual.getSaldo());
    }

    private static double leerMonto() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}