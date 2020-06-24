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

/**
 * A basic exception class to generate serialization exceptions
 * @author AFRIDI KAYAL
 */
public class SerializationException extends RuntimeException {

    private final Class associatedType;
    private final String fix;
    
    public SerializationException(String message, String fix, Class type) {
        super(message);
        this.fix = fix;
        this.associatedType = type;
    }

    @Override
    public String toString() {
        return getMessage() + ", Associated class: [" + associatedType.getSimpleName() + "]\nFix: " + fix;
    }
}
