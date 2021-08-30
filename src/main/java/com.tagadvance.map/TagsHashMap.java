package com.tagadvance.map;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.stream.IntStream;

class TagsHashMap<K, V> implements Map<K, V> {
    private final List<KeyValuePair> keyValuePairs;

    public TagsHashMap() {
        this.keyValuePairs = new ArrayList<>();
    }

    public TagsHashMap(int initialSize) {
        this.keyValuePairs = new ArrayList<>(initialSize);
    }

    @Override
    public int size() {
        return this.keyValuePairs.size();
    }

    @Override
    public boolean isEmpty() {
        return this.keyValuePairs.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.getIndexOfKey(key).isPresent();
    }

    @Override
    public boolean containsValue(Object value) {
        return this.getIndexOfValue(value).isPresent();
    }

    @Override
    public V get(Object key) {
        var o = this.getIndexOfKey(key);
        if (o.isPresent()) {
            var i = o.getAsInt();
            return this.keyValuePairs.get(i).getValue();
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        V previousValue = null;

        var i = this.getIndexOfKey(key);
        if (i.isPresent()) {
            previousValue = this.keyValuePairs.get(i.getAsInt()).getValue();

            this.keyValuePairs.remove(i);
        }

        this.keyValuePairs.add(new KeyValuePair(key, value));

        return previousValue;
    }

    @Override
    public V remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public void clear() {
        this.keyValuePairs.clear();
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private OptionalInt getIndexOfKey(Object key) {
        Preconditions.checkNotNull(key, "key must not be null");

        var hashCode = key.hashCode();
        return IntStream.range(0, this.keyValuePairs.size())
                .filter(i -> {
                    var keyValuePair = this.keyValuePairs.get(i);
                    return hashCode == keyValuePair.getKeyHash() && Objects.equals(key, keyValuePair.getKey());
                })
                .findFirst();
    }

    private OptionalInt getIndexOfValue(Object value) {
        Preconditions.checkNotNull(value, "value must not be null");

        var hashCode = value.hashCode();
        return IntStream.range(0, this.keyValuePairs.size())
                .filter(i -> {
                    var keyValuePair = this.keyValuePairs.get(i);
                    return hashCode == keyValuePair.getValueHash() && Objects.equals(value, keyValuePair.getValue());
                })
                .findFirst();
    }

    private class KeyValuePair {

        private final K key;
        private final V value;
        private final int keyHash, valueHash;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
            this.keyHash = key.hashCode();
            this.valueHash = value.hashCode();
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getKeyHash() {
            return keyHash;
        }

        public int getValueHash() {
            return valueHash;
        }
    }
}
