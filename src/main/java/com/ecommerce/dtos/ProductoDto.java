package com.ecommerce.dtos;

/**
 * DTO (Data Transfer Object) que representa un producto en el sistema.
 * Este objeto se utiliza para transferir los detalles de un producto entre las capas de la aplicación.
 */
public class ProductoDto {

    /**
     * Identificador único del producto.
     */
    private Long id; 

    /**
     * Nombre del producto.
     */
    private String nombre;

    /**
     * Descripción del producto.
     */
    private String descripcion;

    /**
     * Precio del producto.
     */
    private double precio;

    /**
     * Imagen del producto en formato de arreglo de bytes.
     */
    private byte[] imagen;  

    /**
     * Cantidad en stock disponible del producto.
     */
    private int stock;

    /**
     * Categoría del producto.
     * Este campo permite clasificar el producto en una categoría específica.
     */
    private String categoria;  // Nuevo campo para la categoría

    /**
     * Constructor vacío de la clase ProductoDto.
     * Este constructor es necesario para algunas bibliotecas de serialización/deserialización.
     */
    public ProductoDto() {
    }

    /**
     * Constructor de la clase ProductoDto con todos los campos, incluyendo la categoría.
     * 
     * @param id el identificador del producto
     * @param nombre el nombre del producto
     * @param descripcion la descripción del producto
     * @param precio el precio del producto
     * @param imagen la imagen del producto en formato de arreglo de bytes
     * @param stock la cantidad de producto disponible en stock
     * @param categoria la categoría del producto
     */
    public ProductoDto(Long id, String nombre, String descripcion, double precio, byte[] imagen, int stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.stock = stock;
        this.categoria = categoria;  // Asignación de la categoría
    }

    /**
     * Obtiene el identificador del producto.
     * 
     * @return el identificador del producto
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del producto.
     * 
     * @param id el identificador del producto
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return el nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre el nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     * 
     * @return la descripción del producto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     * 
     * @param descripcion la descripción del producto
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return el precio del producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precio el precio del producto
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la imagen del producto en formato de arreglo de bytes.
     * 
     * @return la imagen del producto
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * Establece la imagen del producto.
     * 
     * @param imagen la imagen del producto
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene la cantidad de producto disponible en stock.
     * 
     * @return la cantidad en stock del producto
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece la cantidad de producto disponible en stock.
     * 
     * @param stock la cantidad en stock del producto
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Obtiene la categoría del producto.
     * 
     * @return la categoría del producto
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del producto.
     * 
     * @param categoria la categoría del producto
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
