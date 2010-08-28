package com.zilotti.hostsjuggler.service;

import com.zilotti.hostsjuggler.domain.HostsFile;

/**
 * All operations associated to a hosts file. 
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public interface HostsFileService
{
	/**
	 * Loads a hosts file into its corresponding object
	 * representation.
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	HostsFile loadHostsFile(String path) throws Exception;
	
	
	/**
	 * Save a hosts file from its object representation.
	 * 
	 * @param hostsFile
	 * @throws Exception
	 */
	void saveHostsFile(HostsFile hostsFile) throws Exception;
	
}
