/*
 * Copyright 2016 Microprofile.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nosoftskills.travianbot.model;

public class ResField extends Field {

    public enum Type {
        WOOD, CLAY, IRON, CROP
    }

    private Type type;

    public ResField() {
    }

    public ResField(int address, Type type) {
        this(address, 0, type);
    }

    public ResField(int address, int level, Type type) {
        super(address, level);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
