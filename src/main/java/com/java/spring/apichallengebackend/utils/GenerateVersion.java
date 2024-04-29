package com.java.spring.apichallengebackend.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenerateVersion {

    public static String getNewVersion(String currentVersion) {
        int incrementalVersion = Integer.parseInt(currentVersion.substring(1));
        return "v".concat(String.valueOf(incrementalVersion+1));
    }
}
