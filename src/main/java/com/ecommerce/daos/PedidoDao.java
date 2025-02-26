package com.ecommerce.daos;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Representa un pedido realizado por un usuario en el sistema.
 * Esta entidad está mapeada a la tabla 'pedidos' en la base de datos.
 */
@Entity
@Table(name = "pedidos", schema = "gestion")
public class PedidoDao {

    /**
     * Identificador único del pedido.
     * Este campo es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    /**
     * Fecha en la que se realizó el pedido.
     */
    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido;

    /**
     * Usuario que realizó el pedido.
     * Relación muchos a uno con la entidad UsuarioDao.
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioDao usuario;

    /**
     * Productos asociados al pedido.
     * Relación uno a muchos con la entidad PedidoProductoDao.
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProductoDao> pedidosProductos;

    /**
     * Estado actual del pedido (por ejemplo, "pendiente", "enviado", etc.).
     */
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    /**
     * Método de pago utilizado para el pedido.
     */
    @Column(name = "metodo_pago", length = 30)
    private String metodoPago;

    /**
     * Monto total del pedido.
     */
    @Column(name = "total", nullable = false)
    private Double total;

    // Campos adicionales relacionados con la información de pago

    /**
     * Información de contacto del usuario para el pedido (por ejemplo, correo o teléfono).
     */
    @Column(name = "contacto", length = 100, nullable = false)
    private String contacto;

    /**
     * Dirección de envío del pedido.
     */
    @Column(name = "direccion", length = 255, nullable = false)
    private String direccion;

    /**
     * Nombre del titular de la tarjeta de pago.
     */
    @Column(name = "nombre_tarjeta", length = 100)
    private String nombreTarjeta;

    /**
     * Número de tarjeta de pago.
     */
    @Column(name = "numero_tarjeta", length = 255)
    private String numeroTarjeta;

    /**
     * Fecha de expiración de la tarjeta de pago.
     */
    @Column(name = "fecha_expiracion", length = 15)
    private String fechaExpiracion;

    /**
     * Código CVC de la tarjeta de pago.
     */
    @Column(name = "cvc", length = 255)
    private String cvc;

    // Getters y Setters

    /**
     * Obtiene el identificador único del pedido.
     * @return El identificador del pedido.
     */
    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Obtiene la fecha en que se realizó el pedido.
     * @return La fecha del pedido.
     */
    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /**
     * Obtiene el usuario que realizó el pedido.
     * @return El usuario asociado al pedido.
     */
    public UsuarioDao getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDao usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la lista de productos asociados al pedido.
     * @return La lista de productos.
     */
    public List<PedidoProductoDao> getPedidosProductos() {
        return pedidosProductos;
    }

    public void setPedidosProductos(List<PedidoProductoDao> pedidosProductos) {
        this.pedidosProductos = pedidosProductos;
    }

    /**
     * Obtiene el estado del pedido.
     * @return El estado del pedido.
     */
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el método de pago utilizado para el pedido.
     * @return El método de pago.
     */
    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene el monto total del pedido.
     * @return El total del pedido.
     */
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * Obtiene la información de contacto del usuario para el pedido.
     * @return El contacto asociado al pedido.
     */
    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    /**
     * Obtiene la dirección de envío del pedido.
     * @return La dirección del pedido.
     */
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el nombre del titular de la tarjeta de pago.
     * @return El nombre del titular.
     */
    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    /**
     * Obtiene el número de tarjeta de pago.
     * @return El número de tarjeta.
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * Obtiene la fecha de expiración de la tarjeta de pago.
     * @return La fecha de expiración.
     */
    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    /**
     * Obtiene el código CVC de la tarjeta de pago.
     * @return El código CVC.
     */
    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    // Constructores

    /**
     * Constructor por defecto de la clase PedidoDao.
     */
    public PedidoDao() {
        super();
    }

    /**
     * Constructor de la clase PedidoDao con parámetros para inicializar todos los campos.
     * @param idPedido El identificador del pedido.
     * @param fechaPedido La fecha del pedido.
     * @param usuario El usuario que realizó el pedido.
     * @param pedidosProductos La lista de productos asociados al pedido.
     * @param estado El estado del pedido.
     * @param metodoPago El método de pago utilizado.
     * @param total El monto total del pedido.
     * @param contacto La información de contacto.
     * @param direccion La dirección de envío.
     * @param nombreTarjeta El nombre del titular de la tarjeta de pago.
     * @param numeroTarjeta El número de la tarjeta de pago.
     * @param fechaExpiracion La fecha de expiración de la tarjeta de pago.
     * @param cvc El código CVC de la tarjeta.
     */
    public PedidoDao(Long idPedido, LocalDate fechaPedido, UsuarioDao usuario, List<PedidoProductoDao> pedidosProductos,
                     String estado, String metodoPago, Double total, String contacto, String direccion,
                     String nombreTarjeta, String numeroTarjeta, String fechaExpiracion, String cvc) {
        super();
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.usuario = usuario;
        this.pedidosProductos = pedidosProductos;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.total = total;
        this.contacto = contacto;
        this.direccion = direccion;
        this.nombreTarjeta = nombreTarjeta;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.cvc = cvc;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto PedidoDao.
     * @return La cadena con los datos del pedido.
     */
    @Override
    public String toString() {
        return "PedidoDao [idPedido=" + idPedido + ", fechaPedido=" + fechaPedido + ", usuario=" + usuario
                + ", pedidosProductos=" + pedidosProductos + ", estado=" + estado + ", metodoPago=" + metodoPago
                + ", total=" + total + ", contacto=" + contacto + ", direccion=" + direccion
                + ", nombreTarjeta=" + nombreTarjeta + ", numeroTarjeta=" + numeroTarjeta
                + ", fechaExpiracion=" + fechaExpiracion + ", cvc=" + cvc + "]";
    }
}