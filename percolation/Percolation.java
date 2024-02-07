import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // representation of surface
    private boolean[][] grid;

    // quick union find to find continuous blob
    private WeightedQuickUnionUF uf;

    // quick union to account for backwash
    private WeightedQuickUnionUF bUf;

    // virtual top row
    private int bottomVirtualIndex;

    // number of items
    private int size;

    // number of open sites
    private int openSiteCount;

    // virtual bottom row
    private int topVirtualIndex;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            String error = n + "-sized grid cannot be created";
            throw new IllegalArgumentException(error);
        }
        size = n;
        int length = size * size;
        uf = new WeightedQuickUnionUF(length + 2);
        bUf = new WeightedQuickUnionUF(length + 2);
        grid = new boolean[n][n];
        topVirtualIndex = length;
        bottomVirtualIndex = length + 1;
        openSiteCount = 0;
    }

    // convert [grid] indices to array indices
    private int flatten(int row, int col) {
        return row * size + col;
    }

    // check if row and column are valid
    // throws an exception if not valid
    private void validate(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            String error = "invalid value(s) for rows and/or columns";
            throw new IllegalArgumentException(error);
        }
    }

    // check if row and column is valid
    // no exception thrown
    private boolean valid(int row, int col) {
        return !(row < 0 || row >= size || col < 0 || col >= size);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col))
            return;
        grid[row][col] = true;
        if (valid(row + 1, col) && isOpen(row + 1, col)) {
            uf.union(flatten(row, col), flatten(row + 1, col));
            bUf.union(flatten(row, col), flatten(row + 1, col));
        }

        if (valid(row - 1, col) && isOpen(row - 1, col)) {
            uf.union(flatten(row, col), flatten(row - 1, col));
            bUf.union(flatten(row, col), flatten(row - 1, col));
        }

        if (valid(row, col + 1) && isOpen(row, col + 1)) {
            uf.union(flatten(row, col), flatten(row, col + 1));
            bUf.union(flatten(row, col), flatten(row, col + 1));
        }

        if (valid(row, col - 1) && isOpen(row, col - 1)) {
            uf.union(flatten(row, col), flatten(row, col - 1));
            bUf.union(flatten(row, col), flatten(row, col - 1));
        }

        if (row == 0) {
            uf.union(topVirtualIndex, flatten(row, col));
            bUf.union(topVirtualIndex, flatten(row, col));
        }
        if (row == size - 1)
            uf.union(flatten(row, col), bottomVirtualIndex);
        openSiteCount++;

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        boolean connectsTop =
                bUf.find(flatten(row, col))
                        == bUf.find(topVirtualIndex);
        return isOpen(row, col) && connectsTop;
        // return uf.find(flatten(row, col)) == uf.find(topVirtualIndex);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(bottomVirtualIndex) == uf.find(topVirtualIndex);
    }

    // unit testing (required)
    public static void main(String[] args) {
        In infile = new In(args[0]);
        int n = infile.readInt();
        Percolation pc = new Percolation(n);
        while (!infile.isEmpty()) {
            int row = infile.readInt();
            int col = infile.readInt();
            pc.open(row, col);
        }

        StdOut.println("Does it percolate  ? " + pc.percolates());
        StdOut.println("First cell is open ? " + pc.isOpen(0, 0));
        StdOut.println("First cell is full ? " + pc.isFull(0, 0));
        StdOut.println("Number of open sites: " + pc.numberOfOpenSites());
    }
}
