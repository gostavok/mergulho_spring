package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.dto.EntregaDTO;
import com.algaworks.algalog.domain.model.EntregaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {

	private ModelMapper modelMapper;
	
	public EntregaDTO toModel(EntregaModel entrega) {
		return modelMapper.map(entrega, EntregaDTO.class);
	}
	
	public List<EntregaDTO> toCollectionModel(List<EntregaModel> entregas){
		return entregas.stream().map(this::toModel).collect(Collectors.toList());
	}

}
