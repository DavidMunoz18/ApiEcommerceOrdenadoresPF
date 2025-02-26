package com.ecommerce.controladores;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.RegistroUsuarioDto;
import com.ecommerce.servicios.UsuarioServicio;
import com.ecommerce.utilidades.Utilidades;

/**
 * Controlador para gestionar el registro de usuarios y el almacenamiento de códigos de verificación.
 * <p>
 * Proporciona endpoints para registrar un nuevo usuario y almacenar códigos de verificación
 * asociados a correos electrónicos.
 * </p>
 */
@RestController
@RequestMapping("/api/registro")
public class RegistroControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    /**
     * Registra un nuevo usuario en la base de datos.
     * <p>
     * Antes de proceder con el registro, se validan los siguientes aspectos:
     * - Que el correo electrónico no sea nulo o vacío.
     * - Que el correo electrónico no esté registrado previamente.
     * </p>
     *
     * @param registroUsuarioDto DTO con los datos del usuario a registrar.
     * @return {@link ResponseEntity} con el resultado de la operación.
     */
    @PostMapping("/usuario")
    public ResponseEntity<String> registroUsuario(@RequestBody RegistroUsuarioDto registroUsuarioDto) {
        try {
            // Validación mínima para evitar registros incorrectos
            if (registroUsuarioDto.getEmailUsuario() == null || registroUsuarioDto.getEmailUsuario().trim().isEmpty()) {
                Utilidades.escribirLog("ERROR", "RegistroControlador", "registroUsuario", "El correo es obligatorio.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El correo es obligatorio.");
            }

            // Verificar si el email ya está registrado en la base de datos
            if (usuarioServicio.emailExistsUsuario(registroUsuarioDto.getEmailUsuario())) {
                Utilidades.escribirLog("ERROR", "RegistroControlador", "registroUsuario", "El correo ya está registrado: " + registroUsuarioDto.getEmailUsuario());
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El correo ya está registrado.");
            }

            // El Dynamic Web Project se encarga de validar el código de verificación antes de enviar los datos
            usuarioServicio.registrarUsuarioConVerificacion(registroUsuarioDto);
            
            Utilidades.escribirLog("INFO", "RegistroControlador", "registroUsuario", "Usuario registrado exitosamente: " + registroUsuarioDto.getEmailUsuario());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario registrado exitosamente.");
        } catch (Exception e) {
            Utilidades.escribirLog("ERROR", "RegistroControlador", "registroUsuario", "Error al registrar el usuario: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor.");
        }
    }
    
    /**
     * Almacena un código de verificación asociado a un correo electrónico.
     * <p>
     * Este código será utilizado para verificar la identidad del usuario durante el proceso de registro.
     * </p>
     *
     * @param request Mapa con las claves "emailUsuario" y "codigoVerificacion".
     * @return {@link ResponseEntity} con un mensaje indicando el resultado de la operación.
     */
    @PostMapping("/almacenarCodigo")
    public ResponseEntity<String> almacenarCodigo(@RequestBody Map<String, String> request) {
        String correo = request.get("emailUsuario");
        String codigo = request.get("codigoVerificacion");

        if (correo == null || correo.trim().isEmpty() || codigo == null || codigo.trim().isEmpty()) {
            Utilidades.escribirLog("ERROR", "RegistroControlador", "almacenarCodigo", "El correo y el código de verificación son obligatorios.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El correo y el código de verificación son obligatorios.");
        }

        try {
            // Almacenar el código en la base de datos o caché temporal
            usuarioServicio.almacenarCodigo(correo, codigo);
            Utilidades.escribirLog("INFO", "RegistroControlador", "almacenarCodigo", "Código almacenado correctamente para el correo: " + correo);
            return ResponseEntity.ok("Código almacenado correctamente.");
        } catch (Exception e) {
            Utilidades.escribirLog("ERROR", "RegistroControlador", "almacenarCodigo", "Error al almacenar el código para el correo: " + correo);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al almacenar el código de verificación.");
        }
    }
}
