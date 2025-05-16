package com.ecommerce.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.ecommerce.dtos.CarritoDto;
import com.ecommerce.utilidades.Utilidades;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Servicio para la gestión del carrito de compras.
 * 
 * Este servicio maneja las operaciones de agregar, obtener y eliminar productos del carrito.
 * Utiliza la clase {@link Utilidades} para registrar logs de las acciones realizadas.
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarritoServicio {

    @Autowired
    private HttpSession session;

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
            @SuppressWarnings("unchecked")
            List<CarritoDto> carrito = (List<CarritoDto>) session.getAttribute("carrito");
            if (carrito == null) {
                carrito = new ArrayList<>();
            }
            carrito.add(producto);
            session.setAttribute("carrito", carrito);
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
            @SuppressWarnings("unchecked")
            List<CarritoDto> carrito = (List<CarritoDto>) session.getAttribute("carrito");
            if (carrito == null) {
                carrito = new ArrayList<>();
                session.setAttribute("carrito", carrito);
            }
            return new ArrayList<>(carrito);
        } catch (Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoServicio", "obtenerCarrito", "Error: " + e.getMessage());
            throw e;
        }
    } /**
     * Elimina un producto del carrito del usuario en sesión.
     * 
     * Este método busca el producto por su ID en la lista del carrito almacenada
     * en HttpSession y lo elimina si lo encuentra. Luego vuelve a guardar la lista
     * en sesión para que la vista JSP refleje el cambio.
     * 
     * @param session Sesión HTTP del usuario.
     * @param id El ID del producto a eliminar.
     * @return {@code true} si el producto fue eliminado, {@code false} si no se encontró el producto.
     */
    @SuppressWarnings("unchecked")
    public boolean eliminarProducto(HttpSession session, long id) {
        Utilidades.escribirLog("[INFO]", "CarritoServicio", "eliminarProducto",
                "Iniciando ejecución con id: " + id);
        try {
            List<CarritoDto> carrito = (List<CarritoDto>) session.getAttribute("carrito");
            if (carrito == null) {
                carrito = new ArrayList<>();
            }
            boolean eliminado = carrito.removeIf(p -> p.getId() == id);
            session.setAttribute("carrito", carrito);

            if (eliminado) {
                Utilidades.escribirLog("[INFO]", "CarritoServicio", "eliminarProducto",
                        "Producto eliminado con id: " + id);
            } else {
                Utilidades.escribirLog("[ERROR]", "CarritoServicio", "eliminarProducto",
                        "Producto no encontrado con id: " + id);
            }
            return eliminado;
        } catch (Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoServicio", "eliminarProducto",
                    "Error: " + e.getMessage());
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
            session.setAttribute("carrito", new ArrayList<CarritoDto>());
            Utilidades.escribirLog("[INFO]", "CarritoServicio", "limpiarCarrito", "Carrito limpiado correctamente.");
            return true;
        } catch(Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoServicio", "limpiarCarrito", "Error: " + e.getMessage());
            throw e;
        }
    }
}
