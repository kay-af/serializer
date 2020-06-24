/*
 * Copyright (C) 2020 AFRIDI KAYAL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.fridworks.serializer.utils;

import com.fridworks.serializer.Serializable;
import java.util.LinkedList;

/**
 * A simple byte writer to efficiently write primitive type data bytes
 * @author AFRIDI KAYAL
 */
public class DataWriter {

    private final LinkedList<Byte> dataBytes;

    public DataWriter() {
        dataBytes = new LinkedList();
    }

    public byte[] getBytes() {
        byte[] bytes = new byte[dataBytes.size()];
        int index = 0;
        for (byte dataByte : dataBytes) {
            bytes[index++] = dataByte;
        }
        return bytes;
    }

    public void append(byte data) {
        dataBytes.addLast(data);
    }

    public void append(short data) {
        dataBytes.addLast((byte) ((data >> 8) & 0xff));
        dataBytes.addLast((byte) ((data) & 0xff));
    }

    public void append(int data) {
        dataBytes.addLast((byte) ((data >> 24) & 0xff));
        dataBytes.addLast((byte) ((data >> 16) & 0xff));
        dataBytes.addLast((byte) ((data >> 8) & 0xff));
        dataBytes.addLast((byte) ((data) & 0xff));
    }

    public void append(long data) {
        dataBytes.addLast((byte) ((data >> 56) & 0xff));
        dataBytes.addLast((byte) ((data >> 48) & 0xff));
        dataBytes.addLast((byte) ((data >> 40) & 0xff));
        dataBytes.addLast((byte) ((data >> 32) & 0xff));
        dataBytes.addLast((byte) ((data >> 24) & 0xff));
        dataBytes.addLast((byte) ((data >> 16) & 0xff));
        dataBytes.addLast((byte) ((data >> 8) & 0xff));
        dataBytes.addLast((byte) ((data) & 0xff));
    }

    public void append(float data) {
        append(Float.floatToIntBits(data));
    }

    public void append(double data) {
        append(Double.doubleToLongBits(data));
    }

    public void append(boolean data) {
        append(data ? (byte) 1 : (byte) 0);
    }

    public void append(byte[] data) {
        append(data.length);
        for (byte d : data) {
            append(d);
        }
    }

    public void append(short[] data) {
        append(data.length);
        for (short d : data) {
            append(d);
        }
    }

    public void append(int[] data) {
        append(data.length);
        for (int d : data) {
            append(d);
        }
    }

    public void append(long[] data) {
        append(data.length);
        for (long d : data) {
            append(d);
        }
    }

    public void append(float[] data) {
        append(data.length);
        for (float d : data) {
            append(d);
        }
    }

    public void append(double[] data) {
        append(data.length);
        for (double d : data) {
            append(d);
        }
    }

    public void append(boolean[] data) {
        append(data.length);
        int requiredBytes = data.length / 8;

        int dataPointer = 0;
        for (int i = 0; i < requiredBytes; i++) {
            byte b = 0;
            for (int j = 0; j < 8 && dataPointer < data.length; j++, dataPointer++) {
                if (data[dataPointer]) {
                    b = (byte) (b | (1 << j));
                }
            }
            append(b);
        }
    }

    public void append(String data) {
        byte[] stringBytes = data.getBytes();
        append(stringBytes.length);
        for (int i = 0; i < stringBytes.length; i++) {
            append(stringBytes[i]);
        }
    }

    public void append(Byte[] data) {
        byte[] array = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            array[i] = data[i];
        }
        append(array);
    }

    public void append(Short[] data) {
        short[] array = new short[data.length];
        for (int i = 0; i < data.length; i++) {
            array[i] = data[i];
        }
        append(array);
    }

    public void append(Integer[] data) {
        int[] array = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            array[i] = data[i];
        }
        append(array);
    }

    public void append(Long[] data) {
        long[] array = new long[data.length];
        for (int i = 0; i < data.length; i++) {
            array[i] = data[i];
        }
        append(array);
    }

    public void append(Float[] data) {
        float[] array = new float[data.length];
        for (int i = 0; i < data.length; i++) {
            array[i] = data[i];
        }
        append(array);
    }

    public void append(Double[] data) {
        double[] array = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            array[i] = data[i];
        }
        append(array);
    }

    public void append(Boolean[] data) {
        boolean[] array = new boolean[data.length];
        for (int i = 0; i < data.length; i++) {
            array[i] = data[i];
        }
        append(array);
    }

    public void append(String[] data) {
        append(data.length);
        for (String d : data) {
            append(d);
        }
    }
    
    public <T extends Serializable> void append(T data) {
        data.serialize(this);
    }
}