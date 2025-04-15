package com.ecommerce.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.dtos.CarritoDto;
import com.ecommerce.utilidades.Utilidades;

/**
 * Servicio para la gestión del carrito de compras.
 * 
 * Este servicio maneja las operaciones de agregar, obtener y eliminar productos del carrito.
 * Utiliza la clase {@link Utilidades} para registrar logs de las acciones realizadas.
 */
@Service
public class CarritoServicio {

    // Lista que simula el carrito de compras.
    private final List<CarritoDto> carrito = new ArrayList<>();

    /**
     * Agrega un producto al carrito.
     * 
     * Este método asume que las validaciones necesarias ya se realizaron en la capa de negocio.
     * Registra un log con el detalle de la acción y retorna un valor que indica si el producto fue agregado con éxito.
     * 
     * @param producto El producto que se va a agregar al carrito.
     * @return {@code true} si el producto fue agregado correctamente, {@code false} en caso contrario.
     */
    public boolean agregarProducto(CarritoDto producto) {
        Utilidades.escribirLog("[INFO]", "CarritoServicio", "agregarProducto", "Iniciando ejecución");
        try {
            // Se asume que las validaciones se realizaron en la capa de negocio (Dynamic Web)
            carrito.add(producto);
            Utilidades.escribirLog("[INFO]", "CarritoServicio", "agregarProducto", "Producto agregado: " + producto);
            return true;
        } catch (Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoServicio", "agregarProducto", "Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene los productos actualmente presentes en el carrito.
     * 
     * Este método retorna una copia de la lista del carrito para evitar modificaciones directas
     * sobre la lista original. Registra un log con el detalle de la acción.
     * 
     * @return Una lista de objetos {@link CarritoDto} que representan los productos en el carrito.
     */
    public List<CarritoDto> obtenerCarrito() {
        Utilidades.escribirLog("[INFO]", "CarritoServicio", "obtenerCarrito", "Iniciando ejecución");
        try {
            return new ArrayList<>(carrito);
        } catch (Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoServicio", "obtenerCarrito", "Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Elimina un producto del carrito por su ID.
     * 
     * Este método busca el producto por su ID en la lista del carrito y lo elimina si lo encuentra.
     * Registra un log con el detalle de la acción, indicando si el producto fue encontrado y eliminado.
     * 
     * @param id El ID del producto a eliminar.
     * @return {@code true} si el producto fue eliminado, {@code false} si no se encontró el producto.
     */
    public boolean eliminarProducto(int id) {
        Utilidades.escribirLog("[INFO]", "CarritoServicio", "eliminarProducto", "Iniciando ejecución con id: " + id);
        try {
            boolean eliminado = carrito.removeIf(producto -> producto.getId() == id);
            if (eliminado) {
                Utilidades.escribirLog("[INFO]", "CarritoServicio", "eliminarProducto", "Producto eliminado con id: " + id);
            } else {
                Utilidades.escribirLog("[ERROR]", "CarritoServicio", "eliminarProducto", "Producto no encontrado con id: " + id);
            }
            return eliminado;
        } catch (Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoServicio", "eliminarProducto", "Error: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Limpia (elimina TODOS los productos) del carrito.
     * 
     * @return {@code true} si el carrito se limpió correctamente.
     */
    public boolean limpiarCarrito() {
        Utilidades.escribirLog("[INFO]", "CarritoServicio", "limpiarCarrito", "Iniciando ejecución");
        try {
            carrito.clear();
            Utilidades.escribirLog("[INFO]", "CarritoServicio", "limpiarCarrito", "Carrito limpiado correctamente.");
            return true;
        } catch(Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoServicio", "limpiarCarrito", "Error: " + e.getMessage());
            throw e;
        }
    }
    
}
