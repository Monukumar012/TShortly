package com.tshortly.common.utlity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class DateUtility {
    private DateUtility() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static LocalDateTime toLocalDateTime(ZonedDateTime zonedDateTime) {
        if(zonedDateTime == null) {
            return null;
        }
        return zonedDateTime.toLocalDateTime();
    }

    public static ZonedDateTime toZoneDateTime(LocalDateTime localDateTime) {
        if(localDateTime == null) {
            return null;
        }
        return localDateTime.atZone(ZoneId.systemDefault());
    }
}