/*
 * Copyright 2010 Ivan Zilotti Alencar <ialencar@zilotti.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zilotti.hostsjuggler.logging;

import java.io.File;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.RootLogger;

import com.zilotti.utils.FileUtils;

public class LogFactory
{
	/** Root logger */
	private static Logger rootLogger = Logger.getRootLogger();

	/** Log file name */
	private static final String LOGGING_FILE_NAME = "HostsJuggler.log";
	
	static
	{
		try
		{
			/* Console appender */
			rootLogger.setLevel(Level.DEBUG);
			rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %c{1} [%p] %m%n")));
	
			/*
			 * File appender
			 */
			String logginFilePath = FileUtils.getTempPath().concat(String.valueOf(File.separatorChar)).concat(LOGGING_FILE_NAME);
			FileAppender fileAppender = new FileAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %c{1} [%p] %m%n"), logginFilePath);
			rootLogger.addAppender(fileAppender);
		}
		catch(Exception e)
		{
			System.err.println("Error initializing system!");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Private constructor to defeat instantiation
	 */
	private LogFactory()
	{
		super();
	}
	
	
	
	/**
	 * @param klass
	 * @return
	 */
	public static Logger getLogger(Class<? extends Object> klass)
	{
		return RootLogger.getLogger(klass);
	}
	
	
}
