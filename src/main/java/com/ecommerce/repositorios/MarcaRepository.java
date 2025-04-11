package com.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.daos.MarcaDao;
import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaDao, Long> {
    // Este método ya está disponible gracias a JpaRepository, pero lo puedes agregar si quieres
    Optional<MarcaDao> findById(Long id);
}
