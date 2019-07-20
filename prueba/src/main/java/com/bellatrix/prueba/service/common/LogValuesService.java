package com.bellatrix.prueba.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bellatrix.prueba.entity.LogValue;
import com.bellatrix.prueba.repository.LogValuesRepository;

@Service
public class LogValuesService {

	@Autowired
    private LogValuesRepository repository;
 
    public List<LogValue> getLogsValue() {
        return repository.findAll();
    }
    
    public void saveLogsValue(LogValue logValues){
    	repository.save(logValues);
    }
}
