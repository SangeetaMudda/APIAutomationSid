package helper;

import java.io.File;
import java.util.Date;

public class Helper {
    public static void CreateFolder(String path){
        // File is a class inside java.io package
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }else
            {
            System.out.println("Folder already exists");
            }
    }

    public static String TimeStamp(){
        Date now = new Date();
        String Timesstamp = now.toString().replace(":", "_");
        return Timesstamp;
    }

}
