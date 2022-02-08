/*
 * SimpleMemory
 *
 * 0.0.1
 *
 * 06/02/2022
 */
package fr.enimaloc.procemu.simple;

import fr.enimaloc.procemu.api.Memory;
import fr.enimaloc.procemu.utils.ArrayUtils;

/**
 *
 */
public class SimpleMemory implements Memory {

    private boolean[] data = new boolean[8];

    @Override
    public boolean[] toBinary() {
        return this.data;
    }

    @Override
    public void set(int i) {
        this.data = ArrayUtils.unwrap(Memory.toBinary(this.data.length,
                                                      Byte.toUnsignedInt(
                                                              (byte) i)));
    }

    @Override
    public String toString() {
        return Memory.toString(this);
    }
}
