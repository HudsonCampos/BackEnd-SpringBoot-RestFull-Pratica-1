package com.curso.poo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.poo.model.vo.CarroVo;
import com.curso.poo.service.CarroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:2200")
@RestController
@RequestMapping(path = "/api/carro/v1")
@Api(value = "Crud Carro", tags = "Crud de consulta, e inserção de carro.")
public class CarroController {
	
	@Autowired
	CarroService carroService;		

	@PostMapping(consumes = {"application/json", "application/xml", "application/x-yaml"},
			produces = {"application/json", "application/xml", "application/x-yaml"})
	@ApiOperation(value = "Inserir carro.", tags = "Created de carro na controller")
	public CarroVo created( @Valid @RequestBody CarroVo carrovo) {
		CarroVo carroVo = carroService.created(carrovo);		
		return carroVo;
	}
	
	
	@GetMapping(value = "/{id}", 			
			consumes = {"application/json", "application/xml", "application/x-yaml"},
			produces = {"application/json", "application/xml", "application/x-yaml"})			
	public CarroVo buscarPorId(@PathVariable("id") Long id) {
		CarroVo carrovo = carroService.buscarPorId(id);
		carrovo.add(linkTo(methodOn(CarroController.class).buscarPorId(id)).withSelfRel());
		return carrovo;
	}
	
	@PatchMapping(value = "/{id}", consumes = {"application/json", "application/xml", "application/x-yaml"},
			produces = {"application/json", "application/xml", "application/x-yaml"})
	public CarroVo ativarCarro(@PathVariable("id") Long id) {
		CarroVo carrovo = carroService.ativarCarro(id);
		carrovo.add(linkTo(methodOn(CarroController.class).buscarPorId(id)).withSelfRel());
		return carrovo;
	}
	
	@GetMapping(consumes = {"application/json", "application/xml", "application/x-yaml"},
			produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<Page<CarroVo>> buscarTodosCarros(
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "limit", defaultValue = "2") Integer limit,
				@RequestParam(value = "direcao", defaultValue = "asc") String direcao
			){
		
		//var sortDirection = "desc".equalsIgnoreCase(direction)? Direction.DESC : Direction.ASC;
		
		var sortDirection = "desc".equalsIgnoreCase(direcao) ? Direction.DESC : Direction.ASC;		
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));		
		
		Page<CarroVo> pageCarrovo = carroService.buscarTodosCarros(pageable);
		
		pageCarrovo
			.stream()
			.forEach(c -> c.add(linkTo(methodOn(CarroController.class).buscarPorId(c.getId())).withSelfRel()));	
		
		return ResponseEntity.ok(pageCarrovo);
	}
	
	@GetMapping(value = "/buscarCarroMarca/{marca}", consumes = {"application/json", "application/xml", "application/x-yaml"},
			produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<Page<CarroVo>> buscarTodosCarrosPorMarca(
				@PathVariable("marca") String marca,
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "limit", defaultValue = "2") Integer limit,
				@RequestParam(value = "direcao", defaultValue = "asc") String direcao
			){
		
		//var sortDirection = "desc".equalsIgnoreCase(direction)? Direction.DESC : Direction.ASC;
		
		var sortDirection = "desc".equalsIgnoreCase(direcao) ? Direction.DESC : Direction.ASC;		
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));		
		
		Page<CarroVo> pageCarrovo = carroService.buscarTodosCarrosPorMarca(marca,pageable);
		
		pageCarrovo
			.stream()
			.forEach(c -> c.add(linkTo(methodOn(CarroController.class).buscarPorId(c.getId())).withSelfRel()));	
		
		return ResponseEntity.ok(pageCarrovo);
	}
		
	
}
