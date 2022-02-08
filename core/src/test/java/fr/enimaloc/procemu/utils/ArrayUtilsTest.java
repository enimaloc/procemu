package fr.enimaloc.procemu.utils;

import fr.enimaloc.enutils.tests.UnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@UnitTest
class ArrayUtilsTest {

    @Test
    void wrap() {
        assertArrayEquals(new Boolean[]{true, true, false, true, false},
                          ArrayUtils.wrap(
                                  new boolean[]{true, true, false, true,
                                          false}));
    }

    @Test
    void unwrap() {
        assertArrayEquals(new boolean[]{true, true, false, true, false},
                          ArrayUtils.unwrap(
                                  new Boolean[]{true, true, false, true,
                                          false}));
    }
}