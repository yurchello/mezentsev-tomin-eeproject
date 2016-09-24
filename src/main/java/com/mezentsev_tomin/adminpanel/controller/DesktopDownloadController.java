package com.mezentsev_tomin.adminpanel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mezentsev.Y on 9/24/2016.
 */
@Controller
public class DesktopDownloadController {

    private static String DOWNLOAD_WINDOWS_DESKTOP_LOCATION = "C:/desktop_dude/windows/";
    private static String DOWNLOAD_IOS_DESKTOP_LOCATION = "C:/desktop_dude/ios/";
    private static String DOWNLOAD_ANDROID_DESKTOP_LOCATION = "C:/desktop_dude/android/";

    @RequestMapping(value = { "/download"}, method = RequestMethod.GET)
    public String downloadPage(ModelMap model) {
        model.addAttribute("windowsFiles", getFilesList(DOWNLOAD_WINDOWS_DESKTOP_LOCATION));
        model.addAttribute("iosFiles", getFilesList(DOWNLOAD_IOS_DESKTOP_LOCATION));
        model.addAttribute("androidFiles", getFilesList(DOWNLOAD_ANDROID_DESKTOP_LOCATION));
        return "download";
    }

    @RequestMapping(value = { "/download/windows-{file}"}, method = RequestMethod.GET)
    public void downloadWindows(HttpServletResponse response, @PathVariable("file") String file) {
        File fileDownload = new File(DOWNLOAD_WINDOWS_DESKTOP_LOCATION,file);
        try {
            getFile(response, fileDownload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = { "/download/android-{file}"}, method = RequestMethod.GET)
    public void downloadAndroid(HttpServletResponse response, @PathVariable("file") String file) {
        File fileDownload = new File(DOWNLOAD_ANDROID_DESKTOP_LOCATION,file);
        try {
            getFile(response, fileDownload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = { "/download/ios-{file}"}, method = RequestMethod.GET)
    public void downloadIOS(HttpServletResponse response, @PathVariable("file") String file) {
        File fileDownload = new File(DOWNLOAD_IOS_DESKTOP_LOCATION,file);
        try {
            getFile(response, fileDownload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getFile(HttpServletResponse response, File fileDownload) throws IOException {
        InputStream is = new FileInputStream(fileDownload);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + fileDownload.getName() + "\"");
        OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        os.flush();
        os.close();
        is.close();
    }

    private List<String> getFilesList(String folder){
        File file = new File(folder);
        return Arrays.asList(file.list());
    }
}
