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
package com.zilotti.utils;

import java.io.File;

/**
 * Recurrent operations related to files. 
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public class FileUtils
{
	/** Temporary directory name */
	private static final String TEMP_DIRECTORY_NAME = ".hostsJuggler";	
	
	/**
	 * Private constructor to defeat instantiation. 
	 */
	private FileUtils()
	{
		super();
	}


	/**
	 * Obtains the path to the temporary folder associated to
	 * the current user.
	 * 
	 * @return
	 */
	public static String getTempPath()
	{
		return System.getProperty("user.home") + File.separatorChar + TEMP_DIRECTORY_NAME;
	}
}
