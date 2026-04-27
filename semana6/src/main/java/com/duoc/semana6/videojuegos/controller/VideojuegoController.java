package com.duoc.semana6.videojuegos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.semana6.videojuegos.dto.VideojuegoRequestDTO;
import com.duoc.semana6.videojuegos.dto.VideojuegoResponseDTO;
import com.duoc.semana6.videojuegos.service.VideojuegoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

	private final VideojuegoService videojuegoService;

	public VideojuegoController(VideojuegoService videojuegoService) {
		this.videojuegoService = videojuegoService;
	}

	@GetMapping
	public ResponseEntity<List<VideojuegoResponseDTO>> listarVideojuegos() {
		return ResponseEntity.ok(videojuegoService.listarVideojuegos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<VideojuegoResponseDTO> buscarVideojuego(@PathVariable int id) {
		VideojuegoResponseDTO dto = videojuegoService.buscarVideojuegoPorId(id);
		if (dto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<VideojuegoResponseDTO>> buscarVideojuegoPorTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(videojuegoService.buscarVideojuegoPorTitulo(titulo));
	}

	@GetMapping("/plataforma/{plataforma}")
	public ResponseEntity<List<VideojuegoResponseDTO>> listarVideojuegosPorPlataforma(@PathVariable String plataforma) {
		return ResponseEntity.ok(videojuegoService.listarVideojuegosPorPlataforma(plataforma));
	}

	@PostMapping
	public ResponseEntity<VideojuegoResponseDTO> agregarVideojuego(@Valid @RequestBody VideojuegoRequestDTO body) {
		VideojuegoResponseDTO creado = videojuegoService.crearVideojuego(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<VideojuegoResponseDTO> actualizarVideojuego(@PathVariable int id,
			@Valid @RequestBody VideojuegoRequestDTO body) {
		VideojuegoResponseDTO actualizado = videojuegoService.actualizarVideojuego(id, body);
		if (actualizado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(actualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarVideojuego(@PathVariable int id) {
		if (!videojuegoService.eliminarVideojuego(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
