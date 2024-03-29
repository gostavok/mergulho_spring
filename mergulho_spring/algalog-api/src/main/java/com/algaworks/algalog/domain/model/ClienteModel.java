package com.algaworks.algalog.domain.model;


import com.algaworks.algalog.domain.ValidationGroups;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cliente")
public class ClienteModel {

	@NotNull(groups = ValidationGroups.ClienteId.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	@Size(max= 60)
	private String nome;
	
	@NotBlank
	@Size(max= 255)
	@Email
	private String email;
	
	@NotBlank
	@Size(max= 20)
	@Column(name = "telefone")
	private String telefone;
	
}
