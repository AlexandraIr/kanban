package services;

import java.io.*;
import java.util.Scanner;

public class DateProperties {

    public static void changeFile(String fileName, String id) throws Exception{
        FileWriter fr = new FileWriter("C:/Users/ADM/IdeaProjects/kanban_double/src/main/resources/file/" + fileName + ".txt");
        fr.write(id);
        fr.close();
    }

    public static String getFile(String fileName) throws Exception{
        FileReader  fr = new FileReader ("C:/Users/ADM/IdeaProjects/kanban_double/src/main/resources/file/" + fileName + ".txt");
        Scanner scan = new Scanner(fr);
        String string = scan.nextLine();
        fr.close();
        return string;
    }
}
