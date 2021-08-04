package test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<k,v> extends LinkedHashMap<k,v> {

    private int capacity; //缓存数量

    public LRU(int capacity) {
        super(capacity,075,true); // true:最近最少使用    false：最近新使用
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<k,v> eldest) {
        return super.size()>capacity;
    }

    public static void main(String[] args) {
        LRU lru = new LRU(3);
        lru.put(1,"一");
        lru.put(2,"二");
        lru.put(3,"三");
        System.out.println(lru.keySet());
        lru.get(2);
        System.out.println(lru.keySet());
        lru.put(4,"四");
        System.out.println(lru.keySet());
        lru.put(5,"五");
        System.out.println(lru.keySet());
    }
}
