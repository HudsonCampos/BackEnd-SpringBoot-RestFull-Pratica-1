package com.curso.poo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.curso.poo.converter.DozerConverter;
import com.curso.poo.model.Carro;
import com.curso.poo.model.vo.CarroVo;
import com.curso.poo.repository.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	CarroRepository carroRespository;

	public CarroVo created(CarroVo carrovo) {
		var entity = DozerConverter.parseObject(carrovo, Carro.class);
		carroRespository.save(entity);
		return DozerConverter.parseObject(entity, CarroVo.class);
	}

	public CarroVo buscarPorId(Long id) {
		var entity = carroRespository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não localizado!!"));
		return DozerConverter.parseObject(entity, CarroVo.class);
	}

	@Transactional
	public CarroVo ativarCarro(Long id) {
		carroRespository.ativarCarro(id);
		var entity = carroRespository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não existe!!"));
		return DozerConverter.parseObject(entity, CarroVo.class);
	}

	public Page<CarroVo> buscarTodosCarros(Pageable pageable) {
		var pagevo = carroRespository.findAll(pageable);
		return pagevo.map(this::convertPageObject);		
	}
	
	public Page<CarroVo> buscarTodosCarrosPorMarca(String marca, Pageable pageable) {
		var pageCarroName = carroRespository.buscarTodosCarrosPorMarca(marca, pageable);
		return pageCarroName.map(this::convertPageObject);
	}

	private CarroVo convertPageObject(Carro carroEntity) {
		return DozerConverter.parseObject(carroEntity, CarroVo.class);
	}
	
}

