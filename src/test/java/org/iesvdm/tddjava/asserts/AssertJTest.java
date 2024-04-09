package org.iesvdm.tddjava.asserts;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class AssertJTest {

    void whenBooleanIsTrue() {
        assertThat(1 == 1).isTrue();
    }
    @Test
    void whenBooleanIsFalse() {
        boolean flag =false;
        assertThat(flag).isFalse();
    }

    @Test
    void whenObjectIsNull() {
        Object nullObj = null;
        assertThat(nullObj).isNull();

    }


    @Test
    void whenObjectIsNotNull() {
        Object obj = new Object();

        assertThat(obj).isNotNull();

    }


    @Test
    void shouldBeEqual() {
        final Integer ACTUAL = 9;
        final Integer EXPECTED = 9;

        assertThat(ACTUAL).isEqualTo(EXPECTED);

    }

    @Test
    void shouldReferToSameObject() {
        final Object ACTUAL = 9;
        final Object EXPECTED = ACTUAL;

        assertThat(ACTUAL).isSameAs(EXPECTED);

    }

    @Test
    void shouldNotReferToSameObject() {
        final Object ACTUAL = 9;
        final Object EXPECTED = ACTUAL;

        assertThat(ACTUAL).isNotSameAs(new Object());

    }


    @Test
    void shouldContainSameIntegers() {
        final int[] ACTUAL = new int[]{2, 5, 7};
        final int[] EXPECTED = new int[]{2, 5, 7};

        assertThat(ACTUAL).containsExactly(EXPECTED);

    }

    @Test
    void shouldContainTwoElements() {
        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertThat(list).hasSize(2);

    }

    @Test
    void shouldNotContainAnyElement() {

        List list = Arrays.asList();

        assertThat(list).isEmpty();

    }

    @Test
    void shouldContainCorrectElementsInGivenOrder() {

        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertThat(list).containsExactlyElementsOf(Arrays.asList(first, second));

    }

    @Test
    void shouldContainCorrectElementsInAnyOrder() {
        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        List list2 = Arrays.asList(second, first);


        assertThat(list).containsExactlyInAnyOrderElementsOf(list2);

    }

    @Test
    void shouldContainCorrectElementOnce() {

        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertThat(list).containsOnlyOnce(first);

    }

    @Test
    void shouldNotContainIncorrectElement() {

        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertThat(list).doesNotContain(new Object());

    }

    @Test
    void shouldContainCorrectKey() {
        final String KEY = "key";
        final String VALUE = "value";

        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map).containsKey(KEY);
    }

    @Test
    void shouldNotContainCorrectKey() {
        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";


        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map).doesNotContainKeys(INCORRECT_KEY);

    }

    @Test
    void shouldContainGivenEntry() {

        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";

        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map).containsEntry(KEY, VALUE);

    }

    @Test
    void shouldNotContainGivenEntry() {

        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";

        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map).doesNotContainEntry(INCORRECT_KEY, VALUE);

    }


}
