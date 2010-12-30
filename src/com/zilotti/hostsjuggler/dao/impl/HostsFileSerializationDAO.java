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
package com.zilotti.hostsjuggler.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;

import com.zilotti.hostsjuggler.dao.HostsFileDAO;
import com.zilotti.hostsjuggler.domain.HostsFile;
import com.zilotti.hostsjuggler.logging.LogFactory;
import com.zilotti.utils.FileUtils;

/**
 * Java serialization based implementation of HostsFileDAO.
 *  
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public class HostsFileSerializationDAO implements HostsFileDAO
{
	/** Logger */
	private static final Logger log = LogFactory.getLogger(HostsFileSerializationDAO.class);
	
	/** 
	 * Name of the file that all objects under the hosts file and itself
	 * will be persisted to  
	 */
	private static final String PERSISTENCE_FILE_NAME = "HostsFilesData.bin"; 
	
	
	/**
	 * @see com.zilotti.hostsjuggler.dao.HostsFileDAO#getById(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HostsFile getById(Long id) throws IOException, ClassNotFoundException
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getPersistenceFile()));
		
		List<HostsFile> hostsFileList = null;
		
		try
		{
			hostsFileList = (List<HostsFile>) ois.readObject();
		}
		finally
		{
			ois.close();
		}
		
		/*
		 * Finds the matching element in the list
		 */
		for(HostsFile hf : hostsFileList)
			if(hf.getId().equals(id))
				return hf;
				
		throw new IOException("Hosts file '"+ id +"' not found!");
	}

	
	/**
	 * @see com.zilotti.hostsjuggler.dao.HostsFileDAO#save(com.zilotti.hostsjuggler.domain.HostsFile)
	 */
	@Override
	public void save(HostsFile hostsFile) throws IOException, Exception
	{
		File hf = getPersistenceFile();
		if(log.isDebugEnabled())
			log.debug("Saving hosts file "+ hostsFile.toString() +" to "+ hf);
		
		List<HostsFile> hostsFileList = getAll();
		
		/*
		 * Searches and updates the element in the current list of hosts files
		 */
		ListIterator<HostsFile> lit = hostsFileList.listIterator();
		while(lit.hasNext())
		{
			int index = lit.nextIndex();
			
			HostsFile h = lit.next();
			if(h.getId().equals(hostsFile.getId()))
			{
				if(log.isDebugEnabled())
					log.debug("Updated hosts file found at the position "+ index);
				
				hostsFileList.set(index, hostsFile);				
			}
		}
		
		
		/*
		 * Writes the whole list of hosts files.
		 */
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(hf));

		try
		{
			oos.writeObject(hostsFileList);
		}
		finally
		{
			oos.close();
		}
	}
	
	
	/**
	 * @see com.zilotti.hostsjuggler.dao.HostsFileDAO#save(java.util.List)
	 */
	@Override
	public void save(List<HostsFile> hostsFileList) throws Exception
	{
		File hf = getPersistenceFile();

		if(log.isDebugEnabled())
			log.debug("Saving hosts files to "+ hf);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(hf));

		try
		{
			oos.writeObject(hostsFileList);
		}
		finally
		{
			oos.close();
		}
	}
	
	
	/**
	 * @see com.zilotti.hostsjuggler.dao.HostsFileDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HostsFile> getAll() throws Exception
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getPersistenceFile()));
		
		List<HostsFile> hostsFileList = null;
		
		try
		{
			hostsFileList = (List<HostsFile>) ois.readObject();
		}
		finally
		{
			ois.close();
		}
		
		return hostsFileList;
	}

	/**
	 * Builds the File instance associated to the persistence file.
	 * 
	 * @return
	 * @throws IOException
	 */
	private File getPersistenceFile() throws IOException
	{
		String path = FileUtils.getTempPath() + File.separatorChar + PERSISTENCE_FILE_NAME;
		return new File(path);
	}
}
