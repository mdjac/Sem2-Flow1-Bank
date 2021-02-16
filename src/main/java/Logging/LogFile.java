package Logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public abstract class LogFile {
    private static int day;
    private static int month;
    private static int year;
    private static LocalDateTime localdatetime;
    private static String filename;
    private static void CreateFile() {
        localdatetime = LocalDateTime.now();
        //localdatetime = localdatetime.plusDays(1); //To hack next day simulation
        day = localdatetime.getDayOfMonth();
        month = localdatetime.getMonthValue();
        year = localdatetime.getYear();
        try {
            filename = year + "-" + month + "-" + day + ".txt";
            File myObj = new File(filename);
            //File myObj = new File("C:\\Users\\mikke\\filename.txt"); //Der skal være dobbelt backslash, da single backslash er escape character
            if (myObj.createNewFile()) {
                System.out.println("LogFile created: " + myObj.getName() + "\n" + myObj.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void writeToLog(String logMessage) {
        try {
            CreateFile();
            localdatetime = LocalDateTime.now();
            FileWriter myWriter = new FileWriter(filename, true);
            myWriter.write(""+localdatetime.getHour()+ ":" +localdatetime.getMinute()+":"+localdatetime.getSecond()+" "+ logMessage + System.lineSeparator());
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

}

