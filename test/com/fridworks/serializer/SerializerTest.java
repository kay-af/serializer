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

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class to test the functionality of Serialization
 * @author AFRIDI KAYAL
 */
public class SerializerTest {
    
    public SerializerTest() {
    }

    /**
     * Test the serialization process.
     */
    @Test
    public void dataIsUnchanged() {
        TestModel expected = new TestModel();
        byte[] bytes = Serializer.serialize(expected);
        TestModel result = Serializer.deserialize(bytes, TestModel.class);
        Assert.assertEquals(expected, result);
    }
    
    @Test
    public void inheritedBaseModel() {
        TestModel2 expected = new TestModel2();
        byte[] bytes = Serializer.serialize(expected);
        TestModel2 result = Serializer.deserialize(bytes, TestModel2.class);
        Assert.assertEquals(expected, result);
    }
}
