package com.airplaneSoft.translateMeDude.controller;

import com.airplaneSoft.translateMeDude.exceptions.ResourceNotFoundException;
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
 * This controller provide to download application for desktop
 */
@Controller
public class DesktopDownloadController extends BaseController {

    private static String DOWNLOAD_WINDOWS_DESKTOP_LOCATION = "C:/desktop_dude/windows/";
    private static String DOWNLOAD_IOS_DESKTOP_LOCATION = "C:/desktop_dude/ios/";
    private static String DOWNLOAD_ANDROID_DESKTOP_LOCATION = "C:/desktop_dude/android/";

    /**
     *This method provide download page to view
     */
    @RequestMapping(value = { "/download"}, method = RequestMethod.GET)
    public String downloadPage(ModelMap model) {
        model.addAttribute("windowsFiles", getFilesList(DOWNLOAD_WINDOWS_DESKTOP_LOCATION));
        model.addAttribute("iosFiles", getFilesList(DOWNLOAD_IOS_DESKTOP_LOCATION));
        model.addAttribute("androidFiles", getFilesList(DOWNLOAD_ANDROID_DESKTOP_LOCATION));
        return "download";
    }

    @RequestMapping(value = { "/download/windows-{file}"}, method = RequestMethod.GET)
    public String downloadWindows(HttpServletResponse response, @PathVariable("file") String file,ModelMap model)  {
        return getFile(response,DOWNLOAD_WINDOWS_DESKTOP_LOCATION,file, model);
    }

    @RequestMapping(value = { "/download/android-{file}"}, method = RequestMethod.GET)
    public String downloadAndroid(HttpServletResponse response, @PathVariable("file") String file,ModelMap model){
        return getFile(response,DOWNLOAD_ANDROID_DESKTOP_LOCATION,file, model);
    }

    @RequestMapping(value = { "/download/ios-{file}"}, method = RequestMethod.GET)
    public String downloadIOS(HttpServletResponse response, @PathVariable("file") String file,ModelMap model)  {
        return getFile(response,DOWNLOAD_IOS_DESKTOP_LOCATION,file, model);
    }


    /**
     *This method provide to redirect file output stream to HttpServletResponse
     */
    private String getFile(HttpServletResponse response,String filesLocation, String file, ModelMap model) {
        try {
            File fileDownload = new File(filesLocation ,file);
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
            return "";
        }catch (IOException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    private List<String> getFilesList(String folder){
        File file = new File(folder);
        List<String> fileList;
        try {
            fileList = Arrays.asList(file.list());
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return fileList;
    }
}
