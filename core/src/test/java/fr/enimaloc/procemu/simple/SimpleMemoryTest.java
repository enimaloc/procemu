package fr.enimaloc.procemu.simple;

import ch.qos.logback.classic.Logger;
import fr.enimaloc.enutils.tests.UnitTest;
import fr.enimaloc.procemu.api.Memory;
import fr.enimaloc.procemu.utils.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@UnitTest
class SimpleMemoryTest {

    static Logger LOG = (Logger) LoggerFactory.getLogger(
            SimpleMemoryTest.class);

    Memory memory;
    byte   val;

    @BeforeEach
    void setUp() {
        memory = new SimpleMemory();
        val = (byte) ((byte) ThreadLocalRandom.current()
                                              .nextInt(255) - Byte.MAX_VALUE);
        LOG.info("Starting with {}", val);
        memory.set(val);
        LOG.debug("memory.data = {}", memory.toBinary());
    }

    @Test
    void toInt() {
        assertEquals(val, memory.toInt());
    }

    @Test
    void toDouble() {
        assertEquals(val, memory.toDouble());
    }

    @Test
    void toLong() {
        assertEquals(val, memory.toLong());
    }

    @Test
    void toChar() {
        assertEquals(Byte.toUnsignedInt(val), memory.toChar());
    }

    @Test
    void toHex() {
        assertEquals(Integer.toHexString(Byte.toUnsignedInt(val)),
                     memory.toHex());
    }

    @Test
    void toByte() {
        assertEquals(val, memory.toByte());
    }

    @Test
    void toUnsignedByte() {
        assertEquals(Byte.toUnsignedInt(val), memory.toUnsignedByte());
    }

    @Test
    void setByte() {
        val = (byte) ((byte) ThreadLocalRandom.current()
                                              .nextInt(127));
        memory.set(val);
        LOG.info("New value: {}", val);

        assertEquals(val, memory.toByte());
    }

    @Test
    void setInt() {
        val = (byte) ((byte) ThreadLocalRandom.current()
                                              .nextInt(127));
        memory.set((int) val);
        LOG.info("New value: {}", val);

        assertEquals(val, memory.toByte());
    }

    @Test
    void add() {
        val++;
        memory.add(1);

        assertEquals(val, memory.toByte());
    }

    @Test
    void remove() {
        val--;
        memory.remove(1);

        assertEquals(val, memory.toByte());
    }

    @Test
    void testToString() {
        String binary = String.format("%" + memory.toBinary().length + "s",
                                      Integer.toBinaryString(
                                              Byte.toUnsignedInt(val)))
                              .replace(' ', '0');
        int sub = binary.length() > memory.toBinary().length
                  ? memory.toBinary().length : 0;
        assertEquals(binary.substring(sub), memory.toString());
    }

    @Test
    void toBinary() {
        assertArrayEquals(ArrayUtils.unwrap(Arrays.stream(
                                                          String.format("%" + memory.toBinary().length + "s",
                                                                        Integer.toBinaryString(Byte.toUnsignedInt(val)))
                                                                .replace(' ',
                                                                         '0')
                                                                .split(""))
                                                  .map(v -> v.equals("1"))
                                                  .toArray(Boolean[]::new)),
                          memory.toBinary());
    }
}