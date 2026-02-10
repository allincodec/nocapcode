# Graph Algorithms - Core Concepts Guide

## What is a Graph?

A graph is a data structure consisting of **nodes (vertices)** connected by **edges** that represent relationships or connections between entities.

**Key Components:**
- **Vertices (V):** The nodes/points in the graph
- **Edges (E):** The connections between vertices
- **Weight:** Optional value associated with an edge (cost, distance, capacity)

**Real-world Examples:**
- Social networks (people = nodes, friendships = edges)
- Road networks (cities = nodes, roads = edges)
- Web pages (pages = nodes, hyperlinks = edges)
- Dependencies (tasks = nodes, prerequisites = edges)

---

## Graph Types

### 1. Directed vs Undirected

**Undirected Graph:**
- Edges have no direction (bidirectional)
- If A connects to B, then B connects to A
- Example: Facebook friendships, undirected roads

**Directed Graph (Digraph):**
- Edges have direction (one-way)
- A → B doesn't mean B → A
- Example: Twitter following, one-way streets, task dependencies

---

### 2. Weighted vs Unweighted

**Unweighted Graph:**
- All edges are equal (or weight = 1)
- Example: Social network connections

**Weighted Graph:**
- Edges have associated weights/costs
- Example: Road network with distances, network with latencies

---

### 3. Cyclic vs Acyclic

**Cyclic Graph:**
- Contains at least one cycle (path from node back to itself)
- Example: Most real-world networks

**Acyclic Graph:**
- No cycles exist
- **DAG (Directed Acyclic Graph):** Special type used in scheduling, dependencies
- Example: Course prerequisites, build dependencies

---

### 4. Connected vs Disconnected

**Connected Graph:**
- Path exists between every pair of vertices
- Single component

**Disconnected Graph:**
- Multiple separate components
- No path between some vertices

---

### 5. Special Graph Types

**Tree:**
- Connected acyclic graph
- V nodes have V-1 edges
- Exactly one path between any two nodes

**Complete Graph:**
- Every vertex connected to every other vertex
- E = V × (V-1) / 2 edges

**Bipartite Graph:**
- Vertices can be divided into two sets
- Edges only between sets, not within
- Example: Job assignments (workers ↔ jobs)

---

## Graph Representations

### 1. Adjacency Matrix
**Structure:** 2D array `matrix[i][j]`

**Representation:**
- `matrix[i][j] = 1` if edge exists from i to j
- `matrix[i][j] = 0` if no edge
- For weighted: `matrix[i][j] = weight`

**Pros:**
- O(1) edge lookup
- Simple to implement
- Good for dense graphs

**Cons:**
- O(V²) space (wasteful for sparse graphs)
- O(V) to iterate through neighbors

**When to use:**
- Dense graphs (many edges)
- Frequent edge existence checks
- Graph algorithms needing edge lookup (Floyd-Warshall)

```java
int[][] adjMatrix = new int[V][V];
adjMatrix[u][v] = 1; // or weight
```

---

### 2. Adjacency List
**Structure:** Array/Map of lists

**Representation:**
- `list[i]` contains all neighbors of vertex i
- For weighted: store pairs (neighbor, weight)

**Pros:**
- O(V + E) space (efficient for sparse graphs)
- O(degree) to iterate neighbors
- Most common in practice

**Cons:**
- O(degree) to check edge existence
- Slightly more complex

**When to use:**
- Sparse graphs (few edges)
- Most graph problems
- When iterating through neighbors

```java
List<Integer>[] adjList = new ArrayList[V];
// Or for weighted:
List<int[]>[] adjList = new ArrayList[V]; // [neighbor, weight]
// Or use Map:
Map<Integer, List<Integer>> graph = new HashMap<>();
```

---

### 3. Edge List
**Structure:** List of edges

**Representation:**
- Store edges as (u, v) or (u, v, weight)

**Pros:**
- Simplest representation
- Good for algorithms that process edges directly

**Cons:**
- O(E) to find neighbors
- Inefficient for most traversals

**When to use:**
- Kruskal's MST
- Edge-centric algorithms

```java
List<int[]> edges = new ArrayList<>();
edges.add(new int[]{u, v, weight});
```

---

## Core Graph Traversal Algorithms

### 1. Depth-First Search (DFS)

**Strategy:** Go as deep as possible before backtracking

**Implementation:** Recursion or explicit stack

**Time:** O(V + E)
**Space:** O(V) for visited array + O(V) recursion stack

**Use Cases:**
- Detect cycles
- Find connected components
- Topological sort
- Path finding (any path, not shortest)
- Maze solving
- Graph coloring

**Templates:**

**Recursive DFS:**
```java
void dfs(int node, boolean[] visited) {
    visited[node] = true;
    // Process node

    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            dfs(neighbor, visited);
        }
    }
}
```

**Iterative DFS:**
```java
void dfs(int start) {
    Stack<Integer> stack = new Stack<>();
    boolean[] visited = new boolean[V];

    stack.push(start);

    while (!stack.isEmpty()) {
        int node = stack.pop();
        if (visited[node]) continue;

        visited[node] = true;
        // Process node

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                stack.push(neighbor);
            }
        }
    }
}
```

**Pattern Recognition:**
- Need to explore "deeply" or "completely"
- Cycle detection
- Backtracking needed
- Memory not a concern (recursion okay)

---

### 2. Breadth-First Search (BFS)

**Strategy:** Explore level by level (nearest first)

**Implementation:** Queue

**Time:** O(V + E)
**Space:** O(V) for queue and visited

**Use Cases:**
- **Shortest path in unweighted graph** ⭐
- Level-order traversal
- Find all nodes at distance K
- Check bipartiteness
- Find minimum steps/moves

**Template:**
```java
void bfs(int start) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[V];

    queue.offer(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
        int node = queue.poll();
        // Process node

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.offer(neighbor);
            }
        }
    }
}
```

**BFS for Shortest Path:**
```java
int bfsShortestPath(int start, int end) {
    Queue<Integer> queue = new LinkedList<>();
    int[] distance = new int[V];
    Arrays.fill(distance, -1);

    queue.offer(start);
    distance[start] = 0;

    while (!queue.isEmpty()) {
        int node = queue.poll();

        if (node == end) return distance[end];

        for (int neighbor : graph[node]) {
            if (distance[neighbor] == -1) {
                distance[neighbor] = distance[node] + 1;
                queue.offer(neighbor);
            }
        }
    }
    return -1; // Not reachable
}
```

**Pattern Recognition:**
- Need **shortest path** (unweighted)
- Level-by-level exploration
- Minimum steps/moves
- Find nearest/closest

---

### 3. DFS vs BFS - When to Use What?

| Aspect | DFS | BFS |
|--------|-----|-----|
| **Shortest Path** | ✗ No | ✓ Yes (unweighted) |
| **Memory** | O(height) stack | O(width) queue |
| **Implementation** | Recursion/Stack | Queue |
| **Use for Path** | Any path | Shortest path |
| **Use for Components** | ✓ Yes | ✓ Yes |
| **Use for Cycles** | ✓ Yes | ✗ Not typical |
| **Use for Topological Sort** | ✓ Yes | ✗ No |
| **Deep graphs** | Stack overflow risk | Memory for wide graphs |
| **Wide graphs** | Better | Worse |

**Rule of Thumb:**
- Need shortest path in unweighted graph → **BFS**
- Need to explore all paths or detect cycles → **DFS**
- Space-constrained → **DFS** (if graph is deep, BFS if wide)
- Need topological sort → **DFS**

---

## Classic Graph Algorithm Patterns

### Pattern 1: Shortest Path Algorithms

#### Dijkstra's Algorithm
**Problem:** Single-source shortest path in **weighted graph with non-negative weights**

**Strategy:** Greedy - always pick closest unvisited vertex

**Time:** O((V + E) log V) with priority queue
**Space:** O(V)

**When to use:**
- Weighted graph
- Non-negative weights
- Single source to all destinations OR single source to single destination

**Implementation:**
```java
int[] dijkstra(int start) {
    int[] dist = new int[V];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[start] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, distance]
    pq.offer(new int[]{start, 0});

    while (!pq.isEmpty()) {
        int[] curr = pq.poll();
        int node = curr[0];
        int d = curr[1];

        if (d > dist[node]) continue; // Already processed with shorter distance

        for (int[] edge : graph[node]) { // [neighbor, weight]
            int neighbor = edge[0];
            int weight = edge[1];
            int newDist = dist[node] + weight;

            if (newDist < dist[neighbor]) {
                dist[neighbor] = newDist;
                pq.offer(new int[]{neighbor, newDist});
            }
        }
    }
    return dist;
}
```

**Common Mistakes:**
- Using with negative weights (use Bellman-Ford instead)
- Not checking if `d > dist[node]` before processing

---

#### Bellman-Ford Algorithm
**Problem:** Single-source shortest path with **possible negative weights**

**Strategy:** Relax all edges V-1 times

**Time:** O(V × E)
**Space:** O(V)

**When to use:**
- Graph has negative edge weights
- Need to detect negative cycles
- Simpler to implement than Dijkstra (but slower)

**Can detect:** Negative weight cycles

**Implementation:**
```java
int[] bellmanFord(int start) {
    int[] dist = new int[V];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[start] = 0;

    // Relax all edges V-1 times
    for (int i = 0; i < V - 1; i++) {
        for (int[] edge : edges) { // [u, v, weight]
            int u = edge[0], v = edge[1], w = edge[2];
            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
            }
        }
    }

    // Check for negative cycles
    for (int[] edge : edges) {
        int u = edge[0], v = edge[1], w = edge[2];
        if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
            // Negative cycle detected
            return null;
        }
    }

    return dist;
}
```

---

#### Floyd-Warshall Algorithm
**Problem:** All-pairs shortest path

**Strategy:** Dynamic programming - try all intermediate vertices

**Time:** O(V³)
**Space:** O(V²)

**When to use:**
- Need shortest path between ALL pairs
- Dense graph (many edges)
- Graph is small (V ≤ 400)
- Can handle negative weights (but not negative cycles)

**Implementation:**
```java
void floydWarshall(int[][] dist) {
    // dist[i][j] initialized with edge weights or INF

    for (int k = 0; k < V; k++) {           // Intermediate vertex
        for (int i = 0; i < V; i++) {       // Source
            for (int j = 0; j < V; j++) {   // Destination
                if (dist[i][k] != INF && dist[k][j] != INF) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
```

---

### Pattern 2: Minimum Spanning Tree (MST)

**Goal:** Connect all vertices with minimum total edge weight (no cycles)

**Properties:**
- Tree: V vertices, V-1 edges
- Connects all vertices
- Minimum total weight
- Unique if all edge weights are distinct

---

#### Kruskal's Algorithm
**Strategy:** Sort edges, add cheapest edge that doesn't create cycle

**Time:** O(E log E) for sorting
**Space:** O(V) for Union-Find

**When to use:**
- Sparse graph (few edges)
- Edges given as list
- Simple to implement

**Implementation:**
```java
class UnionFind {
    int[] parent, rank;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return false; // Already connected

        if (rank[px] < rank[py]) parent[px] = py;
        else if (rank[px] > rank[py]) parent[py] = px;
        else {
            parent[py] = px;
            rank[px]++;
        }
        return true;
    }
}

int kruskal(int[][] edges) { // edges = [u, v, weight]
    Arrays.sort(edges, (a, b) -> a[2] - b[2]); // Sort by weight

    UnionFind uf = new UnionFind(V);
    int mstWeight = 0;
    int edgesUsed = 0;

    for (int[] edge : edges) {
        if (uf.union(edge[0], edge[1])) {
            mstWeight += edge[2];
            edgesUsed++;
            if (edgesUsed == V - 1) break; // MST complete
        }
    }

    return mstWeight;
}
```

---

#### Prim's Algorithm
**Strategy:** Grow MST from starting vertex, add cheapest edge to tree

**Time:** O((V + E) log V) with priority queue
**Space:** O(V + E)

**When to use:**
- Dense graph (many edges)
- Graph given as adjacency list
- Need MST rooted at specific vertex

**Implementation:**
```java
int prim(int start) {
    boolean[] inMST = new boolean[V];
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, weight]

    pq.offer(new int[]{start, 0});
    int mstWeight = 0;

    while (!pq.isEmpty()) {
        int[] curr = pq.poll();
        int node = curr[0];
        int weight = curr[1];

        if (inMST[node]) continue;

        inMST[node] = true;
        mstWeight += weight;

        for (int[] edge : graph[node]) { // [neighbor, edgeWeight]
            int neighbor = edge[0];
            int edgeWeight = edge[1];
            if (!inMST[neighbor]) {
                pq.offer(new int[]{neighbor, edgeWeight});
            }
        }
    }

    return mstWeight;
}
```

---

### Pattern 3: Cycle Detection

#### Detect Cycle in Undirected Graph

**Method:** DFS with parent tracking

**Logic:** If we reach a visited node that's not our parent → cycle

```java
boolean hasCycleUndirected(int node, int parent, boolean[] visited) {
    visited[node] = true;

    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            if (hasCycleUndirected(neighbor, node, visited)) {
                return true;
            }
        } else if (neighbor != parent) {
            return true; // Visited node that's not parent → cycle
        }
    }
    return false;
}
```

---

#### Detect Cycle in Directed Graph

**Method:** DFS with recursion stack

**Logic:** If we reach a node in current recursion path → cycle

```java
boolean hasCycleDirected(int node, boolean[] visited, boolean[] recStack) {
    visited[node] = true;
    recStack[node] = true;

    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            if (hasCycleDirected(neighbor, visited, recStack)) {
                return true;
            }
        } else if (recStack[neighbor]) {
            return true; // Back edge → cycle
        }
    }

    recStack[node] = false; // Remove from recursion stack
    return false;
}
```

**Three states approach:**
- **White (0):** Not visited
- **Gray (1):** Visiting (in recursion stack)
- **Black (2):** Visited completely

```java
boolean hasCycleDirected(int node, int[] color) {
    color[node] = 1; // Gray - visiting

    for (int neighbor : graph[node]) {
        if (color[neighbor] == 1) return true; // Gray neighbor → cycle
        if (color[neighbor] == 0 && hasCycleDirected(neighbor, color)) {
            return true;
        }
    }

    color[node] = 2; // Black - visited
    return false;
}
```

---

### Pattern 4: Topological Sort

**Problem:** Order vertices such that for edge u → v, u comes before v

**Constraints:** Only possible in **Directed Acyclic Graph (DAG)**

**Use cases:**
- Course scheduling (prerequisites)
- Build systems (dependencies)
- Task ordering
- Detecting if ordering is possible

---

#### Method 1: DFS-based Topological Sort

**Strategy:** Do DFS, add to result in post-order (after visiting children), then reverse

```java
List<Integer> topologicalSort() {
    boolean[] visited = new boolean[V];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < V; i++) {
        if (!visited[i]) {
            topoDFS(i, visited, stack);
        }
    }

    List<Integer> result = new ArrayList<>();
    while (!stack.isEmpty()) {
        result.add(stack.pop());
    }
    return result;
}

void topoDFS(int node, boolean[] visited, Stack<Integer> stack) {
    visited[node] = true;

    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            topoDFS(neighbor, visited, stack);
        }
    }

    stack.push(node); // Add after visiting all descendants
}
```

**Time:** O(V + E)

---

#### Method 2: Kahn's Algorithm (BFS-based)

**Strategy:** Repeatedly remove vertices with in-degree 0

```java
List<Integer> topologicalSortKahn() {
    int[] inDegree = new int[V];

    // Calculate in-degrees
    for (int u = 0; u < V; u++) {
        for (int v : graph[u]) {
            inDegree[v]++;
        }
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < V; i++) {
        if (inDegree[i] == 0) {
            queue.offer(i);
        }
    }

    List<Integer> result = new ArrayList<>();

    while (!queue.isEmpty()) {
        int node = queue.poll();
        result.add(node);

        for (int neighbor : graph[node]) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] == 0) {
                queue.offer(neighbor);
            }
        }
    }

    // If result.size() < V, there's a cycle
    return result.size() == V ? result : null;
}
```

**Time:** O(V + E)

**Advantage:** Can detect cycles (if result size < V)

---

### Pattern 5: Union-Find (Disjoint Set Union)

**Use cases:**
- Detect cycles in undirected graph
- Find connected components
- Kruskal's MST
- Dynamic connectivity queries
- Network connectivity

**Operations:**
- `find(x)`: Find root/representative of x's set
- `union(x, y)`: Merge sets containing x and y

**Optimizations:**
- **Path Compression:** Make nodes point directly to root
- **Union by Rank:** Attach smaller tree under larger

```java
class UnionFind {
    int[] parent;
    int[] rank;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // Find with path compression
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union by rank
    boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false; // Already in same set

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }

    // Check if x and y are connected
    boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```

**Time Complexity:** Nearly O(1) amortized with both optimizations (actually O(α(n)) where α is inverse Ackermann function)

---

### Pattern 6: Bipartite Graph Check

**Definition:** Graph whose vertices can be divided into two sets such that edges only go between sets

**Use cases:**
- Job assignment (workers ↔ jobs)
- Matching problems
- Graph coloring with 2 colors

**Method:** BFS/DFS with 2-coloring

```java
boolean isBipartite() {
    int[] color = new int[V]; // 0: uncolored, 1: color1, -1: color2

    for (int start = 0; start < V; start++) {
        if (color[start] != 0) continue;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color[start] = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph[node]) {
                if (color[neighbor] == 0) {
                    color[neighbor] = -color[node]; // Opposite color
                    queue.offer(neighbor);
                } else if (color[neighbor] == color[node]) {
                    return false; // Same color as neighbor → not bipartite
                }
            }
        }
    }
    return true;
}
```

---

### Pattern 7: Strongly Connected Components (SCC)

**Definition:** Maximal subgraph where every vertex is reachable from every other vertex

**Use cases:**
- Social network analysis
- Web page ranking
- Code optimization (grouping)

---

#### Kosaraju's Algorithm

**Strategy:**
1. Do DFS and store finish times
2. Create transpose graph (reverse edges)
3. Do DFS on transpose in decreasing finish time order

**Time:** O(V + E)

```java
void findSCCs() {
    Stack<Integer> stack = new Stack<>();
    boolean[] visited = new boolean[V];

    // Step 1: Fill stack with finish times
    for (int i = 0; i < V; i++) {
        if (!visited[i]) {
            dfsFinishTime(i, visited, stack);
        }
    }

    // Step 2: Create transpose graph
    List<Integer>[] transpose = createTranspose();

    // Step 3: DFS on transpose
    Arrays.fill(visited, false);
    while (!stack.isEmpty()) {
        int node = stack.pop();
        if (!visited[node]) {
            // This DFS will find one SCC
            List<Integer> scc = new ArrayList<>();
            dfsTranspose(node, visited, transpose, scc);
            // Process SCC
        }
    }
}
```

---

### Pattern 8: Articulation Points and Bridges

**Articulation Point (Cut Vertex):** Vertex whose removal increases number of connected components

**Bridge (Cut Edge):** Edge whose removal increases number of connected components

**Use cases:**
- Network vulnerability
- Critical connections
- Graph reliability

**Method:** Tarjan's algorithm using DFS with discovery time and low-link values

```java
void findArticulationPoints() {
    boolean[] visited = new boolean[V];
    int[] disc = new int[V];  // Discovery time
    int[] low = new int[V];   // Lowest reachable
    int[] parent = new int[V];
    boolean[] ap = new boolean[V]; // Articulation points

    Arrays.fill(parent, -1);

    for (int i = 0; i < V; i++) {
        if (!visited[i]) {
            dfsAP(i, visited, disc, low, parent, ap);
        }
    }
}

int time = 0;

void dfsAP(int u, boolean[] visited, int[] disc, int[] low, int[] parent, boolean[] ap) {
    int children = 0;
    visited[u] = true;
    disc[u] = low[u] = ++time;

    for (int v : graph[u]) {
        if (!visited[v]) {
            children++;
            parent[v] = u;
            dfsAP(v, visited, disc, low, parent, ap);

            low[u] = Math.min(low[u], low[v]);

            // Check if u is an articulation point
            // Root: if has 2+ children
            if (parent[u] == -1 && children > 1) {
                ap[u] = true;
            }
            // Non-root: if low[v] >= disc[u]
            if (parent[u] != -1 && low[v] >= disc[u]) {
                ap[u] = true;
            }
        } else if (v != parent[u]) {
            low[u] = Math.min(low[u], disc[v]);
        }
    }
}
```

---

## Advanced Graph Patterns

### Pattern 9: Maximum Flow

**Problem:** Find maximum flow from source to sink in flow network

**Algorithms:**
- **Ford-Fulkerson:** O(E × max_flow) - uses DFS
- **Edmonds-Karp:** O(V × E²) - uses BFS (faster in practice)

**Use cases:**
- Network routing
- Bipartite matching
- Circulation problems

---

### Pattern 10: Traveling Salesman Problem (TSP)

**Problem:** Find shortest route visiting all vertices exactly once

**Approaches:**
- **Brute Force:** O(n!) - try all permutations
- **Dynamic Programming with Bitmask:** O(n² × 2ⁿ)
- **Approximation algorithms** for large graphs

**State:** `dp[mask][last]` = minimum cost to visit nodes in mask, ending at last

---

## Graph Problem Recognition Cheat Sheet

| Problem Description | Likely Algorithm |
|---------------------|------------------|
| Shortest path (unweighted) | BFS |
| Shortest path (weighted, non-negative) | Dijkstra |
| Shortest path (negative weights) | Bellman-Ford |
| All-pairs shortest path | Floyd-Warshall |
| Minimum spanning tree | Kruskal or Prim |
| Detect cycle (undirected) | DFS or Union-Find |
| Detect cycle (directed) | DFS with recursion stack |
| Topological ordering | DFS or Kahn's |
| Connected components | DFS or BFS or Union-Find |
| Strongly connected components | Kosaraju or Tarjan |
| Bipartite check | BFS/DFS with 2-coloring |
| Cut vertices/bridges | Tarjan's algorithm |
| Maximum flow | Ford-Fulkerson/Edmonds-Karp |
| Minimum cut | Max flow |
| Path exists? | BFS/DFS |
| Count paths | DFS with memoization |
| Eulerian path/circuit | Check degrees, DFS |
| Hamiltonian path/circuit | Backtracking or DP |

---

## Common Graph Mistakes

### Mistake 1: Not Handling Disconnected Graphs
**Problem:** Only starting traversal from node 0

**Solution:**
```java
for (int i = 0; i < V; i++) {
    if (!visited[i]) {
        dfs(i); // Start new component
    }
}
```

---

### Mistake 2: Infinite Loop in Directed Graphs
**Problem:** Not tracking visited nodes properly

**Solution:** Always maintain visited array/set

---

### Mistake 3: Using Wrong Algorithm
**Problem:** Using DFS when shortest path needed

**Solution:** BFS for shortest path in unweighted, Dijkstra for weighted

---

### Mistake 4: Forgetting to Handle Self-Loops or Multiple Edges
**Problem:** Assuming simple graph

**Solution:** Check for these explicitly if they matter

---

### Mistake 5: Index Confusion (0-indexed vs 1-indexed)
**Problem:** Off-by-one errors

**Solution:** Be consistent, comment your choice

---

### Mistake 6: Not Checking for Negative Cycles
**Problem:** Using Dijkstra with negative weights

**Solution:** Use Bellman-Ford when negative weights possible

---

### Mistake 7: Modifying Graph During Traversal
**Problem:** Adding/removing edges while iterating

**Solution:** Collect changes, apply after traversal

---

## Graph Algorithm Selection Flow

```
Need shortest path?
├─ Unweighted? → BFS
├─ Weighted, non-negative? → Dijkstra
├─ Weighted, negative possible? → Bellman-Ford
└─ All pairs? → Floyd-Warshall

Need to connect all vertices?
└─ Minimum cost? → MST (Kruskal/Prim)

Need ordering?
└─ Directed acyclic? → Topological Sort

Need to check property?
├─ Cycle? → DFS (directed) or Union-Find (undirected)
├─ Connected? → DFS/BFS/Union-Find
├─ Bipartite? → 2-coloring with BFS/DFS
└─ Strongly connected? → Kosaraju/Tarjan

Need path/reachability?
├─ Any path? → DFS
└─ Shortest? → BFS/Dijkstra

Need components?
├─ Connected components? → DFS/BFS
└─ Strongly connected? → Kosaraju
```

---

## Practice Strategy

### Level 1: Fundamentals
1. **Implement graph representations** (adjacency list, matrix)
2. **DFS traversal** (recursive and iterative)
3. **BFS traversal**
4. **Number of islands** (connected components)
5. **Clone graph**

### Level 2: Basic Algorithms
1. **Cycle detection** (directed and undirected)
2. **Topological sort** (Kahn's and DFS)
3. **Bipartite check**
4. **Shortest path in maze** (BFS)
5. **Number of connected components**

### Level 3: Intermediate
1. **Dijkstra's shortest path**
2. **Minimum spanning tree** (Kruskal/Prim)
3. **Union-Find** implementation and applications
4. **Course schedule problems** (topological sort variants)
5. **Word ladder** (BFS on implicit graph)

### Level 4: Advanced
1. **Bellman-Ford algorithm**
2. **Floyd-Warshall**
3. **Strongly connected components**
4. **Articulation points and bridges**
5. **Network delay time** (Dijkstra variant)

### Level 5: Expert
1. **TSP with DP**
2. **Maximum flow**
3. **Minimum cut**
4. **Eulerian path/circuit**
5. **Graph reconstruction problems**

---

## Key Takeaways

1. **Graph representation matters:** Choose based on density
   - Sparse (few edges) → Adjacency list
   - Dense (many edges) → Adjacency matrix

2. **Traversal choice matters:**
   - Any path/explore all → DFS
   - Shortest path (unweighted) → BFS
   - Shortest path (weighted) → Dijkstra/Bellman-Ford

3. **DAG = Special case:**
   - Enables topological sort
   - Simplifies many problems
   - Check for cycles first

4. **Union-Find is versatile:**
   - Dynamic connectivity
   - Cycle detection
   - MST (Kruskal)
   - Connected components

5. **Time complexity awareness:**
   - BFS/DFS: O(V + E)
   - Dijkstra: O((V + E) log V)
   - Bellman-Ford: O(V × E)
   - Floyd-Warshall: O(V³)

6. **Most graph problems reduce to:**
   - Traversal (DFS/BFS)
   - Shortest path
   - Connectivity
   - Ordering (topological)

**Remember:** Graph problems often require combining multiple techniques. Identify the core operation needed, then apply the appropriate algorithm.
