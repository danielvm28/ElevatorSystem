package model;

/**
 * HashTableI
 */
public interface HashTableI<K,V> {

    public void put(K key, V value);

    public V get(K key);

    public void clear();
    
    public int hashFunction(K key);
}