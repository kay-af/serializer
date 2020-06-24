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
package com.fridworks.serializer;

import com.fridworks.serializer.utils.DataReader;
import com.fridworks.serializer.utils.DataWriter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * A test model
 * @author AFRIDI KAYAL
 */
public class TestModel implements Serializable {

    private int intData;
    private Float[] arrayOfRefFloat;
    private String[] arrayOfStrings;

    public static class InnerTestModel implements Serializable {

        private double value;
        private String aString;

        public InnerTestModel() {
            Random r = new Random();
            value = r.nextDouble();
            aString = String.valueOf(r.nextLong());
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getaString() {
            return aString;
        }

        public void setaString(String aString) {
            this.aString = aString;
        }

        @Override
        public void serialize(DataWriter writer) {
            writer.append(value);
            writer.append(aString);
        }

        @Override
        public void deserialize(DataReader reader) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
            value = reader.readDouble();
            aString = reader.readString();
        }

        @Override
        public boolean equals(Object obj) {
            return hashCode() == obj.hashCode();
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 31 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
            hash = 31 * hash + Objects.hashCode(this.aString);
            return hash;
        }
    }

    private InnerTestModel itm;

    public TestModel() {
        Random r = new Random();
        intData = r.nextInt();

        arrayOfRefFloat = new Float[10];
        arrayOfStrings = new String[10];

        for (int i = 0; i < 10; i++) {
            arrayOfRefFloat[i] = r.nextFloat();
            arrayOfStrings[i] = String.valueOf(r.nextLong());
        }
        
        itm = new InnerTestModel();
    }

    public int getIntData() {
        return intData;
    }

    public void setIntData(int intData) {
        this.intData = intData;
    }

    public Float[] getArrayOfRefFloat() {
        return arrayOfRefFloat;
    }

    public void setArrayOfRefFloat(Float[] arrayOfRefFloat) {
        this.arrayOfRefFloat = arrayOfRefFloat;
    }

    public String[] getArrayOfStrings() {
        return arrayOfStrings;
    }

    public void setArrayOfStrings(String[] arrayOfStrings) {
        this.arrayOfStrings = arrayOfStrings;
    }

    public InnerTestModel getItm() {
        return itm;
    }

    public void setItm(InnerTestModel itm) {
        this.itm = itm;
    }

    @Override
    public void serialize(DataWriter writer) {
        writer.append(intData);
        writer.append(arrayOfRefFloat);
        writer.append(arrayOfStrings);
        writer.append(itm);
    }

    @Override
    public void deserialize(DataReader reader) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        intData = reader.readInt();
        arrayOfRefFloat = reader.readFloatRefArray();
        arrayOfStrings = reader.readStringArray();
        itm = reader.read(itm.getClass());
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.intData;
        hash = 97 * hash + Arrays.deepHashCode(this.arrayOfRefFloat);
        hash = 97 * hash + Arrays.deepHashCode(this.arrayOfStrings);
        hash = 97 * hash + Objects.hashCode(this.itm);
        return hash;
    }

}
