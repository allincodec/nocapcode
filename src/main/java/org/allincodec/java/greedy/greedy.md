# Greedy Algorithms - Core Concepts Guide

## What is a Greedy Algorithm?

A greedy algorithm builds up a solution piece by piece, always choosing the next piece that offers the most immediate benefit (local optimum), without reconsidering previous choices.

**Key Characteristic:** Once a choice is made, it's never reversed or reconsidered.

---

## When Does Greedy Work?

Greedy algorithms work when a problem has these two properties:

### 1. Greedy Choice Property
- A global optimum can be arrived at by making locally optimal (greedy) choices
- You can make the best choice at each step without worrying about future consequences
- The locally optimal choice leads to a globally optimal solution

### 2. Optimal Substructure
- An optimal solution contains optimal solutions to subproblems
- After making a greedy choice, you're left with a similar but smaller problem

**Both properties must hold for greedy to guarantee correctness.**

---

## How to Recognize Greedy Problems

### Strong Indicators:
1. **Optimization problems** asking for minimum or maximum
2. **Selection problems** where you pick items based on some criteria
3. **Scheduling problems** involving time intervals or deadlines
4. Problems where **sorting the input** makes the solution obvious
5. Problems where making the "obvious best choice" at each step feels natural

### Red Flags (Greedy might NOT work):
1. Choices have **long-term consequences** that aren't immediately visible
2. You need to **compare multiple paths** before deciding
3. Problem involves **counting ways** or finding **all solutions**
4. **Constraints are interdependent** (choosing X affects whether you can choose Y later)
5. The problem explicitly asks for **optimal across all possible combinations**

---

## Classic Greedy Problem Patterns

### Pattern 1: Activity/Interval Selection
**Problem type:** Select maximum non-overlapping intervals

**Greedy choice:** Always pick the interval that ends earliest

**Why it works:** Leaves maximum room for future selections

**Example problems:**
- Meeting room scheduling
- Non-overlapping intervals
- Maximum number of events to attend

---

### Pattern 2: Fractional Resource Allocation
**Problem type:** Maximize value when items can be divided

**Greedy choice:** Pick highest value-to-weight ratio first

**Why it works:** Every gram taken is optimal

**Example problems:**
- Fractional knapsack
- Resource allocation with divisible units

---

### Pattern 3: Minimum Spanning Tree
**Problem type:** Connect all nodes with minimum total cost

**Greedy choice:** Add cheapest edge that doesn't create a cycle (Kruskal's) OR add cheapest edge to growing tree (Prim's)

**Why it works:** Locally cheapest edge never makes global solution worse

**Example problems:**
- Network design
- Connecting cities with minimum cable

---

### Pattern 4: Huffman Coding / Merging
**Problem type:** Build optimal binary tree

**Greedy choice:** Merge two smallest elements repeatedly

**Why it works:** Smallest elements should be deepest in tree

**Example problems:**
- File compression
- Minimize cost of merging files

---

### Pattern 5: Two-Pointer / Furthest Reach
**Problem type:** Jumping or reaching problems

**Greedy choice:** At each position, go as far as possible

**Why it works:** Going further never makes the solution worse

**Example problems:**
- Jump game
- Gas station circuit

---

## When Greedy FAILS - Common Traps

### Trap 1: 0/1 Knapsack
**Why greedy fails:** Items cannot be divided. Taking high-value items early might prevent optimal combination.

**Correct approach:** Dynamic Programming

**Example:**
- Capacity: 10
- Items: (weight=5, value=10), (weight=6, value=12), (weight=4, value=7)
- Greedy by value/weight would take first item, but optimal is second + third

---

### Trap 2: Coin Change (Minimum Coins)
**Why greedy fails:** Coin denominations might not allow greedy to work

**Greedy works for:** Standard denominations (1, 5, 10, 25)
**Greedy fails for:** Arbitrary denominations like (1, 3, 4)

**Example with (1, 3, 4) for target 6:**
- Greedy: 4 + 1 + 1 = 3 coins
- Optimal: 3 + 3 = 2 coins

**Correct approach:** Dynamic Programming

---

### Trap 3: Longest Path in Graph
**Why greedy fails:** Locally longest edge might block access to longer paths later

**Correct approach:** DFS/DP depending on graph type

---

### Trap 4: Subset Sum / Partition Problems
**Why greedy fails:** Need to explore multiple combinations

**Correct approach:** Backtracking or Dynamic Programming

---

## How to Prove Greedy Correctness

### Method 1: Exchange Argument
1. Assume there's an optimal solution different from greedy solution
2. Show you can exchange elements to transform optimal → greedy
3. Prove this exchange doesn't make solution worse
4. Therefore greedy is optimal

### Method 2: Staying Ahead
1. Show greedy solution is always "ahead" of any other solution at each step
2. Ahead means: more beneficial position for future choices

### Method 3: Structural Induction
1. Prove greedy choice is safe (doesn't eliminate optimal solution)
2. Prove remaining subproblem has same structure
3. Induction shows greedy works for all sizes

---

## Decision Framework: Greedy vs DP

Ask yourself these questions:

### Use GREEDY if:
- ✓ Can you sort inputs to make a clear "best choice" obvious?
- ✓ Does picking the best option now never hurt future options?
- ✓ Is there a clear, simple ordering criterion (earliest finish, highest ratio, etc.)?
- ✓ Can you prove greedy choice property with exchange argument?

### Use DP if:
- ✓ Do current choices significantly affect future possibilities?
- ✓ Are you computing the same subproblems multiple times?
- ✓ Do you need to compare multiple sequences of choices?
- ✓ Is the problem asking "how many ways" or "all possible"?

### Neither - Use Backtracking if:
- Need to explore ALL solutions
- Need to find ANY valid solution (not optimal)
- Constraints are complex and interdependent

---

## Common Greedy Mistakes

### Mistake 1: Assuming Greedy Works Without Proof
**Problem:** Just because a greedy approach seems logical doesn't mean it's correct

**Solution:** Always verify with proof or counterexample

### Mistake 2: Wrong Greedy Choice
**Problem:** Choosing wrong criterion (e.g., sort by start time instead of end time)

**Solution:** Test multiple greedy choices, prove why yours is correct

### Mistake 3: Missing Edge Cases
**Problem:** Greedy works for most cases but fails on boundary conditions

**Solution:** Test with small inputs, empty inputs, single element

### Mistake 4: Not Sorting First
**Problem:** Many greedy problems require sorted input to work

**Solution:** Identify if ordering matters, sort accordingly

---

## Practice Strategy

### Step 1: Pattern Recognition
When you see a problem, ask:
1. Is this asking for maximum/minimum of something?
2. Can I sort the input meaningfully?
3. Is there an obvious "best choice" at each step?
4. What happens if I make the greedy choice - can I prove it's safe?

### Step 2: Proof Before Code
Before implementing:
1. Write down your greedy choice criterion
2. Sketch a proof (exchange argument or staying ahead)
3. Try to find a counterexample
4. If counterexample exists → not greedy, try DP

### Step 3: Compare with Optimal
For small inputs:
1. Brute force the optimal solution
2. Run your greedy algorithm
3. Compare results
4. If they differ, analyze why

---

## Greedy vs DP Cheat Sheet

| Problem Type | Greedy? | DP? | Key Difference |
|--------------|---------|-----|----------------|
| Activity Selection | ✓ | ✗ | Non-overlapping constraint makes greedy safe |
| Fractional Knapsack | ✓ | ✗ | Can divide items |
| 0/1 Knapsack | ✗ | ✓ | Cannot divide items - combinations matter |
| Coin Change (standard) | ✓ | ✗ | Standard denominations have greedy property |
| Coin Change (arbitrary) | ✗ | ✓ | Arbitrary denominations break greedy |
| Shortest Path (non-negative) | ✓ | ✗ | Dijkstra's greedy choice is safe |
| Longest Path | ✗ | ✓ | Greedy can get stuck on wrong path |
| Jump Game (can reach end?) | ✓ | ✗ | Maximum reach is always beneficial |
| Jump Game (minimum jumps) | ✓ | ✓ | Both work, greedy more efficient |

---

## Key Takeaway

**Greedy is an optimization** - it's faster than DP but only works for specific problem structures. When in doubt:

1. Try to prove greedy works
2. If you can't prove it, try to find counterexample
3. If counterexample exists → use DP
4. If no counterexample but can't prove → implement both and compare

**Remember:** Most problems are NOT greedy-solvable. Greedy is the exception, not the rule.