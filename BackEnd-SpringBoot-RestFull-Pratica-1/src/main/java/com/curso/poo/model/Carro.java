package com.curso.poo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_carro")
public class Carro implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	//@NotEmpty(message = "{campo.marca.obrigatorio}")
	@Column(nullable = false, length = 200, name = "marca")
	private String marca;	
	
	@Column(nullable = false, length = 200, name = "modelo")
	private String modelo;
	
	@Column(nullable = false)
	private Boolean funcionando;

	
}
