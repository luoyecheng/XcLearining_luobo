package demo.MyList;

public class SkipListNode<T> {
    public int key;
    public T value;
    public SkipListNode<T> up,down,left,right;  //上下左右四个指针

    public static final int HEAD_KEY=Integer.MIN_VALUE;
    public static final int TAIL_KEY=Integer.MAX_VALUE;

    public SkipListNode(int k,T v){
        key=k;
        value=v;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean equals(Object o){
        if(this==o)
            return true;
        if(o==null)
            return false;
        if(!(o instanceof SkipListNode<?>))
            return false;
        SkipListNode<T> ent;
        try{
            ent=(SkipListNode<T>) o;
        }catch (ClassCastException ex){
            return false;
        }
        return (ent.getKey()==key)&&(ent.getValue()==value);
    }

    @Override
    public String toString() {
        return "SkipListNode{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
