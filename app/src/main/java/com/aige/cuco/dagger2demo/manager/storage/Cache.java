package com.aige.cuco.dagger2demo.manager.storage;

import android.support.annotation.NonNull;

import java.util.Set;

/**
 * @package com.aige.cuco.dagger2demo.manager.storage
 * @fileName Cache
 * @date 2018/11/14
 * @describe
 * @email shenzhencuco@gmail
 */
public interface Cache<K, V> {
    /**
     * 返回使用的缓存类型
     */
    interface Factory {
        @NonNull
        Cache build(CacheType type);
    }

    /**
     * 返回当前缓存已占用的总size
     *
     * @return
     */
    int size();

    /**
     * 获取总容量
     *
     * @return
     */
    int getMaxSize();

    /**
     * 获取指定的值
     *
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 存入缓存
     *
     * @param key
     * @param value
     * @return
     */
    V put(K key, V value);

    /**
     * 移除指定的对象
     *
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * 判断是否包含当前文件
     *
     * @param key
     * @return
     */
    boolean containsKey(K key);

    /**
     * 返回Key的集合
     *
     * @return
     */
    Set<K> keySet();

    /**
     * 清理缓存
     */
    void clear();
}
