# Sudoku Game

A Java Swing Sudoku game supporting both 9x9 and 16x16 (Devilish) boards.

## Running the Game

To properly test this Game of sudoku please run the Jar file.

Create a profile under any name.

Place the test file in the directory titled "Sudoku" that was created when maing your user profile.
The directory will be in the computers default directory. i.e. mine is C:\Users\Christopher\Documents\Sudoku
The file MUST be titled:

"sudoku_input_file_example.txt"

without the quotation marks of course.
**do not place the file in your user directory. Just within the Sudoku directory is fine.**


After that to open this test file in the game you must login under any profile you have created.
Then click "New Game" from the Main Menu.

This will bring up five different options.

The option you are looking for is "Test"
Click Test in the dropdown menu and click "OK" to start your game!


HOPE YOU HAVE FUN! :)


## Running Tests

The project includes a JUnit 4 test suite covering core game logic.

### Quick Start

```bash
./run_tests.sh
```

### Test Classes

| Test Class | Description |
|------------|-------------|
| DifficultyTest | Tests for difficulty levels (Easy, Medium, Hard, Devilish) |
| MainBoardTest | Tests for input validation (isInteger methods) |
| ToolsTest | Tests for board generation utilities |
| SaveTest | Tests for game save/load functionality |

### Build Tools

The project supports both Gradle and Maven:

```bash
# Using the test runner (recommended - no network required)
./run_tests.sh

# Using Gradle (requires network for first run)
gradle test

# Using Maven (requires network for first run)
mvn test
```

### Project Structure

```
Sudoku/
├── *.java              # Source files (sudoku package)
├── test/
│   └── sudoku/         # JUnit test files
├── binary/
│   └── Sudoku.jar      # Compiled game
├── build.gradle        # Gradle build config
├── pom.xml             # Maven build config
└── run_tests.sh        # Test runner script
```