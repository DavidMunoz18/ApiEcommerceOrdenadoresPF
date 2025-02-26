package com.ecommerce.dtos;

/**
 * DTO (Data Transfer Object) que representa un producto dentro de un pedido.
 * Este objeto se utiliza para transferir los detalles de los productos de un pedido entre las capas de la aplicación.
 */
public class PedidoProductoDto {

    /**
     * Identificador único del producto.
     */
    private Long idProducto;

    /**
     * Nombre del producto.
     */
    private String nombreProducto;

    /**
     * Cantidad del producto en el pedido.
     */
    private Integer cantidad;

    /**
     * Precio del producto.
     */
    private Double precio;

    /**
     * Obtiene el identificador del producto.
     * 
     * @return el identificador del producto
     */
    public Long getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto.
     * 
     * @param idProducto el identificador del producto
     */
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return el nombre del producto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombreProducto el nombre del producto
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene la cantidad del producto en el pedido.
     * 
     * @return la cantidad del producto
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto en el pedido.
     * 
     * @param cantidad la cantidad del producto
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return el precio del producto
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precio el precio del producto
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Constructor vacío de la clase PedidoProductoDto.
     */
    public PedidoProductoDto() {
    }

    /**
     * Constructor de la clase PedidoProductoDto con todos los campos.
     * 
     * @param idProducto el identificador del producto
     * @param nombreProducto el nombre del producto
     * @param cantidad la cantidad del producto
     * @param precio el precio del producto
     */
    public PedidoProductoDto(Long idProducto, String nombreProducto, Integer cantidad, Double precio) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    /**
     * Devuelve una representación en cadena del objeto PedidoProductoDto.
     * 
     * @return una cadena con los valores del producto en el pedido
     */
    @Override
    public String toString() {
        return "PedidoProductoDto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
