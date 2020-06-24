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

/**
 * A simple data reader to efficiently read primitive type data bytes
 * @author AFRIDI KAYAL
 */
public class DataReader {
    
    private final byte[] dataBytes;
    private int readerPosition = 0;
    
    public DataReader(byte[] dataBytes) {
        this.dataBytes = dataBytes;
        this.readerPosition = 0;
    }
    
    public boolean hasData() {
        return readerPosition < dataBytes.length;
    }
    
    public byte readByte() {
        return dataBytes[readerPosition++];
    }
    
    public short readShort() {
        int b0 = (readByte() << 8) & 0xff00;
        int b1 = readByte() & 0xff;
        return (short)(b0 | b1);
    }
    
    public int readInt() {
        int b0 = (readShort() << 16) & 0xffff0000;
        int b1 = readShort() & 0xffff;
        return b0 | b1;
    }
    
    public long readLong() {
        long b0 = ((long)readInt() << 32) & 0xffffffff00000000L;
        long b1 = (long)readInt() & 0xffffffffL;
        return (long)(b0 | b1);
    }
    
    public float readFloat() {
        int rawIntBits = readInt();
        return Float.intBitsToFloat(rawIntBits);
    }
    
    public double readDouble() {
        long rawLongBits = readLong();
        return Double.longBitsToDouble(rawLongBits);
    }
    
    public boolean readBoolean() {
        return readByte() == 1;
    }
    
    public byte[] readByteArray() {
        int length = readInt();
        byte[] array = new byte[length];
        
        for(int i=0; i<length; i++) {
            array[i] = readByte();
        }
        
        return array;
    }
    
    public short[] readShortArray() {
        int length = readInt();
        short[] array = new short[length];
        
        for(int i=0; i<length; i++) {
            array[i] = readShort();
        }
        
        return array;
    }
    
    public int[] readIntArray() {
        int length = readInt();
        int[] array = new int[length];
        
        for(int i=0; i<length; i++) {
            array[i] = readInt();
        }
        
        return array;
    }
    
    public long[] readLongArray() {
        int length = readInt();
        long[] array = new long[length];
        
        for(int i=0; i<length; i++) {
            array[i] = readLong();
        }
        
        return array;
    }
    
    public float[] readFloatArray() {
        int length = readInt();
        float[] array = new float[length];
        
        for(int i=0; i<length; i++) {
            array[i] = readFloat();
        }
        
        return array;
    }
    
    public double[] readDoubleArray() {
        int length = readInt();
        double[] array = new double[length];
        
        for(int i=0; i<length; i++) {
            array[i] = readDouble();
        }
        
        return array;
    }
    
    public boolean[] readBooleanArray() {
        int length = readInt();
        boolean[] array = new boolean[length];
        
        int dataPointer = 0;
        int requiredBytes = length / 8;
        for(int i=0; i<requiredBytes; i++) {
            byte b = readByte();
            for(int j=0; j<8 && dataPointer<length; j++, dataPointer++) {
                array[dataPointer] = (byte)(b & (1 << j)) == 1;
            }
        }
        
        return array;
    }
    
    public String readString() {
        int length = readInt();
        byte[] stringBytes = new byte[length];
        
        for(int i=0; i<length; i++) stringBytes[i] = readByte();
        
        return new String(stringBytes);
    }
    
    public Byte[] readByteRefArray() {
        byte[] array = readByteArray();
        Byte[] res = new Byte[array.length];
        for(int i=0; i<res.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
    
    public Short[] readShortRefArray() {
        short[] array = readShortArray();
        Short[] res = new Short[array.length];
        for(int i=0; i<res.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
    
    public Integer[] readIntRefArray() {
        int[] array = readIntArray();
        Integer[] res = new Integer[array.length];
        for(int i=0; i<res.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
    
    public Long[] readLongRefArray() {
        long[] array = readLongArray();
        Long[] res = new Long[array.length];
        for(int i=0; i<res.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
    
    public Float[] readFloatRefArray() {
        float[] array = readFloatArray();
        Float[] res = new Float[array.length];
        for(int i=0; i<res.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
    
    public Double[] readDoubleRefArray() {
        double[] array = readDoubleArray();
        Double[] res = new Double[array.length];
        for(int i=0; i<res.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
    
    public Boolean[] readBooleanRefArray() {
        boolean[] array = readBooleanArray();
        Boolean[] res = new Boolean[array.length];
        for(int i=0; i<res.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
    
    public String[] readStringArray() {
        int length = readInt();
        String[] array = new String[length];
        
        for(int i=0; i<length; i++) array[i] = readString();
        
        return array;
    }
    
    public <T extends Serializable> T read(Class<T> type) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        T instance = type.newInstance();
        instance.deserialize((DataReader)this);
        return instance;
    }
}
