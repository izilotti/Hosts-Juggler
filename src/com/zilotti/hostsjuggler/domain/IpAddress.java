package com.zilotti.hostsjuggler.domain;

import java.io.Serializable;

/**
 * Javabean that represents an IP address.
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public class IpAddress implements Serializable
{
	/** Serial version UID */
	private static final long serialVersionUID = 1L;
	
	/** IP Address */
	private String ipAddress;

	/*
	 * Getters & setters
	 */
	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
}
