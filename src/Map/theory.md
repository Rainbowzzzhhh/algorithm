# 并查集理论基础

https://programmercarl.com/kamacoder/%E5%9B%BE%E8%AE%BA%E5%B9%B6%E6%9F%A5%E9%9B%86%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html#%E5%B9%B6%E6%9F%A5%E9%9B%86%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80

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

# 拓扑排序精讲:给出一个<u>有向图</u>，把这个有向图转成线性的排序就叫拓扑排序。

https://programmercarl.com/kamacoder/0117.%E8%BD%AF%E4%BB%B6%E6%9E%84%E5%BB%BA.html#%E6%8B%93%E6%89%91%E6%8E%92%E5%BA%8F%E7%9A%84%E8%83%8C%E6%99%AF

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

        if (result.size() == n) {
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

# dijkstra算法：在有权图（权值非负数）中求从起点到其他节点的最短路径算法

https://programmercarl.com/kamacoder/0047.%E5%8F%82%E4%BC%9Adijkstra%E6%9C%B4%E7%B4%A0.html#%E6%80%9D%E8%B7%AF

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

            // 1、选距离源点最近且未访问过的节点
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
                        && grid[cur][v] != Integer.MAX_VALUE 
                        && minDist[cur] + grid[cur][v] < minDist[v]) {
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

## 堆优化版
```java
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

# 最小生成树：所有节点的最小连通子图，即：以最小的成本（边的权值）将图中所有节点链接到一起

## prim（节点）：

https://programmercarl.com/kamacoder/0053.%E5%AF%BB%E5%AE%9D-prim.html#%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF

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




