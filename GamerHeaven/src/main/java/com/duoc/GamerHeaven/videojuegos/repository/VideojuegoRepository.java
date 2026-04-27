package com.duoc.GamerHeaven.videojuegos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.GamerHeaven.videojuegos.entity.VideojuegoEntity;

@Repository
public interface VideojuegoRepository extends JpaRepository<VideojuegoEntity, Integer> {

	List<VideojuegoEntity> findByTituloContainingIgnoreCase(String titulo);

	List<VideojuegoEntity> findByPlataformaIgnoreCase(String plataforma);
}
