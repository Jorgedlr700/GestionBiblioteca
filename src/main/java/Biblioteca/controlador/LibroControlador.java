package Biblioteca.controlador;





import Biblioteca.modelo.Libro;
import Biblioteca.modelo.LibroDAO;
import Biblioteca.consola.ControladorConsola;

import java.util.List;

public class LibroControlador {

    private LibroDAO libroDAO;
    private ControladorConsola vista;

    public LibroControlador(LibroDAO libroDAO, ControladorConsola vista) {
        this.libroDAO = libroDAO;
        this.vista = vista;
    }

    public void menuLibros() {
        int opcion;
        do {
            vista.mostrarMenuLibros();
            opcion = vista.leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> agregarLibro();
                case 2 -> listarLibros();
                case 3 -> editarLibro();
                case 4 -> eliminarLibro();
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void agregarLibro() {
        System.out.println("--- Agregar Nuevo Libro ---");
        String titulo = vista.leerCadena("Título: ");
        String autor = vista.leerCadena("Autor: ");
        int anio = vista.leerEntero("Año publicación: ");

        Libro libro = new Libro(0, titulo, autor, anio);
        if (libroDAO.agregarLibro(libro)) {
            System.out.println("Libro agregado correctamente.");
        } else {
            System.out.println("Error al agregar libro.");
        }
    }

    private void listarLibros() {
        System.out.println("--- Lista de Libros ---");
        List<Libro> libros = libroDAO.listarLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros disponibles.");
        } else {
            for (Libro libro : libros) {
                System.out.printf("ID: %d | Título: %s | Autor: %s | Año: %d%n",
                        libro.getId(), libro.getTitulo(), libro.getAutor(), libro.getAnioPublicacion());
            }
        }
    }

    private void editarLibro() {
        System.out.println("--- Editar Libro ---");
        int id = vista.leerEntero("Ingrese ID del libro a editar: ");

        Libro libro = libroDAO.listarLibros()
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);

        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        String nuevoTitulo = vista.leerCadena("Nuevo título (enter para mantener: " + libro.getTitulo() + "): ");
        String nuevoAutor = vista.leerCadena("Nuevo autor (enter para mantener: " + libro.getAutor() + "): ");
        String anioStr = vista.leerCadena("Nuevo año publicación (enter para mantener: " + libro.getAnioPublicacion() + "): ");

        if (!nuevoTitulo.isEmpty()) libro.setTitulo(nuevoTitulo);
        if (!nuevoAutor.isEmpty()) libro.setAutor(nuevoAutor);
        if (!anioStr.isEmpty()) {
            try {
                int nuevoAnio = Integer.parseInt(anioStr);
                libro.setAnioPublicacion(nuevoAnio);
            } catch (NumberFormatException e) {
                System.out.println("Año no válido. Se mantiene el anterior.");
            }
        }

        if (libroDAO.actualizarLibro(libro)) {
            System.out.println("Libro actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar libro.");
        }
    }

    private void eliminarLibro() {
        System.out.println("--- Eliminar Libro ---");
        int id = vista.leerEntero("Ingrese ID del libro a eliminar: ");
        if (libroDAO.eliminarLibro(id)) {
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("Error al eliminar libro o no existe el ID.");
        }
    }
}

