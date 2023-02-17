package com.gulteking.mqttbackendserver.utils;

import com.gulteking.mqttbackendserver.utils.constants.IntegerConstants;
import com.gulteking.mqttbackendserver.utils.constants.StringConstants;

public class AppUtils {
    /**
     * In this method we are receiving int n
     * we are creating alpha numeric string of given length
     **/
    public static String getAlphaNumericString(int n) {
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        for (int i = IntegerConstants.INT_ZERO; i < n; i++) {
            int index
                    = (int) (StringConstants.TEXT_ALPHA_NUMERIC_STRING.length()
                    * Math.random());
            sb.append(StringConstants.TEXT_ALPHA_NUMERIC_STRING.charAt(index));
        }
        return sb.toString();
    }
}
