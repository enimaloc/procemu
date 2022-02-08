/*
 * ArrayUtils
 *
 * 0.0.1
 *
 * 06/02/2022
 */
package fr.enimaloc.procemu.utils;

/**
 *
 */
public class ArrayUtils {

    ArrayUtils() {
    }

    public static Boolean[] wrap(boolean[] array) {
        Boolean[] wrapped = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            wrapped[i] = array[i];
        }
        return wrapped;
    }

    public static boolean[] unwrap(Boolean[] array) {
        boolean[] wrapped = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            wrapped[i] = array[i];
        }
        return wrapped;
    }

}
