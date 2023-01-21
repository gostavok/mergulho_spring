package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.model.EntregaModel;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;

	public EntregaModel buscar(Long entregaId) {
		return entregaRepository.findById(entregaId).orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
	
}
