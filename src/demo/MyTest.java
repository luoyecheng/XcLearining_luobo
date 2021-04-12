package demo;

import org.junit.Test;

import java.util.*;

public class MyTest {
    public MyTest(){}

    class ListNode{
        private int value;
        private ListNode next;
        public ListNode(int val){this.value=val;}
    }

    public ListNode reverse(ListNode node){
        ListNode pre=null;
        ListNode cur=node;
        while(cur!=null){
            ListNode pos=cur.next;
            cur.next=pre;
            pre=cur;
            cur=pos;
        }
        return pre;
    }

    public ListNode sum(ListNode node1,ListNode node2){
        node1=reverse(node1);
        node2=reverse(node2);
        ListNode ans=node1;
        ListNode pre=null;
        int flag=0;
        while(node1!=null&&node2!=null){
            pre=node1;
            node1.value+=node2.value+flag;
            flag=0;
            if(node1.value>9){
                flag=1;
                node1.value%=10;
            }
            node1=node1.next;
            node2=node2.next;
        }
        if(node2!=null)
            pre.next=node2;
        while(pre.next!=null&&flag==1){
            pre.next.value++;
            flag=0;
            if(pre.next.value>9){
                flag=1;
                pre.next.value%=10;
            }
            pre=pre.next;
        }
        if(flag==1){
            ListNode node=new ListNode(1);
            pre.next=node;
        }
        ans=reverse(ans);
        return ans;
    }

    private int check(ListNode node1,ListNode node2){
        ListNode t1=node1,t2=node2;
        while(t1!=null&&t2!=null){
            t1=t1.next;
            t2=t2.next;
        }
        if(t1==null&&t2==null){
            t1=node1;
            t2=node2;
            while(t1!=null){
                if(t1.value>t2.value)
                    return 1;
                if(t2.value>t1.value)
                    return -1;
                t1=t1.next;
                t2=t2.next;
            }
            return 0;
        }
        if(t1==null)
            return -1;
        return 1;
    }

    public ListNode min(ListNode node1,ListNode node2){
        int check = check(node1, node2);
        if(check==0)
            return new ListNode(0);
        if(check<0){
            ListNode temp=node2;
            node2=node1;
            node1=temp;
        }
        node1=reverse(node1);
        node2=reverse(node2);
        ListNode ans=node1;
        int flag=0;
        while(node1!=null&&node2!=null){
            node1.value-=flag;
            node1.value-=node2.value;
            flag=0;
            if(node1.value<0){
                flag=1;
                node1.value+=10;
            }
            node1=node1.next;
            node2=node2.next;
        }
        while(flag==1){
            node1.value-=flag;
            flag=0;
            while(node1.value<0){
                flag=1;
                node1.value+=10;
            }
            node1=node1.next;
        }
        ans=reverse(ans);
        while(ans.value==0)
            ans=ans.next;
        if(check<0)
            ans.value*=(-1);
        return ans;
    }

    public ListNode minus(ListNode node1,ListNode node2){
        if(node1.value<0){
            if(node2.value<0){
                node1.value*=(-1);
                node2.value*=(-1);
                return min(node2,node1);
            }
            node1.value*=(-1);
            ListNode sum = sum(node1, node2);
            sum.value*=(-1);
            return sum;
        }
        if(node2.value<0){
            node2.value*=(-1);
            return sum(node1,node2);
        }
        return min(node1, node2);
    }


    @Test
    public void test(){
        int[] a1=new int[]{1,2,3,4,5,6,7};
        int[] a2=new int[]{2,3,4,5,6,7,9};
        ListNode node1=new ListNode(a1[0]);
        ListNode t1=node1;
        for(int i=1;i<a1.length;i++){
            ListNode node=new ListNode(a1[i]);
            t1.next=node;
            t1=t1.next;
        }
        ListNode node2=new ListNode(a2[0]);
        ListNode t2=node2;
        for(int i=1;i<a2.length;i++){
            ListNode node=new ListNode(a2[i]);
            t2.next=node;
            t2=t2.next;
        }
        ListNode sum = sum(node1, node2);
        while(sum!=null){
            System.out.print(sum.value);
            sum=sum.next;
        }
        System.out.println();
        System.out.println(1234567+2345679);
    }

    @Test
    public void test_min(){
        Random random=new Random();
        ListNode node1=new ListNode(random.nextInt(9)+1);
        ListNode node2=new ListNode(random.nextInt(9)+1);
        ListNode t1=node1;
        ListNode t2=node2;
        for(int i=0;i<10;i++){
            ListNode n1=new ListNode(random.nextInt(10));
            ListNode n2=new ListNode(random.nextInt(10));
            t1.next=n1;
            t2.next=n2;
            t1=t1.next;
            t2=t2.next;
        }
        ListNode q1=node1;
        ListNode q2=node2;
        System.out.println("node1:");
        while(q1!=null){
            System.out.print(q1.value);
            q1=q1.next;
        }
        System.out.println();
        System.out.println("node2:");
        while(q2!=null){
            System.out.print(q2.value);
            q2=q2.next;
        }
        System.out.println();
        System.out.println("result:");
        ListNode minus = minus(node1, node2);
        while(minus!=null){
            System.out.print(minus.value);
            minus=minus.next;
        }
    }

    public static void main(String[] args) {
    }
}
