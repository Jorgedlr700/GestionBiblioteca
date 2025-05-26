package Biblioteca.modelo;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LectorDAO {

    // Crear
    public boolean agregarLector(Lector lector) {
        String sql = "INSERT INTO lectores (nombre, email) VALUES (?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, lector.getNombre());
            ps.setString(2, lector.getEmail());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar lector: " + e.getMessage());
            return false;
        }
    }

    // Leer (Listar todos)
    public List<Lector> listarLectores() {
        List<Lector> lectores = new ArrayList<>();
        String sql = "SELECT * FROM lectores";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Lector lector = new Lector();
                lector.setId(rs.getInt("id"));
                lector.setNombre(rs.getString("nombre"));
                lector.setEmail(rs.getString("email"));
                lectores.add(lector);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar lectores: " + e.getMessage());
        }
        return lectores;
    }

    // Actualizar
    public boolean actualizarLector(Lector lector) {
        String sql = "UPDATE lectores SET nombre = ?, email = ? WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, lector.getNombre());
            ps.setString(2, lector.getEmail());
            ps.setInt(3, lector.getId());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar lector: " + e.getMessage());
            return false;
        }
    }

    // Eliminar
    public boolean eliminarLector(int id) {
        String sql = "DELETE FROM lectores WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar lector: " + e.getMessage());
            return false;
        }
    }
}
