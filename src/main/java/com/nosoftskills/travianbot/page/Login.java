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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

    private WebDriver webDriver;
    private String rootUrl;

    public Login(WebDriver webDriver, String rootUrl) {
        this.webDriver = webDriver;
        this.rootUrl = rootUrl;
    }

    public void load() {
        webDriver.get(rootUrl);
    }

    public void login(String userName, String password) {
        WebElement userNameElement = webDriver.findElement(By.name("name"));
        userNameElement.sendKeys(userName);
        WebElement passwordElement = webDriver.findElement(By.name("password"));
        passwordElement.sendKeys(password);
        passwordElement.submit();

    }
}
