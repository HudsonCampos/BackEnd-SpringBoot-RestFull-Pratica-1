package com.curso.poo.erro;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class ApiErro {

	
	private List<String> erros;
	
	private Long tamanhoPermitido;
	
	public ApiErro(String msg) {
		this.erros = Arrays.asList(msg);
	}
	
	public ApiErro(List<String> msgs) {
		this.erros = msgs;
	}
	
	public ApiErro(String msg, Long tamanho) {
		this.erros = Arrays.asList(msg);
		this.tamanhoPermitido = tamanho;
	}
}
