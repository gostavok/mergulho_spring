package com.algaworks.algalog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.OcorrenciaAssembler;
import com.algaworks.algalog.api.dto.OcorrenciaDTO;
import com.algaworks.algalog.domain.model.EntregaModel;
import com.algaworks.algalog.domain.model.OcorrenciaModel;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private BuscaEntregaService buscaEntregaService;
	private RegistroOcorrenciaService registroOcorrenciaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTO registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaDTO ocorrencia) {
		OcorrenciaModel ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrencia.getDescricao());
		
		return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
		EntregaModel entrega = buscaEntregaService.buscar(entregaId);
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
	}
	
}