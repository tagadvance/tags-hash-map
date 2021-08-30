package com.tagadvance.map;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SimpleHashMap<K, V> implements Map<K, V> {

    private final List<KeyValuePair> keyValuePairs;

    public SimpleHashMap() {
        this.keyValuePairs = new ArrayList<>();
    }

    public SimpleHashMap(int initialSize) {
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
        V previousValue = this.remove(key);

        this.keyValuePairs.add(new KeyValuePair(key, value));

        return previousValue;
    }

    @Override
    public V remove(Object key) {
        V previousValue = null;

        var o = this.getIndexOfKey(key);
        if (o.isPresent()) {
            var i = o.getAsInt();
            previousValue = this.keyValuePairs.get(i).getValue();

            this.keyValuePairs.remove(i);
        }

        return previousValue;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        map.entrySet().forEach(e -> this.put(e.getKey(), e.getValue()));
    }

    @Override
    public void clear() {
        this.keyValuePairs.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.keyValuePairs.stream().map(KeyValuePair::getKey).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return this.keyValuePairs.stream().map(KeyValuePair::getValue).collect(Collectors.toList());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.keyValuePairs.stream().map(pair -> new Entry<K, V>() {
            @Override
            public K getKey() {
                return pair.getKey();
            }

            @Override
            public V getValue() {
                return pair.getValue();
            }

            @Override
            public V setValue(V v) {
                throw new RuntimeException("this operation is not supported");
            }
        }).collect(Collectors.toSet());
    }

    private OptionalInt getIndexOfKey(Object key) {
        Preconditions.checkNotNull(key, "key must not be null");

        var hashCode = key.hashCode();
        return IntStream.range(0, this.keyValuePairs.size())
                .filter(i -> {
                    var keyValuePair = this.keyValuePairs.get(i);
                    return hashCode == keyValuePair.getKey().hashCode() && Objects.equals(key, keyValuePair.getKey());
                })
                .findFirst();
    }

    private OptionalInt getIndexOfValue(Object value) {
        Preconditions.checkNotNull(value, "value must not be null");

        var hashCode = value.hashCode();
        return IntStream.range(0, this.keyValuePairs.size())
                .filter(i -> {
                    var keyValuePair = this.keyValuePairs.get(i);
                    return hashCode == keyValuePair.getValue().hashCode() && Objects.equals(value, keyValuePair.getValue());
                })
                .findFirst();
    }

    private class KeyValuePair {

        private final K key;
        private final V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

    }

}
