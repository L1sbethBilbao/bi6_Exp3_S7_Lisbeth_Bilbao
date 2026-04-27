package com.duoc.semana7.videojuegos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.semana7.videojuegos.dto.VideojuegoRequestDTO;
import com.duoc.semana7.videojuegos.dto.VideojuegoResponseDTO;
import com.duoc.semana7.videojuegos.entity.VideojuegoEntity;
import com.duoc.semana7.videojuegos.repository.VideojuegoRepository;

@Service
public class VideojuegoService {

	private final VideojuegoRepository videojuegoRepository;

	public VideojuegoService(VideojuegoRepository videojuegoRepository) {
		this.videojuegoRepository = videojuegoRepository;
	}

	public List<VideojuegoResponseDTO> listarVideojuegos() {
		return videojuegoRepository.findAll().stream().map(this::toDTO).toList();
	}

	public VideojuegoResponseDTO buscarVideojuegoPorId(int id) {
		return videojuegoRepository.findById(id).map(this::toDTO).orElse(null);
	}

	public List<VideojuegoResponseDTO> buscarVideojuegoPorTitulo(String titulo) {
		if (titulo == null || titulo.isBlank()) {
			return List.of();
		}
		return videojuegoRepository.findByTituloContainingIgnoreCase(titulo.trim()).stream().map(this::toDTO).toList();
	}

	public List<VideojuegoResponseDTO> listarVideojuegosPorPlataforma(String plataforma) {
		if (plataforma == null || plataforma.isBlank()) {
			return List.of();
		}
		return videojuegoRepository.findByPlataformaIgnoreCase(plataforma.trim()).stream().map(this::toDTO).toList();
	}

	public VideojuegoResponseDTO crearVideojuego(VideojuegoRequestDTO request) {
		VideojuegoEntity entity = new VideojuegoEntity();
		entity.setTitulo(request.getTitulo());
		entity.setPrecio(request.getPrecio());
		entity.setPlataforma(request.getPlataforma());
		entity.setDisponibilidad(request.getDisponibilidad());
		return toDTO(videojuegoRepository.save(entity));
	}

	public VideojuegoResponseDTO actualizarVideojuego(int id, VideojuegoRequestDTO request) {
		return videojuegoRepository.findById(id).map(entity -> {
			entity.setTitulo(request.getTitulo());
			entity.setPrecio(request.getPrecio());
			entity.setPlataforma(request.getPlataforma());
			entity.setDisponibilidad(request.getDisponibilidad());
			return toDTO(videojuegoRepository.save(entity));
		}).orElse(null);
	}

	public boolean eliminarVideojuego(int id) {
		if (!videojuegoRepository.existsById(id)) {
			return false;
		}
		videojuegoRepository.deleteById(id);
		return true;
	}

	private VideojuegoResponseDTO toDTO(VideojuegoEntity entity) {
		return new VideojuegoResponseDTO(
				entity.getId(),
				entity.getTitulo(),
				entity.getPrecio(),
				entity.getPlataforma(),
				entity.isDisponibilidad());
	}
}
