package com.ecommerce.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.PedidoDto;
import com.ecommerce.servicios.PedidoServicio;
import com.ecommerce.utilidades.Utilidades;

/**
 * Controlador para la gestión de pedidos.
 * <p>
 * Proporciona un endpoint para la creación de pedidos en el sistema. 
 * Este controlador se encarga de delegar la persistencia de los pedidos al servicio correspondiente,
 * sin realizar ninguna validación de los datos de entrada.
 * </p>
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoControlador {

    private final PedidoServicio pedidoServicio;

    /**
     * Constructor que inyecta el servicio de pedidos.
     *
     * @param pedidoServicio Servicio encargado de la lógica de negocio y la persistencia de pedidos.
     */
    public PedidoControlador(PedidoServicio pedidoServicio) {
        this.pedidoServicio = pedidoServicio;
    }

    /**
     * Crea un nuevo pedido en el sistema.
     * <p>
     * Este método recibe un objeto {@link PedidoDto} con los datos del pedido 
     * y delega la persistencia del mismo al servicio {@link PedidoServicio}.
     * </p>
     * <ul>
     *     <li><b>201 Created</b> si el pedido se creó correctamente.</li>
     *     <li><b>400 Bad Request</b> si los datos no son correctos.</li>
     *     <li><b>500 Internal Server Error</b> si ocurre un error inesperado en el servidor.</li>
     * </ul>
     *
     * @param pedidoDto Objeto {@link PedidoDto} con los datos del pedido a registrar.
     * @return {@link ResponseEntity} con el resultado de la operación.
     */
    @PostMapping
    public ResponseEntity<String> crearPedido(@RequestBody PedidoDto pedidoDto) {
        String mensaje = "Creando pedido con los datos: " + pedidoDto.toString();
        Utilidades.escribirLog("INFO", "PedidoControlador", "crearPedido", mensaje);

        try {
            // Llamada al servicio para la creación del pedido
            String respuesta = pedidoServicio.crearPedido(pedidoDto);

            // Si la respuesta es positiva, devolver respuesta 201
            if ("Pedido creado correctamente".equals(respuesta)) {
                mensaje = "Pedido creado correctamente";
                Utilidades.escribirLog("INFO", "PedidoControlador", "crearPedido", mensaje);
                return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
            } else {
                // En caso de error, devolver respuesta 400
                mensaje = "Error al crear el pedido: " + respuesta;
                Utilidades.escribirLog("ERROR", "PedidoControlador", "crearPedido", mensaje);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }
        } catch (Exception e) {
            // Manejo de excepciones inesperadas
            mensaje = "Error al crear el pedido: " + e.getMessage();
            Utilidades.escribirLog("ERROR", "PedidoControlador", "crearPedido", mensaje);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el pedido: " + e.getMessage());
        }
    }
}