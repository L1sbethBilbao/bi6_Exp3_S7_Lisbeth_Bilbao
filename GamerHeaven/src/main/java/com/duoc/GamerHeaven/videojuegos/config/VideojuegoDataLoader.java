package com.duoc.GamerHeaven.videojuegos.config;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.duoc.GamerHeaven.videojuegos.entity.VideojuegoEntity;
import com.duoc.GamerHeaven.videojuegos.repository.VideojuegoRepository;

@Component
@Profile("h2")
public class VideojuegoDataLoader implements ApplicationRunner {

	private final VideojuegoRepository videojuegoRepository;

	public VideojuegoDataLoader(VideojuegoRepository videojuegoRepository) {
		this.videojuegoRepository = videojuegoRepository;
	}

	@Override
	public void run(ApplicationArguments args) {
		if (videojuegoRepository.count() > 0) {
			return;
		}
		List<VideojuegoEntity> seed = List.of(
				new VideojuegoEntity(null, "The Legend of Zelda: Tears of the Kingdom", 59990, "Nintendo Switch", true),
				new VideojuegoEntity(null, "God of War RagnarÃ¶k", 49990, "PlayStation 5", true),
				new VideojuegoEntity(null, "Halo Infinite", 39990, "Xbox Series X|S", false),
				new VideojuegoEntity(null, "Elden Ring", 44990, "PC", true),
				new VideojuegoEntity(null, "Mario Kart 8 Deluxe", 54990, "Nintendo Switch", true));
		videojuegoRepository.saveAll(seed);
	}
}
