package org.shenefelt.Helpers;

import org.shenefelt.Model.User;

import java.util.Scanner;

import static java.lang.System.out;

public class InputCollector
{
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputCollector() { }

    public static User collectUserInfo()
    {
        String username, password;
        User u = null;

        out.println("Please enter your username: ");
        username = SCANNER.nextLine().strip();

        out.println("Please enter your password: ");
        password = SCANNER.nextLine().strip();

        return new User(username, password);

    }

    public static int collectMenuChoice()
    {
        out.println("Please enter a menu choice: ");
        return SCANNER.nextInt();
    }
}
