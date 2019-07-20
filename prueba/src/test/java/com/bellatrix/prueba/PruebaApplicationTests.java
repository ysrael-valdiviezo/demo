package com.bellatrix.prueba;

import java.util.List;

import org.jfree.util.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bellatrix.prueba.bean.LogMessageBean;
import com.bellatrix.prueba.entity.LogValue;
import com.bellatrix.prueba.refactor.JobLoggerRefactorizedService;
import com.bellatrix.prueba.service.LogValuesService;

//import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@Slf4j
public class PruebaApplicationTests {

	@Autowired
	LogValuesService logValuesService;
	@Autowired
	JobLoggerRefactorizedService jobLoggerRefactorizedService;
	
	//@Test
	public void contextLoads() {
	}
	
	//@Test
	public void saveLogTestAndList() {
		//Log.info("saveLogTestAndList()");
		System.out.println("saveLogTestAndList()");
		LogValue logValues = new LogValue();
		logValues.setLogType("info");
		logValues.setLogMessage("mensaje prueba");
		
		Log.info("LogValues:" + logValues);
		System.out.println("LogValues:" + logValues);
		logValuesService.saveLogsValue(logValues);
		
		List<LogValue> logList = logValuesService.getLogsValue();
		System.out.println("logList:" + logList);
	}
	
	@Test
	public void processJobLogger() throws Exception {
		System.out.println("processJobLogger()");
		//JobLoggerRefactorizedService jobLogger = new JobLoggerRefactorizedService();
		LogMessageBean logMessageBean = new LogMessageBean();
		logMessageBean.setMessageText("MENSAJE DE PRUEBA");
		logMessageBean.setError(true);
		logMessageBean.setMessage(true);
		logMessageBean.setWarning(true);
		
		jobLoggerRefactorizedService.LogMessageRefactorized(logMessageBean);
		
		List<LogValue> logList = logValuesService.getLogsValue();
		System.out.println("logList:" + logList);
	}

}
