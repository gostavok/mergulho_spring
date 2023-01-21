package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.EntregaModel;
import com.algaworks.algalog.domain.model.OcorrenciaModel;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public OcorrenciaModel registrar(Long entregaId, String descricao) {
		EntregaModel entrega = buscaEntregaService.buscar(entregaId);
		
		return entrega.adicionarOcorrencia(descricao);
	}
	
}
