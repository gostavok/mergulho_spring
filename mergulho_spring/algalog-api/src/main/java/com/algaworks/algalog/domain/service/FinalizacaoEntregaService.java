package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.EntregaModel;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	private EntregaRepository entregaRepository;
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public void finalizar(Long entregaId) {
		EntregaModel entrega = buscaEntregaService.buscar(entregaId);		
		entrega.finalizar();
		
		entregaRepository.save(entrega);
	}
	
}
