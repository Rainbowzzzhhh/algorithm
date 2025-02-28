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

# 拓扑排序精讲:给出一个<u>有向图</u>，把这个有向图转成线性的排序 就叫拓扑排序。
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

