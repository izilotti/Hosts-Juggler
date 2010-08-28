package com.zilotti.hostsjuggler.domain;


/**
 * A hosts file line has a literal representation, exactly like
 * the one stored in the hosts file.  
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public interface HostsFileLine
{
	/**
	 * @return The line
	 */
	String getLine();
	
	/**
	 * @param line
	 */
	void setLine(String line);
}
