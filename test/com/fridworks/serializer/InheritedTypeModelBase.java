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

/**
 * A test model
 * @author AFRIDI KAYAL
 */
public class InheritedTypeModelBase implements Serializable {
    
    public int baseData = 15;
    
    public InheritedTypeModelBase() { }
    
    @Override
    public void serialize(DataWriter writer) {
        writer.append(baseData);
    }

    @Override
    public void deserialize(DataReader reader) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        baseData = reader.readInt();
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.baseData;
        return hash;
    }
    
    
}
