/*
 * Register
 *
 * 0.0.1
 *
 * 06/02/2022
 */
package fr.enimaloc.procemu.api;

import ch.qos.logback.classic.Logger;
import fr.enimaloc.procemu.simple.SimpleMemory;
import fr.enimaloc.procemu.utils.ArrayUtils;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represent memory data
 *
 * @see fr.enimaloc.procemu.simple.SimpleMemory simple implementation of Memory
 */
public interface Memory {

    Logger LOG = (Logger) LoggerFactory.getLogger(Memory.class);

    /**
     * {@link Object#toString()} override for {@link Memory} can't be
     * implemented in {@code interface}, need to be implemented in your
     * class with <pre>{@code
     * @Override
     * public String toString() {
     *     return Memory.toString(this);
     * }
     * }</pre>
     *
     * @param memory actual object to convert to string
     * @return basically an binary string
     * @see Object#toString()
     * @see Memory#toByte()
     * @see Memory#toDouble()
     * @see Memory#toBinary()
     * @see Memory#toChar()
     * @see Memory#toHex()
     * @see Memory#toLong()
     * @see Memory#toString()
     * @see Memory#toUnsignedByte()
     */
    static String toString(Memory memory) {
        LOG.debug("Memory.toString(Memory)");
        LOG.trace("memory = {}", memory);
        return String.format(String.format("%%%ds", memory.toBinary().length),
                             Arrays.stream(ArrayUtils.wrap(memory.toBinary()))
                                   .map(b -> b != null && b ? "1" : "0")
                                   .collect(Collectors.joining()))
                     .replace(' ', '0');
    }

    /**
     * Convert {@link Integer} to {@link Boolean} array using {@link
     * Integer#toBinaryString(int)}
     * where {@code 1} equals to {@link Boolean#TRUE true}
     *
     * @param length of the array
     * @param i      number to translate
     * @return Boolean array, {@code 1} equals to {@link Boolean#TRUE true}
     * @see Integer#toBinaryString(int)
     * @see Memory#toByte()
     * @see Memory#toDouble()
     * @see Memory#toBinary()
     * @see Memory#toChar()
     * @see Memory#toHex()
     * @see Memory#toLong()
     * @see Memory#toString()
     * @see Memory#toUnsignedByte()
     */
    static Boolean[] toBinary(int length, int i) {
        LOG.debug("Memory.toBinary(int, int)");
        LOG.trace("length = {}, i = {}", length, i);
        return Arrays.stream(ArrayUtils.wrap(
                             String.format(String.format("%%%ds", length),
                                           Integer.toBinaryString(i))
                                   .toCharArray()))
                     .map(c -> c == '1')
                     .toArray(Boolean[]::new);
    }

    /**
     * Convert {@link Memory memory} data to {@link Integer int} using {@link
     * Memory#toByte()}
     *
     * @return {@link Integer int} value of {@link Memory memory} data
     * @see Memory#toByte()
     * @see Memory#toDouble()
     * @see Memory#toBinary()
     * @see Memory#toChar()
     * @see Memory#toHex()
     * @see Memory#toLong()
     * @see Memory#toString()
     * @see Memory#toUnsignedByte()
     */
    default int toInt() {
        LOG.debug("Memory#toInt()");
        return toByte();
    }

    /**
     * Convert {@link Memory memory} data to {@link Double double} using {@link
     * Memory#toByte()}
     *
     * @return {@link Double double} value of {@link Memory memory} data
     * @see Memory#toByte()
     * @see Memory#toInt()
     * @see Memory#toBinary()
     * @see Memory#toChar()
     * @see Memory#toHex()
     * @see Memory#toLong()
     * @see Memory#toString()
     * @see Memory#toUnsignedByte()
     */
    default double toDouble() {
        LOG.debug("Memory#toDouble()");
        return toByte();
    }

    /**
     * Convert {@link Memory memory} data to {@link Long long} using {@link
     * Memory#toByte()}
     *
     * @return {@link Long long} value of {@link Memory memory} data
     * @see Memory#toByte()
     * @see Memory#toInt()
     * @see Memory#toBinary()
     * @see Memory#toChar()
     * @see Memory#toHex()
     * @see Memory#toDouble()
     * @see Memory#toString()
     * @see Memory#toUnsignedByte()
     */
    default long toLong() {
        LOG.debug("Memory#toLong()");
        return toByte();
    }

    /**
     * @return the data array in your implementation class
     * @see SimpleMemory#toBinary()
     * @see Memory#toByte()
     * @see Memory#toInt()
     * @see Memory#toBinary()
     * @see Memory#toChar()
     * @see Memory#toHex()
     * @see Memory#toLong()
     * @see Memory#toString()
     * @see Memory#toUnsignedByte()
     */
    boolean[] toBinary();

    /**
     * Convert {@link Memory memory} data to {@link Character char} using {@link
     * Memory#toUnsignedByte()}
     *
     * @return {@link Character char} value of {@link Memory memory} data
     * @see Memory#toByte()
     * @see Memory#toInt()
     * @see Memory#toBinary()
     * @see Memory#toDouble()
     * @see Memory#toHex()
     * @see Memory#toLong()
     * @see Memory#toString()
     * @see Memory#toUnsignedByte()
     */
    default char toChar() {
        LOG.debug("Memory#toChar()");
        return (char) toUnsignedByte();
    }

    /**
     * Convert {@link Memory memory} data to {@link String hexadecimal} using
     * {@link Memory#toUnsignedByte()} and {@link Integer#toHexString(int)}
     *
     * @return {@link String hexadecimal} value of {@link Memory memory} data
     * @see Memory#toByte()
     * @see Memory#toInt()
     * @see Memory#toBinary()
     * @see Memory#toChar()
     * @see Memory#toDouble()
     * @see Memory#toLong()
     * @see Memory#toString()
     * @see Memory#toUnsignedByte()
     */
    default String toHex() {
        LOG.debug("Memory#toHex()");
        return Integer.toHexString(toUnsignedByte());
    }

    /**
     * Convert {@link Memory memory} data to {@link Byte byte} using
     * {@link Memory#toString()} and {@link Integer#parseInt(String, int)}
     * with a {@code radix} of {@code 2}
     *
     * @return {@link Double double} value of {@link Memory memory} data
     * @see Memory#toDouble()
     * @see Memory#toInt()
     * @see Memory#toBinary()
     * @see Memory#toChar()
     * @see Memory#toHex()
     * @see Memory#toLong()
     * @see Memory#toString()
     * @see Memory#toUnsignedByte()
     */
    default byte toByte() {
        LOG.debug("Memory#toByte()");
        return (byte) Integer.parseInt(toString(), 2);
    }

    /**
     * Convert {@link Memory memory} data to {@link Integer unsigned byte}
     * using {@link Memory#toByte()} and {@link Byte#toUnsignedInt(byte)}
     *
     * @return {@link Integer unsigned byte} value of {@link Memory memory} data
     * @see Memory#toByte()
     * @see Memory#toInt()
     * @see Memory#toBinary()
     * @see Memory#toChar()
     * @see Memory#toHex()
     * @see Memory#toLong()
     * @see Memory#toString()
     * @see Memory#toDouble()
     */
    default int toUnsignedByte() {
        LOG.debug("Memory#toUnsignedByte()");
        return Byte.toUnsignedInt(toByte());
    }

    /**
     * Set {@link Memory memory} data using {@link Memory#set(int)} and {@link
     * Byte#toUnsignedInt(byte)}
     *
     * @param b value to set in data
     * @see Byte#toUnsignedInt(byte)
     * @see Memory#set(int)
     * @see Memory#add(byte)
     * @see Memory#remove(byte)
     */
    default void set(byte b) {
        LOG.debug("Memory#set(double)");
        LOG.trace("b = {}", b);
        set(Byte.toUnsignedInt(b));
    }

    /**
     * Set {@link Memory memory} data
     *
     * @param i value to set in data
     * @see SimpleMemory#set(int)
     * @see Memory#set(byte)
     * @see Memory#add(int)
     * @see Memory#remove(int)
     */
    void set(int i);

    /**
     * Add {@code b} to {@link Memory memory} data using {@link
     * Memory#set(byte)} and {@link Memory#toByte()}
     *
     * @param b value to add
     * @see Memory#toByte()
     * @see Memory#add(int)
     * @see Memory#set(byte)
     * @see Memory#remove(byte)
     */
    default void add(byte b) {
        LOG.debug("Memory#add(byte)");
        LOG.trace("b = {}", b);
        set(toByte() + b);
    }

    /**
     * Add {@code i} to {@link Memory memory} data using {@link Memory#set(int)}
     * and {@link Memory#toUnsignedByte()}
     *
     * @param i value to add
     * @see Memory#toUnsignedByte()
     * @see Memory#add(byte)
     * @see Memory#set(int)
     * @see Memory#remove(int)
     */
    default void add(int i) {
        LOG.debug("Memory#add(int)");
        LOG.trace("i = {}", i);
        set(toUnsignedByte() + i);
    }

    /**
     * Add {@code b} to {@link Memory memory} data using {@link
     * Memory#set(byte)} and {@link Memory#toByte()}
     *
     * @param b value to remove
     * @see Memory#toByte()
     * @see Memory#remove(int)
     * @see Memory#set(byte)
     * @see Memory#add(byte)
     */
    default void remove(byte b) {
        LOG.debug("Memory#remove(byte)");
        LOG.trace("b = {}", b);
        set(toByte() - b);
    }

    /**
     * Add {@code i} to {@link Memory memory} data using {@link Memory#set(int)}
     * and {@link Memory#toUnsignedByte()}
     *
     * @param i value to remove
     * @see Memory#toUnsignedByte()
     * @see Memory#remove(byte)
     * @see Memory#set(int)
     * @see Memory#add(int)
     */
    default void remove(int i) {
        LOG.debug("Memory#remove(int)");
        LOG.trace("i = {}", i);
        set(toUnsignedByte() - i);
    }

}
