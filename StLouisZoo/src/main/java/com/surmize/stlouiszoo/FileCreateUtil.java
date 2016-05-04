package com.surmize.stlouiszoo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by davidbogue on 12/1/13.
 */
public class FileCreateUtil {

    private static String filePath="/Users/davidbogue/AndroidStudioProjects/MyApplication/StLouisZoo/src/main/res/raw/";

    public static void main(String args[]) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath+"animal_list.txt");

            Scanner listScanner = new Scanner(inputStream);
            while (listScanner.hasNextLine()) {
                String line = listScanner.nextLine();
                if (line.startsWith("#")) continue;  // treat the # as a comment
                String fileName = getAnimalDetailsName(line);
                System.out.println(fileName);
                PrintWriter out = new PrintWriter(filePath+fileName);
                out.close();
            }
            listScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static String getAnimalDetailsName(String name) {
        return "details_"+getAnimalNameForFile(name)+".txt";
    }

    private static String getAnimalNameForFile(String name){
        String imageName = name;
        imageName = imageName.replaceAll("'", "")
                .replaceAll("-", "")
                .replaceAll("\\.", "")
                .replaceAll(" ", "_");
        return imageName.toLowerCase();
    }
}
