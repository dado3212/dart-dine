package com.example.amir.dhp;

import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by alexbeals on 2/25/16.
 */
public class Util {
    public static String formattedMinutes(int total) {
        int minutes = total % 60;
        int hours = (total - minutes)/60;

        if (hours == 0) {
            if (minutes == 1)
                return minutes + " minute";
            else
                return minutes + " minutes";
        } else if (hours == 1) {
            if (minutes == 0)
                return hours + " hour";
            else if (minutes == 1)
                return hours + " hour, " + minutes + " minute";
            else
                return hours + " hour, " + minutes + " minutes";
        } else {
            if (minutes == 0)
                return hours + " hours";
            else if (minutes == 1)
                return hours + " hours, " + minutes + " minute";
            else
                return hours + " hours, " + minutes + " minutes";
        }
    }

    public static ArrayList<Integer> pickOptions(int count) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> options = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            options.add(i);
        }
        if (count <= 3) {
            return options;
        } else {
            Random rnd = new Random();
            for (int i = 0; i < 3; i++) {
                int index = rnd.nextInt(options.size());
                result.add(options.get(index));
                options.remove(index);
            }
        }
        return result;
    }

    public static ArrayList<MenuItem> getFocoMenu() {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        // Add menu items
        menuItems.add(new MenuItem("Olives", 5, 0, 0, 0, 0, 10, 0, true, true, true));
        menuItems.add(new MenuItem("Green Peas", 10, 0, 0, 0, 0, 0, 0, true, true, true));
        menuItems.add(new MenuItem("Kale Salad", 18, 0, 0, 0, 15, 20, 0, true, true, true));
        menuItems.add(new MenuItem("Caesar Salad", 80, 34, 34, 0, 5, 25, 8, false, true, false));
        menuItems.add(new MenuItem("Mushroom Soup", 120, 40, 40, 0, 9, 24, 0, true, true, true));
        menuItems.add(new MenuItem("Cream of Tomato Soup", 140, 45, 45, 0, 12, 18, 0, true, true, true));
        menuItems.add(new MenuItem("Mac and Cheese", 230, 70, 70, 0, 12, 40, 3, false, true, false));
        menuItems.add(new MenuItem("Stuffed Pasta", 290, 72, 72, 0, 12, 38, 9, false, true, false));
        menuItems.add(new MenuItem("Omelette ", 220, 64, 64, 0, 20, 40, 18, true, true, false));
        menuItems.add(new MenuItem("Boiled Eggs", 55, 21, 21, 0, 0, 0, 12, true, true, false));
        menuItems.add(new MenuItem("French Toast", 80, 34, 34, 0, 7, 21, 9, false, true, false));
        menuItems.add(new MenuItem("Feta Cheese", 39, 45, 45, 0, 0, 0, 0, true, true, false));
        menuItems.add(new MenuItem("Bacon", 220, 100, 80, 20, 10, 12, 10, true, false, false));
        menuItems.add(new MenuItem("Cheese Pizza", 210, 78, 78, 0, 20, 25, 14, false, true, false));
        menuItems.add(new MenuItem("Vegetarian Pizza", 240, 78, 78, 0, 20, 25, 14, false, true, false));
        menuItems.add(new MenuItem("Meat Lovers Pizza", 300, 110, 110, 0, 24, 30, 20, false, false, false));
        menuItems.add(new MenuItem("Pepperoni Pizza", 290, 95, 95, 0, 22, 28, 18, false, false, false));
        menuItems.add(new MenuItem("Steamed Broccoli", 20, 0, 0, 0, 0, 0, 0, true, true, true));
        menuItems.add(new MenuItem("Stuffed Bell Peppers", 170, 40, 40, 0, 12, 21, 7, true, true, true));
        menuItems.add(new MenuItem("Cajun Catfish", 234, 89, 89, 0, 21, 31, 22, true, false, false));
        menuItems.add(new MenuItem("Smoked Salmon", 253, 97, 97, 0, 28, 32, 21, true, true, false));
        menuItems.add(new MenuItem("Chicken Thigh", 320, 112, 112, 0, 13, 21, 28, true, true, false));
        menuItems.add(new MenuItem("Fried Chicken", 315, 251, 221, 30, 12, 45, 23, true, false, false));
        menuItems.add(new MenuItem("French Fries", 295, 147, 112, 35, 32, 60, 8, false, true, true));

        return menuItems;
    }

    public static ArrayList<MenuItem> getHopMenu() {
        return getFocoMenu();
    }

    public static ArrayList<MenuItem> getCollisMenu() {
        return getFocoMenu();
    }

    public static String[] getFocoTime() {
        // Format the time (current to onCreate)
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int currentMinutes = ((hour * 60) + minute);

        SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE");
        String week = weekFormat.format(Calendar.getInstance().getTime());

        String msg = "Foco is currently closed.";
        if (dayOfWeek == 0) { // Sunday
            if (currentMinutes < 480) { // 8 am
                msg += "  It will open again in " + Util.formattedMinutes(480 - currentMinutes) + ".";
            } else if (currentMinutes < 870) { // 2:30 pm
                msg = "Foco is open for another " + Util.formattedMinutes(870 - currentMinutes) + ".";
            } else if (currentMinutes < 1020) { // 5 pm
                msg += "  It will open again in " + Util.formattedMinutes(1020 - currentMinutes) + ".";
            } else if (currentMinutes < 1230) { // 8:30 pm
                msg = "Foco is open for another " + Util.formattedMinutes(1230 - currentMinutes) + ".";
            } else {
                msg += "  It will open again in " + Util.formattedMinutes(450 + (1440 - currentMinutes)) + ".";
            }
        } else if (dayOfWeek == 6) { // Saturday
            if (currentMinutes < 480) { // 8 am
                msg += "  It will open again in " + Util.formattedMinutes(480 - currentMinutes) + ".";
            } else if (currentMinutes < 630) { // 10:30 am
                msg = "Foco is open for another " + Util.formattedMinutes(630 - currentMinutes) + ".";
            } else if (currentMinutes < 660) { // 11 am
                msg += "  It will open again in " + Util.formattedMinutes(660 - currentMinutes) + ".";
            } else if (currentMinutes < 870) { // 2:30 pm
                msg = "Foco is open for another " + Util.formattedMinutes(870 - currentMinutes) + ".";
            } else if (currentMinutes < 1020) { // 5 pm
                msg += "  It will open again in " + Util.formattedMinutes(1020 - currentMinutes) + ".";
            } else if (currentMinutes < 1230) { // 8:30 pm
                msg = "Foco is open for another " + Util.formattedMinutes(1230 - currentMinutes) + ".";
            } else {
                msg += "  It will open again in " + Util.formattedMinutes(480 + (1440 - currentMinutes)) + ".";
            }
        } else {
            if (currentMinutes < 450) { // 7:30 am
                msg += "  It will open again in " + Util.formattedMinutes(450 - currentMinutes) + ".";
            } else if (currentMinutes < 630) { // 10:30 am
                msg = "Foco is open for another " + Util.formattedMinutes(630 - currentMinutes) + ".";
            } else if (currentMinutes < 660) { // 11 am
                msg += "  It will open again in " + Util.formattedMinutes(660 - currentMinutes) + ".";
            } else if (currentMinutes < 900) { // 3 pm
                msg = "Foco is open for another " + Util.formattedMinutes(900 - currentMinutes) + ".";
            } else if (currentMinutes < 1020) { // 5 pm
                msg += "  It will open again in " + Util.formattedMinutes(1020 - currentMinutes) + ".";
            } else if (currentMinutes < 1230) { // 8:30 pm
                msg = "Foco is open for another " + Util.formattedMinutes(1230 - currentMinutes) + ".";
            } else {
                msg += "  It will open again in " + Util.formattedMinutes(450 + (dayOfWeek == 5 ? 30 : 0) + (1440 - currentMinutes)) + ".";
            }
        }

        return new String[] {"Today is " + week + ".", msg};
    }

    public static String [] getCollisTime() {
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int currentMinutes = ((hour * 60) + minute);

        SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE");
        String week = weekFormat.format(Calendar.getInstance().getTime());

        String msg = "Collis is currently closed.";
        if (dayOfWeek == 0 || dayOfWeek == 6) { // Saturday or Sunday
            if (currentMinutes < 120) { // 2 am
                msg = "Collis is open for another " + Util.formattedMinutes(120 - currentMinutes) + ".";
            } else if (currentMinutes < 1330) { // 9:30 pm
                msg += "  It will open again in " + Util.formattedMinutes(1330 - currentMinutes) + ".";
            } else {
                msg = "Collis is open for another " + Util.formattedMinutes(90 + (dayOfWeek == 6 ? 30 : 0) + (1440 - currentMinutes)) + ".";
            }
        } else {
            if (currentMinutes < 90) { // 1:30 am
                msg = "Collis is open for another " + Util.formattedMinutes(90 - currentMinutes) + ".";
            } else if (currentMinutes < 420) { // 7 am
                msg += "  It will open again in " + Util.formattedMinutes(420 - currentMinutes) + ".";
            } else if (currentMinutes < 1200) { // 8 pm
                msg = "Collis is open for another " + Util.formattedMinutes(1200 - currentMinutes) + ".";
            } else if (currentMinutes < 1330) { // 8 pm
                msg += "  It will open again in " + Util.formattedMinutes(1330 - currentMinutes) + ".";
            } else {
                msg = "Collis is open for another" + Util.formattedMinutes(90 + (dayOfWeek == 5 ? 30 : 0) + (1440 - currentMinutes)) + ".";
            }
        }

        return new String [] {"Today is " + week + ".", msg};
    }

    public static String [] getHopTime() {
        // Format the time (current to onCreate)
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int currentMinutes = ((hour * 60) + minute);

        SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE");
        String week = weekFormat.format(Calendar.getInstance().getTime());

        String msg = "The Hop is currently closed.";
        if (dayOfWeek == 0 || dayOfWeek == 6) { // Saturday or Sunday
            if (dayOfWeek == 6 && currentMinutes < 30) { // 12:30 am
                msg = "The Hop is open for another " + Util.formattedMinutes(30 - currentMinutes) + ".";
            } else if (currentMinutes < 630) { // 10:30 am
                msg += "  It will open again in " + Util.formattedMinutes(630 - currentMinutes) + ".";
            } else { // midnight
                msg = "The Hop is open for another " + Util.formattedMinutes(1440 - currentMinutes) + ".";
            }
        } else {
            if (currentMinutes < 480) { // 8 am
                msg += "  It will open breakfast items in " + Util.formattedMinutes(480 - currentMinutes) + ".";
            } else if (currentMinutes < 660) { // 11 am
                msg = "The Hop is open for breakfast items, and will switch to full service in " + Util.formattedMinutes(660 - currentMinutes) + ".";
            } else {
                msg = "The Hop is open for another " + Util.formattedMinutes(30 + 1440 - currentMinutes) + ".";
            }
        }
        return new String [] {"Today is " + week + ".", msg};
    }
}
