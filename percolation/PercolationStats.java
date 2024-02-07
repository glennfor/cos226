import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    // 95% level confidence factor
    private static final double CONFIDENCE_95 = 1.96;

    // threshold values for each trial
    private double[] threshholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            String error = "number of trials and size must be positive";
            throw new IllegalArgumentException(error);
        }
        threshholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniformInt(0, n);
                int col = StdRandom.uniformInt(0, n);
                p.open(row, col);
            }
            threshholds[i] = (double) p.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mean = mean(), stddev = stddev();
        double trials = threshholds.length;
        return mean - (CONFIDENCE_95 * stddev) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mean = mean(), stddev = stddev();
        double trials = threshholds.length;
        return mean + (CONFIDENCE_95 * stddev) / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        Stopwatch watch = new Stopwatch();
        PercolationStats ps = new PercolationStats(n, trials);
        double timeTaken = watch.elapsedTime();
        StdOut.println("mean()           = " + ps.mean());
        StdOut.println("stddev()         = " + ps.stddev());
        StdOut.println("confidenceLow()  = " + ps.confidenceLow());
        StdOut.println("confidenceHigh() = " + ps.confidenceHigh());
        StdOut.println("elapsed time    = " + timeTaken);
    }

}
