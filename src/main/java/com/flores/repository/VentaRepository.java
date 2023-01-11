package com.flores.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flores.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

	Optional<Venta> findById(Integer id);
	
	@Query("select c from Venta c where to_char(c.fecha,'yyyy-mm-dd') = :a1")
	List<Venta> findByFecha(@Param("a1") String fecha);
}
