# Sudoku

A desktop Sudoku game built with Java Swing, featuring multiple difficulty levels, user profiles, save/load functionality, and a leaderboard system.

## Features

- **Multiple difficulty levels**: Easy (9x9), Medium (9x9), Hard (9x9), and Devilish (16x16)
- **User profiles**: Create and manage multiple player profiles
- **Save and load**: Resume games where you left off
- **Leaderboard**: Tracks the top 25 scores per difficulty level
- **Doodle/pencil marks**: Add up to 4 candidate numbers per cell
- **Keyboard and number pad input**: Play using either input method
- **Board validation**: Check your progress at any time with scoring feedback
- **Custom puzzles**: Load your own Sudoku puzzles via a test input file

## Requirements

- Java SE 6 or higher

No external dependencies are required.

## Running the Game

A pre-compiled JAR is included in the `binary/` directory:

```bash
java -jar binary/Sudoku.jar
```

## Compiling from Source

```bash
javac *.java
```

## How to Play

1. Run the application and create a user profile
2. Log in and select **New Game** from the main menu
3. Choose a difficulty level and click **OK**
4. Fill in the empty cells using the keyboard or the on-screen number pad
5. Use **Check Board** to validate your entries and see your score
6. Use **Doodle** mode to add pencil marks for candidate numbers

## Loading a Custom Puzzle

1. Create a user profile and log in at least once
2. Place a file named `sudoku_input_file_example.txt` in `Documents/Sudoku/<your-username>/`
3. The file should contain comma-separated values in the format: `row,column,value`
4. Start a new game and select the **Test** difficulty

## Scoring

Scores are calculated based on the number of cells you need to fill and the time taken:

- **Standard (9x9)**: `(81 - displayed_cells) * 7 - elapsed_seconds`
- **Devilish (16x16)**: `(256 - displayed_cells) * 3 - elapsed_seconds`

Higher scores are better. Incorrect guesses checked during gameplay reduce your score.

## Project Structure

| File | Description |
|------|-------------|
| `Sudoku.java` | Application entry point |
| `Login.java` | User login and profile management |
| `NewLogin.java` | New user creation dialog |
| `MainMenu.java` | Main menu (new game, continue, leaderboard) |
| `GameView.java` | Game window layout and controls |
| `MainBoard.java` | Sudoku grid rendering and input handling |
| `Difficulty.java` | Difficulty level definitions |
| `LeaderBoard.java` | Leaderboard display and persistence |
| `Save.java` | Game save/load and file I/O |
| `Tools.java` | Board generation and utility methods |
