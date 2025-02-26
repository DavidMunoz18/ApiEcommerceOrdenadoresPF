package com.ecommerce.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.servicios.ProductoServicio;
import com.ecommerce.servicios.UsuarioServicio;
import com.ecommerce.utilidades.Utilidades;

/**
 * Controlador encargado de manejar las operaciones de eliminación de usuarios y productos.
 * Proporciona endpoints para eliminar usuarios y productos del sistema.
 */
@RestController
@RequestMapping("/api/eliminar")
public class EliminarControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private ProductoServicio productoServicio;

    /**
     * Endpoint para eliminar un usuario por su ID.
     *
     * @param idUsuario El ID del usuario a eliminar.
     * @return Una respuesta HTTP con el resultado de la eliminación:
     *         <ul>
     *         <li>204 (NO CONTENT): Usuario eliminado exitosamente.</li>
     *         <li>404 (NOT FOUND): Usuario no encontrado.</li>
     *         <li>500 (INTERNAL SERVER ERROR): Error al eliminar el usuario.</li>
     *         </ul>
     */
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable("id") Long idUsuario) {
        String mensaje = "Iniciando ejecución con idUsuario: " + idUsuario;
        Utilidades.escribirLog("INFO", "EliminarControlador", "borrarUsuario", mensaje);

        try {
            boolean eliminado = usuarioServicio.borrarUsuario(idUsuario);
            if (eliminado) {
                mensaje = "Usuario eliminado exitosamente.";
                Utilidades.escribirLog("INFO", "EliminarControlador", "borrarUsuario", mensaje);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensaje);
            } else {
                mensaje = "Usuario no encontrado.";
                Utilidades.escribirLog("INFO", "EliminarControlador", "borrarUsuario", mensaje);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
            }
        } catch (Exception e) {
            mensaje = "Error al eliminar el usuario: " + e.getMessage();
            Utilidades.escribirLog("ERROR", "EliminarControlador", "borrarUsuario", mensaje);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
        }
    }
    
    /**
     * Endpoint para eliminar un producto por su ID.
     *
     * @param id El ID del producto a eliminar.
     * @return Una respuesta HTTP:
     *         <ul>
     *         <li>204 (NO CONTENT): Si el producto se elimina correctamente.</li>
     *         <li>404 (NOT FOUND): Si el producto no existe.</li>
     *         </ul>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            // Delegamos la lógica completamente al servicio
            productoServicio.eliminarProducto(id);
            return ResponseEntity.noContent().build(); // Respuesta 204 si se eliminó correctamente
        } catch (Exception e) {
            // En caso de cualquier error, devolver un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
