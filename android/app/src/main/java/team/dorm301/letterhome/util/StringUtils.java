package team.dorm301.letterhome.util;

public class StringUtils {
    public static String getLastName(String name) {
        if (name.length() < 1) {
            return "";
        }
        char[] lastname = new char[5];
        lastname[0] = name.charAt(0);
        lastname[1] = '\0';
        return new String(lastname);
    }
}
