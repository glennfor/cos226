Programming Assignment 1: Percolation

Answer these questions after you implement your solution.

/* *****************************************************************************
 *  Describe the data structures (i.e., instance variables) you used to
 *  implement the Percolation API.
 **************************************************************************** */

1. private boolean[][] grid;
    The 2-D boolean array represents the grid corresponding to the model of
    the percolation system. A false value represents a closed site and true,
    an open site
2. private WeightedQuickUnionUF uf;
    A union-find data structure to track the connectivity of open sites which
    will be used to determined if the top and bottom rows are connected
    and eventually if the system percolates
3. private WeightedQuickUnionUF bUf;
    A union-find data structure to track connectivity of open sites while
     accounting for backwash (that is when (an) open site(s) connects the top
     and bottom rows but not along the entire structure, but as a result of
    the functioning of the union-find data structure itself)

The other instance variables are not data structures, but primitives that
 keep track of essential information within the percolation object

/* *****************************************************************************
 *  Briefly describe the algorithms you used to implement each method in
 *  the Percolation API.
 **************************************************************************** */
open(): the open method is used to methodologically collect connected
cells using a quick union, such that a connection between the top, some cells
and the bottom row will indicate the system percolates. the connection is
realised by collecting the cells connected to the cell of interest in a
set that will eventually grow to include the top and tottom virtual cells
if the system were to percolate.

isOpen(): validates the row and column passed and returns the value of the
corresponding cell which represents whether the cell is open or closed

isFull(): a site is full if the collection it is part of (as referenced in
open above) is also connected to the top row. the method does this by
confirming that the cell of interest is open and that its leader is connected
to the top row.

numberOfOpenSites(): returns the number of open sites. An instance counter
is kept to track each site that is open to avoid having to run an n^2
nested loop to determine the number of open sites each time it is required.


percolates(): A system percolates if the top and bottom virtual index are part
of the same set(have the same leader) in the union-find datatype.

/* *****************************************************************************
 *  First, implement Percolation using QuickFindUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

PercolationStats will handle about 220 input  size at
T = 100 trails

 n          time (seconds)
--------------------------
16          0.012
32          0.04
64          0.38
128         5.672
256        88.034




/* *****************************************************************************
 *  Describe the strategy you used for selecting the values of n.
 **************************************************************************** */
I first use the estimate for 60-seconds to decide a reasonable factor for
my n values (I found 2). Then from the value the yields time close to 60s
I divide by the factor and work backwards to suggest other values of n.



/* *****************************************************************************
 *  Next, implement Percolation using WeightedQuickUnionUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

PercolationStats will handle about 2000-input size at T = 100

Using estimated n with the same method as above, I have

 n          time (seconds)
--------------------------
128     0.086
256     0.309
512     1.308
1024    7.045
2048    62.643


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
- No known bugs



/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
- No serious problem encountered



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */

Percolation was quite hard to get a hold of as the first COS226
project. The description was clear but not very helpful getting started (
that is, understanding the problem with the use of a union-find
