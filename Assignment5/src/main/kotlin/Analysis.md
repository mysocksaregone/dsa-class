## Matrix Multiplication Performance

I implemented conventional matrix multiplication, Strassen's algorithm, and a hybrid algorithm that switches to 
conventional multiplication below a chosen cutoff.

Conventional multiplication has a time complexity of O(n^3), while Strassen's algorithm has a 
theoretical time complexity of O(2.807). However, Strassen's extra additions and recursive 
calls can make it slower for small matrices.

In order to benchmark the algorithms, I adjusted the matrix size and tested several different 
cutoff values for the hybrid algorithm. 

### Results:

Size | Regular ms | Strassen ms | Hybrid cutoff times: (ms)

16   | 0.08       | 1.19         | 16: 0.11, 32: 0.05, 64: 0.02

32   | 0.03       | 3.41         | 16: 0.16, 32: 0.10, 64: 0.12

64   | 0.15       | 20.51        | 16: 0.64, 32: 0.52, 64: 0.53
   
128  | 1.32       | 93.34        | 16: 3.32, 32: 2.49, 64: 2.72

256  | 12.42      | 798.47       | 16: 42.99, 32: 26.63, 64: 20.94

### Conclusion: 
Based on the matrix sizes I chose, the regular matrix multiplication algorithm was 
the fastest method. Strassen's algorithm was significantly slower, while the hybrid algorithm 
performed better than just Strassen's algorithm. For matrices large and small, the difference 
between Strassen's and conventional matrix multiplication were noticeable, with regular multiplication
only taking 12.42 ms at size 256 and Strassen taking almost 800 milliseconds. Either something is amiss with 
my implementation that I just can't tell yet or there's another factors that play into the speed 
of these algorithms. 

## Sequence Alignment

I implemented the Needleman–Wunsch algorithm using dynamic programming. The algorithm fills a score table
using match, mismatch, and gap scores, then uses backtracking to reconstruct the alignment.

I tested the implementation on small sequences, including identical sequences, mismatches,
and sequences requiring gaps. I then ran it on the provided Salmonella DNA sequences to obtain the alignment 
score and aligned sequences.

### Results
Alignment score: 143

Aligned genome snippet:
TGGCGACAACCG-TAGCGGAATATTTTCGCGACCAGGGAAAACGGGTCGTGCTT-TTTATCGATTC-CATGACCCGTTATGCGC-GTGCTTTGCGAGACGTGGCACTGG-CGTCGG-GAGAGCGTCCGG-CTCGTCGAGGTTATCCCGCC-TCCGTA-TTCGAT-AATTTGCCCCG-CTTGCTGGAACGCCCAGGG---GCGACCAGCGAGGGAAGCATTACTGCCTTTTATACGGTACTGCTGGAAAGCGAGGA-A-GAGGCGGACCCGATGGCGGATGAAATTCGCTCTA-TCCTTGACGGTCA-CCTGTATCTGA-GCAGAAAGCTGGCCGGGCA-GGGACATTACCCGGCAATCGATGTACT-GAAAAGCGTAAGCCGCGTTTTT

Aligned target sequence:
TGGCCACCA-CGATAGCAGAATTTTTTCGCGATAATGGAAAGCGAGTCGT-CTTGCTTGCCGACTCAC-TGACGCGTTATGC-CAGGGCCGCACGGGAAATCGCTCTGGCCGCCGGAGAGACCG--CGGTTTC-TGGAGAATAT-CCGCCAGGCGTATTTAG-TGCA-TTGCCACGACTT-TTAGAACGTAC-GGGAATGGGA-GA-AAAAGGCAGTATTACCGCATTTTATACGGTACTGGTGGAAGGCGATGATATGA--ATGAGCCGTTGGCGGATGAAGTCCGTTC-ACTGCTTGATGGACATATTGTA-CT-ATCCCGACGGCTTGCAGAG-AGGGGGCATTATCCTGCCATTGACGTGTTGGCAACGC-TCAGCCGCGTTTTT
