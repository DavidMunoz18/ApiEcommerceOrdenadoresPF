package com.ecommerce.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.daos.UsuarioDao;

/**
 * Repositorio para gestionar las operaciones relacionadas con la entidad {@link UsuarioDao}.
 * 
 * Extiende {@link JpaRepository} para proporcionar las operaciones CRUD básicas y algunos métodos específicos
 * para buscar y verificar la existencia de usuarios según el email, el ID o el token.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDao, Long> {

    /**
     * Busca un usuario por su email.
     * 
     * @param emailUsuario el email del usuario.
     * @return el usuario encontrado con ese email.
     */
    UsuarioDao findByEmailUsuario(String emailUsuario);

    /**
     * Verifica si un usuario con el email dado ya existe en la base de datos.
     * 
     * @param emailUsuario el email del usuario.
     * @return true si el usuario con ese email existe, false en caso contrario.
     */
    boolean existsByEmailUsuario(String emailUsuario);

    /**
     * Busca un usuario por su ID.
     * 
     * @param id el ID del usuario.
     * @return un {@link Optional} que contiene el usuario encontrado, o vacío si no se encuentra.
     */
    Optional<UsuarioDao> findById(Long id); 
    
    /**
     * Busca un usuario por su token.
     * 
     * @param tokenUsuario el token del usuario.
     * @return el usuario encontrado con ese token.
     */
    UsuarioDao findByTokenUsuario(String tokenUsuario);
}
