package com.airplaneSoft.translateMeDude.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Mezentsev.Y on 10/7/2016.
 */
public class FileUtilities {
    public static String saveFile(MultipartFile multipartFile, String dirName, String fileName) throws IOException {
        File file = new File(dirName, fileName);
        FileCopyUtils.copy(multipartFile.getBytes(), file);
        return file.getPath();
    }

    public static String getRawFileFromDrive(String path) throws IOException {
        if (path==null)return null;
        File file = new File(path);

            FileInputStream fis=new FileInputStream(file);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            int b;
            byte[] buffer = new byte[1024];
            while((b=fis.read(buffer))!=-1){
                bos.write(buffer,0,b);
            }
            byte[] fileBytes=bos.toByteArray();
            fis.close();
            bos.close();
            byte[] encoded=
                    org.apache.commons.codec.binary.Base64.encodeBase64(fileBytes);
            return new String(encoded);
    }

    public static String getRawFileFromDrive(byte[] fileBytes){
        byte[] encoded=
                org.apache.commons.codec.binary.Base64.encodeBase64(fileBytes);
        return new String(encoded);
    }

}
