package com.ecommerce.daos;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa la entidad Usuario en la base de datos.
 * Contiene los atributos y relaciones correspondientes a un usuario en el sistema,
 * incluyendo su información personal, token de recuperación y las relaciones con pedidos y reseñas.
 */
@Entity
@Table(name = "usuarios", schema = "gestion")
public class UsuarioDao {

    /**
     * Identificador único del usuario en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", updatable = false)
    private long idUsuario;

    /**
     * Nombre del usuario.
     */
    @Column(name = "nombre_usuario", length = 100, nullable = false)
    private String nombreUsuario;

    /**
     * Teléfono del usuario.
     */
    @Column(name = "telefono_usuario", length = 15)
    private String telefonoUsuario;

    /**
     * Foto del usuario, almacenada como un arreglo de bytes.
     */
    @Column(name = "foto_usuario", columnDefinition = "bytea")
    private byte[] fotoUsuario;

    /**
     * Email del usuario, debe ser único.
     */
    @Column(name = "email_usuario", unique = true, length = 150, nullable = false)
    private String emailUsuario;

    /**
     * Contraseña del usuario.
     */
    @Column(name = "passwd_usuario", length = 255, nullable = false)
    private String passwordUsuario;

    /**
     * Rol del usuario (por ejemplo, admin, cliente, etc.).
     */
    @Column(name = "rol_usuario", length = 50)
    private String rol;
    
    /**
     * Token de recuperación de contraseña del usuario.
     */
    @Column(name = "token_usuario")
    private String tokenUsuario;

    /**
     * Fecha de expiración del token de recuperación de contraseña.
     */
    @Column(name = "fecha_expiracion_token")
    private Date fechaExpiracionToken;

    /**
     * Relación Uno a Muchos con Pedidos.
     * Un usuario puede tener múltiples pedidos.
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoDao> pedidos;

    /**
     * Relación Uno a Muchos con Reseñas.
     * Un usuario puede escribir varias reseñas.
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReseniaDao> resenias;

    // ============================
    // Constructores, Getters y Setters
    // ============================

    public UsuarioDao() {}

    /**
     * Constructor completo sin listas de pedidos y reseñas.
     * 
     * @param idUsuario El identificador único del usuario.
     * @param nombreUsuario El nombre del usuario.
     * @param telefonoUsuario El teléfono del usuario.
     * @param fotoUsuario La foto del usuario.
     * @param emailUsuario El email del usuario.
     * @param passwordUsuario La contraseña del usuario.
     * @param rol El rol del usuario.
     * @param tokenUsuario El token de recuperación de contraseña del usuario.
     * @param fechaExpiracionToken La fecha de expiración del token de recuperación.
     * @param pedidos Los pedidos realizados por el usuario.
     * @param resenias Las reseñas realizadas por el usuario.
     */
    public UsuarioDao(long idUsuario, String nombreUsuario, String telefonoUsuario, byte[] fotoUsuario,
                      String emailUsuario, String passwordUsuario, String rol, String tokenUsuario, Date fechaExpiracionToken,
                      List<PedidoDao> pedidos, List<ReseniaDao> resenias) {
        super();
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fotoUsuario = fotoUsuario;
        this.emailUsuario = emailUsuario;
        this.passwordUsuario = passwordUsuario;
        this.rol = rol;
        this.tokenUsuario = tokenUsuario;
        this.fechaExpiracionToken = fechaExpiracionToken;
        this.pedidos = pedidos;
        this.resenias = resenias;
    }

    // Getters y Setters para todos los campos, incluyendo listas

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public byte[] getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(byte[] fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
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

    public List<PedidoDao> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoDao> pedidos) {
        this.pedidos = pedidos;
    }

    public List<ReseniaDao> getReseñas() {
        return resenias;
    }

    public void setReseñas(List<ReseniaDao> resenias) {
        this.resenias = resenias;
    }

    public String getTokenUsuario() {
        return tokenUsuario;
    }

    public void setTokenUsuario(String tokenUsuario) {
        this.tokenUsuario = tokenUsuario;
    }

    public Date getFechaExpiracionToken() {
        return fechaExpiracionToken;
    }

    public void setFechaExpiracionToken(Date fechaExpiracionToken) {
        this.fechaExpiracionToken = fechaExpiracionToken;
    }

    public List<ReseniaDao> getResenias() {
        return resenias;
    }

    public void setResenias(List<ReseniaDao> resenias) {
        this.resenias = resenias;
    }

    @Override
    public String toString() {
        return "UsuarioDao [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", telefonoUsuario="
                + telefonoUsuario + ", fotoUsuario=" + Arrays.toString(fotoUsuario) + ", emailUsuario=" + emailUsuario
                + ", passwordUsuario=" + passwordUsuario + ", rol=" + rol + ", tokenUsuario=" + tokenUsuario
                + ", fechaExpiracionToken=" + fechaExpiracionToken + ", pedidos=" + pedidos + ", resenias=" + resenias
                + "]";
    }
}
