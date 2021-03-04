package demo;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<K,V> extends LinkedHashMap<K,V> {
    private final int CACHE_SIZE;

    public LRU(int cache_size) {
        super((int)Math.ceil(cache_size/0.75)+1,0.75f,true);
        CACHE_SIZE = cache_size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>CACHE_SIZE;
    }

}
