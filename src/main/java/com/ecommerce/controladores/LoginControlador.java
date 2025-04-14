package com.ecommerce.controladores;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.LoginUsuarioDto;
import com.ecommerce.servicios.UsuarioServicio;
import com.ecommerce.utilidades.Utilidades;

/**
 * Controlador para gestionar la consulta de datos de usuario sin validación de contraseña.
 * <p>
 * Proporciona un endpoint para obtener los datos de un usuario basándose en su correo electrónico.
 * </p>
 */
@RestController
@RequestMapping("/api/login")
public class LoginControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    /**
     * Consulta y retorna los datos de un usuario a partir de su correo electrónico.
     * <p>
     * Se espera un objeto {@link LoginUsuarioDto} que contenga el email del usuario. El método
     * utiliza el servicio {@link UsuarioServicio} para obtener los datos y, en función del resultado,
     * retorna:
     * </p>
     * <ul>
     *     <li><b>200 OK</b> junto con un {@code Map<String, Object>} con los datos del usuario, si se encontró.</li>
     *     <li><b>404 Not Found</b> junto con un mensaje de error, si no se encontró el usuario.</li>
     * </ul>
     *
     * @param usuario Objeto {@link LoginUsuarioDto} que contiene el email del usuario a consultar.
     * @return {@link ResponseEntity} con el resultado de la consulta.
     */
    @PostMapping("/consultarUsuario")
    public ResponseEntity<Object> obtenerUsuario(@RequestBody LoginUsuarioDto usuario) {
        String mensaje = "Iniciando ejecución con email: " + usuario.getEmail();
        Utilidades.escribirLog("INFO", "LoginControlador", "obtenerUsuario", mensaje);

        try {
            // Se obtiene el usuario a través del email proporcionado
            Map<String, Object> usuarioEncontrado = usuarioServicio.obtenerDatosPorEmail(usuario.getEmail());

            if (usuarioEncontrado == null) {
                mensaje = "Usuario no encontrado con email: " + usuario.getEmail();
                Utilidades.escribirLog("INFO", "LoginControlador", "obtenerUsuario", mensaje);
                return ResponseEntity.status(404).body("Usuario no encontrado");
            }

            // Se retorna el Map con los datos del usuario encontrado
            mensaje = "Usuario encontrado con email: " + usuario.getEmail();
            Utilidades.escribirLog("INFO", "LoginControlador", "obtenerUsuario", mensaje);
            return ResponseEntity.status(200).body(usuarioEncontrado);

        } catch (Exception e) {
            mensaje = "Error al consultar usuario con email: " + usuario.getEmail() + ". Error: " + e.getMessage();
            Utilidades.escribirLog("ERROR", "LoginControlador", "obtenerUsuario", mensaje);
            return ResponseEntity.status(500).body("Error interno al obtener los datos del usuario");
        }
    }
    
    
}
