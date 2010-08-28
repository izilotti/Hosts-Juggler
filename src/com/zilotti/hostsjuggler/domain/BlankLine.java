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
package com.zilotti.hostsjuggler.domain;

import java.io.Serializable;


/**
 * Javabean that represents a blank line.
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public class BlankLine implements HostsFileLine, Serializable
{
	/** Serial version UID */
	private static final long serialVersionUID = 1L;

	/** Line */
	private String line;
	

	/**
	 * @see com.zilotti.hostsjuggler.domain.HostsFileLine#getLine()
	 */
	@Override
	public String getLine()
	{
		return this.line;
	}

	/**
	 * @see com.zilotti.hostsjuggler.domain.HostsFileLine#setLine(java.lang.String)
	 */
	@Override
	public void setLine(String line)
	{
		this.line = line;
	}
}
