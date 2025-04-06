package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author rainbow
 * @time 2025-04-02 10:48
 * @description ...
 */
import java.util.*;

import java.io.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Test1 {
    public static final int MAXN=200001;
    public static HashMap<Integer,Integer> map=new HashMap<>();
    public static HashSet<Integer> set=new HashSet<>();
    public static int[] father=new int[MAXN];
    public static int[][] question=new int[MAXN][3];
    public static int[][] edge=new int[MAXN][2];
    public static HashMap<Long,Integer> stringmap=new HashMap<>();
    public static int base=499999;
    public static int n;
    public static int m;
    public static int q;
    public static long tohashcode(int a,int b){
        //类似字符串hash让其自然溢出同样也是做赌狗，如果过不去可以试着换下base的值(建议换成一个大质数)
        return (long)a*base*base+b*base;
    }
    //一定要做扁平化处理不然会超时
    public static int find(int v){
        if(father[v]!=v){
            father[v]=find(father[v]);
        }
        return father[v];
    }
    public static void union(int l,int r){
        int fl=find(l),fr=find(r);
        if(fl!=fr) father[fl]=fr;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        String[] str=br.readLine().split(" ");
        n=Integer.parseInt(str[0]);
        m=Integer.parseInt(str[1]);
        q=Integer.parseInt(str[2]);
        for(int i=0;i<m;i++){
            str=br.readLine().split(" ");
            int V1=Integer.parseInt(str[0]),V2=Integer.parseInt(str[1]);
            edge[i][0]=Math.min(V1,V2);
            edge[i][1]=Math.max(V1,V2);
            set.add(V1);
            set.add(V2);
        }
        for(int i=0;i<q;i++){
            str=br.readLine().split(" ");
            question[i][0]=Integer.parseInt(str[0]);
            int V1=Integer.parseInt(str[1]),V2=Integer.parseInt(str[2]);
            question[i][1]=Math.min(V1,V2);
            question[i][2]=Math.max(V1,V2);
            set.add(V1);
            set.add(V2);
        }
        //离散化(给人员重新编号)
        ArrayList<Integer> list=new ArrayList<>(set);
        Collections.sort(list);
        for(int i=0;i<list.size();i++){
            map.put(list.get(i),i+1);
        }
        for(int i=0;i<=list.size();i++){
            father[i]=i;
        }
        //将初始时的边转换为一个long类型数据并放入哈希表中
        for(int i=0;i<m;i++){
            int a=map.get(edge[i][0]),b=map.get(edge[i][1]);
            Long hashcode=tohashcode(a,b);
            stringmap.put(hashcode,i);
        }
        //离线后还需要真正运行的操作索引集合
        ArrayList<Integer> edge_add=new ArrayList<>();
        //重前往后遍历时在处理同一条边删除两次时只处理第一次
        for(int i=0;i<q;i++){
            if(question[i][0]==1){
                int a=map.get(question[i][1]),b=map.get(question[i][2]);
                long hashcode=tohashcode(a,b);
                //判断该边是否存在且没有被处理掉
                if(stringmap.get(hashcode)!=null){
                    edge_add.add(i);
                    stringmap.remove(hashcode);
                }
            }else{
                edge_add.add(i);
            }
        }
        //现在stringmap中只存在不会被删除的边获取出来
        for(Map.Entry<Long,Integer> entry:stringmap.entrySet()){
            int index=entry.getValue();
            int a=map.get(edge[index][0]),b=map.get(edge[index][1]);
            union(a,b);
        }
        ArrayList<String> ans=new ArrayList<>();
        //从下往上遍历
        for(int i=edge_add.size()-1;i>=0;i--){
            int index=edge_add.get(i);
            int a=map.get(question[index][1]),b=map.get(question[index][2]);
            if(question[index][0]==2){
                String R=find(a)==find(b)?"Yes":"No";
                ans.add(R);
            }else{
                union(a,b);
            }
        }
        for(int i=ans.size()-1;i>=0;i--){
            bw.write(ans.get(i)+"\n");
        }
        bw.close();
        br.close();
    }
}

