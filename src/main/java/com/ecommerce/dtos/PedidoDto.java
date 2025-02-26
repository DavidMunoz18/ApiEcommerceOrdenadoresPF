package com.ecommerce.dtos;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO (Data Transfer Object) que representa un pedido realizado por un usuario.
 * Este objeto se utiliza para transferir los datos de un pedido entre las capas de la aplicación.
 */
public class PedidoDto {

    /**
     * Identificador único del usuario que realiza el pedido.
     */
    private Long idUsuario;

    /**
     * Información de contacto del usuario.
     */
    private String contacto;

    /**
     * Dirección de envío del pedido.
     */
    private String direccion;

    /**
     * Método de pago elegido para el pedido.
     */
    private String metodoPago;

    /**
     * Nombre del titular de la tarjeta de pago.
     */
    private String nombreTarjeta;

    /**
     * Número de la tarjeta de pago.
     */
    private String numeroTarjeta;

    /**
     * Fecha de expiración de la tarjeta de pago.
     */
    private String fechaExpiracion;

    /**
     * Código de seguridad (CVC) de la tarjeta de pago.
     */
    private String cvc;

    /**
     * Lista de productos que forman parte del pedido.
     */
    private List<PedidoProductoDto> productos;
    
    // Campos agregados para la lógica de negocio (se establecen en el Dynamic Web Project)
    
    /**
     * Fecha en que se realizó el pedido.
     */
    private LocalDate fechaPedido;
    
    /**
     * Estado del pedido (por ejemplo, "PENDIENTE").
     */
    private String estado;
    
    /**
     * Total calculado del pedido.
     */
    private double total;

    /**
     * Constructor vacío de la clase PedidoDto.
     */
    public PedidoDto() {}

    // Getters y setters

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public List<PedidoProductoDto> getProductos() {
        return productos;
    }

    public void setProductos(List<PedidoProductoDto> productos) {
        this.productos = productos;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
