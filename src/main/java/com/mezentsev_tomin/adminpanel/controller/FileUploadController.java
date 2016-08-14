package com.mezentsev_tomin.adminpanel.controller;

import com.mezentsev_tomin.adminpanel.model.FileBucket;
import com.mezentsev_tomin.adminpanel.model.MultiFileBucket;
import com.mezentsev_tomin.adminpanel.utils.FileValidator;
import com.mezentsev_tomin.adminpanel.utils.MultiFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mezentsev.Y on 8/13/2016.
 */
//@Controller
public class FileUploadController {

    private static String UPLOAD_LOCATION="C:/aaa/";

    @Autowired
    FileValidator fileValidator;

    @Autowired
    MultiFileValidator multiFileValidator;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @InitBinder("multiFileBucket")
    protected void initBinderMultiFileBucket(WebDataBinder binder) {
        binder.setValidator(multiFileValidator);
    }


    @RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "singleFileUploader";
    }

    @RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
    public String singleFileUpload(@Valid FileBucket fileBucket,
                                   BindingResult result, ModelMap model) throws IOException {

        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            // Now do something with file...
            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File( UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            return "success";
        }
    }

}
