package model;

public class HashNode<K,V> {
    private K key;
    private V value;

    private HashNode<K, V> next;
    private HashNode<K, V> previous;

    public HashNode(K key, V value) {
        this.setKey(key);
        this.setValue(value);
        setNext(null);
        setPrevious(null);
    }

    public HashNode<K, V> getPrevious() {
        return previous;
    }

    public void setPrevious(HashNode<K, V> previous) {
        this.previous = previous;
    }

    public HashNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }
}
