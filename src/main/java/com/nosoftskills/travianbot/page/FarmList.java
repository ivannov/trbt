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

import com.nosoftskills.travianbot.Main;
import com.nosoftskills.travianbot.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class FarmList {

    private WebDriver webDriver;
    private String rootUrl;

    public FarmList(WebDriver webDriver, String rootUrl) {
        this.webDriver = webDriver;
        this.rootUrl = rootUrl;
    }

    public void load() {
        webDriver.get(rootUrl + "build.php?tt=99&id=39");
    }

    public void raidSingleList(String listName) {
        List<WebElement> listTitles = webDriver.findElements(By.className("listTitleText"));
        int i = 0;
        for (WebElement listTitle : listTitles) {
            if (listTitle.getText().trim().equals(listName)) {
                break;
            }
            i++;
        }

        List<WebElement> raidTables = webDriver.findElements(By.className("list"));
        if (raidTables.size() == 0) {
            throw new IllegalStateException("Page was not loaded. Most probably the server is down or you don't have internet");
        }
        List<WebElement> startRaidButtons = webDriver.findElements(By.tagName("button"))
                .stream()
                .filter(buttonElement -> buttonElement.getAttribute("value").equals("Стартирай набег"))
                .collect(Collectors.toList());

        doRaid(raidTables.get(i), startRaidButtons.get(i));
    }

    private void doRaid(WebElement farmTable, WebElement raidButton) {
        selectGreen(farmTable);
        raidButton.click();

    }

    private static void selectGreen(WebElement webElement) {
        webElement.findElements(By.className("slotRow"))
                .stream()
                .filter(FarmList::isGreenFlag)
                .forEach(FarmList::select);
    }

    private static boolean isGreenFlag(WebElement webElement) {
        try {
            webElement.findElement(By.className("iReport1"));
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private static void select(WebElement webElement) {
        webElement.findElement(By.className("markSlot")).click();
    }

}
