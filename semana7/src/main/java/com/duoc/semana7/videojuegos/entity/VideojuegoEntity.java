package com.duoc.semana7.videojuegos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VIDEOJUEGOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideojuegoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "TITULO", nullable = false, length = 255)
	private String titulo;

	@Column(name = "PRECIO", nullable = false)
	private int precio;

	@Column(name = "PLATAFORMA", nullable = false, length = 100)
	private String plataforma;

	@Column(name = "DISPONIBILIDAD", nullable = false)
	private boolean disponibilidad;
}
