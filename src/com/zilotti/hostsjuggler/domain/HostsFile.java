package com.zilotti.hostsjuggler.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Javabean that represents as hosts file.
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public class HostsFile implements Serializable
{
	/** Serial version UID */
	private static final long serialVersionUID = 1L;
	
	/** The lines of the file */
	private List<HostsFileLine> hostsFileLines;
	
	/** Identifier (automatically generated, unique) */
	private Long id;
	
	/** Name (cannot be empty) */
	private String name;
	
	/** Description */
	private String description;
	
	
	/**
	 * @param hostsFileLines
	 */
	public HostsFile()
	{
		super();
		this.hostsFileLines = new LinkedList<HostsFileLine>();
	}

	
	public void addLine(HostsFileLine hfl)
	{
		this.hostsFileLines.add(hfl);
	}


	/*
	 * Getters & setters
	 */
	public List<HostsFileLine> getHostsFileLines()
	{
		return hostsFileLines;
	}

	public void setHostsFileLines(List<HostsFileLine> hostsFileLines)
	{
		this.hostsFileLines = hostsFileLines;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}


	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getDescription()
	{
		return description;
	}


	public void setDescription(String description)
	{
		this.description = description;
	}
	
}
