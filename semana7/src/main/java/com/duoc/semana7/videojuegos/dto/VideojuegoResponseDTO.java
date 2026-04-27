package com.duoc.semana7.videojuegos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideojuegoResponseDTO {

	private Integer id;

	private String titulo;

	private int precio;

	private String plataforma;

	private boolean disponibilidad;
}
