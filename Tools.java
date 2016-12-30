/** Class containing various methods used throughout program */
package sudoku;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tools {

    private static String user;
    private static Difficulty difficulty;

    /**
     * Method to generate default Documents folder and return as String
     */
    public static String getDocumentsPath() {
        return new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
    }

    public static void generateBoard(String user, Difficulty difficulty) {
        Tools.user = user;
        Tools.difficulty = difficulty;
        switch (difficulty) {
            case EASY:
            case MEDIUM:
            case HARD:
                generateBoard(difficulty.getNumberOfCellsToDisplay(), false);
                break;
            case DEVILISH:
                generateBoard(difficulty.getNumberOfCellsToDisplay(), true);
                break;
        }
    }

    private static void generateBoard(int numberOfCellsToDisplay, boolean isDevilish) {
        int dimensions = 0;
        int total = 81;
        PrintWriter saveWriter = null;
        try {
            int[][] completeSolution;
            if (!isDevilish) {
                dimensions = 9;
                total = 81;
                switch (random(1, 3)) {
                    case 1:
                        completeSolution = BOARD1;
                        break;
                    case 2:
                        completeSolution = BOARD2;
                        break;
                    case 3:
                        completeSolution = BOARD3;
                        break;
                    default:
                        completeSolution = BOARD1;      // not necessary but needed to make sure initialized
                }
            } else {
                dimensions = 16;
                total = 256;
                completeSolution = BOARD4;
            }
            //System.out.println("Original Solution");
            //print2D(completeSolution);
            randomize(completeSolution, isDevilish);
            //System.out.println("Row Swapped Solution");
            //print2D(completeSolution);
            transpose(completeSolution);
            //System.out.println("Transposed Solution");
            //print2D(completeSolution);
            randomize(completeSolution, isDevilish);
            //System.out.println("Complete Randomized Solution");
            //print2D(completeSolution);

            int[][] partialSolution = clone2DArray(completeSolution);

            ArrayList<Integer[]> a = new ArrayList<>();

            for (int i = 0; i < (total - numberOfCellsToDisplay); i++) {
                while (true) {
                    Integer[] integer = {random(0, dimensions - 1), random(0, dimensions - 1)};
                    if (!contains(integer, a)) {
                        partialSolution[integer[0]][integer[1]] = 0;
                        a.add(integer);
                        break;
                    }
                }
            }

            System.out.println("Partial Solution");
            print2D(partialSolution);
            saveWriter = new PrintWriter(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + difficulty + ".txt", "UTF-8");
            System.out.println(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + difficulty + ".txt");

            saveWriter.println(difficulty);
            saveWriter.println(dimensions);
            saveWriter.println("Time:\n0\n");

            for (int i = 0; i < dimensions; i++)
                for (int j = 0; j < dimensions; j++) {
                    if (partialSolution[i][j] != 0) {
                        saveWriter.println((i + 1) + "," + (j + 1) + "," + partialSolution[i][j] + ",init");
                        //System.out.println("Got here");
                    }
                }
            saveWriter.println();
            for (int i = 0; i < dimensions; i++)
                for (int j = 0; j < dimensions; j++) {
                    saveWriter.println((i + 1) + "," + (j + 1) + "," + completeSolution[i][j] + ",key");
                }
            saveWriter.println();
            saveWriter.close();

        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            System.out.println(ex);
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (saveWriter != null)
                saveWriter.close();
        }

    }

    private static boolean contains(Integer[] a, ArrayList<Integer[]> b) {
        for (Integer[] i : b) {
            if (a[0].equals(i[0]) && a[1].equals(i[1])) {
                return true;
            }
        }
        return false;
    }


    private static int[][] clone2DArray(int[][] a) {
        int[][] b = new int[a.length][];
        for (int i = 0; i < a.length; i++)
            b[i] = a[i].clone();
        return b;
    }

    private static void randomize(int[][] m, boolean isDevilish) {
        int a = (!isDevilish ? 9 : 16);
        int b = (!isDevilish ? 3 : 4);
        for (int i = 0; i < a; i = i + b) {
            for (int j = 1; j < b + 1; j++) {
                swapRows(m, (i + j), random((i + 1), (i + b)));
            }
        }
    }

    private static void transpose(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m[i].length; j++) {
                int temp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = temp;
            }
        }
    }

    private static void print2D(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void swapRows(int a[][], int row1, int row2) {
        int temp[] = a[row1 - 1];
        a[row1 - 1] = a[row2 - 1];
        a[row2 - 1] = temp;
    }

    private static int random(int low, int high) {
        Random r = new Random();
        return r.nextInt((high + 1) - low) + low;
    }

    /**
     * Completed boards
     */
    private static final int[][] BOARD1 = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}};

    private static final int[][] BOARD2 = {{6, 3, 2, 7, 8, 1, 9, 4, 5},
            {4, 8, 7, 5, 9, 6, 2, 1, 3},
            {5, 1, 9, 2, 4, 3, 8, 7, 6},
            {8, 6, 4, 3, 5, 2, 7, 9, 1},
            {7, 5, 1, 9, 6, 8, 3, 2, 4},
            {2, 9, 3, 1, 7, 4, 6, 5, 8},
            {9, 4, 5, 6, 3, 7, 1, 8, 2},
            {1, 7, 6, 8, 2, 5, 4, 3, 9},
            {3, 2, 8, 4, 1, 9, 5, 6, 7}};

    private static final int[][] BOARD3 = {{1, 5, 2, 4, 6, 9, 3, 7, 8},
            {7, 8, 9, 2, 1, 3, 4, 5, 6},
            {4, 3, 6, 5, 8, 7, 2, 9, 1},
            {6, 1, 3, 8, 7, 2, 5, 4, 9},
            {9, 7, 4, 1, 5, 6, 8, 2, 3},
            {8, 2, 5, 9, 3, 4, 1, 6, 7},
            {5, 6, 7, 3, 4, 8, 9, 1, 2},
            {2, 4, 8, 6, 9, 1, 7, 3, 5},
            {3, 9, 1, 7, 2, 5, 6, 8, 4}};

    // Devilish Board
    private static final int[][] BOARD4 = {{15, 8, 6, 3, 16, 9, 11, 12, 14, 2, 4, 1, 13, 5, 10, 7},
            {9, 1, 14, 4, 15, 6, 3, 8, 10, 5, 7, 13, 2, 11, 12, 16},
            {11, 13, 5, 2, 14, 4, 10, 7, 6, 9, 16, 12, 1, 15, 3, 8},
            {12, 10, 7, 16, 13, 2, 5, 1, 8, 3, 11, 15, 14, 4, 6, 9},
            {1, 12, 8, 14, 11, 13, 2, 16, 3, 4, 6, 10, 7, 9, 15, 5},
            {2, 15, 4, 11, 8, 12, 6, 5, 13, 16, 9, 7, 10, 3, 14, 1},
            {3, 16, 10, 13, 1, 14, 7, 9, 11, 8, 15, 5, 12, 2, 4, 6},
            {6, 7, 9, 5, 4, 3, 15, 10, 2, 12, 1, 14, 8, 13, 16, 11},
            {5, 14, 13, 8, 7, 11, 4, 2, 12, 6, 10, 9, 15, 16, 1, 3},
            {16, 2, 3, 6, 9, 8, 14, 13, 1, 15, 5, 11, 4, 12, 7, 10},
            {4, 11, 12, 10, 3, 16, 1, 15, 7, 14, 2, 8, 9, 6, 5, 13},
            {7, 9, 15, 1, 5, 10, 12, 6, 4, 13, 3, 16, 11, 14, 8, 2},
            {14, 6, 11, 9, 2, 1, 8, 3, 5, 10, 12, 4, 16, 7, 13, 15},
            {8, 3, 16, 12, 6, 7, 9, 14, 15, 1, 13, 2, 5, 10, 11, 4},
            {13, 5, 1, 7, 10, 15, 16, 4, 9, 11, 14, 6, 3, 8, 2, 12},
            {10, 4, 2, 15, 12, 5, 13, 11, 16, 7, 8, 3, 6, 1, 9, 14}};
}
