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

import java.util.HashMap;
import java.util.Map;

import static com.nosoftskills.travianbot.model.ResField.Type.*;

public class Village {

    private String name;
    private int xCoord;
    private int yCoord;

    private OuterVillage outerVillage = new OuterVillage();
//    private InnerVillage innerVillage;

    private Map<Type, Integer> productionPerHour;

    public Village(String name, int xCoord, int yCoord) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.outerVillage = new OuterVillage();
        productionPerHour = new HashMap<>(4);
    }

    public String getName() {
        return name;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public OuterVillage getOuterVillage() {
        return outerVillage;
    }

    public int getWoodProduction() {
        return productionPerHour.get(WOOD);
    }

    public int getClayProduction() {
        return productionPerHour.get(CLAY);
    }

    public int getIronProduction() {
        return productionPerHour.get(IRON);
    }

    public int getCropProduction() {
        return productionPerHour.get(CROP);
    }

    public void setWoodProduction(int production) {
        productionPerHour.put(WOOD, production);
    }

    public void setClayProduction(int production) {
        productionPerHour.put(CLAY, production);
    }

    public void setIronProduction(int production) {
        productionPerHour.put(IRON, production);
    }

    public void setCropProduction(int production) {
        productionPerHour.put(CROP, production);
    }
}
