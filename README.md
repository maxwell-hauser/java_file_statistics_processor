# File Statistics Processor

Simple Swing-based utility that reads a text file containing an integer count followed by that many integer values, echoes the values to an output file with 10 numbers per line, and appends statistics (minimum, maximum, total, average).

- Input format: first integer = file size (count of following integers)
- Output format: first line repeats file size, next lines = values (10 per line), followed by min, max, total, average (one per line)
- UI: uses dialogs to choose input and output files; shows a summary when done

## Quick Start

```powershell
Push-Location "g:\My Drive\GITHUB\java\java_file_statistics_processor_gh"
javac FileStatisticsProcessor.java
java FileStatisticsProcessor
Pop-Location
```

## Sample Data

Example files are in `data/`. Choose any of the `Data - *.txt` files as input. The output can be saved anywhere; if no extension is provided, `.txt` is appended automatically.

## Notes

- The program validates the declared file size and reads exactly that many integers.
- Non-integer tokens or insufficient values will show an error dialog.
- Average is written with two decimal places.