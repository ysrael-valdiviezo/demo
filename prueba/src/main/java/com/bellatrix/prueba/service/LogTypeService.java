package com.bellatrix.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bellatrix.prueba.entity.LogType;
import com.bellatrix.prueba.repository.LogTypeRepository;

@Service
public class LogTypeService {

	@Autowired
    private LogTypeRepository repository;
 
    public List<LogType> getLogsType() {
        return repository.findAll();
    }
    
    public void saveLogsType(LogType logsType){
    	repository.save(logsType);
    }
}
