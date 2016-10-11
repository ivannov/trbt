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
package com.nosoftskills.travianbot;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "/usr/bin/firefox");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://ts1.travian.com");
        WebElement userNameElement = driver.findElement(By.name("name"));
        userNameElement.sendKeys("Father1987");
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(args[0]);
        passwordElement.submit();
//        driver.get("http://ts1.travian.com/dorf2.php?newdid=80822&");
//        driver.get("http://ts1.travian.com/build.php?id=34");
//        WebElement phalanxElement = driver.findElement(By.name("t1"));
//        phalanxElement.sendKeys("42");
//        phalanxElement.submit();
        loadRaidsPage(driver);
        List<WebElement> raidTables = driver.findElements(By.className("list"));

        for (int i = 0; i < raidTables.size(); i++) {
            loadRaidsPage(driver);
            List<WebElement> raidTablesReload = driver.findElements(By.className("list"));
            selectGreen(raidTablesReload.get(i));
            List<WebElement> startRaidButtons = driver.findElements(By.tagName("button"))
                    .stream()
                    .filter(buttonElement -> buttonElement.getAttribute("value").equals("Start raid"))
                    .collect(Collectors.toList());
            startRaidButtons.get(i).click();
        }
    }

    private static void loadRaidsPage(WebDriver driver) {
        driver.get("http://ts1.travian.com/build.php?tt=99&id=39");
    }

    private static void selectGreen(WebElement webElement) {
        webElement.findElements(By.className("slotRow"))
                .stream()
                .filter(Main::isGreenFlag)
                .forEach(Main::select);
    }

    private static void select(WebElement webElement) {
        webElement.findElement(By.className("markSlot")).click();
    }

    private static boolean isGreenFlag(WebElement webElement) {
        try {
            webElement.findElement(By.className("iReport1"));
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
