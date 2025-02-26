package com.ecommerce.controladores;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.UsuarioDto;
import com.ecommerce.servicios.UsuarioServicio;
import com.ecommerce.utilidades.Utilidades;

/**
 * Controlador REST para la recuperación y actualización de contraseñas de usuarios.
 * <p>
 * Este controlador expone endpoints para:
 * <ul>
 *   <li>Actualizar la contraseña de un usuario utilizando un token.</li>
 *   <li>Recuperar una contraseña mediante el almacenamiento de un token y su fecha de expiración.</li>
 *   <li>Obtener la fecha de expiración de un token en formato timestamp.</li>
 *   <li>Listar todos los usuarios.</li>
 * </ul>
 * Se asume que la validación de seguridad (por ejemplo, comprobación de token) se realiza en el Dynamic Web Project.
 * </p>
 */
@RestController
@RequestMapping("/api/usuarios")
public class RecuperarContraseniaControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    /**
     * Actualiza la contraseña de un usuario.
     * <p>
     * Recibe un objeto JSON que debe contener el token y la nueva contraseña.
     * Se invoca el servicio de actualización de contraseña y se retorna un mensaje
     * indicando si la operación fue exitosa o no.
     * </p>
     *
     * @param request un Map que contiene:
     *                <ul>
     *                   <li>"token": token asociado al usuario.</li>
     *                   <li>"nuevaContrasenia": la nueva contraseña a establecer.</li>
     *                </ul>
     * @return un ResponseEntity con un mensaje de éxito o error.
     */
    @PostMapping("/actualizarContrasenia")
    public ResponseEntity<String> actualizarContrasenia(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String nuevaContrasenia = request.get("nuevaContrasenia");

        try {
            boolean actualizado = usuarioServicio.actualizarContrasenia(token, nuevaContrasenia);
            if (actualizado) {
                Utilidades.escribirLog("INFO", "RecuperarContraseniaControlador", "actualizarContrasenia", "Contraseña actualizada exitosamente para el token: " + token);
                return ResponseEntity.ok("Contraseña actualizada exitosamente");
            } else {
                Utilidades.escribirLog("ERROR", "RecuperarContraseniaControlador", "actualizarContrasenia", "Error al actualizar la contraseña para el token: " + token);
                return ResponseEntity.internalServerError().body("Error al actualizar la contraseña");
            }
        } catch (Exception e) {
            Utilidades.escribirLog("ERROR", "RecuperarContraseniaControlador", "actualizarContrasenia", "Error actualizando la contraseña: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error actualizando la contraseña");
        }
    }

    /**
     * Guarda un token para recuperar la contraseña de un usuario.
     * <p>
     * Recibe un objeto JSON con el token, el correo del usuario y la fecha de expiración (en formato timestamp como String).
     * Se convierte la fecha de expiración a un objeto java.sql.Date y se guarda el token mediante el servicio.
     * </p>
     *
     * @param request un Map que contiene:
     *                <ul>
     *                   <li>"token": el token a guardar.</li>
     *                   <li>"correo": el correo del usuario.</li>
     *                   <li>"fechaExpiracion": fecha de expiración en formato timestamp (String).</li>
     *                </ul>
     * @return un ResponseEntity con un mensaje de confirmación o error.
     */
    @PostMapping("/recuperarContrasenia")
    public ResponseEntity<String> recuperarContrasenia(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String correo = request.get("correo");
        String fechaExpiracionString = request.get("fechaExpiracion");

        try {
            if (fechaExpiracionString != null && !fechaExpiracionString.isEmpty()) {
                long fechaExpiracionLong = Long.parseLong(fechaExpiracionString);
                Date fechaExpiracion = new Date(fechaExpiracionLong);
                java.sql.Date fechaExpiracionSql = new java.sql.Date(fechaExpiracion.getTime());

                boolean exito = usuarioServicio.guardarToken(token, correo, fechaExpiracionSql);
                if (exito) {
                    Utilidades.escribirLog("INFO", "RecuperarContraseniaControlador", "recuperarContrasenia", "Token guardado correctamente para el correo: " + correo);
                    return ResponseEntity.ok("El token se ha guardado correctamente");
                } else {
                    Utilidades.escribirLog("ERROR", "RecuperarContraseniaControlador", "recuperarContrasenia", "Error al guardar el token para el correo: " + correo);
                    return ResponseEntity.internalServerError().body("Error al guardar el token");
                }
            } else {
                Utilidades.escribirLog("ERROR", "RecuperarContraseniaControlador", "recuperarContrasenia", "Fecha de expiración no proporcionada o inválida para el token: " + token);
                return ResponseEntity.badRequest().body("Fecha de expiración no proporcionada o inválida");
            }
        } catch (NumberFormatException e) {
            Utilidades.escribirLog("ERROR", "RecuperarContraseniaControlador", "recuperarContrasenia", "Formato de fecha de expiración inválido: " + e.getMessage());
            return ResponseEntity.badRequest().body("Formato de fecha de expiración inválido");
        } catch (Exception e) {
            Utilidades.escribirLog("ERROR", "RecuperarContraseniaControlador", "recuperarContrasenia", "Error guardando el token: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error guardando el token");
        }
    }

    /**
     * Obtiene la fecha de expiración de un token.
     * <p>
     * Recibe el token como parámetro y utiliza el servicio para obtener la fecha de expiración asociada.
     * Se retorna la fecha de expiración en formato timestamp (Long). Si el token no existe, se retorna 404.
     * </p>
     *
     * @param token el token del que se desea obtener la fecha de expiración.
     * @return un ResponseEntity con la fecha de expiración en formato Long o un 404 si no se encuentra.
     */
    @GetMapping("/obtenerFechaExpiracionToken")
    public ResponseEntity<Long> obtenerFechaExpiracionToken(@RequestParam String token) {
        try {
            Date fechaExpiracion = usuarioServicio.obtenerFechaExpiracionToken(token);
            
            if (fechaExpiracion == null) {
                Utilidades.escribirLog("ERROR", "RecuperarContraseniaControlador", "obtenerFechaExpiracionToken", "Token no encontrado: " + token);
                return ResponseEntity.notFound().build();
            }
            
            Utilidades.escribirLog("INFO", "RecuperarContraseniaControlador", "obtenerFechaExpiracionToken", "Fecha de expiración obtenida para el token: " + token);
            return ResponseEntity.ok(fechaExpiracion.getTime());
        } catch (Exception e) {
            Utilidades.escribirLog("ERROR", "RecuperarContraseniaControlador", "obtenerFechaExpiracionToken", "Error al obtener la fecha de expiración del token: " + e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Lista todos los usuarios.
     * <p>
     * Invoca el servicio para obtener una lista de usuarios y la retorna en el cuerpo de la respuesta.
     * </p>
     *
     * @return un ResponseEntity que contiene la lista de usuarios en formato JSON.
     */
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
        try {
            List<UsuarioDto> usuariosDto = usuarioServicio.listarUsuarios();
            Utilidades.escribirLog("INFO", "RecuperarContraseniaControlador", "listarUsuarios", "Listado de usuarios recuperado. Total de usuarios: " + usuariosDto.size());
            return ResponseEntity.ok(usuariosDto);
        } catch (Exception e) {
            Utilidades.escribirLog("ERROR", "RecuperarContraseniaControlador", "listarUsuarios", "Error al listar los usuarios: " + e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
