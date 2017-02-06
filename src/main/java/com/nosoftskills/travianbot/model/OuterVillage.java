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

import com.nosoftskills.travianbot.model.ResField.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nosoftskills.travianbot.model.ResField.Type.*;

public class OuterVillage {

    private Map<Type, List<ResField>> fields;

    public OuterVillage() {
        fields = new HashMap<>(4);
        fields.put(WOOD, new ArrayList<>());
        fields.put(CLAY, new ArrayList<>());
        fields.put(IRON, new ArrayList<>());
        fields.put(CROP, new ArrayList<>());
    }

    public OuterVillage withResField(ResField resField) {
        fields.get(resField.getType()).add(resField);
        return this;
    }

    public Map<Type, List<ResField>> getFields() {
        return fields;
    }
}
