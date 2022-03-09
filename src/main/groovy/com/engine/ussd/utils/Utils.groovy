package com.engine.ussd.utils

import java.util.regex.Pattern

class Utils {
    /**
     * Match the USSD menu with a passed USSD menu code.
     * @param s
     * @param pattern
     * @return
     */
   static boolean menuTextMatch(String s, Pattern pattern) {
        try {
            return pattern.matcher(s).matches()
        } catch (Exception e) {
            e.printStackTrace()
            false
        }
    }
}
