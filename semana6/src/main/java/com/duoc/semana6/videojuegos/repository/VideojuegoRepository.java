package com.duoc.semana6.videojuegos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.semana6.videojuegos.entity.VideojuegoEntity;

@Repository
public interface VideojuegoRepository extends JpaRepository<VideojuegoEntity, Integer> {

	List<VideojuegoEntity> findByTituloContainingIgnoreCase(String titulo);

	List<VideojuegoEntity> findByPlataformaIgnoreCase(String plataforma);
}
