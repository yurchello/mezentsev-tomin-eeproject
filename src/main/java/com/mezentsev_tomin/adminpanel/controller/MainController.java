package com.mezentsev_tomin.adminpanel.controller;


import com.mezentsev_tomin.adminpanel.model.FileBucket;
import com.mezentsev_tomin.adminpanel.model.User;
import com.mezentsev_tomin.adminpanel.model.UserProfile;
import com.mezentsev_tomin.adminpanel.model.vocabulary.Word;
import com.mezentsev_tomin.adminpanel.model.vocabulary.WordsGroup;
import com.mezentsev_tomin.adminpanel.service.UserProfileService;
import com.mezentsev_tomin.adminpanel.service.UserService;
import com.mezentsev_tomin.adminpanel.service.WordGroupService;
import com.mezentsev_tomin.adminpanel.service.WordService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.*;
/**
 * Created by Mezentsev.Y on 7/17/2016.
 */
@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class MainController {

    static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserService userService;

    @Autowired
    WordGroupService wordGroupService;

    @Autowired
    WordService wordService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;



    private static String UPLOAD_LOCATION="C:/aaa/";

//    @Autowired
//    FileValidator fileValidator;
//
//    @Autowired
//    MultiFileValidator multiFileValidator;

    //@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/avatarUpload", method = RequestMethod.POST)
    public void addAdvert(@RequestParam(name = "mainImage", required = false) MultipartFile mainImage) throws IOException {
        String ssoId = getSSOIdifAutentificated();
        String fileName = ssoId + "." + FilenameUtils.getExtension(mainImage.getOriginalFilename());
        String path = saveFile(mainImage, UPLOAD_LOCATION, fileName);
        User user = userService.findBySSO(ssoId);
        user.setPhoto(path);
        userService.updateUser(user);
        logger.info("Avatar path saved: ", path);
    }

    private String saveFile(MultipartFile multipartFile, String dirName, String fileName) throws IOException {
        File file = new File(dirName, fileName);
        FileCopyUtils.copy(multipartFile.getBytes(), file);
        return file.getPath();
    }

    /**
     * checking password length
     * @param password
     * @return
     */
    @RequestMapping(value = { "/checkStrength" }, method = RequestMethod.GET, produces = {"text/html"})
    public @ResponseBody String checkStrength(String password){
        final int WEAK = 1;
        final int FEAR = 5;
        final int STRONG = 7;
        int pasLen = password.length();
        if (pasLen>= WEAK && pasLen<FEAR){
            return "1";
        }else if (pasLen>= FEAR && pasLen<STRONG){
            return "2";
        }else if (pasLen>= STRONG){
            return "3";
        }
        return "";
    }


    @RequestMapping(value = { "/confirmPassword" }, method = RequestMethod.GET, produces = {"text/html"})
    public @ResponseBody String confirmPassword(String password1, String password2){
        return password1 + password2;
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) {
        if (user.getUserProfiles() == null || user.getUserProfiles().size() == 0){
            ArrayList<UserProfile> roles = (ArrayList<UserProfile>) model.get("roles");
            setUserRoles(user, roles.get(2));
        }

        boolean hasProfileError = false;
        List<ObjectError> allErrors =  result.getAllErrors();
        for (Object error : allErrors) {
            FieldError fieldError = (FieldError) error;
            if ("userProfiles".equals(fieldError.getField())) {
                hasProfileError = true;
            }
        }

        if (result.hasErrors() && !(result.getAllErrors().size() == 1 && hasProfileError)) {
            return "registration";
        }

        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }

        userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
        return "redirect:/user-"+user.getSsoId();
    }



    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "home2";
        } else {
            String ssoId = getSSOIdifAutentificated();
            return "redirect:/user-" + ssoId;
        }

    }

    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        String ssoId = getSSOIdifAutentificated();
        return "redirect:/user-" + ssoId;
    }




    @RequestMapping(value = { "/user-{ssoId}" }, method = RequestMethod.GET)
    public String customAccount(@PathVariable String ssoId, ModelMap model){
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("loggedinuser", getPrincipal());
        String image = getRawFileFromDrive(user.getPhoto());
        model.addAttribute("photoPath", image);
        return "account";
    }

    //@PreAuthorize("isAuthenticated() and hasPermission(#ssoId, 'isProfileOwner')")
    @RequestMapping(value = {"/editUser-{ssoId}"}, method = RequestMethod.GET)
    public String editProfile(@PathVariable String ssoId, ModelMap model){
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);

        //List<WordsGroup> list = wordGroupService.findAllGroups();
        //wordGroupService.createGroup("English", user);
        String image = getRawFileFromDrive(user.getPhoto());
        model.addAttribute("photoPath", image);
        model.addAttribute("loggedinuser", getPrincipal());
        return "editProfile";
    }


    @RequestMapping(value = {"/editUser-{ssoId}"}, method = RequestMethod.POST)
    public String updateProfile(@Valid User user, BindingResult result,
                                ModelMap model, HttpServletRequest request){
        if (user.getUserProfiles() == null || user.getUserProfiles().size() == 0){
            setUserRoles(user, (List<UserProfile>) model.get("roles"));
        }

        userService.updateUser(user);
        return "redirect:/user-{ssoId}";
    }

//    @RequestMapping(value = {"/vocabulary-{ssoId}"}, method = RequestMethod.GET)
//    public String vocabularyView(@PathVariable String ssoId, ModelMap model){
//        List<WordsGroup> list = wordGroupService.findAllGroups();
//        model.addAttribute("wordsGroups", list);
//        return "vocabulary";
//    }

    @RequestMapping(value = {"/groupsList-{ssoId}"}, method = RequestMethod.GET)
    public String groupView(@PathVariable String ssoId, ModelMap model){
        User user = userService.findBySSO(ssoId);
        List<WordsGroup> list = wordGroupService.findAllUserGroups(user);
        model.addAttribute("user", user);
        model.addAttribute("wordsGroups", list);
        return "groupsList";
    }

    @RequestMapping(value = {"delete-group"}, method = RequestMethod.GET)
    public String deleteGroup(@RequestParam Integer id, @RequestParam String ssoId, ModelMap model){
        WordsGroup wordsGroup = wordGroupService.findById(id);
        wordGroupService.deleteGroup(wordsGroup);
        return "redirect:/groupsList-" + ssoId;
    }

    @RequestMapping(value = {"view-group"}, method = RequestMethod.GET)
    public String editGroup(@RequestParam Integer id, @RequestParam String ssoId, ModelMap model){
        WordsGroup wordsGroup = wordGroupService.findById(id);
        User user = userService.findBySSO(ssoId);
        model.addAttribute("wordsGroup",wordsGroup);
        model.addAttribute("user", user);
        //Request.setCharacterEncoding
        return "viewGroup";
    }

    @RequestMapping(value = {"/newGroup"}, method = RequestMethod.GET)
    public String createGroup(@RequestParam String ssoId, ModelMap model){
        model.addAttribute("ssoId", ssoId);
        model.addAttribute("wordsGroup", new WordsGroup());
        return "newGroup";
    }

    @RequestMapping(value = {"/newGroup"}, method = RequestMethod.POST)
    public String createGroupAdd(WordsGroup wordsGroup, String ssoId, ModelMap model, RedirectAttributes redirectAttributes){
        User user = userService.findBySSO(ssoId);
        wordGroupService.createGroup(wordsGroup, user);
        redirectAttributes.addAttribute("id", wordsGroup.getId());
        redirectAttributes.addAttribute("ssoId", ssoId);
        return "redirect:/view-group";
    }

    @RequestMapping(value = {"newWord"}, method = RequestMethod.GET)
    public String newWord(@RequestParam String id, @RequestParam String ssoId, ModelMap model){
        model.addAttribute("ssoId", ssoId);
        model.addAttribute("wordsGroupId",id);
        Word word = new Word();
        model.addAttribute("word",word);
        return "newWord";
    }

    @RequestMapping(value = {"/newWord"}, method = RequestMethod.POST )
    public String newWordAdd(Word word, Integer wordsGroupId, String ssoId, ModelMap model, RedirectAttributes redirectAttributes){
        WordsGroup wordsGroup = wordGroupService.findById(wordsGroupId);
        word.setId(null);
        wordGroupService.addWordToGroup(word,wordsGroup);
        redirectAttributes.addAttribute("wordsGroupId", wordsGroupId);
        redirectAttributes.addAttribute("ssoId", ssoId);
        return "redirect:/view-group";
    }

    @RequestMapping(value = {"/editWord"}, method = RequestMethod.GET)
    public String editWord(@RequestParam Integer wordId, @RequestParam Integer groupId,@RequestParam String ssoId, ModelMap model){
        Word word = wordService.findById(wordId);
        model.addAttribute("ssoId", ssoId);
        model.addAttribute("wordsGroupId",groupId);
        model.addAttribute("word",word);
        return "editWord";
    }

    @RequestMapping(value = {"/editWord"}, method = RequestMethod.POST )
    public String editDone( Word word, Integer wordsGroupId, String ssoId,ModelMap model, RedirectAttributes redirectAttributes){
        wordService.update(word);
        redirectAttributes.addAttribute("id", wordsGroupId);
        redirectAttributes.addAttribute("ssoId", ssoId);
        return "redirect:/view-group";
    }

    @RequestMapping(value = {"/deleteWord"}, method = RequestMethod.GET)
    public String deleteWord(Integer wordId, Integer groupId, String ssoId,  RedirectAttributes redirectAttributes){
        Word word = wordService.findById(wordId);
        WordsGroup wordsGroup = wordGroupService.findById(groupId);
        wordGroupService.removeWordFromGroup(word, wordsGroup);
        redirectAttributes.addAttribute("id", groupId);
        redirectAttributes.addAttribute("ssoId", ssoId);
        return "redirect:/view-group";
    }

    @RequestMapping(value = { "/changePhotoUser-{ssoId}" }, method = RequestMethod.GET)
    public String changePhotoUser(@PathVariable String ssoId, ModelMap model, String photoPath){
        FileBucket fileModel = new FileBucket();

        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("fileBucket", fileModel);
        String image = getRawFileFromDrive(photoPath);
        model.addAttribute("photoPath", image);
        return "singleFileUploader";
    }



    private String getRawFileFromDrive(String path){
        if (path==null)return null;
        File file = new File(path);
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private String getRawFileFromDrive(byte[] fileBytes){
            byte[] encoded=
                    org.apache.commons.codec.binary.Base64.encodeBase64(fileBytes);
            return new String(encoded);
    }

    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    private void setUserRoles(User user, List<UserProfile> userProfiles){
        Set<UserProfile> set = new HashSet<>();
        for (UserProfile userProfile: userProfiles){
            if (userProfile != null){
                set.add(userProfile);
            }
        }
        user.setUserProfiles(set);
    }

    private void setUserRoles(User user, UserProfile... userProfiles){
        Set<UserProfile> set = new HashSet<>();
        for (UserProfile userProfile: userProfiles){
            if (userProfile != null){
                set.add(userProfile);
            }
        }
        user.setUserProfiles(set);
    }

    private String getSSOIdifAutentificated(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
