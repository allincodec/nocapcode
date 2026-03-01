# Dynamic Programming - Core Concepts Guide

## What is Dynamic Programming?

Dynamic Programming (DP) is an optimization technique that solves complex problems by breaking them down into simpler overlapping subproblems, solving each subproblem once, and storing the results to avoid redundant computations.

**Key Characteristic:** Solutions to subproblems are stored (memoized or tabulated) and reused, transforming exponential time complexity into polynomial time.

**Core Philosophy:** If you're solving the same problem multiple times, save the answer and look it up next time.

---

## When Does Dynamic Programming Work?

DP works when a problem has these two critical properties:

### 1. Optimal Substructure
- An optimal solution to the problem contains optimal solutions to its subproblems
- You can construct the solution from solutions to smaller instances
- The problem can be broken down recursively

**Example:** Shortest path from A to C through B = Shortest(A to B) + Shortest(B to C)

### 2. Overlapping Subproblems
- The same subproblems are solved multiple times during recursion
- Without memoization, the same calculation is repeated
- Storing results eliminates redundant work

**Example:** Fibonacci - fib(5) requires fib(3) multiple times

**Both properties must hold for DP to be beneficial.**

---

## How to Recognize DP Problems

### Strong Indicators:
1. **Optimization problems** asking for maximum, minimum, longest, shortest
2. **Counting problems** asking "how many ways"
3. Problems involving **sequences, substrings, or subarrays**
4. Problems with **choices that affect future possibilities**
5. **Recursive solution exists but times out** (overlapping subproblems)
6. Problems asking for **all possibilities** but with constraints
7. Keywords: "maximize", "minimize", "longest", "shortest", "count ways", "optimal"

### Strong Keywords/Phrases:
- "Maximum/Minimum number of..."
- "Longest/Shortest subsequence/subarray/path"
- "Count the number of ways to..."
- "Is it possible to..."
- "Find all ways to achieve..."
- "Optimal solution"
- "Best strategy"

### Problem Structures that Scream DP:
1. **Strings/Arrays:** Subsequence, substring, subarray problems
2. **Grids:** Path counting, minimum cost paths
3. **Trees:** Subset problems, maximum path sums
4. **Games:** Optimal strategy, win/lose scenarios
5. **Resource Allocation:** With constraints and optimization

---

## Classic DP Problem Patterns

### Pattern 1: 0/1 Knapsack
**Problem type:** Choose items to maximize value with weight constraint

**State:** `dp[i][w]` = maximum value using first i items with capacity w

**Recurrence:** `dp[i][w] = max(dp[i-1][w], value[i] + dp[i-1][w-weight[i]])`

**Core decision:** Include or exclude each item (binary choice)

**Example problems:**
- 0/1 Knapsack
- Subset sum
- Partition equal subset sum
- Target sum
- Last stone weight II

**Recognition:**
- Binary choice per element
- Constraint (weight/capacity)
- Optimization (maximize/minimize)
- Cannot use same element twice

---

### Pattern 2: Unbounded Knapsack
**Problem type:** Choose items unlimited times to optimize value

**State:** `dp[w]` = maximum value with capacity w

**Recurrence:** `dp[w] = max(dp[w], value[i] + dp[w-weight[i]])` for all items

**Core decision:** Include item multiple times or move to next

**Example problems:**
- Coin change (minimum coins)
- Coin change II (count ways)
- Rod cutting
- Unbounded knapsack

**Recognition:**
- Can use same element multiple times
- Unlimited supply
- Target/capacity to fill

---

### Pattern 3: Longest Common Subsequence (LCS)
**Problem type:** Find longest subsequence common to two sequences

**State:** `dp[i][j]` = length of LCS of s1[0..i] and s2[0..j]

**Recurrence:**
```
if s1[i] == s2[j]: dp[i][j] = 1 + dp[i-1][j-1]
else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
```

**Core decision:** Match characters or skip from either string

**Example problems:**
- Longest common subsequence
- Longest common substring
- Edit distance
- Minimum insertions/deletions
- Shortest common supersequence

**Recognition:**
- Two sequences/strings
- Find common elements
- Order matters but doesn't need to be contiguous

---

### Pattern 4: Longest Increasing Subsequence (LIS)
**Problem type:** Find longest strictly increasing subsequence

**State:** `dp[i]` = length of longest increasing subsequence ending at index i

**Recurrence:** `dp[i] = max(dp[j] + 1)` where `j < i` and `arr[j] < arr[i]`

**Core decision:** Extend previous subsequence or start new

**Example problems:**
- Longest increasing subsequence
- Longest decreasing subsequence
- Maximum sum increasing subsequence
- Russian doll envelopes
- Number of LIS

**Recognition:**
- Single sequence
- Looking for increasing/decreasing pattern
- Subsequence (not subarray - elements can be non-contiguous)

---

### Pattern 5: Palindromic Subsequence/Substring
**Problem type:** Find palindromes in strings

**State:** `dp[i][j]` = property of substring from i to j

**Recurrence:**
```
if s[i] == s[j]: dp[i][j] = dp[i+1][j-1] + 2
else: dp[i][j] = max(dp[i+1][j], dp[i][j-1])
```

**Core decision:** Match ends or skip from either side

**Example problems:**
- Longest palindromic subsequence
- Longest palindromic substring
- Minimum insertions to make palindrome
- Palindromic partitioning

**Recognition:**
- String problems
- Palindrome properties
- Usually requires checking both ends

---

### Pattern 6: Kadane's Algorithm (Maximum Subarray)
**Problem type:** Find maximum sum contiguous subarray

**State:** `dp[i]` = maximum sum ending at index i

**Recurrence:** `dp[i] = max(arr[i], dp[i-1] + arr[i])`

**Core decision:** Extend current subarray or start new

**Example problems:**
- Maximum subarray sum
- Maximum product subarray
- Maximum circular subarray
- Best time to buy/sell stock

**Recognition:**
- Contiguous subarray
- Optimization of sum/product
- Single pass possible

---

### Pattern 7: Matrix Chain Multiplication (MCM)
**Problem type:** Find optimal order for operations

**State:** `dp[i][j]` = minimum cost to compute from i to j

**Recurrence:** `dp[i][j] = min(dp[i][k] + dp[k+1][j] + cost(i,k,j))` for all k

**Core decision:** Where to split the problem

**Example problems:**
- Matrix chain multiplication
- Burst balloons
- Minimum cost to merge stones
- Palindrome partitioning
- Boolean parenthesization

**Recognition:**
- Range/interval based
- Need to try all split points
- Cost depends on combining subproblems

---

### Pattern 8: DP on Trees
**Problem type:** Optimize on tree structures

**State:** `dp[node]` = optimal solution for subtree rooted at node

**Recurrence:** Combine results from children nodes

**Core decision:** Include/exclude node, or aggregate from children

**Example problems:**
- House robber III
- Binary tree cameras
- Maximum path sum in tree
- Diameter of binary tree
- Tree DP with subtree results

**Recognition:**
- Tree structure
- Decision at each node affects subtree
- Bottom-up or top-down traversal

---

### Pattern 9: DP on Grids
**Problem type:** Path counting or optimization on 2D grids

**State:** `dp[i][j]` = solution at cell (i, j)

**Recurrence:** `dp[i][j] = f(dp[i-1][j], dp[i][j-1], ...)`

**Core decision:** How to reach current cell

**Example problems:**
- Unique paths
- Minimum path sum
- Dungeon game
- Cherry pickup
- Count square submatrices

**Recognition:**
- 2D grid/matrix
- Movement restrictions (usually right/down)
- Counting paths or optimizing cost

---

### Pattern 10: Partition DP
**Problem type:** Partition array/string optimally

**State:** `dp[i]` = optimal solution for first i elements

**Recurrence:** Try all possible last partitions

**Core decision:** Where to make the last cut

**Example problems:**
- Partition array for maximum sum
- Palindrome partitioning II
- Decode ways
- Word break

**Recognition:**
- Need to split into parts
- Each part has constraints
- Optimization of partition

---

## DP Approaches: Top-Down vs Bottom-Up

### Top-Down (Memoization)
**Method:** Start with original problem, recursively solve, cache results

**Pros:**
- Intuitive - follows natural recursive thinking
- Only solves needed subproblems
- Easy to convert from recursive solution

**Cons:**
- Recursion overhead (stack space)
- Slightly slower due to function calls

**When to use:** When problem is easier to think recursively, or not all subproblems are needed

**Template:**
```java
Map<State, Result> memo = new HashMap<>();

Result solve(State state) {
    if (baseCase) return baseResult;
    if (memo.containsKey(state)) return memo.get(state);

    Result result = computeResult(); // recursive calls
    memo.put(state, result);
    return result;
}
```

---

### Bottom-Up (Tabulation)
**Method:** Start from base cases, build up to final solution iteratively

**Pros:**
- No recursion overhead
- Often faster
- Better space optimization possible
- Easier to optimize with space tricks

**Cons:**
- May solve unnecessary subproblems
- Less intuitive initially
- Order of computation must be carefully determined

**When to use:** When all subproblems need to be solved, or space/time optimization is critical

**Template:**
```java
int[][] dp = new int[n][m];
// Initialize base cases
for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
        // Compute dp[i][j] from previous states
        dp[i][j] = function(dp[i-1][j], dp[i][j-1], ...);
    }
}
return dp[n-1][m-1];
```

---

## State Definition: The Most Critical Step

### What is State?
State is the **minimum information needed** to uniquely identify a subproblem.

### How to Define State:
1. **Identify what changes** as you solve subproblems
2. **Determine what information** you need to make decisions
3. **Keep it minimal** - more dimensions = more complexity
4. **Think about dependencies** - what does this subproblem need?

### Common State Patterns:
- **1D DP:** `dp[i]` - solution using first i elements
- **2D DP:** `dp[i][j]` - solution for range [i, j] or using i from first array, j from second
- **3D DP:** `dp[i][j][k]` - multiple constraints or dimensions
- **State with boolean:** `dp[i][taken]` - with/without some condition
- **State with previous choice:** `dp[i][prev]` - depends on last choice

### Examples:
- **0/1 Knapsack:** `dp[index][remainingWeight]` - at item index with weight left
- **LCS:** `dp[i][j]` - LCS of first i chars of s1 and first j chars of s2
- **Longest Path:** `dp[node][visitedMask]` - at node with visited set
- **Stock with Cooldown:** `dp[day][hasStock]` - at day, whether holding stock

---

## Transition: From State to State

### Transition Equation (Recurrence Relation)
The **mathematical relationship** between current state and previous states.

### How to Derive Transition:
1. **List all choices** available at current state
2. **For each choice**, determine which previous state(s) it depends on
3. **Combine results** using appropriate operation (max, min, sum, etc.)
4. **Add current contribution** (cost, value, count, etc.)

### Common Transition Patterns:
```
Maximum: dp[i] = max(option1, option2, ...)
Minimum: dp[i] = min(option1, option2, ...)
Count: dp[i] = dp[state1] + dp[state2] + ...
Boolean: dp[i] = dp[state1] OR dp[state2] OR ...
```

### Example - Coin Change:
```java
// State: dp[amount] = minimum coins for amount
// Transition: try each coin
for (int coin : coins) {
    if (amount >= coin) {
        dp[amount] = min(dp[amount], dp[amount - coin] + 1);
    }
}
```

---

## The 5-Step DP Problem-Solving Framework

### Step 1: Identify if it's a DP Problem
- Does it have optimal substructure?
- Are there overlapping subproblems?
- Is it asking for optimization or counting?

### Step 2: Define the State
- What are the changing parameters?
- What minimal info uniquely identifies a subproblem?
- How many dimensions do you need?

### Step 3: Establish the Recurrence Relation
- What choices do you have at each state?
- How do choices affect future states?
- What operation combines subproblem results?

### Step 4: Determine Base Cases
- What's the simplest subproblem?
- What are the boundaries?
- What happens with empty/zero inputs?

### Step 5: Determine Computation Order
- Top-down (memoization) or Bottom-up (tabulation)?
- If bottom-up, what order ensures dependencies are computed first?
- Can you optimize space?

---

## Common DP Mistakes

### Mistake 1: Wrong State Definition
**Problem:** State doesn't capture all necessary information

**Example:** Forgetting to track "used" items in knapsack

**Solution:** Ask "Can two different scenarios lead to same state but need different answers?"

---

### Mistake 2: Incorrect Base Cases
**Problem:** Wrong initialization leads to wrong results

**Example:** Initializing `dp[0]` as 0 when it should be 1 for counting problems

**Solution:** Manually trace small examples, verify base cases make sense

---

### Mistake 3: Wrong Iteration Order
**Problem:** Computing state before its dependencies

**Example:** Computing `dp[i]` before `dp[i-1]` in bottom-up

**Solution:** Draw dependency graph, ensure all dependencies computed first

---

### Mistake 4: Off-by-One Errors
**Problem:** Array bounds, loop ranges, or indexing mistakes

**Example:** Using `dp[i]` when representing first i elements (0-indexed vs 1-indexed)

**Solution:** Be consistent with indexing convention, add comments

---

### Mistake 5: Forgetting to Handle Edge Cases
**Problem:** Empty input, single element, or special conditions

**Solution:** Test with minimal inputs, boundaries, special cases

---

### Mistake 6: Unnecessary Dimensions
**Problem:** Adding extra state dimensions that aren't needed

**Example:** Storing both index and value when index implies value

**Solution:** Minimize state, remove redundant information

---

## When DP FAILS - Use Different Approaches

### When DP is NOT the Answer:

#### 1. Need Actual Solution Path (not just optimal value)
**Problem:** DP typically computes optimal value, not the actual solution
**Solution:** Add backtracking or parent pointers to reconstruct path

#### 2. State Space Too Large
**Problem:** Number of states is exponential (e.g., all subsets)
**Solution:** Use greedy, approximation, or heuristic algorithms

#### 3. No Overlapping Subproblems
**Problem:** Each subproblem is unique, no repetition
**Solution:** Plain recursion or divide-and-conquer

#### 4. Greedy Works
**Problem:** Greedy gives optimal solution faster
**Solution:** Prove greedy correctness, use greedy

#### 5. Constraints Make DP Impractical
**Problem:** n=10^6 with O(n²) DP times out
**Solution:** Optimize algorithm, use different approach

---

## Decision Framework: When to Use DP

### Use DP if:
- ✓ Problem asks for optimization (min/max/longest/shortest)
- ✓ Problem asks for counting ("how many ways")
- ✓ Recursive solution exists but has repeated subproblems
- ✓ Greedy doesn't work (current choice affects future significantly)
- ✓ Need to compare multiple possibilities
- ✓ Problem has optimal substructure

### DON'T use DP if:
- ✗ Greedy provably works and is simpler
- ✗ State space is exponential with no structure
- ✗ Need actual solution and backtracking is too complex
- ✗ Time/space constraints make DP infeasible
- ✗ Problem is purely combinatorial with no optimization

---

## DP vs Greedy vs Backtracking

| Aspect | Dynamic Programming | Greedy | Backtracking |
|--------|-------------------|--------|--------------|
| **Goal** | Optimal solution via subproblems | Optimal via local choices | Find all/any solution |
| **Choices** | Explores all choices, remembers | Makes one best choice | Explores all with pruning |
| **Overlapping** | Yes, stores results | No need | No, different paths |
| **Optimality** | Guaranteed if structured correctly | Not always guaranteed | Finds all, can pick optimal |
| **Time** | Polynomial (often) | Linear/Log-linear | Exponential |
| **Space** | Needs storage for states | Minimal | Recursion stack |
| **Example** | 0/1 Knapsack, LCS | Activity Selection, Huffman | N-Queens, Sudoku |

---

## Space Optimization Techniques

### Technique 1: Rolling Array
**When:** Current state depends only on previous row/column

**Example:** 2D DP to 1D
```java
// Before: dp[i][j] = dp[i-1][j] + dp[i][j-1]
// After: dp[j] = dp[j] + dp[j-1]
// Space: O(n*m) → O(m)
```

---

### Technique 2: Two Arrays
**When:** Current row depends only on previous row

**Example:**
```java
int[] prev = new int[n];
int[] curr = new int[n];
// Compute curr from prev, then swap
```

---

### Technique 3: Reverse Iteration
**When:** Update in-place without overwriting needed values

**Example:** Knapsack - iterate weight from high to low
```java
for (int w = W; w >= weight[i]; w--) {
    dp[w] = max(dp[w], dp[w-weight[i]] + value[i]);
}
```

---

### Technique 4: Hash Map for Sparse States
**When:** Most states are never visited

**Example:** Instead of large array, use HashMap for reachable states only

---

## Time Complexity Analysis

### Standard Formula:
**Time = Number of States × Time per State**

### Examples:
- **0/1 Knapsack:** O(n × W) - n items, W capacity, O(1) per state
- **LCS:** O(n × m) - n×m states, O(1) per state
- **Matrix Chain:** O(n³) - O(n²) states, O(n) per state (trying splits)
- **Subset Sum:** O(n × sum) - pseudo-polynomial

### Common Complexities:
- **1D DP with simple transition:** O(n)
- **2D DP with simple transition:** O(n²) or O(n × m)
- **Range DP with split points:** O(n³)
- **Bitmask DP:** O(n × 2ⁿ)

---

## Classic DP Problem Categories

### 1. Sequence DP
- **Problems:** LIS, LDS, Maximum sum subsequence
- **Pattern:** Build solution from previous elements
- **State:** Usually `dp[i]` = best ending at i

### 2. String DP
- **Problems:** LCS, Edit distance, Palindromes
- **Pattern:** Two pointers or range-based
- **State:** `dp[i][j]` for substring or two strings

### 3. Grid DP
- **Problems:** Unique paths, min path sum
- **Pattern:** Cells depend on neighbors
- **State:** `dp[i][j]` = solution at cell

### 4. Tree DP
- **Problems:** Diameter, max path, subtree problems
- **Pattern:** Aggregate from children
- **State:** `dp[node]` = solution for subtree

### 5. Digit DP
- **Problems:** Count numbers with properties
- **Pattern:** Build number digit by digit
- **State:** `dp[pos][tight][...]`

### 6. Bitmask DP
- **Problems:** TSP, assignment, subsets
- **Pattern:** Use bit to represent set
- **State:** `dp[mask]` = solution for set

### 7. Probability DP
- **Problems:** Expected value, probability of winning
- **Pattern:** Sum probabilities × values
- **State:** `dp[state]` = probability/expected value

### 8. Game Theory DP
- **Problems:** Nim, optimal play
- **Pattern:** Minimax, winning/losing states
- **State:** `dp[state]` = can win from state

---

## Practice Strategy

### Level 1: Foundation (Start Here)
1. **Fibonacci series** - Basic memoization
2. **Climbing stairs** - 1D DP
3. **House robber** - Simple choice: rob or skip
4. **Maximum subarray (Kadane's)** - Classic pattern

### Level 2: Common Patterns
1. **0/1 Knapsack** - Core pattern
2. **Coin change** - Unbounded knapsack
3. **Longest common subsequence** - 2D DP
4. **Longest increasing subsequence** - Sequence DP
5. **Unique paths** - Grid DP

### Level 3: Intermediate
1. **Edit distance** - Multiple transitions
2. **Palindrome problems** - Range DP
3. **Word break** - Partition DP
4. **Best time to buy/sell stock series** - State machine

### Level 4: Advanced
1. **Matrix chain multiplication** - Range DP with split
2. **Burst balloons** - Non-obvious state
3. **Regular expression matching** - Complex transitions
4. **Interleaving string** - Multi-dimensional

### Level 5: Expert
1. **Distinct subsequences** - Counting variants
2. **Wildcard matching** - Pattern matching
3. **Dungeon game** - Reverse DP
4. **Cherry pickup** - Multi-path DP

---

## Quick Recognition Cheat Sheet

| Keyword in Problem | Likely Pattern |
|-------------------|---------------|
| "Subsequence" | LCS or LIS |
| "Substring" | Sliding window or Palindrome DP |
| "Subarray" | Kadane's or Prefix sum |
| "Path in grid" | Grid DP |
| "Partition" | Partition DP or Knapsack |
| "Count ways" | Counting DP |
| "Maximum/Minimum" | Optimization DP |
| "Target sum" | 0/1 Knapsack |
| "Coin change" | Unbounded Knapsack |
| "Palindrome" | Range DP |
| "Buy/Sell stock" | State machine DP |
| "Two strings" | LCS variants |

---

## DP Mental Checklist

Before coding, ask yourself:

**State:**
- [ ] What changes as I solve subproblems?
- [ ] Is my state minimal yet complete?
- [ ] Can two different scenarios need different answers with same state?

**Recurrence:**
- [ ] What choices do I have at each state?
- [ ] How do I combine subproblem results?
- [ ] Does my recurrence cover all cases?

**Base Cases:**
- [ ] What's the simplest problem?
- [ ] What happens with empty/zero input?
- [ ] Are boundary conditions handled?

**Implementation:**
- [ ] Top-down or bottom-up?
- [ ] What's the computation order?
- [ ] Can I optimize space?
- [ ] Have I handled edge cases?

**Verification:**
- [ ] Does it work for small examples?
- [ ] Is time complexity acceptable?
- [ ] Is space complexity acceptable?
- [ ] Are there off-by-one errors?

---

## Key Takeaway

**Dynamic Programming is about recognizing structure.** Once you identify:
1. The **state** (what defines a subproblem)
2. The **transition** (how subproblems relate)
3. The **base cases** (where to start)

The implementation follows naturally. The hard part is the thinking, not the coding.

**Remember:**
- Not every problem with recursion is DP
- Not every optimization problem needs DP
- When in doubt, try to find overlapping subproblems - that's your DP signal

**Practice Pattern Recognition** - Most DP problems are variations of ~15 core patterns. Master those, and you can solve hundreds of problems.
