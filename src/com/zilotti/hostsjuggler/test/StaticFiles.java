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
package com.zilotti.hostsjuggler.test;

public class StaticFiles
{

	/**
	 * A faily complex MS Windows hosts file.
	 *  
	 * @return
	 */
	public static String getWindowsHostsFile()
	{
		return "# Copyright (c) 1993-1999 Microsoft Corp.\n"+
		"#\n"+
		"# This is a sample HOSTS file used by Microsoft TCP/IP for Windows.\n"+
		"#\n"+
		"# This file contains the mappings of IP addresses to host names. Each\n"+
		"# entry should be kept on an individual line. The IP address should\n"+
		"# be placed in the first column followed by the corresponding host name.\n"+
		"# The IP address and the host name should be separated by at least one\n"+
		"# space.\n"+
		"#\n"+
		"# Additionally, comments (such as these) may be inserted on individual\n"+
		"# lines or following the machine name denoted by a '#' symbol.\n"+
		"#\n"+
		"# For example:\n"+
		"#\n"+
		"#      102.54.94.97     rhino.acme.com          # source server\n"+
		"#       38.25.63.10     x.acme.com              # x client host\n"+
        "\n"+
        "\n"+
		"#\n"+
		"# PRODUCTION\n"+
		"#\n"+
		"#127.0.0.1       localhost\n"+
		"#172.16.120.6    Hawk.CWICO.COM  domino1.web.cwico.com\n"+
		"#172.16.120.58   Flamingo.CWICO.COM      \n"+
		"#172.16.120.69   Falcon.cwico.com        www.cwico.com\n"+
		"#172.16.120.5	Seagull.cwico.com\n"+
		"#172.16.120.4	CWIST2	Pelican.CWICO.Com\n"+
		"#172.16.120.23   penguin.cwico.com\n"+
		"#172.16.120.10   www.ckhlawyers.com\n"+
		"#10.10.14.42 cwidev2.insurance.cwico.com chickenhawk\n"+
		"#172.16.120.101  harrier.cwico.com       Harrier\n"+
		"#172.16.120.102  kite.cwico.com  Kite\n"+
		"#172.16.120.18	Falcon.cwico.com www.cwico.com\n"+
		"#172.16.120.23 www.cwico.com #WEB SERVER\n"+
        "\n"+
		"#\n"+
		"# ALL ENVIRONMENTS\n"+
		"#\n"+
		"127.0.0.1       localhost\n"+ 
        "  \n"+
        " \n"+
		"#\n"+
		"# TEST\n"+ 
		"#\n"+
		"#---[Domino Servers]------------------------------------------------------------------------------------------\n"+
		"172.16.120.57   CWI2.CWICO.COM hawk.cwico.com  domino1.web.cwico.com  chickenhawk.insurance.cwico.com  webtest.web.cwico.com\n"+
        "\n"+ 
        "\n"+
		"#---[WebSphere Servers]---------------------------------------------------------------------------------------\n"+
		"10.10.152.78	Seagull.cwico.com   penguin.cwico.com   webtest1  www.ckhlawyers.com  \n"+
		"#10.1.99.107 www.cwico.com\n"+
        "\n"+
        " \n"+ 
		"#\n"+
		"# DEVELOPMENT\n"+
		"#\n"+
		"#10.10.14.42     hawk.cwico.com  domino1.web.cwico.com  chickenhawk.insurance.cwico.com\n"+
		"#172.16.120.50	Seagull.cwico.com   penguin.cwico.com   webtest1  www.ckhlawyers.com  www.cwico.com\n"+
        "\n"+ 
        "\n"+
		"#\n"+
		"# PRODUCTION\n"+
		"#\n"+
		"#172.16.125.126  www.cwico.com # WEBSEAL\n"+
		"#-- OLD --172.16.125.22 www.cwico.com\n"+
        "\n"+
        "\n"+
        "\n"+
		"# (LOCALHOST)\n"+ 
		"#10.10.14.42     hawk.cwico.com  domino1.web.cwico.com  chickenhawk.insurance.cwico.com\n"+
        "\n"+
		"# (PRODUCTION)\n"+
		"#172.16.120.6    hawk.cwico.com  domino1.web.cwico.com  chickenhawk.insurance.cwico.com\n"+
        "\n"+
        "\n"+
        "\n"+
		"# (LOCALHOST)\n"+ 
		"#172.16.120.50	Seagull.cwico.com   penguin.cwico.com   webtest1  www.ckhlawyers.com  www.cwico.com\n"+
        "\n"+
		"# (PRODUCTION)\n"+
		"#172.16.120.23	Seagull.cwico.com   penguin.cwico.com   webtest1  www.ckhlawyers.com  www.cwico.com\n"+

		"#172.16.120.19 penguin.cwico.com\n"+
		"#172.16.120.121 penguin.cwico.com\n"+
        "\n"+
        "\n"+
		"#172.16.125.22   www.cwico.com\n"+
		"#10.10.14.15     webtest2\n";
	}

	
	/**
	 * Ubuntu Linux 10.04 Default hosts file.
	 * 
	 * @return
	 */
	public static String getLinuxHostsFile()
	{
		return "127.0.0.1	localhost\n"+
				"127.0.1.1	ialencar-laptop\n"+
				"\n"+
				"# The following lines are desirable for IPv6 capable hosts\n"+
				"::1     localhost ip6-localhost ip6-loopback\n"+
				"fe00::0 ip6-localnet\n"+
				"ff00::0 ip6-mcastprefix\n"+
				"ff02::1 ip6-allnodes\n"+
				"ff02::2 ip6-allrouters\n"+
				"ff02::3 ip6-allhosts\n";
	}
	
	
	/**
	 * Mac hosts file
	 * 
	 * @return
	 */
	public static String getMacHostsFile()
	{
		return "##\n"+
				"# Host Database\n"+
				"#\n"+
				"# localhost is used to configure the loopback interface\n"+
				"# when the system is booting.  Do not change this entry.\n"+
				"##\n"+
				"127.0.0.1    localhost\n"+
				"255.255.255.255    broadcasthost\n"+
				"::1             localhost\n"+
				"fe80::1%lo0    localhost		";
	}
}
