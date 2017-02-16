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

public class IncomingAttack {

    private String villageName;
    private String numberOfAttacks;
    private String inTime;
    private String villageLink;

    public IncomingAttack(String villageName) {
        this.villageName = villageName;
    }


    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public void setNumberOfAttacks(String numberOfAttacks) {
        this.numberOfAttacks = numberOfAttacks;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getVillageLink() {
        return villageLink;
    }

    public void setVillageLink(String villageLink) {
        this.villageLink = villageLink;
    }
}
