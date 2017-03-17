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

import com.nosoftskills.travianbot.page.AlliancePlayers;
import com.nosoftskills.travianbot.page.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class DumpPlayers {

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.gecko.driver", "/usr/bin/firefox");
        WebDriver driver = new FirefoxDriver();
        Settings settings = Settings.load();
        Login loginPage = new Login(driver, settings.getRootUrl());
        loginPage.load();
        loginPage.login(settings.getUserName(), settings.getPassword());

        AlliancePlayers alliancePlayersPage = new AlliancePlayers(driver, settings.getRootUrl());
        alliancePlayersPage.load();
        alliancePlayersPage.getPlayers().forEach(System.out::println);
    }
}
