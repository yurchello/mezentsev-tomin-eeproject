package com.airplaneSoft.translateMeDude.controller;

/**
 * Created by Mezentsev.Y on 8/13/2016.
 */
//@Controller
public class FileUploadController {

//    private static String UPLOAD_LOCATION="C:/aaa/";
//
//    @Autowired
//    FileValidator fileValidator;
//
//    @Autowired
//    MultiFileValidator multiFileValidator;
//
//    @InitBinder("fileBucket")
//    protected void initBinderFileBucket(WebDataBinder binder) {
//        binder.setValidator(fileValidator);
//    }
//
//    @InitBinder("multiFileBucket")
//    protected void initBinderMultiFileBucket(WebDataBinder binder) {
//        binder.setValidator(multiFileValidator);
//    }
//
//
//    @RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
//    public String getSingleUploadPage(ModelMap models) {
//        FileBucket fileModel = new FileBucket();
//        models.addAttribute("fileBucket", fileModel);
//        return "singleFileUploader";
//    }
//
//    @RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
//    public String singleFileUpload(@Valid FileBucket fileBucket,
//                                   BindingResult result, ModelMap models) throws IOException {
//
//        if (result.hasErrors()) {
//            System.out.println("validation errors");
//            return "singleFileUploader";
//        } else {
//            System.out.println("Fetching file");
//            MultipartFile multipartFile = fileBucket.getFile();
//
//            // Now do something with file...
//            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File( UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
//            String fileName = multipartFile.getOriginalFilename();
//            models.addAttribute("fileName", fileName);
//            return "success";
//        }
//    }

}
