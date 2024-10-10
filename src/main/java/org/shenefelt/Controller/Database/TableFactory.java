package org.shenefelt.Controller.Database;

import java.util.Arrays;
import java.util.List;

/**
 * Factory design pattern for DB connections.
 */
public class TableFactory
{
    private final static List<String> DB_URLS = Arrays.asList("1","2","3"); // put real URLS
    public static Database getTableInstance(String tName)
    {
        return switch (tName) {
            case "Tasks" -> new TaskDB();
            case "Users" -> new UserRepoWorker(DB_URLS.get(1));
            default -> throw new IllegalArgumentException("Invalid table name: " + tName);
        };
    }
}
