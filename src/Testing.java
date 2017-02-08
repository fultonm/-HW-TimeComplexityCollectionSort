
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author michaelfulton
 * @course Prof. Iverson's CS 211
 * @date Feb 5, 2017
 * @reasonForDoingThis Create a utility for generating and sorting integer data
 * to determine the time efficiency of Collection.sort() sorting algorithm
 */
public class Testing {

    // The instance list used for sorting
    private List<Integer> list;

    /**
     * Default constructor initializes the list.
     */
    public Testing() {
        list = new ArrayList();
    }

    /**
     * Constructor initializes the list with n random Integers from 0 to
     * Integer.MAX_VALUE using the provided list.
     *
     * @param n The number of integers to generate and add to the list
     * @param list The list to use for this experiment
     */
    public Testing(int n, List list) {
        Random r = new Random();
        this.list = list;
        for (int i = 0; i < n * 1000; i++) {
            this.list.add(r.nextInt(Integer.MAX_VALUE));
        }
    }

    /**
     * Measures the amount of time it takes to sorts the list.
     *
     * @return The number of nanoseconds required to sort the list.
     */
    private long timedSort() {
        long t = System.nanoTime();
        Collections.sort(list);
        return System.nanoTime() - t;
    }

    /**
     * Entry point for the program.
     *
     * @param args Not used
     * @throws IOException When saving the results of the experiment fails.
     */
    public static void main(String[] args) throws IOException {
        // Each iteration has a different number of integers to sort
        int iterations = 40;
        // Each trial has the same number of integers to sort. 
        // This is used for detecting outliers in the amount of time it takes to
        // sort.
        int trials = 7;
        // The number of integers to increase each iteration
        int step = 500;

        // Wether to save the results of each experiment to a csv file.
        boolean save = promptSave();

        // ArrayList trials
        TreeMap<Integer, ArrayList<Long>> results = doExperiment(iterations,
                step, trials, "arrayList");
        if (save) {
            saveResults("ArrayList Results.csv", results);
        }

        // LinkedList trials
        results = doExperiment(iterations, step, trials, "linkedList");
        if (save) {
            saveResults("LinkedList Results.csv", results);
        }
    }

    /**
     * Prompts the user to save the results of the experiment.
     *
     * @return True if the user chooses to save the results.
     */
    private static boolean promptSave() {
        System.out.println("Would you like to save experimental results to a "
                + "CSV file?\nThis will create two CSV files containing list "
                + "length with cooresponding sort times in the current "
                + "directory. (Y/n)");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        // Anything but a y is interpreted as a no.
        return input.toLowerCase().equals("y");
    }

    /**
     * Performs the experiment with the specified number of iterations and
     * trials, using the specified list type.
     *
     * @param iterations Number of different integer counts to try
     * @param step Amount to increase the integer count each iteration.
     * @param trials Number of times to duplicate each iteration.
     * @param listType The type of list to use. Provide "arrayList" for an
     * ArrayList, or any other string for LinkedList
     * @return A map of the results with the number of integers in the list as
     * the Key, and a list of sort times as the Value.
     */
    private static TreeMap<Integer, ArrayList<Long>> doExperiment(
            int iterations, int step, int trials, String listType) {
        TreeMap<Integer, ArrayList<Long>> results = new TreeMap();
        for (int i = 1; i <= iterations; i++) {
            // Data size ranges from 1000 - 50,0000 if step = 1000 
            // and iterations = 50
            int dataSize = i * step;
            ArrayList<Long> trialResults = new ArrayList();
            for (int j = 1; j <= trials; j++) {
                // This may be LinkedList, ArrayList depending on the parameter.
                List list = listType.equals("arrayList")
                        ? new ArrayList() : new LinkedList();
                // dataSize works up to 50,000 Integers.
                Testing t = new Testing(dataSize, list);
                long nanos = t.timedSort();
                trialResults.add(nanos);
                // Prints a formatted string to standard output.
                printResults(nanos, j, trials, i, iterations);
            }
            results.put(dataSize, trialResults);
        }
        return results;
    }

    /**
     * Print the results of the current trial.
     *
     * @param nanos The number of nanoseconds the operation took.
     * @param currentTrial The current trial
     * @param totalTrials The total number of trials this iteration
     * @param currentIteration The current iteration
     * @param totalIterations THe total number of iteration this experiment
     */
    private static void printResults(long nanos, int currentTrial,
            int totalTrials, int currentIteration, int totalIterations) {
        System.out.println(
                String.format("(%3$d/%4$d: %5$d/%6$d) Operation took %1$d ms "
                        + "(%2$d μs)",
                        nanos / 1000 / 1000, nanos / 1000, currentIteration,
                        totalIterations, currentTrial, totalTrials));
    }

    /**
     * Saves the results map to file. Another utility is used to convert this
     * data so each experimental value has its own row.
     *
     * @param fileName Name of the file to save.
     * @param results The results map.
     * @throws IOException When saving the file to disk fails.
     */
    private static void saveResults(String fileName,
            TreeMap<Integer, ArrayList<Long>> results) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        for (Map.Entry<Integer, ArrayList<Long>> result : results.entrySet()) {
            out.write(result.getKey() + ",");
            ArrayList<Long> trialResults = result.getValue();
            for (long time : trialResults) {
                out.write(time + ",");
            }
            out.newLine();
        }
        out.flush();
        out.close();
    }
}
