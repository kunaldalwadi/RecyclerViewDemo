package com.dalwadibrothers.kunal.recyclerviewdemo.db;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class Converters {


    /*

    Marks a method as a type converter. A class can have as many @TypeConverter methods as it needs.

    Each converter method should receive 1 parameter and have non-void return type.

    You only have to define one converter pair for each type of data.
    For example : in this app we have two places which have List<> type of variable,
    1. domains and 2. web_pages
    we do not have to define twice for the same.

     */
    @TypeConverter
    public static List<String> stringToList(String domain){
        List<String> listOfDomains = new ArrayList<>();
        listOfDomains.add(domain);
        return listOfDomains;
    }

    @TypeConverter
    public static String listToString(List<String> domains){
        return domains.toString();
    }
}
