package com.ecommerce.daos;

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
 * Clase que representa la entidad Producto en la base de datos.
 * Esta clase está vinculada a la tabla "productos" y mapea las columnas correspondientes.
 */
@Entity
@Table(name = "productos", schema = "gestion")
public class ProductoDao {

    /**
     * Identificador único del producto.
     * Este campo es la clave primaria y se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", updatable = false)
    private long idProducto;

    /**
     * Nombre del producto.
     * Este campo es obligatorio y tiene una longitud máxima de 100 caracteres.
     */
    @Column(name = "nombre_producto", length = 100, nullable = false)
    private String nombreProducto;

    /**
     * Foto del producto almacenada en formato binario.
     * Este campo es opcional.
     */
    @Column(name = "foto_producto", columnDefinition = "bytea")
    private byte[] fotoProducto;

    /**
     * Descripción del producto.
     * Este campo tiene una longitud máxima de 500 caracteres.
     */
    @Column(name = "descripcion_producto", length = 500)
    private String descripcionProducto;

    /**
     * Precio del producto.
     * Este campo es obligatorio.
     */
    @Column(name = "precio_producto", nullable = false)
    private double precioProducto;

    /**
     * Stock disponible del producto.
     * Este campo es obligatorio.
     */
    @Column(name = "stock_producto", nullable = false)
    private int stockProducto;

    /**
     * Categoría a la que pertenece el producto.
     * Este campo es opcional y tiene una longitud máxima de 100 caracteres.
     */
    @Column(name = "categoria_producto", length = 100)
    private String categoriaProducto;

    /**
     * Relación uno a muchos con la entidad PedidoProductoDao.
     * Un producto puede estar presente en muchos pedidos.
     */
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProductoDao> pedidosProductos;

    
    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private MarcaDao marca;

    /**
     * Relación uno a muchos con la entidad ReseniaDao.
     * Un producto puede tener muchas reseñas.
     */
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReseniaDao> resenias;

    // ===========================
    // Getters y Setters
    // ===========================

    /**
     * Obtiene el identificador único del producto.
     * 
     * @return idProducto Identificador único del producto.
     */
    public long getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador único del producto.
     * 
     * @param idProducto Identificador único del producto.
     */
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return nombreProducto Nombre del producto.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombreProducto Nombre del producto.
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene la descripción del producto.
     * 
     * @return descripcionProducto Descripción del producto.
     */
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    /**
     * Establece la descripción del producto.
     * 
     * @param descripcionProducto Descripción del producto.
     */
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return precioProducto Precio del producto.
     */
    public double getPrecioProducto() {
        return precioProducto;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precioProducto Precio del producto.
     */
    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * Obtiene el stock disponible del producto.
     * 
     * @return stockProducto Stock del producto.
     */
    public int getStockProducto() {
        return stockProducto;
    }

    /**
     * Establece el stock disponible del producto.
     * 
     * @param stockProducto Stock disponible del producto.
     */
    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    /**
     * Obtiene la lista de pedidos que contienen este producto.
     * 
     * @return pedidosProductos Lista de pedidos que incluyen este producto.
     */
    public List<PedidoProductoDao> getPedidosProductos() {
        return pedidosProductos;
    }

    /**
     * Establece la lista de pedidos que contienen este producto.
     * 
     * @param pedidosProductos Lista de pedidos que incluirán este producto.
     */
    public void setPedidosProductos(List<PedidoProductoDao> pedidosProductos) {
        this.pedidosProductos = pedidosProductos;
    }

    /**
     * Obtiene la lista de reseñas asociadas a este producto.
     * 
     * @return resenias Lista de reseñas del producto.
     */
    public List<ReseniaDao> getResenias() {
        return resenias;
    }

    /**
     * Establece la lista de reseñas asociadas a este producto.
     * 
     * @param resenias Lista de reseñas a asociar al producto.
     */
    public void setResenias(List<ReseniaDao> resenias) {
        this.resenias = resenias;
    }

    /**
     * Obtiene la foto del producto.
     * 
     * @return fotoProducto Foto del producto en formato binario.
     */
    public byte[] getFotoProducto() {
        return fotoProducto;
    }

    /**
     * Establece la foto del producto.
     * 
     * @param fotoProducto Foto del producto en formato binario.
     */
    public void setFotoProducto(byte[] fotoProducto) {
        this.fotoProducto = fotoProducto;
    }

    /**
     * Obtiene la categoría del producto.
     * 
     * @return categoriaProducto Categoría del producto.
     */
    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    /**
     * Establece la categoría del producto.
     * 
     * @param categoriaProducto Categoría del producto.
     */
    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }
    public MarcaDao getMarca() {
  		return marca;
  	}

  	public void setMarca(MarcaDao marca) {
  		this.marca = marca;
  	}

    // ===========================
    // Constructores
    // ===========================

  

	/**
     * Constructor vacío de la clase ProductoDao.
     */
    public ProductoDao() {
        super();
    }

    /**
     * Constructor con parámetros de la clase ProductoDao.
     * 
     * @param idProducto Identificador único del producto.
     * @param nombreProducto Nombre del producto.
     * @param fotoProducto Foto del producto en formato binario.
     * @param descripcionProducto Descripción del producto.
     * @param precioProducto Precio del producto.
     * @param stockProducto Stock disponible del producto.
     * @param categoriaProducto Categoría del producto.
     * @param pedidosProductos Lista de pedidos que contienen este producto.
     * @param resenias Lista de reseñas asociadas a este producto.
     */
    public ProductoDao(long idProducto, String nombreProducto, byte[] fotoProducto, String descripcionProducto,
                       double precioProducto, int stockProducto, String categoriaProducto, 
                       List<PedidoProductoDao> pedidosProductos, List<ReseniaDao> resenias) {
        super();
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.fotoProducto = fotoProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
        this.categoriaProducto = categoriaProducto;  
        this.pedidosProductos = pedidosProductos;
        this.resenias = resenias;
    }

    /**
     * Retorna la representación en cadena de la clase ProductoDao.
     * 
     * @return String Representación del objeto ProductoDao.
     */
    @Override
    public String toString() {
        return "Producto{" +
               "idProducto=" + idProducto +
               ", nombreProducto='" + nombreProducto + '\'' +
               ", precioProducto=" + precioProducto +
               ", stockProducto=" + stockProducto +
               ", categoriaProducto='" + categoriaProducto + '\'' +  
               '}';
    }
}
