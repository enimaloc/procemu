/*
 * Register
 *
 * 0.0.1
 *
 * 06/02/2022
 */
package fr.enimaloc.procemu.api;

import ch.qos.logback.classic.Logger;
import fr.enimaloc.procemu.utils.ArrayUtils;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 */
public interface Memory {

    Logger LOG = (Logger) LoggerFactory.getLogger(Memory.class);

    static String toString(Memory memory) {
        LOG.debug("Memory.toString(Memory)");
        return String.format(String.format("%%%ds", memory.toBinary().length),
                             Arrays.stream(ArrayUtils.wrap(memory.toBinary()))
                                   .map(b -> b != null && b ? "1" : "0")
                                   .collect(Collectors.joining()))
                     .replace(' ', '0');
    }

    static Boolean[] toBinary(int length, int i) {
        LOG.debug("Memory.toBinary(int, int)");
        return Arrays.stream(String.format(String.format("%%%ds", length),
                                           Integer.toBinaryString(i))
                                   .split(""))
                     .map(s -> s.equals("1"))
                     .toArray(Boolean[]::new);
    }

    default int toInt() {
        LOG.debug("Memory#toInt()");
        return toByte();
    }

    default double toDouble() {
        LOG.debug("Memory#toDouble()");
        return toByte();
    }

    default long toLong() {
        LOG.debug("Memory#toLong()");
        return toByte();
    }

    boolean[] toBinary();

    default char toChar() {
        LOG.debug("Memory#toChar()");
        return (char) toUnsignedByte();
    }

    default String toHex() {
        LOG.debug("Memory#toHex()");
        return Integer.toHexString(toUnsignedByte());
    }

    default byte toByte() {
        LOG.debug("Memory#toByte()");
        return (byte) Integer.parseInt(toString(), 2);
    }

    default int toUnsignedByte() {
        LOG.debug("Memory#toUnsignedByte()");
        return Byte.toUnsignedInt(toByte());
    }

    default void set(byte b) {
        LOG.debug("Memory#set(double)");
        set(Byte.toUnsignedInt(b));
    }

    void set(int i);

    default void add(byte b) {
        LOG.debug("Memory#add(byte)");
        set(toByte() + b);
    }

    default void add(int i) {
        LOG.debug("Memory#add(int)");
        set(toUnsignedByte() + i);
    }

    default void remove(byte b) {
        LOG.debug("Memory#remove(byte)");
        set(toByte() - b);
    }

    default void remove(int i) {
        LOG.debug("Memory#remove(int)");
        set(toUnsignedByte() - i);
    }

}
