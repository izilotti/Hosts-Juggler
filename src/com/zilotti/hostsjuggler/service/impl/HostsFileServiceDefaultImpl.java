package com.zilotti.hostsjuggler.service.impl;

import com.zilotti.hostsjuggler.domain.HostsFile;
import com.zilotti.hostsjuggler.service.HostsFileService;

/**
 * Deals with hosts files.
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public class HostsFileServiceDefaultImpl implements HostsFileService
{
	/**
	 * @see com.zilotti.hostsjuggler.service.HostsFileService#loadHostsFile(java.lang.String)
	 */
	@Override
	public HostsFile loadHostsFile(String path) throws Exception
	{
		return null;
	}

	/**
	 * @see com.zilotti.hostsjuggler.service.HostsFileService#saveHostsFile(com.zilotti.hostsjuggler.domain.HostsFile)
	 */
	@Override
	public void saveHostsFile(HostsFile hostsFile) throws Exception
	{
	}
	
}
