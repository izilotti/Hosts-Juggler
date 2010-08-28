package com.zilotti.hostsjuggler.domain;

import java.io.Serializable;


/**
 * Javabean that represents a comment.
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public class CommentLine implements Serializable, HostsFileLine
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
