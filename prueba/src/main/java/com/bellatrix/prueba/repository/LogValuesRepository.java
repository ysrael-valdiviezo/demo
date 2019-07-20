package com.bellatrix.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bellatrix.prueba.entity.LogValue;

public interface LogValuesRepository extends JpaRepository<LogValue, Integer>{

}
