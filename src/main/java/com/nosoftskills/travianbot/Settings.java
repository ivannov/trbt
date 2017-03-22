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

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Settings {

    private static Settings instance;

    private String rootUrl;
    private String userName;
    private String password;
    private String browserLocation;
    private List<String> lists;

    public String getRootUrl() {
        return rootUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getBrowserLocation() {
        return browserLocation;
    }

    public List<String> getLists() {
        return lists;
    }

    public static Settings load() throws IOException {
        if (instance == null) {
            instance = new Settings();
            Properties props = new Properties();
            props.load(Settings.class.getResourceAsStream("/game.properties"));
            instance.password = System.getProperty("password");
            instance.userName = props.getProperty("userName");
            instance.rootUrl = props.getProperty("rootUrl");
            instance.browserLocation = props.getProperty("browserLocation");
            instance.lists = Arrays.asList(props.getProperty("raidLists").split(","));
        }

        return instance;
    }
}
