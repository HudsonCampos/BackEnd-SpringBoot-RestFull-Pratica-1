package com.curso.poo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.curso.poo.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{

	@Modifying
	@Query("UPDATE Carro c SET c.funcionando=true WHERE c.id=:id")
	void ativarCarro(@Param("id") Long id);
	
	@Query("SELECT c FROM Carro c WHERE c.marca LIKE LOWER(CONCAT ('%', :marca, '%'))")
	Page<Carro> buscarTodosCarrosPorMarca(@Param("marca") String marca, Pageable paginacao);

	


}
