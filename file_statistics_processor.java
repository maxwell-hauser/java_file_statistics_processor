import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.EventQueue;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class file_statistics_processor
{
	public static void main(String[] args) throws IOException
	{
		JOptionPane.showMessageDialog(null, 
                  "                                           CSC 229 – Project 3\n"
                + "                                              File Processing\n"
                + "__________________________________________________________________\n"
                + "            Description: This program reads a text file of integer data\n"
                + "                                and creates an output text file with the same\n"
                + "                                information, plus minimum, maximum, total,\n"
                + "                                and average of data.\n\n"
                + "            Input:           A text file of integer values, file size is the\n"
                + "                                first data item on the first line.\n\n"
                + "            Output:        Entire content of input file, 10 data items per\n"
                + "                                line plus statistics (minimum, maximum, total,\n"
                + "                                average (one per line)).\n"
                + "__________________________________________________________________\n",
                "Project 3 – Hauser", JOptionPane.INFORMATION_MESSAGE);
		
		//When running "chooser.showOpenDialog(null)" after "JOptionPane.showMessageDialog()", 
		//the pop-up wouldn't display and the program wouldn't proceed.
		//Running "chooser.showOpenDialog(null)" inside EventQueue.invokeLater fixed the issue
		
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try
				{
					Scanner inputFile = new Scanner("input");
					PrintWriter outputFile = new PrintWriter("output");
					JFileChooser chooser = new JFileChooser();
					
					String inputFilePath = "";
					String inputFileName = "";
					String outputFilePath = "";
					String outputFileName = "";
					
					int fileSize = 0;
					int fileMinimum = Integer.MAX_VALUE;
					int fileMaximum = Integer.MIN_VALUE;
					double fileSum = 0.0;
					double fileAverage = 0.0;
					
					//Input file path
					chooser.setDialogTitle("Open Data File");
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"Text Data Files", "txt");
					chooser.setFileFilter(filter);
					
					int returnVal = chooser.showOpenDialog(null);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						inputFilePath = chooser.getSelectedFile().getPath();
						inputFileName = chooser.getSelectedFile().getName();
					
						System.out.println(inputFilePath);
						System.out.println(inputFileName);
					}
					else {System.exit(0);}
					
					//Output file path
					chooser.setDialogTitle("Save Output File");
					returnVal = chooser.showSaveDialog(null);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						outputFilePath = chooser.getSelectedFile().getPath();
						outputFileName = chooser.getSelectedFile().getName();
						
						System.out.println(outputFilePath);
						System.out.println(outputFileName);
					}
					else {System.exit(0);}
										
					inputFile = new Scanner(new File(inputFilePath));
					outputFile = new PrintWriter(outputFilePath);
					
					int intData;
					int incrementor = 0;
					fileSize = inputFile.nextInt();
					outputFile.println(Integer.toString(fileSize));

					while(inputFile.hasNext())
					{
						intData = inputFile.nextInt();
						outputFile.print(Integer.toString(intData) + " ");
						fileSum += intData;
						if (intData < fileMinimum)
						{
							fileMinimum = intData;
						}
						if (intData > fileMaximum)
						{
							fileMaximum = intData;
						}
						
						incrementor++;
						if (incrementor == 10)
						{
							outputFile.print("\n");
							incrementor = 0;
						}
					}
					
					fileAverage = fileSum/fileSize;
					outputFile.println(fileMinimum);
					outputFile.println(fileMaximum);
					outputFile.println(fileSum);
					outputFile.println(fileAverage);
					
					JOptionPane.showMessageDialog(null,
							  "                                        CSC 229 - Project 03\n"
							+ "                                           File Processing\n"
							+ "__________________________________________________________________\n"
							+ "                 Input file name              :            " + inputFileName + "\n"
							+ "                 Output file name           :            " + outputFileName + "\n"
							+ "                 File size                         :            " + fileSize + "\n"
							+ "__________________________________________________________________\n"
							+ "                                                Statistics\n"
							+ "__________________________________________________________________\n"
							+ "                                  Minimum      :      " + fileMinimum + "\n"
							+ "                                  Maximum     :      " + fileMaximum + "\n"
							+ "                                  Total            :      " + fileSum + "\n"
							+ "                                  Average        :      " + fileAverage + "\n"
							+ "__________________________________________________________________"
									  
							,"Project 03 - Hauser", JOptionPane.INFORMATION_MESSAGE );
					
					inputFile.close();
					outputFile.close();
					System.exit(0);
				}
				catch (Exception e)
				{
					//This will never throw an exception,
					//but the try-catch is required by InvokeLater's "run()" method.
					System.out.print(e.getMessage());
				}
			}
		});			
	}
}
