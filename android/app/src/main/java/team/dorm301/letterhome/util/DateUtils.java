package team.dorm301.letterhome.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String toDefaultString(Date date) {
        return new SimpleDateFormat(DEFAULT_FORMAT).format(date);
    }
}
