package com.bellatrix.prueba.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
public class LogValue {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    @Column(name = "logType")
    private String logType;
 
    @Column(name = "logMessage")
    private String logMessage;
    
    @Column(name = "logDate")
    private Date logDate;

	public LogValue(String logType, String logMessage, Date logDate) {
		super();
		this.logType = logType;
		this.logMessage = logMessage;
		this.logDate = logDate;
	}
    
    
}
