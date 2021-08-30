package com.tagadvance.map;

import org.junit.jupiter.api.Test;

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

}
