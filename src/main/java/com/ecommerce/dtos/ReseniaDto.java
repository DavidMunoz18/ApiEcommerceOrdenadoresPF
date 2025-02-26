package com.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object) que representa una reseña de un producto realizada por un usuario.
 * Este objeto se utiliza para transferir los detalles de una reseña entre las capas de la aplicación.
 */
public class ReseniaDto {

    /**
     * Identificador único de la reseña.
     * Este campo es mapeado desde el JSON con la propiedad "idResena".
     */
    @JsonProperty("idResena") // Esto mapea el campo JSON "idResena" al campo "id"
    private Long id;

    /**
     * Contenido o texto de la reseña escrita por el usuario.
     */
    private String contenidoResena;

    /**
     * Calificación otorgada al producto en la reseña, generalmente de 1 a 5.
     */
    private Integer calificacion;

    /**
     * Identificador del usuario que escribió la reseña.
     */
    private Long idUsuario;

    /**
     * Identificador del producto al que corresponde la reseña.
     */
    private Long idProducto;

    /**
     * Constructor vacío de la clase ReseniaDto.
     * Este constructor es necesario para algunas bibliotecas de serialización/deserialización.
     */
    public ReseniaDto() {
        super();
    }

    /**
     * Constructor completo de la clase ReseniaDto con todos los campos.
     * 
     * @param idResena el identificador de la reseña
     * @param contenidoResena el contenido de la reseña
     * @param calificacion la calificación otorgada a la reseña
     * @param idUsuario el identificador del usuario que escribió la reseña
     * @param idProducto el identificador del producto relacionado con la reseña
     */
    public ReseniaDto(Long idResena, String contenidoResena, Integer calificacion, Long idUsuario, Long idProducto) {
        super();
        this.id = idResena;  // Usar el campo "idResena" en vez de "id"
        this.contenidoResena = contenidoResena;
        this.calificacion = calificacion;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el identificador de la reseña.
     * 
     * @return el identificador de la reseña
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la reseña.
     * 
     * @param id el identificador de la reseña
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el contenido de la reseña.
     * 
     * @return el contenido de la reseña
     */
    public String getContenidoResena() {
        return contenidoResena;
    }

    /**
     * Establece el contenido de la reseña.
     * 
     * @param contenidoResena el contenido de la reseña
     */
    public void setContenidoResena(String contenidoResena) {
        this.contenidoResena = contenidoResena;
    }

    /**
     * Obtiene la calificación otorgada en la reseña.
     * 
     * @return la calificación de la reseña
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * Establece la calificación de la reseña.
     * 
     * @param calificacion la calificación de la reseña
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Obtiene el identificador del usuario que escribió la reseña.
     * 
     * @return el identificador del usuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario que escribió la reseña.
     * 
     * @param idUsuario el identificador del usuario
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el identificador del producto al que pertenece la reseña.
     * 
     * @return el identificador del producto
     */
    public Long getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto al que pertenece la reseña.
     * 
     * @param idProducto el identificador del producto
     */
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Representación en formato de cadena de texto de la reseña.
     * 
     * @return una cadena con los detalles de la reseña
     */
    @Override
    public String toString() {
        return "ReseniaDTO [id=" + id + ", contenidoResena=" + contenidoResena + ", calificacion=" + calificacion
                + ", idUsuario=" + idUsuario + ", idProducto=" + idProducto + "]";
    }
}
