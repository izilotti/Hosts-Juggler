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
package com.zilotti.utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang.SystemUtils;

/**
 * Utility class that supports network-related operations.
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public class NetworkUtils
{
	/**
	 * Constructor. Defeats instantiation. 
	 */
	private NetworkUtils()
	{
		super();
	}
	
	
	public static final String IPV4_REGEX = "\\A(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}\\z";
	public static final String IPV6_HEX4DECCOMPRESSED_REGEX = "\\A((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?) ::((?:[0-9A-Fa-f]{1,4}:)*)(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}\\z";
	public static final String IPV6_6HEX4DEC_REGEX = "\\A((?:[0-9A-Fa-f]{1,4}:){6,6})(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}\\z";
	public static final String IPV6_HEXCOMPRESSED_REGEX = "\\A((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)\\z";
	public static final String IPV6_REGEX = "\\A(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}\\z";

//    private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
//    private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
//    private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");



	public static boolean isIpAddress(String ipAddress)
	{
		return (isIpV4(ipAddress) || isIpV6(ipAddress));
	}
	
	public static boolean isIpV4(String ipAddress)
	{
		return ipAddress.matches(IPV4_REGEX);
	}
	
	public static boolean isIpV6(String ipAddress)
	{
		return (ipAddress.matches(IPV6_REGEX) 
				|| (isIpV6_Hex4DecCompressed(ipAddress) 
						|| isIpV6_Hex4Dec(ipAddress)
						|| isIpV6_HexCompressed(ipAddress) ));
	}
	
	public static boolean isIpV6_Hex4DecCompressed(String ipAddress)
	{
		return ipAddress.matches(IPV6_HEX4DECCOMPRESSED_REGEX);
	}

	public static boolean isIpV6_Hex4Dec(String ipAddress)
	{
		return ipAddress.matches(IPV6_HEX4DECCOMPRESSED_REGEX);
	}
	
	public static boolean isIpV6_HexCompressed(String ipAddress)
	{
		return ipAddress.matches(IPV6_HEXCOMPRESSED_REGEX);
	}

    /**
     * Validates a host name.
     *
     * @param value
     * @return
     */
    public static boolean isHostName(String value) 
    {
        if(value == null) return false;
        
        /*
         * A valid domain can be 63 chars long with out the extension or 67
         * chars long with it. We should be getting them with extenstions so
         * we allow the 67 length. 
         */
        if(value.length() > 67) 
        {
            return false;
        }
        
        Pattern p = Pattern.compile("^(?=.*?[a-z])(?!\\.)[a-z\\d.-]*[a-z\\d]$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(value);
        
        return m.matches();
    }

    
    
	
    /**
     * Provides the full path to the hosts file of the current
     * operational system.
     *  
     * @return
     */
    public static File getSystemHostsFilePath()
    {
    	if(SystemUtils.IS_OS_LINUX)
    		return new File("/etc/hosts");
    	
    	if(SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX)
    		return new File("/private/etc/hosts");
    		
    	if(SystemUtils.IS_OS_WINDOWS)
    	{
    		// TODO: Implement on Windows environment
    		File[] roots = FileSystemView.getFileSystemView().getRoots();
    		for(File root : roots)
    			System.out.println(root.getPath());
    		
    		return new File("c:/Windows/system32/drivers/etc/hosts");
    	}
    	
    	throw new RuntimeException("Operational System not supported: "+ System.getProperty("os.name"));
    }
}
