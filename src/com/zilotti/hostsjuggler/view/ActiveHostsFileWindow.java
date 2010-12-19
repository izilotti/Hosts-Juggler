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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.zilotti.hostsjuggler.dao.HostsFileDAO;
import com.zilotti.hostsjuggler.dao.impl.HostsFileSerializationDAO;
import com.zilotti.hostsjuggler.domain.BlankLine;
import com.zilotti.hostsjuggler.domain.CommentLine;
import com.zilotti.hostsjuggler.domain.HostLine;
import com.zilotti.hostsjuggler.domain.HostsFile;
import com.zilotti.utils.NetworkUtils;

/**
 * 
 * 
 * @author Ivan Alencar
 * @author $Author:   Ivan Alencar  $
 * @version $Revision:   1.0  $
 */
public class ActiveHostsFileWindow
{
	/** Logger */
	private static final Logger log = Logger.getLogger(ActiveHostsFileWindow.class);  //  @jve:decl-index=0:
	
	/** Active hosts file object */
	private HostsFile activeHostsFile;
	
	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="17,13"
	private Table table = null;
	private Label label = null;
	private Button switchHostsFileButton = null;
	private Button minimizeToTrayButton = null;
	private Button exitButton = null;
	private Menu menuBar = null;
	private StyledText activeHostsFileStyledText = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		/* Before this is run, be sure to set up the launch configuration (Arguments->VM Arguments)
		 * for the correct SWT library path in order to run with the SWT dlls. 
		 * The dlls are located in the SWT plugin jar.  
		 * For example, on Windows the Eclipse SWT 3.1 plugin jar is:
		 *       installation_directory\plugins\org.eclipse.swt.win32_3.1.0.jar
		 */
		Display display = Display.getDefault();
		ActiveHostsFileWindow thisClass = new ActiveHostsFileWindow();
		thisClass.createSShell();
		thisClass.sShell.open();

		/*
		 * Loads active hosts file
		 */
		File activeHostsFile = getActiveHostsFile();
		thisClass.highlightActiveHostsFile(activeHostsFile);
		thisClass.label.setText("Current hosts file (located at "+ activeHostsFile.getPath() +")");

		HostsFile hf = thisClass.loadHostsFile(activeHostsFile);
		
		System.out.println("Lines: "+ hf.getHostsFileLines().size());
		
		HostsFileDAO hfDAO = new HostsFileSerializationDAO();
		hfDAO.save(hf);
		
		
		while(!thisClass.sShell.isDisposed())
		{
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * This method initializes sShell
	 */
	private void createSShell()
	{
		sShell = new Shell();
		sShell.setText("Hosts Juggler");
		sShell.setLayout(null);
		sShell.setSize(new Point(726, 766));
		
		/*
		 * Menu
		 */
		final Menu m = new Menu(sShell, SWT.BAR);
		sShell.setMenuBar(m);
		
		/* Hosts File */
		// create a file menu and add an exit item
	    final MenuItem file = new MenuItem(m, SWT.CASCADE);
	    file.setText("&Hosts File");
	    final Menu filemenu = new Menu(sShell, SWT.DROP_DOWN);
	    file.setMenu(filemenu);
	    final MenuItem openItem = new MenuItem(filemenu, SWT.CASCADE);
	    openItem.setText("&Open");
	    final Menu submenu = new Menu(sShell, SWT.DROP_DOWN);
	    openItem.setMenu(submenu);
	    final MenuItem childItem = new MenuItem(submenu, SWT.PUSH);
	    childItem.setText("&Child\tCTRL+C");
	    childItem.setAccelerator(SWT.CTRL + 'C');
	    final MenuItem dialogItem = new MenuItem(submenu, SWT.PUSH);
	    dialogItem.setText("&Dialog\tCTRL+D");
	    dialogItem.setAccelerator(SWT.CTRL + 'D');
	    final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
	    final MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
	    exitItem.setText("E&xit");

	    // create an edit menu and add cut copy and paste items
	    final MenuItem edit = new MenuItem(m, SWT.CASCADE);
	    edit.setText("&Edit");
	    final Menu editmenu = new Menu(sShell, SWT.DROP_DOWN);
	    edit.setMenu(editmenu);
	    final MenuItem cutItem = new MenuItem(editmenu, SWT.PUSH);
	    cutItem.setText("&Cut");
	    final MenuItem copyItem = new MenuItem(editmenu, SWT.PUSH);
	    copyItem.setText("Co&py");
	    final MenuItem pasteItem = new MenuItem(editmenu, SWT.PUSH);
	    pasteItem.setText("&Paste");

	    //create an options menu and add menu items
	    final MenuItem options = new MenuItem(m, SWT.CASCADE);
	    options.setText("&Options");
	    final Menu optionsmenu = new Menu(sShell, SWT.DROP_DOWN);
	    options.setMenu(optionsmenu);
	    final MenuItem checkItem = new MenuItem(optionsmenu, SWT.CHECK);
	    checkItem.setText("&Checked Option");
	    final MenuItem optionsseparator = new MenuItem(optionsmenu, SWT.SEPARATOR);
	    final MenuItem radioItem1 = new MenuItem(optionsmenu, SWT.RADIO);
	    radioItem1.setText("Radio &One");
	    final MenuItem radioItem2 = new MenuItem(optionsmenu, SWT.RADIO);
	    radioItem2.setText("Radio &Two");

	    //create a Window menu and add Child item
	    final MenuItem window = new MenuItem(m, SWT.CASCADE);
	    window.setText("&Window");
	    final Menu windowmenu = new Menu(sShell, SWT.DROP_DOWN);
	    window.setMenu(windowmenu);
	    final MenuItem maxItem = new MenuItem(windowmenu, SWT.PUSH);
	    maxItem.setText("Ma&ximize");
	    final MenuItem minItem = new MenuItem(windowmenu, SWT.PUSH);
	    minItem.setText("Mi&nimize");

	    // create a Help menu and add an about item
	    final MenuItem help = new MenuItem(m, SWT.CASCADE);
	    help.setText("&Help");
	    final Menu helpmenu = new Menu(sShell, SWT.DROP_DOWN);
	    help.setMenu(helpmenu);
	    final MenuItem aboutItem = new MenuItem(helpmenu, SWT.PUSH);
	    aboutItem.setText("&About");
		
		
//		/* File */
//		MenuItem fileItem = new MenuItem (menuBar, SWT.CASCADE);
//		fileItem.setText("&Hosts File");
//				
//		Menu submenu = new Menu(sShell, SWT.DROP_DOWN);
//		fileItem.setMenu(submenu);
//		MenuItem menuItem = new MenuItem (submenu, SWT.PUSH);
//		menuItem.addListener(SWT.Selection, new Listener() 
//		{
//			public void handleEvent(Event e) 
//			{
//				System.out.println ("Create");
//			}
//		});
//		menuItem.setText("Create &New\tCtrl+N");
//		menuItem.setAccelerator(SWT.MOD1 + 'N');
	
		
		/*
		 * Active hosts file location
		 */
		label = new Label(sShell, SWT.NONE);
		label.setBounds(new Rectangle(15, 10, 692, 15));		
		
		switchHostsFileButton = new Button(sShell, SWT.NONE);
		switchHostsFileButton.setBounds(new Rectangle(330, 682, 103, 26));
		switchHostsFileButton.setText("Switch");
		
		switchHostsFileButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() 
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent e)
			{
			}

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				openSwitchDialog(sShell);
			}
		});
				
				
		minimizeToTrayButton = new Button(sShell, SWT.NONE);
		minimizeToTrayButton.setBounds(new Rectangle(451, 682, 108, 28));
		minimizeToTrayButton.setText("Hide");
		exitButton = new Button(sShell, SWT.NONE);
		exitButton.setBounds(new Rectangle(574, 683, 117, 28));
		exitButton.setText("Exit");
		
		/*
		 * Active file
		 */
		activeHostsFileStyledText = new StyledText(sShell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
		activeHostsFileStyledText.setText("");
		activeHostsFileStyledText.setTabs(5);
		activeHostsFileStyledText.setBounds(new Rectangle(15, 33, 694, 616));
		
	}

	
	/**
	 * Opens 
	 * @param parent
	 */
	private void openSwitchDialog(Shell parent)
	{
		Shell dialog = new Shell(parent, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		dialog.setLayout(new FillLayout());
		dialog.setText("Choose Hosts File");
		dialog.setSize(500, 500);
		dialog.open();
		
		/*
		 * Table
		 */
		table = new Table(dialog,  SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setBounds(new Rectangle(14, 363, 383, 198));

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("IP Address");
		TableColumn tableColumn1 = new TableColumn(table, SWT.NONE);
		
		
		/*
		 * Items 
		 */
		for (int i=0; i<120; i++) 
		{
			TableItem item = new TableItem (table, SWT.NONE);
			
			String[] itemText = new String[] {
					"192.168.1." + i,
					"penguin.insurance.cwico.com seagull.insurance.cwico.com duckling.insurance.cwico.com"
					};
			
			item.setText(itemText);
		}
		


		
		tableColumn1.setWidth(400);
		tableColumn1.setText("Host Names");

	}
	
	
	
	/**
	 * Reads a hosts file from disk and highlights its
	 * elements in a SWT styled text box.
	 * 
	 * @param hostsFile
	 * @throws IOException
	 * @throws ParseException
	 */
	final String REM_LINE_CHAR = "#";

	private void highlightActiveHostsFile(File hostsFile) throws IOException, ParseException
	{
		BufferedReader br = null;
		
		try
		{
			/* Converts the file to text */
			// StringReader fileReader = new StringReader(getLinuxHostsFile()); // For testing
			br = new BufferedReader(new FileReader(hostsFile));
			
			/* Character counter */
			int charCounter = 0;
	
			/* Line counter */
			int lineCounter = 1;
			
			/* Line */
			String line = null;
			
			while((line = br.readLine()) != null)
			{
				line += "\n";
				activeHostsFileStyledText.append(line);
				
				/*
				 * Remark line
				 */
				if(line.startsWith(REM_LINE_CHAR))
				{
					int prevCharCounter = charCounter;
					charCounter += line.length();
					
					formatRemark(prevCharCounter, charCounter);
					
					if(log.isTraceEnabled())
					{
						log.trace("line  ='"+ line +"'");
						//log.trace("remark='"+ getWindowsHostsFile().substring(prevCharCounter, charCounter) +"' ("+ prevCharCounter +","+ charCounter +")");
					}
				} 
				else if(StringUtils.isBlank(line)) // Empty line
				{
					charCounter += line.length();
				}
				else // Expects a host line
				{
					int localCharCounter = charCounter;
					charCounter += line.length();
					
					Scanner scanner = new Scanner(line);
					scanner.useDelimiter(Pattern.compile("(\\s)"));
	
					/* Output of the parsing code */
					String ipAddress = null;
					
					/* Verifies the number of tokens. At least two must exist (IP address and one name) */
					if(scanner.hasNext())
					{
						/* The first token must be an IP address */
						{
							ipAddress = scanner.next();
							
							if(!NetworkUtils.isIpAddress(ipAddress))
								throw new ParseException("IP address expected. Token found: "+ ipAddress, lineCounter);
						
							int prevCharCounter = localCharCounter;				
							localCharCounter += ipAddress.length() + 1; // Sums 1 because of the lost space
							
							formatIpAddress(prevCharCounter, localCharCounter);
						}
						
						/* The remaining tokens are the host names associated to the IP address */
						{
							while(scanner.hasNext())
							{
								String hostName = scanner.next();
								
								if(StringUtils.isWhitespace(hostName) || StringUtils.isBlank(hostName))
								{
									localCharCounter++;
								}
								else if(NetworkUtils.isHostName(hostName))
								{
									int prevCharCounter = localCharCounter;				
									localCharCounter += hostName.length() + 1; // 1 to compensate the space lost
									
	//								if(log.isTraceEnabled())
	//									log.trace("hostName='"+ getWindowsHostsFile().substring(prevCharCounter, localCharCounter) +"' ("+ prevCharCounter +","+ localCharCounter +")");
									
									formatHostName(prevCharCounter, localCharCounter);
								}
								else
									throw new ParseException("Host name expected at token "+ localCharCounter +". Found: "+ hostName, lineCounter);
							}
						}
					}
					else
						throw new ParseException("At least 2 tokens are expected from a host line.", lineCounter);
				}
				
				lineCounter++;
			}
		}
		finally
		{
			if(br != null)
				br.close();
		}
	}
	
	private void formatRemark(int start, int end)
	{
		StyleRange style = new StyleRange();
		style.start = start;
		style.length = end-start;
		style.font = new Font(sShell.getDisplay(), "Courier New", 9, SWT.NORMAL);
		style.foreground = new Color(sShell.getDisplay(), 0, 128, 0); // Green		

		activeHostsFileStyledText.setStyleRange(style);
	}
	
	private void formatIpAddress(int start, int end)
	{
		StyleRange style = new StyleRange();
		style.start = start;
		style.length = end-start;
		style.font = new Font(sShell.getDisplay(), "Courier New", 9, SWT.BOLD);
		style.foreground = new Color(sShell.getDisplay(), 255, 127, 0); // Orange		
		
		activeHostsFileStyledText.setStyleRange(style);
	}
	
	private void formatHostName(int start, int end)
	{
		StyleRange style = new StyleRange();
		style.start = start;
		style.length = end-start;
		style.font = new Font(sShell.getDisplay(), "Courier New", 9, SWT.BOLD);
		style.foreground = sShell.getDisplay().getSystemColor(SWT.COLOR_BLUE);		
		
		activeHostsFileStyledText.setStyleRange(style);
	}

	
	
	/**
	 * Parses a hosts file and loads it into its corresponding Javabean 
	 * representation.
	 *  
	 * @param hostsFile
	 * @return
	 * @throws IOException
	 */
	private HostsFile loadHostsFile(File hostsFile) throws IOException, ParseException
	{
		/* Object to be returned */
		HostsFile hostsFileObject = new HostsFile();
		
		BufferedReader br = null;
		
		int lineCounter = 1;
		try
		{
			br = new BufferedReader(new FileReader(hostsFile));
			
			String line = null;
			while((line = br.readLine()) != null)
			{
				line += "\n";
				
				/* Remark */
				if(line.startsWith(REM_LINE_CHAR))
				{
					CommentLine commentLine = new CommentLine();
					commentLine.setLine(line);
					hostsFileObject.addLine(commentLine);
				}
				else if(StringUtils.isBlank(line)) // Blank line
				{
					BlankLine blankLine = new BlankLine();
					blankLine.setLine(line);
					hostsFileObject.addLine(blankLine);
				}
				else 
				{
					Scanner scanner = new Scanner(line);
					HostLine hostLine = new HostLine();
					
					if(scanner.hasNext())
					{
						/* Expects an IP address */
						String ipAddress = scanner.next();
						
						if(NetworkUtils.isIpAddress(ipAddress))
						{
							hostLine.setIpAddress(ipAddress);
						}
						else
							throw new ParseException("Expected an IP address but found '"+ ipAddress +"'", lineCounter);
						
						/* Expects a list of hostnames */
						List<String> hostNames = new LinkedList<String>();
						String hostName = null;
						while(scanner.hasNext())
						{
							hostName = scanner.next();
							
							if(NetworkUtils.isHostName(hostName))
							{
								hostNames.add(hostName);
							}
							else
								throw new ParseException("Expected a hostname but found '"+ hostName +"'", lineCounter);
						}
						
						hostLine.setHostNames(hostNames);
						hostLine.setLine(line);
						hostsFileObject.addLine(hostLine);
					}
				}
			}
		}
		finally
		{
			if(br != null)
				br.close();
		}
		
		return hostsFileObject;
	}
	
	
	/**
	 * Verifies whether the hosts file exists.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	private static File getActiveHostsFile() throws FileNotFoundException
	{
		File activeHostsFile = NetworkUtils.getSystemHostsFilePath();
		
		if(NetworkUtils.getSystemHostsFilePath().exists())
		{
			return activeHostsFile;
		}
		else
			throw new FileNotFoundException("Active hosts file was not found under the expected path: '"+ activeHostsFile.getPath() +"'.");
	}
}
