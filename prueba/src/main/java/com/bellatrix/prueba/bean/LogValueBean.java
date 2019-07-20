package com.bellatrix.prueba.bean;

import java.util.Date;

import com.bellatrix.prueba.util.LogTypeEnum;

import lombok.Data;

@Data
public class LogValueBean {
	private String messageText; 
	private LogTypeEnum logTypeEnum;
	private Date dateRegister;
}
