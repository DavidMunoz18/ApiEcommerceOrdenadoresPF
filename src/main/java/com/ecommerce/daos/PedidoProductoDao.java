package com.ecommerce.daos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Representa la cantidad, el precio, el producto y el usuario cuando realiza un pedido en el sistema.
 * Esta entidad está mapeada a la tabla 'pedidos_productos' en la base de datos.
 */
@Entity
@Table(name = "pedidos_productos", schema = "gestion")
public class PedidoProductoDao {

    /**
     * Identificador único de la relación entre el pedido y el producto.
     * Este campo es la clave primaria y se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_producto")
    private Long idPedidoProducto;

    /**
     * El pedido al que pertenece este producto.
     * Relación muchos a uno con la entidad PedidoDao.
     */
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private PedidoDao pedido;

    /**
     * El producto que está asociado al pedido.
     * Relación muchos a uno con la entidad ProductoDao.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoDao producto;

    /**
     * La cantidad de unidades del producto en este pedido.
     * Este campo es obligatorio y no puede ser nulo.
     */
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    /**
     * El precio del producto en el momento del pedido.
     * Este campo es obligatorio y no puede ser nulo.
     */
    @Column(name = "precio", nullable = false)
    private Double precio;

    /**
     * Obtiene el identificador único de la relación entre el pedido y el producto.
     * 
     * @return idPedidoProducto Identificador único de la relación.
     */
    public Long getIdPedidoProducto() {
        return idPedidoProducto;
    }

    /**
     * Establece el identificador único de la relación entre el pedido y el producto.
     * 
     * @param idPedidoProducto Identificador único de la relación.
     */
    public void setIdPedidoProducto(Long idPedidoProducto) {
        this.idPedidoProducto = idPedidoProducto;
    }

    /**
     * Obtiene el pedido al que pertenece este producto.
     * 
     * @return pedido El pedido al que pertenece el producto.
     */
    public PedidoDao getPedido() {
        return pedido;
    }

    /**
     * Establece el pedido al que pertenece este producto.
     * 
     * @param pedido El pedido al que pertenece el producto.
     */
    public void setPedido(PedidoDao pedido) {
        this.pedido = pedido;
    }

    /**
     * Obtiene el producto asociado a este pedido.
     * 
     * @return producto El producto asociado al pedido.
     */
    public ProductoDao getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado a este pedido.
     * 
     * @param producto El producto a asociar al pedido.
     */
    public void setProducto(ProductoDao producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad de unidades de este producto en el pedido.
     * 
     * @return cantidad La cantidad de unidades del producto en el pedido.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de unidades de este producto en el pedido.
     * 
     * @param cantidad La cantidad de unidades a establecer.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio del producto en el momento del pedido.
     * 
     * @return precio El precio del producto en el momento del pedido.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto en el momento del pedido.
     * 
     * @param precio El precio del producto a establecer.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Constructor vacío de la clase PedidoProductoDao.
     */
    public PedidoProductoDao() {
        super();
    }

    /**
     * Constructor con parámetros de la clase PedidoProductoDao.
     * 
     * @param idPedidoProducto Identificador único de la relación entre el pedido y el producto.
     * @param pedido El pedido al que pertenece este producto.
     * @param producto El producto asociado al pedido.
     * @param cantidad La cantidad de unidades del producto en este pedido.
     * @param precio El precio del producto en el momento del pedido.
     */
    public PedidoProductoDao(Long idPedidoProducto, PedidoDao pedido, ProductoDao producto, Integer cantidad, Double precio) {
        super();
        this.idPedidoProducto = idPedidoProducto;
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    /**
     * Retorna la representación en cadena de la clase PedidoProductoDao.
     * 
     * @return String Representación del objeto PedidoProductoDao.
     */
    @Override
    public String toString() {
        return "PedidoProductoDao [idPedidoProducto=" + idPedidoProducto + ", pedido=" + pedido + ", producto="
                + producto + ", cantidad=" + cantidad + ", precio=" + precio + "]";
    }
}
