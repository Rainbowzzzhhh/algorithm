package Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author rainbow
 * @time 2025-03-05 11:37
 * @description ...
 */

/*
    稳定性：除了被排序的数据以外，其他数据的顺序是否会发生变化
    原地排序：是否使用额外空间辅助
    内部排序：数据元素全部放在内存中的排序
    外部排序：数据元素太多不能同时放在内存中，根据排序过程的要求不能在内外存之间移动数据的排序
 */

public class Sort {

    //TODO 插入排序
    //插入排序（稳定）
    //算法流程：每次从后往前遍历，找到最小的数放在最前面，循环n-1次
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    //希尔排序（不稳定）
    //算法流程：希尔排序在直接排序之前，进行预排列，将某些极端数据更快的排列到数列前面，构成一个接近排列好的序列，最后再进行一次直接插入排序
    //
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    //TODO 选择排序
    //选择排序（不稳定）
    //算法流程: 每一次遍历待排序的数据元素从中选出最小（或最大）的一个元素，存放在序列的起始（或者末尾）位置，直到全部待排序的数据元素排完
    //  这里我们还可以对直接选择排序做一个优化：每次遍历待排序数据找出最大和最小的数据，分别排列到序列起始和末尾
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) swap(arr, i, minIndex);
        }
    }

    //堆排序（不稳定）堆是所有孩子都比双亲小\大的完全二叉树

    //原则：
    //先将原数组建成堆，需要注意的是排升序要建大堆，排降序建小堆
    //注：以大堆为例
    //建堆：
    //一个根节点与子节点数据如果不符合大堆结构，那么则对根节点数据进行向下调整，而向下调整的前提是左右子树也符合大堆结构,所以从堆尾数据的根节点位置开始向下调整建大堆
    //排序：
    //大堆堆顶数据一定是待排数据中最大的，将堆顶数据与堆尾数据交换,交换后将除堆尾数据看成新堆，对现堆顶数据进行向下调整成大堆,以此循环直至排列完毕
    //向下调整：
    //找到子节点中的较大数据节点比较，如果父节点数据比大子节点小则交换，直到不符合则停止向下交换，此时再次构成了一个大堆结构
    //具体堆排序详解：堆排序超详解
    //具体堆排序详解：https://blog.csdn.net/CS_z_jun/article/details/121298048?spm=1001.2014.3001.5501

    public static void heapSort(int[] arr) {
        int n = arr.length;
        //建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(arr, i, 0);
            heapify(arr, 0, i);// i 是新的堆大小
        }
    }


    private static void heapify(int[] arr, int i, int n) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        //leftChild=root*2+1;
        //rightChild=root*2+2;
        //(leftChild+rightChild)-1)/2=root;

        //左子存在且大于当前节点i,标记为最大
        if (left < n && arr[left] > arr[largest]) largest = left;

        //右子存在且大于当前节点i,标记为最大
        if (right < n && arr[right] > arr[largest]) largest = right;

        //若为最大堆则不操作，否则调整为最大堆
        if (largest != i) {
            //将子树的最大值与当前最大节点互换
            swap(arr, i, largest);
            //****因为之前已经建好最大堆，因此只用管被交换的孩子的子树****
            heapify(arr, n, largest);
        }
    }

//TODO 交换排序

    //冒泡排序 o(n^2) 稳定 o(1)
    //每次遍历待排序数组，对相邻数据进行比较，不符合排序要求则交换
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    //快速排序（不稳定）
    //任取待排序元素序列中的某元素作为基准值，按照该排序码将待排序集合分割成两子序列
    //左子序列中所有元素均小于基准值，右子序列中所有元素均大于基准值 然后最左右子序列重复该过程，直到所有元素都排列在相应位置上为止

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

//TODO 归并排序

    //归并排序（稳定）
    //算法流程：将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, temp);
        mergeSort(arr, mid + 1, right, temp);
        merge(arr, left, mid, right, temp);
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++]; //取小的放到temp[k]
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, left, k);
    }

//TODO 计数排序
    //计数排序（稳定）适用于数据比较集中的数据集
    //时间复杂度：O(MAX(N,range))
    //空间复杂度：O(range)
    //在排序数组中找到最大最小的数据，算出对应范围并创建对应长度个数组用来计数，遍历排序数组，根据每个出现的数据值与计数数组下标构建的相对映射关系进行
    //统计数据出现次数，最后将统计的出的数据按次序赋值给原数组

    public static void countingSort(int[] arr) {
        if (arr.length == 0) return;

        int max = arr[0], min = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int[] count = new int[max - min + 1];
        for (int num : arr) count[num - min]++;

        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                arr[index++] = i + min;
            }
        }
    }

    //TODO 桶排序（稳定）
    //桶排序是一种分布式排序算法，其核心思想是将元素分到多个有序的桶中，每个桶单独排序后合并
    public static void bucketSort(int[] arr) {
        int bucketSize = 10;
        int min = arr[0], max = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
            else if (num > max) max = num;
        }

        int bucketCount = (max - min) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int num : arr) {
            buckets.get((num - min) / bucketSize).add(num);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }

    //TODO 基数排序（稳定）
    //基数排序（Radix Sort）是一种非比较型整数排序算法，其核心思想是：
    //  按位分割：将整数按位数切割成不同的数字
    //  逐位排序：从最低有效位（LSD）到最高有效位（MSD），依次对每一位进行稳定排序
    //  组合结果：每次排序结果作为下次排序的输入，最终得到有序序列
    //  int[] count = new int[10];
    //  for (int num : arr) count[(num / exp) % 10]++;
    //  for (int i = 1; i < 10; i++) count[i] += count[i - 1];
    //  count[i]表示该一共有多少个小于等于该数的数，利用了稳定性


    public static void radixSort(int[] arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSortByDigit(arr, exp);
        }
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) if (num > max) max = num;
        return max;
    }

    private static void countSortByDigit(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];

        for (int num : arr) count[(num / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    // 公共交换方法
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 测试代码
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        // 调用不同的排序方法测试
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
//        shellSort(arr);
        // mergeSort(arr);
//         quickSort(arr);
        // heapSort(arr);
        // countingSort(arr);
        // bucketSort(arr);
        radixSort(arr);

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
