import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.EventQueue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class FileStatisticsProcessor {
    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(() -> {
            try {
                JOptionPane.showMessageDialog(
                        null,
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
                        "Project 3 – Hauser",
                        JOptionPane.INFORMATION_MESSAGE);

                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Open Data File");
                chooser.setFileFilter(new FileNameExtensionFilter("Text Data Files", "txt"));
                chooser.setAcceptAllFileFilterUsed(false);

                String inputFilePath;
                String inputFileName;
                String outputFilePath;
                String outputFileName;

                int returnVal = chooser.showOpenDialog(null);
                if (returnVal != JFileChooser.APPROVE_OPTION) {
                    System.exit(0);
                    return;
                }
                File inputFile = chooser.getSelectedFile();
                inputFilePath = inputFile.getPath();
                inputFileName = inputFile.getName();

                chooser.setDialogTitle("Save Output File");
                returnVal = chooser.showSaveDialog(null);
                if (returnVal != JFileChooser.APPROVE_OPTION) {
                    System.exit(0);
                    return;
                }
                File outputFile = chooser.getSelectedFile();
                outputFilePath = ensureTxtExtension(outputFile.getPath());
                outputFileName = new File(outputFilePath).getName();

                int fileSize;
                int fileMinimum = Integer.MAX_VALUE;
                int fileMaximum = Integer.MIN_VALUE;
                long fileSum = 0L;
                double fileAverage;

                try (Scanner in = new Scanner(new File(inputFilePath));
                     PrintWriter out = new PrintWriter(outputFilePath)) {

                    in.useLocale(Locale.US);

                    if (!in.hasNextInt()) {
                        showError("The first value in the file must be an integer file size.");
                        return;
                    }
                    fileSize = in.nextInt();
                    if (fileSize <= 0) {
                        showError("File size must be a positive integer.");
                        return;
                    }

                    out.println(Integer.toString(fileSize));

                    int itemOnLine = 0;
                    int count = 0;
                    while (count < fileSize) {
                        if (!in.hasNextInt()) {
                            showError("Input ended before reading " + fileSize + " integers.");
                            return;
                        }
                        int value = in.nextInt();

                        out.print(value);
                        count++;
                        itemOnLine++;
                        if (itemOnLine == 10) {
                            out.println();
                            itemOnLine = 0;
                        } else if (count < fileSize) {
                            out.print(" ");
                        }

                        fileSum += value;
                        if (value < fileMinimum) fileMinimum = value;
                        if (value > fileMaximum) fileMaximum = value;
                    }
                    if (itemOnLine != 0) {
                        out.println();
                    }

                    fileAverage = ((double) fileSum) / fileSize;
                    out.println(fileMinimum);
                    out.println(fileMaximum);
                    out.println(fileSum);
                    out.println(String.format(Locale.US, "%.2f", fileAverage));

                    JOptionPane.showMessageDialog(
                            null,
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
                                    + "                                  Average        :      " + String.format(Locale.US, "%.2f", fileAverage) + "\n"
                                    + "__________________________________________________________________",
                            "Project 03 - Hauser",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                showError("An error occurred: " + e.getMessage());
            } finally {
                System.exit(0);
            }
        });
    }

    private static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static String ensureTxtExtension(String path) {
        int dot = path.lastIndexOf('.');
        int sep = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));
        boolean hasExt = dot > sep;
        if (!hasExt) {
            return path + ".txt";
        }
        return path;
    }
}
