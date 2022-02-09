package fr.enimaloc.procemu.utils;

import fr.enimaloc.enutils.tests.UnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@UnitTest
class ArrayUtilsTest {

    @Test
    void wrapBoolean() {
        assertArrayEquals(new Boolean[]{true, true, false, true, false},
                          ArrayUtils.wrap(
                                  new boolean[]{true, true, false, true,
                                          false}));
    }

    @Test
    void unwrapBoolean() {
        assertArrayEquals(new boolean[]{true, true, false, true, false},
                          ArrayUtils.unwrap(
                                  new Boolean[]{true, true, false, true,
                                          false}));
    }

    @Test
    void wrapCharacter() {
        assertArrayEquals(new Character[]{'a', 'z', 'e', 'r', 't', 'y'},
                          ArrayUtils.wrap(
                                  new char[]{'a', 'z', 'e', 'r', 't', 'y'}));
    }
}