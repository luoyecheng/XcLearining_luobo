package demo.IndexMinQueue;

public class IndexMinPriorityQueue<T extends Comparable> {
    private T[] items;
    //保存每个元素在items数组中的索引，pq数组需要堆有序
    private int[] pq;
    //保存pq的逆序，pq值作为索引，pq的索引作为值
    private int[] qp;
    //记录堆中元素个数
    private int n;
    //创建容量为capacity的indexMinPriorityQueue
    public IndexMinPriorityQueue(int capacity) {
        this.items = (T[])new Comparable[capacity + 1];
        this.pq = new int[capacity + 1];
        this.qp = new int[capacity + 1];
        this.n = 0;
        //默认情况下，队列中没有任何存储数据，让qp中的元素都为-1
        for(int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }
    //获取队列中元素的个数
    public int size() {
        return n;
    }
    //判断队列是否为空
    public boolean isEmpty() {
        return n == 0;
    }
    //判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) < 0;
    }
    //交换堆中索引i处元素和索引j处元素
    private void swap(int i, int j) {
        //交换pq中的数据
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
        //更新qp中的数据
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
    //判断k对应的元素是否存在
    public boolean contains(int k) {
        return qp[k] != -1;
    }
    //最小元素关联索引
    public int minIndex() {
        return pq[1];
    }
    //删除队列中最小的元素，并返回该元素关联的索引
    public int delMin() {
        //获取最小元素的关联索引
        int minIndex = pq[1];
        //交换pq中索引1处和最大索引处的元素
        swap(1, n);
        //删除qp中对应的内容
        qp[pq[n]] = -1;
        //删除items中对应的内容
        items[minIndex] = null;
        //删除pq中最大索引处的内容
        pq[n] = -1;
        //元素个数-1
        n--;
        //下沉操作
        sink(1);
        return minIndex;
    }
    //删除索引i关联的元素
    public void delete(int i) {
        //找到i在pq中的索引
        int k = qp[i];
        //交换pq中索引k处的值和索引n处的值
        swap(k, n);
        //删除qp中的内容
        qp[pq[n]] = -1;
        //删除items中的内容
        items[k] = null;
        //删除pq中的内容
        pq[n] = -1;
        //元素的数量-1
        n--;
        //堆的调整
        sink(k);
        swim(k);
    }
    //往队列中插入一个元素，并关联索引
    public void insert(int i, T t) {
        //判断i是否已经被关联，如果已经被关联，则不允许插入
        if(contains(i)) {
            return;
        }
        //元素个数增加
        n++;
        //把数据存储到items对应的i位置
        items[i] = t;
        //把i存储到pq中
        pq[n] = i;
        //通过qp来记录pq中的i
        qp[i] = n;
        //通过堆上浮进行堆的调整
        swim(n);
    }
    //把与索引i关联的元素修改为t
    public void changeItem(int i, T t) {
        //修改items数组中i位置的元素为t
        items[i] = t;
        //找到i在pq中出现的位置
        int k = qp[i];
        //堆调整
        sink(k);
        swim(k);
    }
    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        while(k > 1) {
            if(less(k, k / 2)) {
                swap(k, k / 2);
            }
            k /= 2;
        }
    }
    //使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k) {
        while(2 * k <= n) {
            //找到子节点中的较小值
            int min;
            if(2 * k + 1 <= n) {
                if(less(2 * k, 2 * k + 1)) {
                    min = 2 * k;
                } else {
                    min = 2 * k + 1;
                }
            } else {
                min = 2 * k;
            }
            //比较当前节点和较小值
            if(less(k, min)) {
                break;
            }
            swap(k, min);
            k = min;
        }
    }
}
