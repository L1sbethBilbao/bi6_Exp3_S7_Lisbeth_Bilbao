package com.duoc.GamerHeaven.videojuegos.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

class VideojuegoRequestDtoValidationTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void datosInvalidosGeneranViolaciones() {
		VideojuegoRequestDTO dto = new VideojuegoRequestDTO("", -1, "", null);
		assertThat(validator.validate(dto)).isNotEmpty();
	}
}
