# K-D Tree vs. Brute Force Nearest Neighbor

## Setup
For each (k, numPoints), training points and 1000 query points were drawn uniformly from [0, 1)^k. 
Each configuration was timed once after a warm-up run, and every run checked that the tree and brute force found equally close points.

## Data

| k | numPoints | build | tree queries (1000) | brute force queries (1000) | speedup (brute / tree) |
|---|-----------|-------|---------------------|----------------------------|------------------------|
| 1 | 10 | 111us | 97us | 202.8us | 2.1 |
| 1 | 100 | 89.3us | 135.7us | 372.3us | 2.7 |
| 1 | 1000 | 584.2us | 202us | 3.0075ms | 14.9 |
| 1 | 10000 | 7.4942ms | 475.1us | 29.3855ms | 61.8 |
| 1 | 100000 | 55.9426ms | 978.5us | 435.854ms | 445 |
| 2 | 10 | 57us | 135.1us | 95.1us | 0.70 |
| 2 | 100 | 101.9us | 199.8us | 430.9us | 2.2 |
| 2 | 1000 | 579.9us | 313.2us | 3.4175ms | 10.9 |
| 2 | 10000 | 9.8357ms | 603.4us | 33.6538ms | 55.8 |
| 2 | 100000 | 192.9227ms | 2.0968ms | 516.3119ms | 246 |
| 3 | 10 | 18.3us | 155.9us | 112.9us | 0.72 |
| 3 | 100 | 39.5us | 346us | 536.7us | 1.6 |
| 3 | 1000 | 652.6us | 561.6us | 4.5439ms | 8.1 |
| 3 | 10000 | 10.4156ms | 1.2751ms | 49.2688ms | 38.6 |
| 3 | 100000 | 207.6076ms | 4.2789ms | 540.4293ms | 126 |
| 5 | 10 | 16.9us | 230.4us | 144.8us | 0.63 |
| 5 | 100 | 47.9us | 734.5us | 632.7us | 0.86 |
| 5 | 1000 | 1.5592ms | 3.0478ms | 5.988ms | 2.0 |
| 5 | 10000 | 11.5613ms | 3.3396ms | 67.3355ms | 20.2 |
| 5 | 100000 | 228.3283ms | 21.1089ms | 1.232477s | 58 |
| 10 | 10 | 11.5us | 296.4us | 403.8us | 1.4 |
| 10 | 100 | 110.2us | 2.3415ms | 906.8us | 0.39 |
| 10 | 1000 | 829.3us | 20.551ms | 9.857ms | 0.48 |
| 10 | 10000 | 11.0496ms | 94.6729ms | 121.5361ms | 1.3 |
| 10 | 100000 | 272.1957ms | 922.5373ms | 3.3904553s | 3.7 |
| 20 | 10 | 7.7us | 231.1us | 178.2us | 0.77 |
| 20 | 100 | 31.3us | 2.252ms | 1.5247ms | 0.68 |
| 20 | 1000 | 568us | 27.8771ms | 15.7558ms | 0.57 |
| 20 | 10000 | 9.8352ms | 427.1441ms | 164.9176ms | 0.39 |
| 20 | 100000 | not recorded | not recorded | not recorded | n/a |

Speedup above 1 means the tree was faster. Each row was timed once, 
so differences at the smallest sizes (like k = 10, n = 10) are mostly noise.

## Conclusion
### KD Trees are better with: 

1. Low-dimensional data - At 100,000 points the tree was about 445x faster for 
k = 1, 126x for k = 3, 58x for k = 5 and only 3.7x for k = 10. At k = 20 brute 
force was faster at every size recorded. In high dimensions the splitting lines 
are rarely far enough from the query to rule out a branch, so the search visits 
most of the tree.
2. Large datasets - Brute force time grows linearly with the number of points, 
while tree query time grows roughly logarithmically. For small datasets 
(10 to 100 points) brute force was as fast or faster, because there is little 
for the tree to skip.
3. Many queries on a fixed dataset -  The tree must be built first (about 56 to
270 ms at 100,000 points), but that cost is paid back after roughly 130 to 390 
queries in the cases checked. For a one-off query, brute force is better.

KD trees work well when the number of points is much larger than 2^k. Brute force is the better choice for tiny datasets, high-dimensional data, or only a few queries.