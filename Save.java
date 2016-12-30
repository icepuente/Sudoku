package sudoku;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Save {

    // Instance Variables
    private String name;
    private File saveFile;
    private static Difficulty difficulty;
    private static int size;


    // Constructor
    public Save(String name, File saveFile, Difficulty difficulty) {
        this.name = name;
        this.saveFile = saveFile;
        this.difficulty = difficulty;
        if (difficulty != null && !difficulty.toString().equals("Devilish"))
            size = 9;
        else if (difficulty != null)
            size = 16;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSaveFile(File saveFile) {
        this.saveFile = saveFile;
    }

    public File getSaveFile() {
        return this.saveFile;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    // Method to load all saved usernames
    public static ArrayList<Save> loadSaves() {
        String documentsPath = Tools.getDocumentsPath();

        ArrayList<Save> saves = new ArrayList<Save>();

        File saveFolder = new File(documentsPath + "/Sudoku");
        if (saveFolder.exists() && saveFolder.isDirectory()) {
            System.out.println("DEBUG: SAVE FOLDER LOCATED AT " + saveFolder.getAbsolutePath());
            File[] allFiles = saveFolder.listFiles();

            if (allFiles != null && allFiles.length > 0) {

                for (File file : allFiles) {
                    String[] splitString = file.getName().split("\\.");
                    if (splitString.length > 1 && splitString[1].contains("dat")) {

                        // System.out.println("DEBUG: VALID SAVE FOUND: " + file.getName());

                        Save save = new Save(splitString[0], file, null);
                        saves.add(save);

                        //System.out.println("DEBUG: CREATED USER " + save.getName() + " FROM SAVE FILE LOCATED AT " +
                        //save.getSaveFile().getAbsolutePath());

                    } else {

                        if (file.isDirectory()) {
                            Save save = new Save(splitString[0], file, null);
                            saves.add(save);
                        }
                        //System.out.println("DEBUG adsf: " + file.getName() + " IS NOT A VALID SAVE FILE");
                    }
                }
            }
        } else {
            saveFolder.mkdir();
            System.out.println("DEBUG: SAVE FOLDER CREATED AT DIRECTORY " + saveFolder.getAbsolutePath());
        }
        return saves;
    }

    public static void deleteSave(Path p) {
        deleteFolder(p.toFile());
    }

    /*
    **
    */
    public static void saveGame(String s, Difficulty d) throws IOException {
        String user = s;
        if (!d.toString().equals("Devilish"))
            size = 9;
        else
            size = 16;
        initialSave(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + d + ".txt", d);
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + d.toString() + ".txt", true)))) {
            out.println("");

            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    String JName = GameView.getJBoard().getTextField()[i][j].getName();
                    //System.out.println(JName);

                    String stringsArr[] = JName.split(",");
                    int count = JName.split(",", -1).length - 1;

                    if (count > 1 && !stringsArr[2].equals("init")) {
                        out.println((i + 1) + "," + (j + 1) + "," + GameView.getJBoard().getTextField()[i][j].getText() + "," + stringsArr[2]);
                    }
                }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static boolean loadGame(String s, Difficulty d) {
        String user = s;
        if (!d.toString().equals("Devilish"))
            size = 9;
        else
            size = 16;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + d + ".txt"));
        } catch (FileNotFoundException ex) {
            return false;
        }
        GameView ViewLoad = new GameView(user, d);
        MainMenu.setView(ViewLoad);
        int lineNum = 1;
        String line;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            //sets the time of the game to the time when last saved
            if (lineNum == 4) {
                ViewLoad.setElapsed(Integer.parseInt(line));
            }

            lineNum++;
        }
        scanner.close();

        ViewLoad.getBoard().PopulateBoard(new File(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + d + ".txt"));
        return true;
    }

    private static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    public static void createSave(String name, Difficulty difficulty) {
        try {
            String documentsPath = Tools.getDocumentsPath();
//            File saveFolder = new File(documentsPath + "/Sudoku");
//            File addedPath = new File(saveFolder + "/" + name);
            File saveFolder = new File(documentsPath + "/Sudoku/" + name);
            saveFolder.mkdir();


            if (saveFolder.exists() && saveFolder.isDirectory()) {
                System.out.println("DEBUG: SAVE FOLDER LOCATED AT " + saveFolder.getAbsolutePath());
                File save = new File(saveFolder.getAbsolutePath() + "/" + name + ".dat");
                if (save.createNewFile()) {
                    System.out.println("DEBUG: SUCCESSFULLY CREATED SAVE AT: " + save.getAbsolutePath());

                    Writer output;
                    output = new BufferedWriter(new FileWriter(save, true));
                    output.append(difficulty.toString());
                    output.close();
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }


    public static void initialSave(Difficulty difficulty, String user) {
        try {
            Scanner scanner = new Scanner(new File(Tools.getDocumentsPath() + "/Sudoku/sudoku_input_file_example.txt"));
            Vector<String> saveVector = new Vector<>(1, 1);
            String line;
            int count;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                count = line.split(",", -1).length - 1;

                if (line.length() < 1) {
                } else if (count == 2 && Character.isDigit(line.charAt(0))) {
                    if (exists(line + ",key", saveVector)) {
                        saveVector.add(line + ",init");
                    } else {
                        saveVector.add(line + ",key");
                    }
                } else if (Character.isDigit(line.charAt(0)) || Character.isLetter(line.charAt(0))) {
                    saveVector.add(line);
                }
            }
            scanner.close();

            PrintWriter saveWriter = new PrintWriter(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + difficulty + ".txt", "UTF-8");
            saveWriter.println(saveVector.elementAt(0));
            saveWriter.println(saveVector.elementAt(1));
            saveWriter.println("Time:\n" + MainMenu.getView().getElapsed() + "\n");

            for (int i = 2; i < saveVector.size(); i++) {
                if (saveVector.elementAt(i).split("init", -1).length - 1 > 0) {
                    saveWriter.println(saveVector.elementAt(i));
                }
            }
            saveWriter.println("");
            for (int i = 2; i < saveVector.size(); i++) {
                if (saveVector.elementAt(i).split("key", -1).length - 1 > 0) {
                    saveWriter.println(saveVector.elementAt(i));
                }
            }

            saveWriter.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void initialSave(String file_path, Difficulty difficulty) {
        try {
            Scanner scanner = new Scanner(new File(file_path));
            Vector<String> saveVector = new Vector<>(1, 1);
            String line;
            int count;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                count = line.split(",", -1).length - 1;

                if (line.length() < 1) {
                } else if (count == 2 && Character.isDigit(line.charAt(0))) {
                    if (exists(line + ",key", saveVector)) {
                        saveVector.add(line + ",init");
                    } else {
                        saveVector.add(line + ",key");
                    }
                } else if (Character.isDigit(line.charAt(0)) || Character.isLetter(line.charAt(0))) {
                    saveVector.add(line);
                }
            }
            scanner.close();

            PrintWriter saveWriter = new PrintWriter(file_path, "UTF-8");
            saveWriter.println(saveVector.elementAt(0));
            saveWriter.println(saveVector.elementAt(1));
            saveWriter.println("Time:\n" + MainMenu.getView().getElapsed() + "\n");

            for (int i = 2; i < saveVector.size(); i++) {
                if (saveVector.elementAt(i).split("init", -1).length - 1 > 0) {
                    saveWriter.println(saveVector.elementAt(i));
                }
            }
            saveWriter.println("");
            for (int i = 2; i < saveVector.size(); i++) {
                if (saveVector.elementAt(i).split("key", -1).length - 1 > 0) {
                    saveWriter.println(saveVector.elementAt(i));
                }
            }

            saveWriter.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static boolean exists(String new_line, Vector<String> saveVector) throws FileNotFoundException {
        for (int i = 0; i < saveVector.size(); i++) {
            if (saveVector.elementAt(i).equals(new_line)) {
                return true;
            }
        }
        return false;
    }
}
