package com.duoc.GamerHeaven.videojuegos.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideojuegoRequestDTO {

	@NotBlank(message = "El tÃ­tulo es obligatorio")
	@Size(max = 255, message = "El tÃ­tulo no puede superar 255 caracteres")
	private String titulo;

	@NotNull(message = "El precio es obligatorio")
	@Min(value = 0, message = "El precio no puede ser negativo")
	@Max(value = 2_000_000, message = "El precio excede el mÃ¡ximo permitido")
	private Integer precio;

	@NotBlank(message = "La plataforma es obligatoria")
	@Size(max = 100, message = "La plataforma no puede superar 100 caracteres")
	private String plataforma;

	@NotNull(message = "La disponibilidad es obligatoria")
	private Boolean disponibilidad;
}
