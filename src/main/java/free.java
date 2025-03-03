import lombok.Data;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author rainbow
 * @time 2025-03-02 16:06
 * @description ...
 */

public class free {
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
