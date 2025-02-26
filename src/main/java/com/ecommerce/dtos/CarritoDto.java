package com.ecommerce.dtos;

/**
 * Clase que representa un DTO (Data Transfer Object) para un producto en el carrito de compras.
 * Contiene los atributos necesarios para el funcionamiento del carrito, como el identificador,
 * nombre, cantidad, precio e imagen del producto.
 */
public class CarritoDto {
    
    /**
     * Identificador único del producto en el carrito.
     */
    private Long id; 
    
    /**
     * Nombre del producto en el carrito.
     */
    private String nombre;
    
    /**
     * Cantidad del producto en el carrito.
     */
    private int cantidad;
    
    /**
     * Precio unitario del producto en el carrito.
     */
    private double precio;
    
    /**
     * Imagen del producto almacenada como un arreglo de bytes.
     */
    private byte[] imagen;

    /**
     * Constructor completo para inicializar todos los atributos del carrito.
     * 
     * @param id El identificador único del producto.
     * @param nombre El nombre del producto.
     * @param cantidad La cantidad del producto en el carrito.
     * @param precio El precio unitario del producto.
     * @param imagen La imagen del producto.
     */
    public CarritoDto(Long id, String nombre, int cantidad, double precio, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.imagen = imagen;
    }

    /**
     * Constructor predeterminado necesario para la deserialización del DTO.
     */
    public CarritoDto() {}

    /**
     * Obtiene el identificador del producto.
     * 
     * @return El identificador único del producto.
     */
    public Long getId() { 
        return id;
    }

    /**
     * Establece el identificador del producto.
     * 
     * @param id El identificador único del producto.
     */
    public void setId(Long id) { 
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre El nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad del producto en el carrito.
     * 
     * @return La cantidad del producto.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto en el carrito.
     * 
     * @param cantidad La cantidad del producto.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del producto en el carrito.
     * 
     * @return El precio unitario del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio unitario del producto en el carrito.
     * 
     * @param precio El precio unitario del producto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la imagen del producto en el carrito.
     * 
     * @return La imagen del producto en un arreglo de bytes.
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * Establece la imagen del producto en el carrito.
     * 
     * @param imagen La imagen del producto.
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * Representación en formato String del carrito con todos sus atributos.
     * 
     * @return Una cadena que representa al carrito con su id, nombre, cantidad, precio e imagen.
     */
    @Override
    public String toString() {
        return "CarritoDto{" +
                "id=" + id + 
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
