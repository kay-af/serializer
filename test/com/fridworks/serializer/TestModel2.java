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

/**
 * A test model
 * @author AFRIDI KAYAL
 */
public class TestModel2 implements Serializable {
    
    public InheritedTypeModelBase base;
    public InheritedTypeModelBase[] bases;
    
    public TestModel2() {
        base = new InheritedTypeModelChild();
        bases = new InheritedTypeModelBase[10];
        for(int i=0; i<10; i++) bases[i] = new InheritedTypeModelBase();
    }

    @Override
    public void serialize(DataWriter writer) {
        writer.append(base);
        writer.append(10);
        for(int i=0; i<10; i++) writer.append(bases[i]);
    }

    @Override
    public void deserialize(DataReader reader) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        base = reader.read(base.getClass());
        
        int size = reader.readInt();
        bases = new InheritedTypeModelBase[size];
        
        for(int i=0; i<10; i++) bases[i] = reader.read(InheritedTypeModelBase.class);
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.base);
        hash = 59 * hash + Arrays.deepHashCode(this.bases);
        return hash;
    }

    
}
