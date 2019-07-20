package com.bellatrix.prueba.bean;

import lombok.Data;

@Data
public class LogMessageBean {

	private String messageText; 
	private boolean message; 
	private boolean warning; 
	private boolean error;
}
