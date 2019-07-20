package com.bellatrix.prueba.util;

import java.util.logging.Level;

public class BellatrixUtil {

	public static Level getLogLevel(LogTypeEnum logTypeEnum){
		switch (logTypeEnum) {
		case ERROR:
			return Level.SEVERE;
		case MESSAGE:
			return Level.INFO;
		case WARNING:
			return Level.WARNING;
		default:
			return Level.ALL;
		}
	}
}
