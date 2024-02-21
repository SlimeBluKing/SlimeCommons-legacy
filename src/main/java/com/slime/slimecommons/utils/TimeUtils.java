package com.slime.slimecommons.utils;

import java.util.concurrent.TimeUnit;

public class TimeUtils {
    public static String format(long milliseconds){
        long millis = milliseconds;
        String output = "";

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis = millis - TimeUnit.DAYS.toMillis(days);

        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis = millis - TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis = millis - TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        if (days > 1){
            output += days + " giorni ";
        }
        else if(days == 1) {
            output += days + " giorno ";
        }

        if (hours > 1) {
            output += hours + " ore ";
        }
        else if (hours == 1) {
            output += hours + " ora ";
        }

        if (minutes > 1) {
            output += minutes + " minuti ";
        }
        else if (minutes == 1) {
            output += minutes + " minuto ";
        }

        if (seconds > 1) {
            output += seconds + " secondi ";
        }
        else if (seconds == 1) {
            output += seconds + " secondo ";
        }

        if (output.isEmpty()) {
            return "0 secondi";
        }

        return output.trim();
    }

    public static String shortFormat(long milliseconds){
        long millis = milliseconds;
        String output = "";

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis = millis - TimeUnit.DAYS.toMillis(days);

        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis = millis - TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis = millis - TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        if (days > 1){
            output += days + "d ";
        }
        else if(days == 1) {
            output += days + "d ";
        }

        if (hours > 1) {
            output += hours + "h ";
        }
        else if (hours == 1) {
            output += hours + "h ";
        }

        if (minutes > 1) {
            output += minutes + "m ";
        }
        else if (minutes == 1) {
            output += minutes + "m ";
        }

        if (seconds > 1) {
            output += seconds + "s ";
        }
        else if (seconds == 1) {
            output += seconds + "s ";
        }

        if (output.isEmpty()) {
            return "0s";
        }

        return output.trim();
    }
}
