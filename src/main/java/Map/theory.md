# [并查集理论基础](https://programmercarl.com/kamacoder/%E5%9B%BE%E8%AE%BA%E5%B9%B6%E6%9F%A5%E9%9B%86%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html#%E5%B9%B6%E6%9F%A5%E9%9B%86%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80)

- 当我们需要判断两个元素是否在同一个集合里的时候，我们就要想到用并查集。
- father[A]=B father[B]=C,father数组初始化的时候要 father[i] = i，默认自己指向自己。

## 并查集主要有两个功能：

- 将两个元素添加到一个集合中。
- 判断两个元素在不在同一个集合

## 路径压缩,将非根节点的所有节点直接指向根节点。

```java
// 并查集里寻根的过程
int find(int u) {
    if (u == father[u]) return u;
    else return father[u] = find(father[u]); // 路径压缩
}
```

## 模板

```java
public class UnionFind {
    private int n;
    private int[] father;

    // 初始化并查集
    public UnionFind(int n) {
        this.n = n;
        father = new int[n];
        init();
    }

    private void init() {
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    // 无路径压缩的find方法（更推荐的迭代版）
    public int find(int u) {
        while (father[u] != u) {
            u = father[u];
        }
        return u;
    }

    // 并查集寻根（带路径压缩）
    public int find(int u) {
        if (father[u] != u) {
            father[u] = find(father[u]); // 路径压缩
        }
        return father[u];
    }

    // 判断两个节点是否同属一个集合
    public boolean isSame(int u, int v) {
        return find(u) == find(v);
    }

    // 合并两个节点所属集合
    public void join(int u, int v) {
        u = find(u);    //u的根节点
        v = find(v);    //v的根节点
        if (u == v) return; //根节点相同则不必连接
        father[v] = u;  //根节点不同，赋值u的根节点为v的根节点的上级
    }
}
```

# [拓扑排序精讲:给出一个<u>有向图</u>，把这个有向图转成线性的排序就叫拓扑排序。](https://programmercarl.com/kamacoder/0117.%E8%BD%AF%E4%BB%B6%E6%9E%84%E5%BB%BA.html#%E6%8B%93%E6%89%91%E6%8E%92%E5%BA%8F%E7%9A%84%E8%83%8C%E6%99%AF)

- 拓扑排序也是图论中判断有向无环图的常用方法。
- 其实只要能在把<u>有向无环图</u>进行<u>线性排序</u>的算法 都可以叫做 拓扑排序。

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<List<Integer>> umap = new ArrayList<>(); // 记录文件依赖关系
        int[] inDegree = new int[n]; // 记录每个文件的入度

        for (int i = 0; i < n; i++)
            umap.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int s = scanner.nextInt();
            int t = scanner.nextInt();
            umap.get(s).add(t); // 记录s指向哪些文件
            inDegree[t]++; // t的入度加一
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                // 入度为0的文件，可以作为开头，先加入队列
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        // 拓扑排序
        while (!queue.isEmpty()) {
            int cur = queue.poll(); // 当前选中的文件
            result.add(cur);
            for (int file : umap.get(cur)) {
                inDegree[file]--; // cur的指向的文件入度-1
                if (inDegree[file] == 0) {
                    queue.add(file);
                }
            }
        }

        if (result.size() == n) {   //判断是否成环 如果我们发现结果集元素个数 不等于 图中节点个数，我们就可以认定图中一定有 有向环！
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i));
                if (i < result.size() - 1) {
                    System.out.print(" ");
                }
            }
        } else {
            System.out.println(-1);
        }
    }
}
```

# 最小生成树：所有节点的最小连通子图，即：以最小的成本（边的权值）将图中所有节点链接到一起

## [prim（节点）](https://programmercarl.com/kamacoder/0053.%E5%AF%BB%E5%AE%9D-prim.html#%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF)

- prim算法是从节点的角度采用贪心的策略每次寻找距离最小生成树最近的节点并加入到最小生成树中。

### prim三部曲

- 第一步，选距离生成树最近节点
- 第二步，最近节点加入生成树
- 第三步，更新非生成树节点到生成树的距离（即更新minDist数组）:minDist数组用来记录每一个节点距离最小生成树的最近距离

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int e = scanner.nextInt();

        // 初始化邻接矩阵，所有值初始化为一个大值，表示无穷大
        int[][] grid = new int[v + 1][v + 1];
        for (int i = 0; i <= v; i++) {
            Arrays.fill(grid[i], 10001);
        }

        // 读取边的信息并填充邻接矩阵
        for (int i = 0; i < e; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int k = scanner.nextInt();
            grid[x][y] = k;
            grid[y][x] = k;
        }

        // 所有节点到最小生成树的最小距离
        int[] minDist = new int[v + 1];
        Arrays.fill(minDist, 10001);

        // 记录节点是否在树里
        boolean[] isInTree = new boolean[v + 1];

        // Prim算法主循环
        for (int i = 1; i < v; i++) {
            int cur = -1;
            int minVal = Integer.MAX_VALUE;

            // 选择距离生成树最近的节点
            for (int j = 1; j <= v; j++) {
                if (!isInTree[j] && minDist[j] < minVal) {
                    minVal = minDist[j];
                    cur = j;
                }
            }

            // 将最近的节点加入生成树
            isInTree[cur] = true;

            // 更新非生成树节点到生成树的距离
            for (int j = 1; j <= v; j++) {
                if (!isInTree[j] && grid[cur][j] < minDist[j]) {
                    minDist[j] = grid[cur][j];
                }
            }
        }

        // 统计结果
        int result = 0;
        for (int i = 2; i <= v; i++) {
            result += minDist[i];
        }
        System.out.println(result);
        scanner.close();
    }
}
```

## [kruskal算法](https://programmercarl.com/kamacoder/0053.%E5%AF%BB%E5%AE%9D-Kruskal.html#kruskal%E7%AE%97%E6%B3%95%E7%B2%BE%E8%AE%B2)
<u>kruscal的思路：</u>

- 边的权值排序，因为要优先选最小的边加入到生成树里
- 遍历排序后的边
  - 如果边首尾的两个节点在同一个集合，说明如果连上这条边图中会出现环
  - 如果边首尾的两个节点不在同一个集合，加入到最小生成树，并把两个节点加入同一个集合

```java
import java.util.*;

class Edge {
    int l, r, val;

    Edge(int l, int r, int val) {
        this.l = l;
        this.r = r;
        this.val = val;
    }
}

public class Main {
    private static int n = 10001;
    private static int[] father = new int[n];

    // 并查集初始化
    public static void init() {
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    // 并查集的查找操作
    public static int find(int u) {
        if (u == father[u]) return u;
        return father[u] = find(father[u]);
    }

    // 并查集的加入集合
    public static void join(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return;
        father[v] = u;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int e = scanner.nextInt();
        List<Edge> edges = new ArrayList<>();
        int result_val = 0;

        for (int i = 0; i < e; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            int val = scanner.nextInt();
            edges.add(new Edge(v1, v2, val));
        }

        // 执行Kruskal算法
        //可以用优先队列优化
        edges.sort(Comparator.comparingInt(edge -> edge.val));

        // 并查集初始化
        init();

        // 从头开始遍历边
        for (Edge edge : edges) {
            int x = find(edge.l);
            int y = find(edge.r);

            if (x != y) {
                result_val += edge.val;
                join(x, y);
            }
        }
        System.out.println(result_val);
        scanner.close();
    }
}
```

# 最短路径算法：

# [dijkstra算法：在有权图（权值非负数）中求从起点到其他节点的最短路径算法](https://programmercarl.com/kamacoder/0047.%E5%8F%82%E4%BC%9Adijkstra%E6%9C%B4%E7%B4%A0.html#%E6%80%9D%E8%B7%AF)

- 贪心：每次选取离源点最近的点
- 第一步，选源点到哪个节点近且该节点未被访问过
- 第二步，该最近节点被标记访问过
- 第三步，更新非访问节点到源点的距离（即更新minDist数组）

## 朴素版

```java
public class Main {
    public static void main(String[] args) {

        int[][] grid = new int[n + 1][n + 1];   //不适用位置0

        int start = 1;
        int end = n;

        // 存储从源点到每个节点的最短距离
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        // 记录顶点是否被访问过
        boolean[] visited = new boolean[n + 1];

        minDist[start] = 0;  // 起始点到自身的距离为0

        for (int i = 1; i <= n; i++) { // 遍历所有节点

            int minVal = Integer.MAX_VALUE;
            int cur = 1;

            // 1、！！！选距离源点最近且未访问过的节点
            for (int v = 1; v <= n; ++v) {
                if (!visited[v] && minDist[v] < minVal) {
                    minVal = minDist[v];
                    cur = v;
                }
            }

            visited[cur] = true;  // 2、标记该节点已被访问

            // 3、第三步，更新非访问节点到源点的距离（即更新minDist数组）
            for (int v = 1; v <= n; v++) {
                if (!visited[v]
                        && grid[cur][v] != Integer.MAX_VALUE    //有边
                        && minDist[cur] + grid[cur][v] < minDist[v]) {  //更新minDist数组，便于后面选取离源点最近的节点
                    minDist[v] = minDist[cur] + grid[cur][v];
                }
            }
        }

        if (minDist[end] == Integer.MAX_VALUE) {
            System.out.println(-1); // 不能到达终点
        } else {
            System.out.println(minDist[end]); // 到达终点最短路径
        }
    }
}
```

## 堆优化版（使用邻接表）

```java
import java.util.*;

class Edge {
  int to;  // 邻接顶点
  int val; // 边的权重

  Edge(int to, int val) {
    this.to = to;
    this.val = val;
  }
}

class MyComparison implements Comparator<Pair<Integer, Integer>> {
  @Override
  public int compare(Pair<Integer, Integer> lhs, Pair<Integer, Integer> rhs) {
    return Integer.compare(lhs.second, rhs.second);
  }
}

class Pair<U, V> {
  public final U first;
  public final V second;

  public Pair(U first, V second) {
    this.first = first;
    this.second = second;
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();

    List<List<Edge>> grid = new ArrayList<>(n + 1);
    for (int i = 0; i <= n; i++) {
      grid.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      int p1 = scanner.nextInt();
      int p2 = scanner.nextInt();
      int val = scanner.nextInt();
      grid.get(p1).add(new Edge(p2, val));
    }

    int start = 1;  // 起点
    int end = n;    // 终点

    // 存储从源点到每个节点的最短距离
    int[] minDist = new int[n + 1];
    Arrays.fill(minDist, Integer.MAX_VALUE);

    // 记录顶点是否被访问过
    boolean[] visited = new boolean[n + 1];

    // 优先队列中存放 Pair<节点，源点到该节点的权值>
    PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(new MyComparison());

    // 初始化队列，源点到源点的距离为0，所以初始为0
    pq.add(new Pair<>(start, 0));

    minDist[start] = 0;  // 起始点到自身的距离为0

    while (!pq.isEmpty()) {
      // 1. 第一步，选源点到哪个节点近且该节点未被访问过（通过优先级队列来实现）
      // <节点， 源点到该节点的距离>
      Pair<Integer, Integer> cur = pq.poll();

      if (visited[cur.first]) continue;

      // 2. 第二步，该最近节点被标记访问过
      visited[cur.first] = true;

      // 3. 第三步，更新非访问节点到源点的距离（即更新minDist数组）
      for (Edge edge : grid.get(cur.first)) { // 遍历 cur指向的节点，cur指向的节点为 edge
        // cur指向的节点edge.to，这条边的权值为 edge.val
        if (!visited[edge.to] && minDist[cur.first] + edge.val < minDist[edge.to]) { // 更新minDist
          minDist[edge.to] = minDist[cur.first] + edge.val;
          pq.add(new Pair<>(edge.to, minDist[edge.to]));
        }
      }
    }

    if (minDist[end] == Integer.MAX_VALUE) {
      System.out.println(-1); // 不能到达终点
    } else {
      System.out.println(minDist[end]); // 到达终点最短路径
    }
  }
}
```

# [Bellman_ford 算法精讲](https://programmercarl.com/kamacoder/0094.%E5%9F%8E%E5%B8%82%E9%97%B4%E8%B4%A7%E7%89%A9%E8%BF%90%E8%BE%93I.html#%E6%80%9D%E8%B7%AF)

- 使用了动态规划的思想(松弛机制)：从所有可以到达B节点的路径中，选择最小的最为其本身的状态
- 依然是单源最短路问题，求从<u>节点1到节点n</u>的最小费用。 但本题不同之处在于<u>边的权值是有负数</u>了。
- Bellman_ford算法的核心思想是 对所有边进行松弛n-1次操作（n为节点数量），从而求得目标最短路。
- 如果 通过 A 到 B 这条边可以获得更短的到达B节点的路径，即如果 minDist[B] > minDist[A] + value，那么我们就更新
  minDist[B] = minDist[A] + value ，这个过程就叫做 “松弛” 。

## 原始版

```java
public class Main {

    // Define an inner class Edge
    static class Edge {
        int from;
        int to;
        int val;

        public Edge(int from, int to, int val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // Input processing
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int val = sc.nextInt();
            edges.add(new Edge(from, to, val));
        }

        // Represents the minimum distance from the current node to the original node
        int[] minDist = new int[n + 1];

        // Initialize the minDist array
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // Starts the loop to RELAX all edges n - 1 times to update minDist array
        for (int i = 1; i < n; i++) {

            for (Edge edge : edges) {
                // Updates the minDist array
                if (minDist[edge.from] != Integer.MAX_VALUE && (minDist[edge.from] + edge.val) < minDist[edge.to]) {
                    minDist[edge.to] = minDist[edge.from] + edge.val;
                }
            }
        }

        // Outcome printing
        if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected");
        } else {
            System.out.println(minDist[n]);
        }
    }
}
```

## 队列优化算法（SPFA）

- 只需要对<u>上一次松弛的时候更新过的节点作为出发节点所连接的边</u>进行松弛就够了。
- 在有环且只有正权回路的情况下，即使元素重复加入队列，最后，也会因为 所有边都松弛后，节点数值（minDist数组）不在发生变化了
  而终止。

```java
public class Main {

    // Define an inner class Edge
    static class Edge {
        int from;
        int to;
        int val;

        public Edge(int from, int to, int val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // Input processing
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int val = sc.nextInt();
            graph.get(from).add(new Edge(from, to, val));
        }

        // Declare the minDist array to record the minimum distance form current node to the original node
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // Declare a queue to store the updated nodes instead of traversing all nodes each loop for more efficiency
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        // Declare a boolean array to record if the current node is in the queue to optimise the processing
        boolean[] isInQueue = new boolean[n + 1];

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            isInQueue[curNode] = false; // Represents the current node is not in the queue after being polled
            for (Edge edge : graph.get(curNode)) {
                if (minDist[edge.to] > minDist[edge.from] + edge.val) { // Start relaxing the edge
                    minDist[edge.to] = minDist[edge.from] + edge.val;
                    if (!isInQueue[edge.to]) { // Don't add the node if it's already in the queue
                        queue.offer(edge.to);
                        isInQueue[edge.to] = true;
                    }
                }
            }
        }

        // Outcome printing
        if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected");
        } else {
            System.out.println(minDist[n]);
        }
    }
}
```

# [bellman_ford之判断负权回路](https://programmercarl.com/kamacoder/0095.%E5%9F%8E%E5%B8%82%E9%97%B4%E8%B4%A7%E7%89%A9%E8%BF%90%E8%BE%93II.html#%E6%80%9D%E8%B7%AF)

- 图中可能出现负权回路。（正权因为会增加总权值，不会被选择）
- *负权回路*是指回路的总权值为负，这样的回路使得通过反复经过回路中的道路，理论上可以无限地减少总成本或无限地增加总收益。
- 有负权回路的情况下，一直都会有更短的最短路，所以 松弛 第n次，minDist数组 也会发生改变。

```java
public class Main {

    // Define an inner class Edge
    static class Edge {
        int from;
        int to;
        int val;

        public Edge(int from, int to, int val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // Input processing
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int val = sc.nextInt();
            edges.add(new Edge(from, to, val));
        }

        // Represents the minimum distance from the current node to the original node
        int[] minDist = new int[n + 1];

        // Initialize the minDist array
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // Starts the loop to RELAX all edges n times to update minDist array
        // (n次而不是n-1次是为了判断多relax一次后路径最小值是否稳定以判断是否存在负权环
        boolean flag = false;
        for (int i = 1; i <= n; i++) {
            for (Edge edge : edges) {
                if (i < n) {    //前n-1次放松边
                    // Updates the minDist array
                    if (minDist[edge.from] != Integer.MAX_VALUE && (minDist[edge.from] + edge.val) < minDist[edge.to]) {
                        minDist[edge.to] = minDist[edge.from] + edge.val;
                    }
                } else {    //第n次检验是否稳定
                    if (minDist[edge.from] != Integer.MAX_VALUE && (minDist[edge.from] + edge.val) < minDist[edge.to])
                        flag = true;
                }
            }
        }

        // Outcome printing
        if (flag) {
            System.out.println("circle");
        } else if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected");
        } else {
            System.out.println(minDist[n]);
        }
    }
}
```

# [bellman_ford之单源有限最短路](https://programmercarl.com/kamacoder/0096.%E5%9F%8E%E5%B8%82%E9%97%B4%E8%B4%A7%E7%89%A9%E8%BF%90%E8%BE%93III.html#%E6%80%9D%E8%B7%AF)

- 最多*经过*k个城市（不包括起点）的条件下，而不是一定经过k个城市(bellman_ford,dj)，也可以经过的城市数量比k小，但要最短的路径。
- 动归的思想分析：只要是从所有入度的点上获取最小minDist即可,因此松弛k+1次即可
- 本题可以有负权回路，说明只要多做松弛，结果是会变的。
- 本题要求最多经过k个节点，对松弛次数是有限制的。

```java
public class Main {
    // 基于Bellman_for一般解法解决单源最短路径问题
    // Define an inner class Edge
    static class Edge {
        int from;
        int to;
        int val;

        public Edge(int from, int to, int val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // Input processing
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Edge> graph = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int val = sc.nextInt();
            graph.add(new Edge(from, to, val));
        }

        int src = sc.nextInt();
        int dst = sc.nextInt();
        int k = sc.nextInt();

        int[] minDist = new int[n + 1];
        int[] minDistCopy;

        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[src] = 0;

        for (int i = 0; i < k + 1; i++) { // Relax all edges k + 1 times
            minDistCopy = Arrays.copyOf(minDist, n + 1);
            for (Edge edge : graph) {
                int from = edge.from;
                int to = edge.to;
                int val = edge.val;
                // Use minDistCopy to calculate minDist
                if (minDistCopy[from] != Integer.MAX_VALUE && minDist[to] > minDistCopy[from] + val) {
                    minDist[to] = minDistCopy[from] + val;
                }
            }
        }

        // Output printing
        if (minDist[dst] == Integer.MAX_VALUE) {
            System.out.println("unreachable");
        } else {
            System.out.println(minDist[dst]);
        }
    }
}
```

# [Floyd 算法精讲](https://programmercarl.com/kamacoder/0097.%E5%B0%8F%E6%98%8E%E9%80%9B%E5%85%AC%E5%9B%AD.html#%E6%80%9D%E8%B7%AF)

## 多源最短路问题

- Floyd 算法对边的权值正负没有要求，都可以处理。
- 动态规划：grid[ i ] [ j ] [ k ] = min{grid[ i ] [ k ] [k-1] + grid[ k ] [ j ] [k-1], grid[ i ] [ j ] [k-1]}

```java
public class FloydBase {

    // public static int MAX_VAL = Integer.MAX_VALUE;
    public static int MAX_VAL = 10005; // 边的最大距离是10^4(不选用Integer.MAX_VALUE是为了避免相加导致数值溢出)

    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入N M");
        int n = sc.nextInt();
        int m = sc.nextInt();

        System.out.println("2.输入M条边");

        // ① dp定义（grid[i][j][k] 节点i到节点j 可能经过节点K（k∈[1,n]））的最短路径
        int[][][] grid = new int[n + 1][n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k <= n; k++) {
                    grid[i][j][k] = grid[j][i][k] = MAX_VAL; // 其余设置为最大值
                }
            }
        }

        // ② dp 推导：grid[i][j][k] = min{grid[i][k][k-1] + grid[k][j][k-1], grid[i][j][k-1]}
        while (m-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            grid[u][v][0] = grid[v][u][0] = weight; // 初始化（处理k=0的情况） ③ dp初始化
        }

        // ④ dp推导：floyd 推导
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    grid[i][j][k] = Math.min(grid[i][k][k - 1] + grid[k][j][k - 1], grid[i][j][k - 1]);
                    //grid[i][j] = Math.min(grid[i][j], grid[i][k] + grid[k][j]);
                    //由于本层更新后仍旧是求最小，不会对结果发生影响，因此可以直接在原数组做赋值
                    //本质上是利用
                }
            }
        }

        System.out.println("3.输入[起点-终点]计划个数");
        int x = sc.nextInt();

        System.out.println("4.输入每个起点src 终点dst");

        while (x-- > 0) {
            int src = sc.nextInt();
            int dst = sc.nextInt();
            // 根据floyd推导结果输出计划路径的最小距离
            if (grid[src][dst][n] == MAX_VAL) {
                System.out.println("-1");
            } else {
                System.out.println(grid[src][dst][n]);
            }
        }
    }
}
```

# [A * 算法精讲 （A star算法）](https://programmercarl.com/kamacoder/0126.%E9%AA%91%E5%A3%AB%E7%9A%84%E6%94%BB%E5%87%BBastar.html#%E6%80%9D%E8%B7%AF)

- 启发式函数 要影响的就是队列里元素的排序！
- 每个节点的权值为F，给出公式为：F = G + H
- G：起点达到目前遍历节点的距离
- H：目前遍历的节点到达终点的距离

```java
public class AStar {
  @Data
  static class Knight {
    private int x, y;
    private int g, h, f;

    public void computeH(int b1, int b2) {
      this.h = (x - b1) * (x - b1) + (y - b2) * (y - b2);
    }

    public void computeF() {
      this.f = this.g + this.h;
    }
  }

  static class MyComparator implements Comparator<Knight> {
    @Override
    public int compare(Knight o1, Knight o2) {
      return Integer.compare(o1.f, o2.f);
    }
  }

  int[][] dir = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
  int[][] moves = new int[1001][1001];
  PriorityQueue<Knight> pq = new PriorityQueue<Knight>(new MyComparator());

  public void aStar(int a1, int a2, int b1, int b2) {
    Knight cur = new Knight(); //初始化
    cur.setX(a1);
    cur.setY(a2);
    cur.setG(0);
    cur.computeH(b1, b2);
    cur.computeF();

    Knight next = new Knight(); //初始化

    pq.add(cur);
    while (!pq.isEmpty()) {
      cur = pq.poll();
      if (cur.x == b1 && cur.y == b2) break;
      for (int i = 0; i < 8; i++) {
        next.setX(cur.getX() + dir[i][0]);
        next.setY(cur.getY() + dir[i][1]);
        if (next.x < 1 || next.x > 1000 || next.y < 1 || next.y > 1000) continue;
        if (moves[next.getX()][next.getY()] != 0) {
          moves[next.getX()][next.getY()] = moves[cur.getX()][cur.getY()] + 1;

          //F = G + H
          //G：起点达到目前遍历节点的距离
          //H：目前遍历的节点到达终点的距离
          next.setG(cur.getG() + 5);
          next.computeH(b1, b2);
          next.computeF();
          pq.add(next);
        }
      }

    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    free free = new free();
    while (n-- > 0) {
      int a1 = sc.nextInt();
      int a2 = sc.nextInt();
      int b1 = sc.nextInt();
      int b2 = sc.nextInt();
      free.aStar(a1, a2, b1, b2);
      while (!free.pq.isEmpty()) free.pq.poll();
      System.out.println(free.moves[b1][b2]);
    }


  }
}
```