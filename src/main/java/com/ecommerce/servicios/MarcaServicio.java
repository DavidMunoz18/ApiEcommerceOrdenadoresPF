package com.ecommerce.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.daos.MarcaDao;
import com.ecommerce.dtos.MarcaDto;
import com.ecommerce.repositorios.MarcaRepository;
import com.ecommerce.utilidades.Utilidades;

/**
 * Servicio para manejar las operaciones CRUD sobre las marcas.
 * <p>
 * Este servicio proporciona métodos para obtener todas las marcas,
 * obtener una marca por su ID, guardar una nueva marca, actualizar una marca
 * existente y eliminar una marca.
 * </p>
 */
@Service
public class MarcaServicio {

    @Autowired
    private MarcaRepository marcaRepository;

    /**
     * Obtiene todas las marcas y las convierte a MarcaDto.
     *
     * @return una lista de MarcaDto que representa todas las marcas del sistema.
     */
    public List<MarcaDto> obtenerTodasMarcas() {
        Utilidades.escribirLog("[INFO]", "MarcaServicio", "obtenerTodasMarcas", "Obteniendo todas las marcas");
        List<MarcaDao> marcas = marcaRepository.findAll();
        return marcas.stream()
                     .map(this::convertirADto)
                     .collect(Collectors.toList());
    }

    /**
     * Obtiene una marca por su ID.
     *
     * @param id el ID de la marca a buscar.
     * @return un Optional que contiene la marca encontrada como MarcaDto, o vacío si no se encuentra.
     */
    public Optional<MarcaDto> obtenerMarcaPorId(Long id) {
        Utilidades.escribirLog("[INFO]", "MarcaServicio", "obtenerMarcaPorId", "Buscando marca con ID: " + id);
        Optional<MarcaDao> marcaDao = marcaRepository.findById(id);
        return marcaDao.map(this::convertirADto);
    }

    /**
     * Guarda una nueva marca en la base de datos.
     *
     * @param marcaDto el objeto MarcaDto que contiene los detalles de la marca a guardar.
     * @return la marca guardada, convertida a MarcaDto.
     */
    public MarcaDto guardarMarca(MarcaDto marcaDto) {
        Utilidades.escribirLog("[INFO]", "MarcaServicio", "guardarMarca", "Guardando marca: " + marcaDto.getNombre());
        MarcaDao marcaDao = convertirADao(marcaDto);
        MarcaDao marcaGuardada = marcaRepository.save(marcaDao);
        return convertirADto(marcaGuardada);
    }

    /**
     * Actualiza una marca existente en la base de datos.
     *
     * @param id el ID de la marca a actualizar.
     * @param marcaDto el objeto MarcaDto con los datos a actualizar.
     * @return la marca actualizada, convertida a MarcaDto.
     */
    public MarcaDto actualizarMarca(Long id, MarcaDto marcaDto) {
        Optional<MarcaDao> marcaDaoExistente = marcaRepository.findById(id);
        if (marcaDaoExistente.isPresent()) {
            MarcaDao marcaDao = marcaDaoExistente.get();
            marcaDao.setNombreMarca(marcaDto.getNombre());
            marcaDao.setPaisOrigen(marcaDto.getPaisOrigen());
            marcaDao.setAnioFundacion(marcaDto.getAnioFundacion());
            marcaDao.setDescripcion(marcaDto.getDescripcion());
            MarcaDao marcaActualizada = marcaRepository.save(marcaDao);
            return convertirADto(marcaActualizada);
        }
        return null;  // Si no se encuentra la marca, devuelve null
    }


    /**
     * Elimina una marca de la base de datos por su ID.
     *
     * @param id el ID de la marca a eliminar.
     */
    public void eliminarMarca(Long id) {
        Optional<MarcaDao> marcaDaoExistente = marcaRepository.findById(id);
        if (marcaDaoExistente.isPresent()) {
            marcaRepository.deleteById(id);
            Utilidades.escribirLog("[INFO]", "MarcaServicio", "eliminarMarca", "Marca eliminada con ID: " + id);
        } else {
            Utilidades.escribirLog("[ERROR]", "MarcaServicio", "eliminarMarca", "Marca no encontrada con ID: " + id);
        }
    }

    /**
     * Convierte una entidad MarcaDao a un objeto MarcaDto.
     *
     * @param marcaDao la entidad MarcaDao.
     * @return un objeto MarcaDto con los datos de la entidad, o null si la entidad es null.
     */
    private MarcaDto convertirADto(MarcaDao marcaDao) {
        if (marcaDao == null) {
            return null;
        }
        return new MarcaDto(
        	marcaDao.getIdMarca(),
        	marcaDao.getNombreMarca(),
            marcaDao.getPaisOrigen(),
            marcaDao.getAnioFundacion(),
            marcaDao.getDescripcion()
        );
    }

    /**
     * Convierte un objeto MarcaDto a una entidad MarcaDao.
     *
     * @param marcaDto el objeto MarcaDto.
     * @return la entidad MarcaDao con los datos del DTO, o null si el DTO es null.
     */
    private MarcaDao convertirADao(MarcaDto marcaDto) {
        if (marcaDto == null) {
            return null;
        }
        MarcaDao marcaDao = new MarcaDao();
        marcaDao.setNombreMarca(marcaDto.getNombre());
        marcaDao.setPaisOrigen(marcaDto.getPaisOrigen());
        marcaDao.setAnioFundacion(marcaDto.getAnioFundacion());
        marcaDao.setDescripcion(marcaDto.getDescripcion());
        return marcaDao;
    }
}
