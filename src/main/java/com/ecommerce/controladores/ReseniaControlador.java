package com.ecommerce.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.ReseniaDto;
import com.ecommerce.servicios.ReseniaServicio;
import com.ecommerce.utilidades.Utilidades;

/**
 * Controlador REST para gestionar las reseñas de productos.
 * <p>
 * Proporciona endpoints para agregar reseñas, obtener reseñas por producto y obtener reseñas por usuario.
 * </p>
 */
@RestController
@RequestMapping("/api/resenias")
public class ReseniaControlador {

    @Autowired
    private ReseniaServicio reseniaServicio;

    /**
     * Agrega una nueva reseña para un producto.
     * <p>
     * Antes de registrar la reseña, se valida que el producto y el usuario existan.
     * </p>
     *
     * @param reseniaDto DTO con la información de la reseña a agregar.
     * @return {@link ResponseEntity} con el mensaje de éxito o error.
     */
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarResenia(@RequestBody ReseniaDto reseniaDto) {
        try {
            // Validaciones mínimas para evitar datos incorrectos
            if (reseniaDto.getIdProducto() == null || reseniaDto.getIdUsuario() == null ||
                reseniaDto.getContenidoResena() == null || reseniaDto.getContenidoResena().trim().isEmpty() ||
                reseniaDto.getCalificacion() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos los campos son obligatorios.");
            }

            // Llamada al servicio para agregar la reseña
            ReseniaDto reseniaAgregada = reseniaServicio.agregarResenia(
                    reseniaDto.getIdProducto(),
                    reseniaDto.getIdUsuario(),
                    reseniaDto.getContenidoResena(),
                    reseniaDto.getCalificacion()
            );

            if (reseniaAgregada != null) {
                Utilidades.escribirLog("INFO", "ReseniaControlador", "agregarResenia", "Reseña agregada exitosamente: ProductoID=" + reseniaDto.getIdProducto() + ", UsuarioID=" + reseniaDto.getIdUsuario());
                return ResponseEntity.ok("Reseña agregada con éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Producto o usuario no encontrado.");
            }
        } catch (Exception e) {
            Utilidades.escribirLog("ERROR", "ReseniaControlador", "agregarResenia", "Error al agregar reseña: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar la reseña.");
        }
    }

    /**
     * Obtiene todas las reseñas de un producto específico.
     *
     * @param productoId ID del producto.
     * @return {@link ResponseEntity} con la lista de reseñas del producto.
     */
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<?> obtenerReseniasPorProducto(@PathVariable Long productoId) {
        try {
            Utilidades.escribirLog("INFO", "ReseniaControlador", "obtenerReseniasPorProducto", "Obtención de reseñas para el producto: ProductoID=" + productoId);
            return ResponseEntity.ok(reseniaServicio.obtenerReseniasPorProducto(productoId));
        } catch (Exception e) {
            Utilidades.escribirLog("ERROR", "ReseniaControlador", "obtenerReseniasPorProducto", "Error al obtener reseñas para producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener reseñas.");
        }
    }

    /**
     * Obtiene todas las reseñas realizadas por un usuario específico.
     *
     * @param usuarioId ID del usuario.
     * @return {@link ResponseEntity} con la lista de reseñas del usuario.
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> obtenerReseniasPorUsuario(@PathVariable Long usuarioId) {
        try {
            Utilidades.escribirLog("INFO", "ReseniaControlador", "obtenerReseniasPorUsuario", "Obtención de reseñas para el usuario: UsuarioID=" + usuarioId);
            return ResponseEntity.ok(reseniaServicio.obtenerReseniasPorUsuario(usuarioId));
        } catch (Exception e) {
            Utilidades.escribirLog("ERROR", "ReseniaControlador", "obtenerReseniasPorUsuario", "Error al obtener reseñas para usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener reseñas.");
        }
    }
}
