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

import com.nosoftskills.travianbot.model.IncomingAttack;
import com.nosoftskills.travianbot.page.FarmList;
import com.nosoftskills.travianbot.page.Home;
import com.nosoftskills.travianbot.page.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

    private static final Random random = new Random();
    private static final int MINIMUM_SECONDS = 17 * 60;
    private static final int MAXIMUM_SECONDS = 25 * 60;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");

    private static Map<String, String> previousIncomingAttacks = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Settings settings = Settings.load();

        System.setProperty("webdriver.gecko.driver", settings.getBrowserLocation());
        WebDriver driver = new FirefoxDriver();

        while (true) {
            Login loginPage = new Login(driver, settings.getRootUrl());
            loginPage.load();
            loginPage.login(settings.getUserName(), settings.getPassword());

            Home homePage = new Home(driver, settings.getRootUrl());
            homePage.load();
            List<IncomingAttack> currentIncomingAttacks = homePage.getIncomingAttacks();
            currentIncomingAttacks.forEach(Main::alertForIncomingAttack);
            previousIncomingAttacks.clear();
            currentIncomingAttacks.forEach(attack -> previousIncomingAttacks.put(attack.getVillageName(), attack.getNumberOfAttacks()));

            FarmList farmListPage = new FarmList(driver, settings.getRootUrl());
            farmListPage.load();
            farmListPage.raidSingleList("Bononia - 1");

            int sleepTime = MINIMUM_SECONDS + random.nextInt(MAXIMUM_SECONDS - MINIMUM_SECONDS);
            System.out.println(ZonedDateTime.now().format(formatter) + ": Sent raids. Sleeping for " + sleepTime + " seconds");
            Thread.sleep(sleepTime * 1000);
        }
    }

    private static void alertForIncomingAttack(IncomingAttack incomingAttack) {
        String currentNumber = previousIncomingAttacks.get(incomingAttack.getVillageName());
        if (!incomingAttack.getNumberOfAttacks().equals(currentNumber))
            System.out.println("Incoming attack on " + incomingAttack.getVillageName() + " in " + incomingAttack.getInTime());
    }

}
