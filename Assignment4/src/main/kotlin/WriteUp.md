# Sorting Algorithm Implementation and Complexity Analysis

## Sorting Algorithms

For this assignment, I implemented the following four sorting algorithms that sorted the values into ascending order:

1. Bubble Sort
2. Selection Sort
3. Merge Sort
4. Radix Sort

---

## Bubble Sort

Bubble sort compares pairs of values next to each other. If the current value is greater than the next, they are swapped. 
After each pass through the list, the largest remaining values moves toward the end of the list,
while the smallest values are in the front.

### Complexity

Because each of the list's values has to be compared to all the rest, the outer loop of going through runs approximately `n` 
times. The inner loop is defined by the one value being paired to all the rest (`n` number of values) and runs approximately `n` 
times. Therefore, the algorithm performs approximately `n²` comparisons.
Therefore, the complexity: `O(n²)`.

---

## Insertion Sort

Insertion sort takes one element and moves it to its correct position among the elements that have already been sorted.

### Complexity

In the best case, the list is already sorted. Each element would still need to be checked though, and this would be completed in `O(n)` time.

In the worst case, each new element has to be moved through almost the entire sorted portion of the list. For example, this would 
occur if the list was reversed. In this specific case, the time complexity would be `O(n²)`.

---

## Merge Sort

Merge Sort is an algorithm that sorts an array by splitting it up into the smallest arrays possible, and then merges the 
smaller ones back together using a separate merge function. This separate merge function compares the values of the two 
smaller arrays and puts them together back into an array from least to greatest. It's called recursively on the smallest 
split arrays to compare to the larger split arrays until there's a final merged sorted array. 

### Complexity

Utilizing the Master Theorem, we define `T(n)` as `T(n) = 2T(n/2) + n` because a = 2 (the number of recursive subproblems 
we'll be dealing with) and b = 2 (the factor that the input size is divided by). `n` is added because that's how much time 
needed to remerge the separated arrays. We find that `C_crit = 1`, which gives us Case 2 of the Master Theorem, defining 
the time complexity as `(O(n * logn))`.

---

## Radix Sort

Radix Sort takes each digit of an array of individual digits, starting with the least significant digit and working its 
way to the largest digit from the largest number. It then categorizes them into buckets and subsequently puts them back 
into the list based on the current digit in focus. By the final iteration of this, the list is sorted. 

### Complexity

The time complexity of Radix Sort is `O(n * k)`, where k is the number of digits of the list's highest value. It will 
run through the list for every digit of k, but in the most average case, the number of digits of k is comparable to
`logn`, making out time complexity (if we were only to define in values of n) `O(n * logn)`.

---

# Sorting Algorithm Benchmarking
## Testing Method

I tested the four sorting algorithms using randomly generated lists of integers provided by the assignment.

Every time the SortTests.kt file is run, these lists are filled with random numbers using Kotlin's `random` function. 

I tested the following list sizes:

```text
10
100
1,000
10,000
100,000
```

Varying sizes of lists allow the performance differences between the sorting algorithms to be highlighted. 

Separate copies were made of the randomly generated lists to avoid the other sorting algorithms trying to sort already sorted lists. 

---

## Measuring Runtime

I used Kotlin's `measureTime` function to measure how long each sorting algorithm took to run and then converted the time 
to milliseconds using `var.MILLISECONDS`. I had to switch to using milliseconds because seconds were too large for the 
algorithms with shorter lists. 

For example:

```kotlin
val runTime = measureTime {
    bubble_sort(bubbleArray)
}

runTime.toDouble(DurationUnit.MILLISECONDS)
```
---

## Benchmark

The benchmark was automated so that each list size could be tested without manually running each experiment.
I tested the algorithms using randomly generated integers from 0 to 99,999. I tested lists of sizes 10, 100, 1,000, 10,000, and 100,000.
Each algorithm received a separate copy of the same randomly generated list so that the tests were fair. I used Kotlin's measureTime function and recorded the runtimes in milliseconds.
After running the benchmark, I obtained the following results:

### List sizes: [10, 100, 1000, 10000, 100000]
* Bubble sort runtimes: [0.9834, 0.14, 2.6356, 39.7813, 11920.7962]
* Selection sort runtimes: [0.0032, 0.0449, 1.5376, 19.1386, 2730.8247]
* Merge sort runtimes: [2.4513, 0.1946, 0.9683, 4.2488, 26.9513]
* Radix sort runtimes: [0.0843, 0.1534, 1.5163, 22.998, 1988.3035]

---

# Conclusions
For small lists, selection sort appeared to have a substantially shorter time, but became one of the 
slowest with the larger lists. Merge sort started off the longest, but never increased as fast as the 
others did, and ended up with an extremely short run time in comparison to the rest for the 
longest list size. Selection sort and Radix sort had really similar times, with Radix eventually being shorter for longer lists. 
