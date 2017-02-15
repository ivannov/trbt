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
package com.nosoftskills.travianbot.page;

import com.nosoftskills.travianbot.model.IncomingAttack;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Home {

    private WebDriver webDriver;
    private String homePageUrl;

    public Home(WebDriver webDriver, String rootUrl) {
        this.webDriver = webDriver;
        this.homePageUrl = rootUrl + "/dorf1.php";
    }

    public void load() {
        webDriver.get(homePageUrl);
    }

    public List<IncomingAttack> getIncomingAttacks() {
        List<WebElement> villasUnderAttack = webDriver.findElements(By.cssSelector("li.attack"));
        List<String> villageNames = villasUnderAttack.stream()
                .map(webElement -> webElement.findElement(By.className("name")).getText())
                .collect(Collectors.toList());
        List<String> villageLinks = villasUnderAttack.stream()
                .map(webElement -> webElement.findElement(By.tagName("a")).getAttribute("href"))
                .collect(Collectors.toList());
        List<String> attackTimes = villageLinks.stream()
                .map(this::findInTime)
                .collect(Collectors.toList());

        List<IncomingAttack> incomingAttacks = new ArrayList<>(villageNames.size());
        for (int i = 0; i < villageNames.size(); i++) {
            incomingAttacks.add(new IncomingAttack(villageNames.get(i), attackTimes.get(i)));
        }
        return incomingAttacks;
    }


    private String findInTime(String attribute) {
        webDriver.get(attribute);
        WebElement movementsTable = webDriver.findElement(By.id("movements"));
        return movementsTable.findElements(By.tagName("tr")).stream()
                .filter(webElement -> !webElement.findElements(By.cssSelector("img.att1")).isEmpty())
                .map(webElement -> webElement.findElement(By.className("timer")).getText())
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
