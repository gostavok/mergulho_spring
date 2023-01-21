package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.algalog.domain.ValidationGroups;
import com.algaworks.algalog.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "entrega")
public class EntregaModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@ManyToOne
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	private ClienteModel cliente;
	
	@Embedded
	@Valid
	@NotNull
	private DestinatarioModel destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<OcorrenciaModel> ocorrencias = new ArrayList<>();
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;

	public OcorrenciaModel adicionarOcorrencia(String descricao) {
		OcorrenciaModel ocorrencia = new OcorrenciaModel();
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setDescricao(descricao);
		ocorrencia.setEntrega(this);
		
		this.getOcorrencias().add(ocorrencia);
		
		return ocorrencia;
	}

	public void finalizar() {
		if(!StatusEntrega.PENDENTE.equals(this.getStatus())) {
			throw new NegocioException("Entrega não pode ser finalizada");
		}
		this.setStatus(StatusEntrega.FINALIZADA);
		this.setDataFinalizacao(OffsetDateTime.now());
	}
	
}
