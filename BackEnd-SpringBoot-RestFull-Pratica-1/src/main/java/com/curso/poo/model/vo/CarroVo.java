package com.curso.poo.model.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class CarroVo extends RepresentationModel<CarroVo> implements Serializable{
	 
	private static final long serialVersionUID = 1L;
		
	private Long id;	
	
	@NotEmpty(message = "{campo.marca.obrigatorio}")
	private String marca;	

	private String modelo;	
	
	private Boolean funcionando;


}
