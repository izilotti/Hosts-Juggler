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
package com.zilotti.hostsjuggler.dao;

import java.util.List;

import com.zilotti.hostsjuggler.domain.HostsFile;

/**
 * Data Access Object class responsible for
 * data access operations associated to hosts
 * files.
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public interface HostsFileDAO
{
	/**
	 * Persists a hosts file.
	 * 
	 * If the object's id is null, it is inserted, otherwise
	 * it updates an existing entry with the same id. If such
	 * entry doesn't exist an exception is thrown.
	 * 
	 * @param hostsFile 
	 * @throws Exception
	 */
	void save(HostsFile hostsFile) throws Exception;
	
	
	/**
	 * Persists a list of hosts files following the rules
	 * stated on the save(HostsFile hostsFile) method.
	 * 
	 * @param hostsFileList
	 * @throws Exception
	 */
	void save(List<HostsFile> hostsFileList) throws Exception;
	
	
	/**
	 * Retrieves a persisted hosts file.
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	HostsFile getById(Long id) throws Exception;

	
	/**
	 * Retrieves all persisted hosts files 
	 * 
	 * @return
	 * @throws Exception
	 */
	List<HostsFile> getAll() throws Exception;
}
