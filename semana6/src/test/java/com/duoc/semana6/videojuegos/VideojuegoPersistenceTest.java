package com.duoc.semana6.videojuegos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.duoc.semana6.videojuegos.entity.VideojuegoEntity;
import com.duoc.semana6.videojuegos.repository.VideojuegoRepository;

@SpringBootTest
@ActiveProfiles("h2")
class VideojuegoPersistenceTest {

	@Autowired
	private VideojuegoRepository videojuegoRepository;

	@Test
	void dataLoaderSembradoYConsultasDerivadas() {
		assertThat(videojuegoRepository.count()).isGreaterThanOrEqualTo(5);
		assertThat(videojuegoRepository.findByTituloContainingIgnoreCase("zelda")).isNotEmpty();
		assertThat(videojuegoRepository.findByPlataformaIgnoreCase("pc")).isNotEmpty();
	}

	@Test
	void guardarYEliminarPorId() {
		VideojuegoEntity entity = new VideojuegoEntity(null, "Test JPA", 999, "PC", true);
		VideojuegoEntity saved = videojuegoRepository.save(entity);
		assertThat(saved.getId()).isNotNull();

		videojuegoRepository.deleteById(saved.getId());
		assertThat(videojuegoRepository.findById(saved.getId())).isEmpty();
	}
}
