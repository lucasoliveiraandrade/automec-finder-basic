package com.automecfinder.basic.util;

import java.util.UUID;

public class ActivationTokenUtil {

    public static String getNew() {
        return UUID.randomUUID().toString();
    }
}
