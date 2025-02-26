package com.ecommerce.servicios;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.daos.UsuarioDao;
import com.ecommerce.dtos.RegistroUsuarioDto;
import com.ecommerce.dtos.UsuarioDto;
import com.ecommerce.repositorios.UsuarioRepository;
import com.ecommerce.utilidades.Utilidades;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private ConcurrentHashMap<String, String> mapaCodigosVerificacion = new ConcurrentHashMap<>();

    /**
     * Método para obtener los datos de un usuario basado en su email.
     * <p>
     * Este método recupera al usuario de la base de datos y obtiene su rol e ID sin realizar
     * la validación de la contraseña, ya que esa validación será manejada por el Dynamic Web Project.
     * </p>
     * 
     * @param emailUsuario el email del usuario
     * @return un Map con el rol, ID, y otros datos del usuario, o null si el usuario no existe
     */
    public Map<String, Object> obtenerDatosPorEmail(String emailUsuario) {
        // Intentamos recuperar al usuario por su email
        UsuarioDao usuario = usuarioRepository.findByEmailUsuario(emailUsuario);

        if (usuario == null) {
            return null;
        }

        Map<String, Object> datosUsuario = new HashMap<>();
        datosUsuario.put("idUsuario", usuario.getIdUsuario());
        datosUsuario.put("nombreUsuario", usuario.getNombreUsuario());
        datosUsuario.put("telefonoUsuario", usuario.getTelefonoUsuario());
        datosUsuario.put("emailUsuario", usuario.getEmailUsuario());
        datosUsuario.put("rol", usuario.getRol());
        datosUsuario.put("contrasena", usuario.getPasswordUsuario());

        return datosUsuario;
    }

    /**
     * Verifica si un usuario con el email proporcionado ya está registrado.
     * 
     * @param emailUsuario el email del usuario a verificar
     * @return true si el email ya está registrado, false en caso contrario
     */
    public boolean emailExistsUsuario(String emailUsuario) {
        return usuarioRepository.existsByEmailUsuario(emailUsuario);
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * <p>
     * Toma un DTO con los datos del usuario, encripta la contraseña y la guarda en
     * la base de datos.
     * </p>
     * 
     * @param usuarioDto el DTO con los datos del usuario a registrar
     */
    public void registroUsuario(RegistroUsuarioDto usuarioDto) {
      

        UsuarioDao usuario = new UsuarioDao();
        usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        usuario.setTelefonoUsuario(usuarioDto.getTelefonoUsuario());
        usuario.setEmailUsuario(usuarioDto.getEmailUsuario());
        usuario.setPasswordUsuario(usuarioDto.getPasswordUsuario());
        usuario.setRol("usuario");

        usuarioRepository.save(usuario);
    }

    /**
     * Modifica los datos de un usuario existente en la base de datos.
     * 
     * @param idUsuario     identificador del usuario a modificar
     * @param nuevoNombre   el nuevo nombre del usuario
     * @param nuevoDni      el nuevo DNI del usuario
     * @param nuevoTelefono el nuevo teléfono del usuario
     * @param nuevoRol      el nuevo rol del usuario
     * @param nuevaFoto     la nueva foto del usuario (opcional)
     * @return true si la modificación fue exitosa, false si no se encontró el usuario
     */
    @Transactional
    public boolean modificarUsuario(long idUsuario, String nuevoNombre, String nuevoDni, String nuevoTelefono,
            String nuevoRol, byte[] nuevaFoto) {
        Utilidades.escribirLog("[INFO]", "UsuarioServicio", "modificarUsuario", "Iniciando modificación de usuario con ID: " + idUsuario);
        Optional<UsuarioDao> usuarioOpt = usuarioRepository.findById(idUsuario);

        if (usuarioOpt.isPresent()) {
            UsuarioDao usuario = usuarioOpt.get();
            // Se asignan directamente los valores recibidos sin comprobaciones
            usuario.setNombreUsuario(nuevoNombre);
           
            usuario.setTelefonoUsuario(nuevoTelefono);
            usuario.setRol(nuevoRol);
            usuario.setFotoUsuario(nuevaFoto);

            usuarioRepository.save(usuario);
            Utilidades.escribirLog("[INFO]", "UsuarioServicio", "modificarUsuario", "Usuario modificado exitosamente con ID: " + idUsuario);
            return true;
        }

        Utilidades.escribirLog("[ERROR]", "UsuarioServicio", "modificarUsuario", "Usuario no encontrado con ID: " + idUsuario);
        return false;
    }


    /**
     * Obtiene un usuario por su ID.
     * 
     * @param idUsuario el ID del usuario a obtener
     * @return el objeto UsuarioDao si se encuentra, de lo contrario null
     */
    public UsuarioDao obtenerUsuarioPorId(long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    /**
     * Elimina un usuario por su ID.
     * 
     * @param idUsuario el ID del usuario a eliminar
     * @return true si el usuario fue eliminado correctamente, false si no existía
     */
    public boolean borrarUsuario(Long idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return true;
        }
        return false;
    }

    /**
     * Obtiene el ID del usuario por su email.
     * 
     * @param emailUsuario el email del usuario
     * @return el ID del usuario, o null si no existe
     */
    public Long obtenerIdPorEmail(String emailUsuario) {
        UsuarioDao usuario = usuarioRepository.findByEmailUsuario(emailUsuario);
        if (usuario != null) {
            return usuario.getIdUsuario();
        }
        return null;
    }

    /**
     * Obtiene el rol del usuario por su email.
     * 
     * @param emailUsuario el email del usuario
     * @return el rol del usuario, o null si no existe
     */
    public String obtenerRolPorEmail(String emailUsuario) {
        UsuarioDao usuario = usuarioRepository.findByEmailUsuario(emailUsuario);
        if (usuario != null) {
            return usuario.getRol();
        }
        return null;
    }

    /**
     * Guarda un token para el usuario especificado, recibiendo también la fecha de expiración.
     *
     * @param token            el token a guardar
     * @param correo           el correo del usuario
     * @param fechaExpiracion la fecha de expiración recibida desde el Dynamic Web Project
     * @return true si el token se guardó correctamente, false si el usuario no existe
     */
    public boolean guardarToken(String token, String correo, Date fechaExpiracion) {
        // Buscar el usuario por el correo proporcionado
        UsuarioDao usuario = usuarioRepository.findByEmailUsuario(correo);
        
        if (usuario == null) {
            // Si el usuario no existe, retornar false
            return false;
        }

        // Establecer el token y la fecha de expiración en el usuario
        usuario.setTokenUsuario(token);
        usuario.setFechaExpiracionToken(fechaExpiracion);
        
        // Guardar el usuario con el token
        usuarioRepository.save(usuario);
        return true;
    }

    /**
     * Actualiza la contraseña de un usuario utilizando un token de recuperación.
     * Se asume que la validación del token se realiza en otra capa (Dynamic Web Project).
     *
     * @param token            el token de recuperación
     * @param nuevaContrasenia la nueva contraseña (ya encriptada por el Dynamic Web Project)
     * @return true si la contraseña fue actualizada, false si el token es inválido
     */
    public boolean actualizarContrasenia(String token, String nuevaContrasenia) {
        // Buscar el usuario por el token proporcionado
        UsuarioDao usuario = usuarioRepository.findByTokenUsuario(token);
        
        if (usuario == null) {
            // Si el usuario no tiene el token o el token es inválido, retornar false
            return false;
        }

        // Establecer la nueva contraseña en el usuario
        usuario.setPasswordUsuario(nuevaContrasenia);  // Se espera que la contraseña esté encriptada

        // Eliminar el token y la fecha de expiración después de usarlo
        usuario.setTokenUsuario(null);
        usuario.setFechaExpiracionToken(null);
        
        // Guardar el usuario con la nueva contraseña y los valores nulos para el token
        usuarioRepository.save(usuario);
        return true;
    }

    public Date obtenerFechaExpiracionToken(String token) {
        // Buscar el usuario por el token proporcionado
        UsuarioDao usuario = usuarioRepository.findByTokenUsuario(token);
        
        if (usuario == null) {
            return null;  // Si el usuario no tiene el token, retornar null
        }
        
        // Retornar la fecha de expiración del token
        return usuario.getFechaExpiracionToken();  // Suponiendo que `getFechaExpiracionToken()` retorna la fecha de expiración del token
    }



    /**
     * Obtiene la lista de todos los usuarios en el sistema.
     * 
     * @return una lista de objetos UsuarioDto
     */
    public List<UsuarioDto> listarUsuarios() {
        List<UsuarioDao> usuariosDao = usuarioRepository.findAll();

        return usuariosDao.stream()
                .map(usuario -> new UsuarioDto(
                        usuario.getIdUsuario(),
                        usuario.getNombreUsuario(),
                        usuario.getTelefonoUsuario(),
                        usuario.getEmailUsuario(),
                        usuario.getRol()))
                .collect(Collectors.toList());
    }

    /**
     * Verifica si el código ingresado coincide con el código almacenado para el correo.
     * 
     * @param correo el correo del usuario
     * @param codigoIngresado el código ingresado por el usuario
     * @return true si los códigos coinciden, false en caso contrario
     */
    public boolean verificarCodigo(String correo, String codigoIngresado) {
        String codigoGuardado = mapaCodigosVerificacion.get(correo);
        return codigoGuardado != null && codigoGuardado.equals(codigoIngresado);
    }

    /**
     * Registra al usuario después de verificar el código de verificación.
     * 
     * @param registroUsuarioDto el DTO con los datos del usuario a registrar
     */
    public void registrarUsuarioConVerificacion(RegistroUsuarioDto registroUsuarioDto) {
        registroUsuario(registroUsuarioDto);
        mapaCodigosVerificacion.remove(registroUsuarioDto.getEmailUsuario());
    }

    /**
     * Almacena el código de verificación para el correo especificado.
     * 
     * @param correo el correo del usuario
     * @param codigo el código de verificación generado
     */
    public void almacenarCodigo(String correo, String codigo) {
        mapaCodigosVerificacion.put(correo, codigo);
    }
}