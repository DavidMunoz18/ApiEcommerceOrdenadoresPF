package com.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) para la entidad Usuario.
 * Se utiliza para transferir datos entre la API y la capa de servicio,
 * proporcionando solo los datos necesarios para registrar un usuario.
 */
public class RegistroUsuarioDto {

    /** Identificador único del usuario. */
    private long idUsuario;
    
    /** Nickname único del usuario. */
    @JsonProperty("nicknameUsuario")
    private String nicknameUsuario;
    
    /** Nombre completo del usuario. */
    private String nombreUsuario;
    
    /** Documento de identidad del usuario (DNI). */
    private String dniUsuario;
    
    /** Número de teléfono del usuario. */
    private String telefonoUsuario;
    
    /** Email de contacto del usuario. */
    private String emailUsuario;
    
    /** Contraseña para autenticación del usuario. */
    private String passwordUsuario;
    
    /** Rol del usuario (por ejemplo, ADMIN, USER). */
    private String rol;
    
    /** Código de verificación para confirmar el correo. */
    private String codigoVerificacion;

    // ============================
    // Getters y Setters
    // ============================

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public void setNicknameUsuario(String nicknameUsuario) {
        this.nicknameUsuario = nicknameUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }
}
