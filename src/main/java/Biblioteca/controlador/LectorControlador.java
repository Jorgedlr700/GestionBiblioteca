package Biblioteca.controlador;

import Biblioteca.modelo.Lector;
import Biblioteca.modelo.LectorDAO;
import Biblioteca.consola.ControladorConsola;

import java.util.List;

public class LectorControlador {

    private LectorDAO lectorDAO;
    private ControladorConsola vista;

    public LectorControlador(LectorDAO lectorDAO, ControladorConsola vista) {
        this.lectorDAO = lectorDAO;
        this.vista = vista;
    }

    public void menuLectores() {
        int opcion;
        do {
            vista.mostrarMenuLectores();
            opcion = vista.leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> agregarLector();
                case 2 -> listarLectores();
                case 3 -> editarLector();
                case 4 -> eliminarLector();
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void agregarLector() {
        System.out.println("--- Agregar Nuevo Lector ---");
        String nombre = vista.leerCadena("Nombre: ");
        String email = vista.leerCadena("Email: ");

        Lector lector = new Lector(0, nombre, email);
        if (lectorDAO.agregarLector(lector)) {
            System.out.println("Lector agregado correctamente.");
        } else {
            System.out.println("Error al agregar lector.");
        }
    }

    private void listarLectores() {
        System.out.println("--- Lista de Lectores ---");
        List<Lector> lectores = lectorDAO.listarLectores();
        if (lectores.isEmpty()) {
            System.out.println("No hay lectores disponibles.");
        } else {
            for (Lector lector : lectores) {
                System.out.printf("ID: %d | Nombre: %s | Email: %s%n",
                        lector.getId(), lector.getNombre(), lector.getEmail());
            }
        }
    }

    private void editarLector() {
        System.out.println("--- Editar Lector ---");
        int id = vista.leerEntero("Ingrese ID del lector a editar: ");

        Lector lector = lectorDAO.listarLectores()
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);

        if (lector == null) {
            System.out.println("Lector no encontrado.");
            return;
        }

        String nuevoNombre = vista.leerCadena("Nuevo nombre (enter para mantener: " + lector.getNombre() + "): ");
        String nuevoEmail = vista.leerCadena("Nuevo email (enter para mantener: " + lector.getEmail() + "): ");

        if (!nuevoNombre.isEmpty()) lector.setNombre(nuevoNombre);
        if (!nuevoEmail.isEmpty()) lector.setEmail(nuevoEmail);

        if (lectorDAO.actualizarLector(lector)) {
            System.out.println("Lector actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar lector.");
        }
    }

    private void eliminarLector() {
        System.out.println("--- Eliminar Lector ---");
        int id = vista.leerEntero("Ingrese ID del lector a eliminar: ");
        if (lectorDAO.eliminarLector(id)) {
            System.out.println("Lector eliminado correctamente.");
        } else {
            System.out.println("Error al eliminar lector o no existe el ID.");
        }
    }
}
