package com.tagadvance.map;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TagsHashMapTest {

    @Test
    void containsKeyReturnsTrueAfterPut() {
        var map = new TagsHashMap<String, String>();

        var key = "foo";
        assertFalse(map.containsKey(key));

        var value = "bar";
        map.put(key, value);
        assertTrue(map.containsKey(key));
    }

    @Test
    void putReturnsPreviousValue() {
        var map = new TagsHashMap<String, String>();

        var key = "foo";
        var value = "bar";
        var previousValue = map.put(key, value);
        assertNull(previousValue);

        previousValue = map.put(key, value);
        assertEquals(value, previousValue);
    }

    @Test
    void containsValueAndGet() {
        var map = new TagsHashMap<String, String>();

        var key = "foo";
        var value = "bar";

        assertFalse(map.containsValue(value));
        assertNull(map.get(key));

        map.put(key, value);

        assertTrue(map.containsValue(value));
        assertEquals(value, map.get(key));
    }

    @Test
    void remove() {
        var map = new TagsHashMap<String, String>();

        var key = "foo";
        var value = "bar";

        assertNull(map.remove(key));

        map.put(key, value);

        assertEquals(value, map.remove(key));
        assertFalse(map.containsKey(value));
    }

    @Test
    void keySet() {
        var map = new TagsHashMap<String, String>();

        map.put("a", "alpha");
        map.put("b", "beta");
        map.put("c", "gamma");

        assertEquals(Sets.newHashSet("a", "b", "c"), map.keySet());
    }

    @Test
    void values() {
        var map = new TagsHashMap<String, String>();

        map.put("a", "alpha");
        map.put("b", "beta");
        map.put("c", "gamma");

        assertArrayEquals(
                Arrays.asList("alpha", "beta", "gamma").toArray(),
                map.values().toArray()
        );
    }

    @Test
    void putAll() {
        var map = new HashMap<String, String>();
        map.put("a", "alpha");
        map.put("b", "beta");
        map.put("c", "gamma");

        var copy = new TagsHashMap<String, String>();
        copy.putAll(map);

        assertEquals("alpha", map.get("a"));
        assertEquals("beta", map.get("b"));
        assertEquals("gamma", map.get("c"));
    }

}
