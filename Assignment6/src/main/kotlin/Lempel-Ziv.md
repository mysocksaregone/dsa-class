## Very Brief Write-Up

### Implementation

In order to implement the LZ78-Style Lempel-Ziv
compression, I first identified each key and value as a
Pair: a 12-bit index of the longest previously 
seen prefix and one 8-bit character. The codebook 
maps each phrase to its index. It is stored in the associative 
array I implemented for this assignment, which is a hash table 
with separate chaining, division hashing over prime table sizes, 
and automatic rehashing. Once the codebook reaches 4095 entries, 
no new phrases are added, so every index fits in 12 bits. 

### Results 
| Input | Original (bytes) | Compressed (bytes) | Ratio |
|---|---|---|---|
| "a" x 10,000 | 10000 | 353 | 0.04 |
| "abc" x 5,000 | 15000 | 748 | 0.05 |
| random letters | 10000 | 8755 | 0.88 |
| short text | 357 | 413 | 1.16 |
| long text | 4208 | 3355 | 0.80 |


Lempel-Ziv performs so well on repetitive data because it makes use of 
the phrases it's seen before, calling on past entries to 
save memory and speeds it up because of the nature of hash table structure: 
specifically, the fact that it only takes O(1) time to access an 
existing value. 

There was weak compression on the random letters due to the lack of pattern, 
but what shocked me was that there was bad compression on the short text. 
Upon further analysis, I realized that it was because it was just too short, so 
there weren't enough repetitions or patterns to make use of the properties 
of Lempel-Ziv that make it efficient at compression. This was supported by the 
better compression with much longer text. 