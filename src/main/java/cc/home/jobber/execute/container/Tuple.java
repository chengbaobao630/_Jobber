package cc.home.jobber.execute.container;

/**
 * Created by Administrator on 2017/1/14 0014.
 */
public interface Tuple<K,V> {

    V get(K k);

    void set(K k,V v);
}
