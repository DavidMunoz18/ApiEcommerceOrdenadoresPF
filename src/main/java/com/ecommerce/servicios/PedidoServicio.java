package com.ecommerce.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.daos.PedidoDao;
import com.ecommerce.daos.PedidoProductoDao;
import com.ecommerce.daos.ProductoDao;
import com.ecommerce.daos.UsuarioDao;
import com.ecommerce.dtos.PedidoDto;
import com.ecommerce.dtos.PedidoProductoDto;
import com.ecommerce.repositorios.PedidoRepository;
import com.ecommerce.repositorios.UsuarioRepository;
import com.ecommerce.utilidades.Utilidades;

/**
 * Servicio para la persistencia de pedidos.
 * Se asume que el objeto PedidoDto ya contiene toda la información
 * validada y calculada por la capa de negocio (Dynamic Web Project).
 */
@Service
public class PedidoServicio {

    private final PedidoRepository pedidoRepositorio;
    private final UsuarioRepository usuarioRepository;

    public PedidoServicio(PedidoRepository pedidoRepositorio, UsuarioRepository usuarioRepository) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Persiste un pedido en la base de datos.
     * Se asume que el objeto pedidoDto ya tiene todos los datos (por ejemplo, total, fecha, estado).
     *
     * @param pedidoDto Objeto con la información del pedido.
     * @return Mensaje indicando si la operación fue exitosa o si ocurrió un error.
     */
    @Transactional
    public String crearPedido(PedidoDto pedidoDto) {
        Utilidades.escribirLog("[INFO]", "PedidoServicio", "crearPedido", "Iniciando persistencia con pedidoDto: " + pedidoDto);

        try {
            // Mapeo simple: se asume que el usuario es válido y la lógica de negocio ya se ejecutó.
            // (Si se prefiere, se puede seguir recuperando el usuario desde la BD para establecer la relación)
            UsuarioDao usuario = new UsuarioDao();
            usuario.setIdUsuario(pedidoDto.getIdUsuario());
            
            PedidoDao pedido = new PedidoDao();
            // Se utiliza la fecha y estado enviados desde la capa de negocio
            pedido.setFechaPedido(pedidoDto.getFechaPedido());
            pedido.setEstado(pedidoDto.getEstado());
            pedido.setMetodoPago(pedidoDto.getMetodoPago());
            pedido.setUsuario(usuario);
            pedido.setContacto(pedidoDto.getContacto());
            pedido.setDireccion(pedidoDto.getDireccion());
            pedido.setNombreTarjeta(pedidoDto.getNombreTarjeta());
            pedido.setNumeroTarjeta(pedidoDto.getNumeroTarjeta());
            pedido.setFechaExpiracion(pedidoDto.getFechaExpiracion());
            pedido.setCvc(pedidoDto.getCvc());
            // Se espera que el total ya haya sido calculado en la capa de negocio
            pedido.setTotal(pedidoDto.getTotal());

            List<PedidoProductoDao> detalles = new ArrayList<>();
            for (PedidoProductoDto prodDto : pedidoDto.getProductos()) {
                PedidoProductoDao detalle = new PedidoProductoDao();
                detalle.setPedido(pedido);
                ProductoDao producto = new ProductoDao();
                producto.setIdProducto(prodDto.getIdProducto());
                detalle.setProducto(producto);
                detalle.setCantidad(prodDto.getCantidad());
                detalle.setPrecio(prodDto.getPrecio());
                detalles.add(detalle);
            }
            pedido.setPedidosProductos(detalles);


            pedidoRepositorio.save(pedido);
            Utilidades.escribirLog("[INFO]", "PedidoServicio", "crearPedido", "Pedido persistido correctamente con ID: " + pedido.getIdPedido());

            return "Pedido creado correctamente";
        } catch (Exception e) {
            Utilidades.escribirLog("[ERROR]", "PedidoServicio", "crearPedido", "Error al persistir el pedido: " + e.getMessage());
            return "Error al crear el pedido: " + e.getMessage();
        }
    }
}
