package com.example.video.util;


import com.example.video.global.GlobalMy;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static void writeToLocal(MultipartFile file, String fileLocation) throws IOException {
        File fil=new File(GlobalMy.LOCATION);
        if(!fil.exists())
        {
            fil.mkdir();
        }
        BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(new File(fileLocation)));
        out.write(file.getBytes());
        out.flush();
        out.close();
    }


    public static void deleteFile(String fileLocation) {
            File file = new File(fileLocation);
            if (file.exists() && file.isFile()){
                file.delete();
            }
    }
}
