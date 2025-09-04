import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BancoApp {
    private static Map<String, Double> cuentas = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String cuentaActual;

    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema bancario");
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
        if (monto <= 0) {
            System.out.println("Monto inválido. Debe ser mayor a cero.");
            return;
        }
        cuentas.put(cuentaActual, cuentas.get(cuentaActual) + monto);
        System.out.println("Depósito exitoso. Nuevo saldo: " + cuentas.get(cuentaActual));
    }

    private static void retirarPropio() {
        System.out.print("Ingrese el monto a retirar: ");
    }

    private static void depositarOtraCuenta() {
        System.out.print("Ingrese el número de cuenta destino: ");
        String cuentaDestino = scanner.nextLine();
        System.out.println("Ingrese el monto a depositar: ");
        double monto = leerMonto();
        if (monto <= 0) {
            System.out.println("Monto inválido. Debe ser mayor a cero.");
            return;
        }
        cuentas.putIfAbsent(cuentaActual, 0.0);
        cuentas.put(cuentaDestino, cuentas.get(cuentaDestino) + monto);
        System.out.println("Deposito exitoso a la cuenta " + cuentaDestino + ". Nuevo saldo: " + cuentas.get(cuentaDestino));
    }

    private static void mostrarSaldo() {
        System.out.println("Saldo actual: " + cuentas.get(cuentaActual));
    }

    private static double leerMonto() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
