package com.zilotti.hostsjuggler.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Javabean that represents a host.
 * 
 * IPV4 host: xxx.xxx.xxx.xxx
 * 
 * 
 * IPV6 host: follows http://www.ietf.org/rfc/rfc2732.txt 
 * 
::1     localhost ip6-localhost ip6-loopback
fe00::0 ip6-localnet
ff00::0 ip6-mcastprefix
ff02::1 ip6-allnodes
ff02::2 ip6-allrouters
ff02::3 ip6-allhosts
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public class HostLine implements Serializable, HostsFileLine
{
	/** Serial version UID */
	private static final long serialVersionUID = 1L;

	/** The line of the file */
	private String line;
	
	/** IP address */
	private String ipAddress;
	
	/** Host names */
	private List<String> hostNames;
	
	
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

	public List<String> getHostNames()
	{
		return hostNames;
	}

	public void setHostNames(List<String> hostNames)
	{
		this.hostNames = hostNames;
	}
}
