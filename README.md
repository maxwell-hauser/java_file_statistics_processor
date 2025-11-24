# File Statistics Processor

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?style=flat&logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat)
![Status](https://img.shields.io/badge/Status-Active-success?style=flat)

A Swing-based Java utility that processes integer data files, reformats the output, and calculates comprehensive statistics including minimum, maximum, total, and average values.

## ✨ Features

- **File Processing**: Read and validate integer data files
- **Formatted Output**: Write data with 10 numbers per line
- **Statistical Analysis**: Calculate min, max, total, and average
- **Input Validation**: Verify data count and format integrity
- **GUI Interface**: User-friendly file selection dialogs
- **Sample Data**: Includes multiple test files (10-700 integers)

## 📁 Input Format

- First integer: count of following integers
- Subsequent integers: data values (space or newline separated)

## 📄 Output Format

- Line 1: File size (count)
- Lines 2-n: Data values (10 per line)
- Final lines: Statistics (one per line)
  - Minimum value
  - Maximum value
  - Total sum
  - Average (2 decimal places)

## 🚀 Quick Start

### Using Maven (Recommended)

```powershell
# Build the project
mvn clean package

# Run the application
java -jar target/file-statistics-processor-1.0.0.jar
```

### Without Maven

```powershell
# Compile
javac FileStatisticsProcessor.java

# Run
java FileStatisticsProcessor
```

## 📖 Usage

1. Launch the application
2. Select an input file (sample files available in `data/` directory)
3. Choose output location and filename
4. View processing summary dialog
5. Check output file for formatted data and statistics

## 📊 Sample Data

The `data/` directory includes test files:
- `Data - 10.txt` (10 integers)
- `Data - 20.txt` (20 integers)
- `Data - 50.txt` (50 integers)
- `Data - 100.txt` (100 integers)
- `Data - 200.txt` (200 integers)
- `Data - 400.txt` (400 integers)
- `Data - 600.txt` (600 integers)
- `Data - 700.txt` (700 integers)

## 📋 Requirements

- **JDK 21** (Temurin recommended)
- **Maven 3.9+**

## 🏗️ Building from Source

```powershell
# Clone the repository
git clone https://github.com/maxwell-hauser/java_file_statistics_processor.git
cd java_file_statistics_processor

# Build with Maven
mvn clean install

# Run tests
mvn test
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Maxwell Hauser**

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](https://github.com/maxwell-hauser/java_file_statistics_processor/issues).