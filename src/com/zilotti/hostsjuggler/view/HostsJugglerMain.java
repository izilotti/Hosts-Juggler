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
package com.zilotti.hostsjuggler.view;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.apache.log4j.Logger;

import com.zilotti.hostsjuggler.logging.LogFactory;
import com.zilotti.utils.FileUtils;

/**
 * Main class.
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public class HostsJugglerMain
{
	/** Logger */
	private static final Logger log = LogFactory.getLogger(HostsJugglerMain.class);
	
	/** Static initialization block */
	static
	{
		/* Basic system initialization */
		systemInitializer();
		
		/* Initializes this class' logger */
//		log = Logger.getLogger(HostsJugglerMain.class);
	}
	
	



	
	/**
	 * Constructor
	 */
	public HostsJugglerMain()
	{
		final TrayIcon trayIcon;

		if(SystemTray.isSupported())
		{
			if(log.isDebugEnabled())
			{
				log.debug("System tray supported!");
				log.debug("Temporary folder: "+ FileUtils.getTempPath());
			}
			
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage("icon.gif");

			MouseListener mouseListener = new MouseListener()
			{
				public void mouseClicked(MouseEvent e)
				{
					System.out.println("Tray Icon - Mouse clicked!");
				}

				public void mouseEntered(MouseEvent e)
				{
					System.out.println("Tray Icon - Mouse entered!");
				}

				public void mouseExited(MouseEvent e)
				{
					System.out.println("Tray Icon - Mouse exited!");
				}

				public void mousePressed(MouseEvent e)
				{
					System.out.println("Tray Icon - Mouse pressed!");
				}

				public void mouseReleased(MouseEvent e)
				{
					System.out.println("Tray Icon - Mouse released!");
				}

			};

			ActionListener exitListener = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Exiting...");
					System.exit(0);
				}
			};

			PopupMenu popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);

			trayIcon = new TrayIcon(image, "Hosts Juggler", popup);

			ActionListener actionListener = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					trayIcon.displayMessage("Action Event",
							"An Action Event Has Been Peformed!",
							TrayIcon.MessageType.INFO);
				}
			};

			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(actionListener);
			trayIcon.addMouseListener(mouseListener);

			// Depending on which Mustang build you have, you may need to
			// uncomment
			// out the following code to check for an AWTException when you add
			// an image to the system tray.

			try
			{
				tray.add(trayIcon);
			}
			catch(AWTException e)
			{
				System.err.println("TrayIcon could not be added.");
			}

		}
		else
		{
			System.err.println("System tray is currently not supported.");
		}
	}
	

	/**
	 * Main method.
	 * 
	 * No arguments supported.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		/*
		 * Initialization
		 */
		//systemInitializer();
		
//		String line = "172.16.120.50	Seagull.cwico.com   penguin.cwico.com   webtest1  www.ckhlawyers.com  www.cwico.com\n";
//		
//		Scanner scanner = new Scanner(line);
//		scanner.useDelimiter(Pattern.compile("\\s"));
//		while(scanner.hasNext())
//		{
//			String token = 
//			//System.out.println("token: '"+ scanner.next() +"'");
//		}
		
		new HostsJugglerMain();
	}
	

	
	/**
	 * Basic system initialization.
	 * 
	 * @throws Exception
	 */
	private static void systemInitializer()
	{
//		try
//		{
//			/*
//			 * Logging
//			 */
//			Logger rootLogger = Logger.getRootLogger();
//			
//			/* Console appender */
//			rootLogger.setLevel(Level.DEBUG);
//			rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %c{1} [%p] %m%n")));
//	
//			/*
//			 * File appender
//			 */
//			String logginFilePath = FileUtils.getTempPath().concat(String.valueOf(File.separatorChar)).concat(LOGGING_FILE_NAME);
//			FileAppender fileAppender = new FileAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %c{1} [%p] %m%n"), logginFilePath);
//			rootLogger.addAppender(fileAppender);
//		}
//		catch(Exception e)
//		{
//			System.out.println("Error initializing system!");
//			e.printStackTrace();
//		}
	}	
}