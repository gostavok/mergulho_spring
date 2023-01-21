package com.algaworks.algalog.api.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OcorrenciaDTO {

	private Long id;
	@NotBlank
	private String descricao;
	private OffsetDateTime dataRegistro;
	
}
