package com.ecommerce.dtos;

/**
 * DTO (Data Transfer Object) que representa una marca en el sistema.
 * <p>
 * Este objeto se utiliza para transferir los detalles de una marca entre las capas de la aplicación.
 * Ahora incluye el país de origen, el año de fundación y una descripción de la marca.
 * </p>
 */
public class MarcaDto {

    /** Identificador único de la marca. */
    private Long id;

    /** Nombre de la marca. */
    private String nombre;
    
    /** País de origen de la marca. */
    private String paisOrigen;
    
    /** Año de fundación de la marca. */
    private int anioFundacion;
    
    /** Descripción de la marca. */
    private String descripcion;

    /**
     * Constructor vacío necesario para la serialización/deserialización.
     */
    public MarcaDto() {
    }

    /**
     * Constructor completo de la clase MarcaDto.
     *
     * @param id            el identificador único de la marca.
     * @param nombre        el nombre de la marca.
     * @param paisOrigen    el país de origen de la marca.
     * @param anioFundacion el año de fundación de la marca.
     * @param descripcion   la descripción de la marca.
     */
    public MarcaDto(Long id, String nombre, String paisOrigen, int anioFundacion, String descripcion) {
        this.id = id;
    	this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.anioFundacion = anioFundacion;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el identificador de la marca.
     *
     * @return el id de la marca.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la marca.
     *
     * @param id el id a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la marca.
     *
     * @return el nombre de la marca.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la marca.
     *
     * @param nombre el nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el país de origen de la marca.
     *
     * @return el país de origen.
     */
    public String getPaisOrigen() {
        return paisOrigen;
    }

    /**
     * Establece el país de origen de la marca.
     *
     * @param paisOrigen el país a asignar.
     */
    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    /**
     * Obtiene el año de fundación de la marca.
     *
     * @return el año de fundación.
     */
    public int getAnioFundacion() {
        return anioFundacion;
    }

    /**
     * Establece el año de fundación de la marca.
     *
     * @param anioFundacion el año a asignar.
     */
    public void setAnioFundacion(int anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    /**
     * Obtiene la descripción de la marca.
     *
     * @return la descripción de la marca.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la marca.
     *
     * @param descripcion la descripción a asignar.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
