package demo.MyString;

public class RabinKarp {
    private String pat;
    private long patHash;
    private int M;
    private long Q;
    private int R=256;
    private long RM;

    public RabinKarp(String pat){
        this.pat=pat;           //保存模式字符串
        this.M=pat.length();
        Q=longRandomPrime();
        RM=1;
        for(int i=1;i<=M-1;i++)
            RM=(R*RM)%Q;
        patHash =hash(pat,M);
    }

    private long hash(String key,int M){
        //计算key[0...M-1]的散列值
        long h=0;
        for(int j=0;j<M;j++)
            h=(R*h+key.charAt(j))%Q;
        return h;
    }

    private long longRandomPrime(){
        return -1;
    }

    private boolean checkMatching(String txt,int index){
        return pat.equals(txt.substring(index,pat.length()));
    }

    private int search(String txt){
        //在文中查找相等的散列值
        int N=txt.length();
        long txtHash=hash(txt,M);
        if(patHash ==txtHash&&checkMatching(txt,0)) return 0;
        for(int i=M;i<N;i++){
            //减去第一个数字，加上最后一个数字，再次检查匹配
            txtHash=(txtHash+Q-RM*txt.charAt(i-M)%Q)%Q;
            txtHash=(txtHash*R+txt.charAt(i))%Q;
            if(patHash ==txtHash)
                if(checkMatching(txt,i-M+1))
                    return i-M+1;   //找到匹配
        }
        return N;
    }


}
