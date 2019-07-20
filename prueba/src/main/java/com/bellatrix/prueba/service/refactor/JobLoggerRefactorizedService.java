package com.bellatrix.prueba.service.refactor;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.bellatrix.prueba.bean.LogMessageBean;
import com.bellatrix.prueba.bean.LogValueBean;
import com.bellatrix.prueba.entity.LogValue;
import com.bellatrix.prueba.service.common.LogValuesService;
import com.bellatrix.prueba.util.BellatrixUtil;
import com.bellatrix.prueba.util.LogTypeEnum;

@Service
public class JobLoggerRefactorizedService {

	@Value("${params.logToFile}")
	private boolean logToFile;
	@Value("${params.logToConsole}")
	private boolean logToConsole;
	@Value("${params.logMessage}")
	private boolean logMessage;
	@Value("${params.logWarning}")
	private boolean logWarning;
	@Value("${params.logError}")
	private boolean logError;
	@Value("${params.logToDatabase}")
	private boolean logToDatabase;
	@Value("${params.logFileFolder}")
	private String logFileFolder;
	
	private static Logger logger;
	
	@Autowired
	private LogValuesService logValuesService;
	
	public JobLoggerRefactorizedService() {
		logger = Logger.getLogger("MyBellatrixLog");  
	}
	
	public void LogMessageRefactorized(LogMessageBean logMessageBean) throws Exception{
		
		String messageText = Optional.ofNullable(logMessageBean.getMessageText()).orElse("").trim();
		boolean message = logMessageBean.isMessage(); 
		boolean warning = logMessageBean.isWarning(); 
		boolean error = logMessageBean.isError();
		
		this.validateParams(logMessageBean, message, warning, error);
		
		File logFile = ResourceUtils.getFile(logFileFolder+"/logFile.txt");
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
		
		FileHandler fh = new FileHandler(logFile.toString());
		ConsoleHandler ch = new ConsoleHandler();
		List<LogValueBean> logValueList = new ArrayList<LogValueBean>();
		
		if (error && logError) {
			logValueList.add(this.buildLogValueBean(messageText, LogTypeEnum.ERROR));
		}

		if (warning && logWarning) {
			logValueList.add(this.buildLogValueBean(messageText, LogTypeEnum.WARNING));
		}

		if (message && logMessage) {
			logValueList.add(this.buildLogValueBean(messageText, LogTypeEnum.MESSAGE));
		}
		
		if(logToFile) {
			logger.addHandler(fh);
			logValueList.forEach(lvb ->{
				logger.log(BellatrixUtil.getLogLevel(lvb.getLogTypeEnum()), lvb.getMessageText());
			});
		}
		
		if(logToConsole) {
			logger.addHandler(ch);
			logValueList.forEach(lvb ->{
				logger.log(BellatrixUtil.getLogLevel(lvb.getLogTypeEnum()), lvb.getMessageText());
			});
		}
		
		if(logToDatabase) {
			logValueList.forEach(lvb ->{
				logValuesService.saveLogsValue(new LogValue(lvb.getLogTypeEnum().toString(), lvb.getMessageText(), lvb.getDateRegister()));
			});
		}
	}

	private void validateParams(LogMessageBean logMessageBean, boolean message, boolean warning, boolean error)
			throws Exception {
		if (logMessageBean.getMessageText() == null || logMessageBean.getMessageText().trim().isEmpty()) {
			throw new Exception("Invalid message");
		}
		
		if (!logToConsole && !logToFile && !logToDatabase) {
			throw new Exception("Invalid configuration");
		}
		if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
			throw new Exception("Error or Warning or Message must be specified");
		}
	}

	private LogValueBean buildLogValueBean(String messageText, LogTypeEnum logTypeEnum) {
		LogValueBean logValues = new LogValueBean();
		logValues.setLogTypeEnum(logTypeEnum);
		logValues.setMessageText(messageText);
		logValues.setDateRegister(new Date());
		return logValues;
	}
	
}
