package com.fridworks.serializer;

import com.fridworks.serializer.utils.DataReader;
import com.fridworks.serializer.utils.DataWriter;
import com.sun.istack.internal.logging.Logger;
import java.util.logging.Level;

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
/**
 * Static serialization helper class
 * @author AFRIDI KAYAL
 */
public class Serializer {

    public static <T extends Serializable> byte[] serialize(T data) {
        try {
            DataWriter writer = new DataWriter();
            data.serialize(writer);

            return writer.getBytes();
        } catch (StackOverflowError e) {
            throw new SerializationException("Internal stack overflowed during serialization",
                    "Avoid serializing objects with self or cyclic refrences", data.getClass());
        }
    }

    public static <T extends Serializable> T deserialize(byte[] data, Class<T> type) {
        try {
            DataReader reader = new DataReader(data);
            Object instance = type.newInstance();
            ((Serializable) instance).deserialize(reader);

            if (reader.hasData()) {
                Logger.getLogger(Serializer.class).log(Level.WARNING, "Reader still has data in it after serialization\n"
                        + "An inherited type might have been deserialized as super type\n"
                        + "Use typed serialization to overcome this problem or define custom logic in Serializable implemented methods to determine proper type");
                throw new SerializationException("Possible type mismatch",
                        "Check if the type provided is actual type of the serialized bytes provided", data.getClass());
            }

            return (T) instance;
        } catch (InstantiationException e) {
            throw new SerializationException("Cannot create instance of the class",
                    "Make sure the given type has a public parameterless constructor",
                    type);
        } catch (IllegalAccessException e) {
            throw new SerializationException("Access to class denied",
                    "Make sure the given serializable class is public",
                    type);
        } catch (StackOverflowError e) {
            throw new SerializationException("Internal stack overflowed during deserialization",
                    "Avoid serializing objects with self or cyclic refrences", data.getClass());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SerializationException("Possible type mismatch",
                    "Check if the type provided is actual type of the serialized bytes provided", data.getClass());
        } catch (ClassNotFoundException e) {
            throw new SerializationException("The deserialization type is not defined " + e.toString(),
                    "Check if any model classes are missing from the project. Class names should not be changed after serialization",
                    data.getClass());
        }
    }
}
