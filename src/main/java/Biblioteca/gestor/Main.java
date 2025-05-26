package Biblioteca.gestor;




import Biblioteca.controlador.LibroControlador;
import Biblioteca.controlador.LectorControlador;
import Biblioteca.modelo.LibroDAO;
import Biblioteca.modelo.LectorDAO;
import Biblioteca.vista.ControladorVista;

public class Main {
    public static void main(String[] args) {
        ControladorVista vista = new ControladorVista();
        LibroDAO libroDAO = new LibroDAO();
        LectorDAO lectorDAO = new LectorDAO();

        LibroControlador libroControlador = new LibroControlador(libroDAO, vista);
        LectorControlador lectorControlador = new LectorControlador(lectorDAO, vista);

        boolean salir = false;

        while (!salir) {
            vista.mostrarMenuPrincipal(); // Muestra el menú (void)
            int opcion = vista.leerEntero("Selecciona una opción: "); // Lee la opción (int)
            switch (opcion) {
                case 1:
                    libroControlador.menuLibros();
                    break;
                case 2:
                    lectorControlador.menuLectores();
                    break;
                case 3:
                    salir = true;
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }

    }
}
