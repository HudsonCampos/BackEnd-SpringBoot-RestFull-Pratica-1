package com.curso.poo.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.curso.poo.erro.ApiErro;

@RestControllerAdvice
public class ApiControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErro validErro(MethodArgumentNotValidException ex) {
		BindingResult binding = ex.getBindingResult();
		List<String> messages = binding.getAllErrors().stream()
				.map(ObjectError -> ObjectError.getDefaultMessage())
				.collect(Collectors.toList());
		return new ApiErro(messages);
		//return ResponseEntity.ok().body(messages);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity response(ResponseStatusException ex) {
		String messageErro = ex.getMessage();
		HttpStatus codErro = ex.getStatus();
		
		ApiErro apiErro = new ApiErro(messageErro);
		return new ResponseEntity(apiErro, codErro);
		
	}
	
	@ExceptionHandler(HttpMessageNotWritableException.class)
	public ResponseEntity<String> respMess(HttpMessageNotWritableException exce) {
		String msg = exce.getLocalizedMessage();
		
		return ResponseEntity.ok(msg);		
	}
	
	@ExceptionHandler(FileSizeLimitExceededException.class)
	public ApiErro respFile(FileSizeLimitExceededException exFile) {
		String msgFile = exFile.getMessage();
		Long tamanhoArquivo = exFile.getPermittedSize();		
		
		return new ApiErro(msgFile, tamanhoArquivo);
	}
		
}
