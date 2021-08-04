package test;


import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 1. 限制大小， map的最大size是1024， 超过1024后，就淘汰掉最久没有访问的kv 键值对， 当淘汰时，需要调用一个callback   lruCallback(K key, V value)
 * 2. 具备超时功能， 当键值对1小时内没有被访问， 就被淘汰掉, 当淘汰时， 需要调用一个callback   timeoutCallback(K key, V value);
 */
public class CacheMap<K,V> extends AbstractMap<K,V>
        implements Map<K,V>, Cloneable, Serializable {

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    public static void main(String[] args) {
        /*
        思路：
            1.参考hashMap去实现AbstractMap的entrySet方法； 可参考guava cache的思路
            2.构造函数中，默认设置size值最大值设置为1024。Node<K,V>[] 使用两组，一组记录实际数据，一组记录K和时间并按照时间排序
            3.扩容因子设置为1，重写扩容方法，不做实际扩容，调用lruCallback(K key, V value)，删除最久没有访问的kv 键值对
            4.当有访问时，set和put，第二组记录时间的kv对，重新排序，将有访问的kv移位（如果不做排序，此处也可以用队列来实现）。
            5.lruCallback(K key, V value)方法中，遍历第二组记录时间的kv对，获取最后一条没有访问的数据并删除
            6.写一个定时任务timeoutCallback，因为不是service，那么可以使用while(true)的循环来实现，在构造函数中调用
            7.timeoutCallback方法遍历第二组记录时间的kv对。如果当条数据超过一小时，则删除，继续遍历；反之，结束遍历。
         */
    }
}
