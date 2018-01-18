/**
 * Copyright 2017 Google Inc.
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

package com.google.youtube.gaming.chat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.api.services.youtube.YouTubeScopes;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        if (args.length == 0)
        {
            Main.showUsage();
            return;
        }

        List<String> scopes = new ArrayList<>();
        scopes.add(YouTubeScopes.YOUTUBE_FORCE_SSL);
        scopes.add(YouTubeScopes.YOUTUBE);

        switch (args[0])
        {
        case "login":
            File file = new File("client_secret.json");

            if (!file.exists())
            {
                System.out.println("[YTChat] Couldn't find client_secret.json!");
                return;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine())
            {
                String clientSecret = scanner.nextLine();
                Authentication.authorize(scopes, clientSecret, args[1]);
                System.out.println("[YTChat] Login successfully!");
            }
            break;
        case "logout":
            Authentication.clearCurrentCredentialsTemp(args[1]);
            System.out.println("[YTChat] Logout successfully!");
            break;
        default:
            Main.showUsage();
            return;
        }
    }

    private static void showUsage()
    {
        System.out.println("[YTChat] Supported arguments: <login|logout>");
    }
}