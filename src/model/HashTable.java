package model;

public class HashTable<K, V> implements HashTableI<K, V> {
    private HashNode<K, V>[] table;
    private final int SIZE = 127;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = (HashNode<K, V>[]) new HashNode<?, ?>[SIZE];
    }

    @Override
    public void put(K key, V value) {
        HashNode<K, V> node = new HashNode<>(key, value);

        int pos = hashFunction(key);

        if (table[pos] == null) {
            table[pos] = node;
        } else if (table[pos].getKey().equals(key)) { // If the key is the same, then update the value
            table[pos].setValue(value);
        } else { // Solve the collision by chaining
            boolean foundSameKey = false;
            HashNode<K, V> currNode = table[pos];

            while (currNode.getNext() != null && !foundSameKey) {
                currNode = currNode.getNext();

                // If it founds a coincidence in the keys while chaining, update the value
                if (currNode.getKey().equals(key)) {
                    currNode.setValue(value);
                    foundSameKey = true;
                }
            }

            if (!foundSameKey) {
                currNode.setNext(node);
            }
        }
    }

    @Override
    public V get(K key) {
        int pos = hashFunction(key);
        HashNode<K,V> currNode = table[pos];

        // Check if the key exists
        if (currNode == null) {
            return null;
        } else if (currNode.getNext() == null) { // Check if there was a collision in this position
            return currNode.getValue();
        } else {
            // If there was a collision, find the element
            while (currNode.getNext() != null) {
                if (currNode.getKey().equals(key)) {
                    break;
                } else {
                    currNode = currNode.getNext();
                }
            }

            return currNode.getValue();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        table = (HashNode<K, V>[]) new HashNode<?, ?>[SIZE];
    }

    @Override
    public int hashFunction(K key) {
        if (key instanceof String) {
            String keyS = (String) key;
            long sum = 0; // Store large sums

            for (int i = 0; i < keyS.length(); i++) {
                sum += keyS.charAt(i) * (Math.pow(128, i));
            }

            return (int) Math.abs(sum % SIZE);
        } else if(key instanceof Integer) {
            return (int) key % SIZE;
        }

        return -1;
    }

}