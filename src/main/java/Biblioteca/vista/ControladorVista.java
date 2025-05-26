package Biblioteca.vista;





import java.util.Scanner;

public class ControladorVista {

    private Scanner scanner;

    public ControladorVista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Gestión de Libros");
        System.out.println("2. Gestión de Lectores");
        System.out.println("3. Salir");
    }

    public void mostrarMenuLibros() {
        System.out.println("\n--- Menú Libros ---");
        System.out.println("1. Agregar libro");
        System.out.println("2. Listar libros");
        System.out.println("3. Editar libro");
        System.out.println("4. Eliminar libro");
        System.out.println("5. Volver al menú principal");
    }

    public void mostrarMenuLectores() {
        System.out.println("\n--- Menú Lectores ---");
        System.out.println("1. Agregar lector");
        System.out.println("2. Listar lectores");
        System.out.println("3. Editar lector");
        System.out.println("4. Eliminar lector");
        System.out.println("5. Volver al menú principal");
    }

    public int leerEntero(String mensaje) {
        int numero = -1;
        while (true) {
            System.out.print(mensaje);
            try {
                numero = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
        return numero;
    }

    public String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
}

