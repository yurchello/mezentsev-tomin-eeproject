package com.airplaneSoft.translateMeDude.controller;


import com.airplaneSoft.translateMeDude.models.FileBucket;
import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.UserProfile;
import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.service.UserProfileService;
import com.airplaneSoft.translateMeDude.service.WordGroupService;
import com.airplaneSoft.translateMeDude.service.WordService;
import com.airplaneSoft.translateMeDude.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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


//    @RequestMapping(value = { "/confirmPassword" }, method = RequestMethod.GET, produces = {"text/html"})
//    public @ResponseBody String confirmPassword(String password1, String password2){
//        return password1 + password2;
//    }

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
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationSuccess";
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
        if (!isLoggedInUser()) return "accessDeniedUnregistered";
        User user = userService.findBySSO(ssoId);
        if (user == null) return "page404";
        model.addAttribute("user", user);
        model.addAttribute("loggedinuser", getPrincipal());
        String image = getRawFileFromDrive(user.getPhoto());
        model.addAttribute("photoPath", image);
        return "account";
    }

    @RequestMapping(value = {"/editUser-{ssoId}"}, method = RequestMethod.GET)
    public String editProfile(@PathVariable String ssoId, ModelMap model){
        if (!isAccountOwner(ssoId)) return "accessDeniedHard";
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        String image = getRawFileFromDrive(user.getPhoto());
        model.addAttribute("photoPath", image);
        model.addAttribute("loggedinuser", getPrincipal());
        return "editProfile";
    }

    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    @PreAuthorize("")
    public String test(){
        return "errorOccurred";
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

    @RequestMapping(value = {"/groupsList-{ssoId}"}, method = RequestMethod.GET)
    public String groupView(@PathVariable String ssoId, ModelMap model){
        if (!isLoggedInUser()) return "accessDeniedUnregistered";
        User user = userService.findBySSO(ssoId);
        List<WordsGroup> list = wordGroupService.findAllUserGroups(user);
        model.addAttribute("user", user);
        model.addAttribute("wordsGroups", list);
        model.addAttribute("edit", isAccountOwner(ssoId));
        return "groupsList";
    }

    @RequestMapping(value = {"delete-group"}, method = RequestMethod.GET)
    public String deleteGroup(@RequestParam Integer wordsGroupId, @RequestParam String ssoId, ModelMap model){
        if (!isAccountOwner(ssoId)) return "accessDeniedHard";
        WordsGroup wordsGroup = wordGroupService.findById(wordsGroupId);
        wordGroupService.deleteGroup(wordsGroup);
        return "redirect:/groupsList-" + ssoId;
    }

    @RequestMapping(value = {"view-group"}, method = RequestMethod.GET)
    public String editGroup(@RequestParam Integer wordsGroupId, @RequestParam String ssoId, ModelMap model){
        if (!isLoggedInUser()) return "accessDeniedUnregistered";
        WordsGroup wordsGroup = wordGroupService.findById(wordsGroupId);
        User user = userService.findBySSO(ssoId);
        model.addAttribute("wordsGroup",wordsGroup);
        model.addAttribute("user", user);
        model.addAttribute("edit", isAccountOwner(ssoId));
        return "viewGroup";
    }

    @RequestMapping(value = {"/newGroup"}, method = RequestMethod.GET)
    public String createGroup(@RequestParam String ssoId, ModelMap model){
        if (!isAccountOwner(ssoId)) return "accessDeniedHard";
        model.addAttribute("ssoId", ssoId);
        model.addAttribute("wordsGroup", new WordsGroup());
        return "newGroup";
    }

    @RequestMapping(value = {"/newGroup"}, method = RequestMethod.POST)
    public String createGroupAdd(@Valid WordsGroup wordsGroup, BindingResult result, String ssoId, ModelMap model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "newGroup";
        }
        User user = userService.findBySSO(ssoId);
        wordGroupService.createGroup(wordsGroup, user);
        redirectAttributes.addAttribute("wordsGroupId", wordsGroup.getId());
        redirectAttributes.addAttribute("ssoId", ssoId);
        return "redirect:/view-group";
    }

    @RequestMapping(value = {"newWord"}, method = RequestMethod.GET)
    public String newWord(@RequestParam String wordsGroupId, @RequestParam String ssoId, ModelMap model){
        if (!isAccountOwner(ssoId)) return "accessDeniedHard";
        model.addAttribute("ssoId", ssoId);
        model.addAttribute("wordsGroupId",wordsGroupId);
        Word word = new Word();
        model.addAttribute("word",word);
        return "newWord";
    }

    @RequestMapping(value = {"/newWord"}, method = RequestMethod.POST )
    public String newWordAdd(@Valid Word word, BindingResult result, Integer wordsGroupId, String ssoId, ModelMap model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "newWord";
        }
        WordsGroup wordsGroup = wordGroupService.findById(wordsGroupId);
        word.setId(null);
        wordGroupService.addWordToGroup(word,wordsGroup);
        redirectAttributes.addAttribute("wordsGroupId", wordsGroupId);
        redirectAttributes.addAttribute("ssoId", ssoId);
        return "redirect:/view-group";
    }

    @RequestMapping(value = {"/editWord"}, method = RequestMethod.GET)
    public String editWord(@RequestParam Integer wordId, @RequestParam Integer wordsGroupId,@RequestParam String ssoId, ModelMap model){
        if (!isAccountOwner(ssoId)) return "accessDeniedHard";
        Word word = wordService.findById(wordId);
        model.addAttribute("ssoId", ssoId);
        model.addAttribute("wordsGroupId",wordsGroupId);
        model.addAttribute("word",word);
        return "editWord";
    }

    @RequestMapping(value = {"/editWord"}, method = RequestMethod.POST )
    public String editDone(@Valid Word word, BindingResult result, Integer wordsGroupId, String ssoId,ModelMap model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "editWord";
        }
        wordService.update(word);
        redirectAttributes.addAttribute("wordsGroupId", wordsGroupId);
        redirectAttributes.addAttribute("ssoId", ssoId);
        return "redirect:/view-group";
    }

    @RequestMapping(value = {"/deleteWord"}, method = RequestMethod.GET)
    public String deleteWord(Integer wordId, Integer wordsGroupId, String ssoId,  RedirectAttributes redirectAttributes){
        if (!isAccountOwner(ssoId)) return "accessDeniedHard";
        Word word = wordService.findById(wordId);
        WordsGroup wordsGroup = wordGroupService.findById(wordsGroupId);
        wordGroupService.removeWordFromGroup(word, wordsGroup);
        redirectAttributes.addAttribute("wordsGroupId", wordsGroupId);
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

    @RequestMapping(value="/usersList", method = RequestMethod.GET)
    public String userList(ModelMap model){
        if (!isLoggedInUser()) return "accessDeniedUnregistered";
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        List<String> images = new ArrayList<>();
        for (User user: users){
            String image = getRawFileFromDrive(user.getPhoto());
            images.add(image);
        }
        model.addAttribute("images", images);
        return "usersList";
    }


    private boolean isAccountOwner(String ssoId){
        if (ssoId == null) return false;
        return ssoId.equals(getSSOIdifAutentificated());
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
            logger.debug("Life not found",e);
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

    @RequestMapping(value = {"/testGet"}, method = RequestMethod.GET)
    public String testGet(){
        System.out.println();
        return "testGet";
    }

    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/usersList";
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

    private boolean isLoggedInUser(){
        return !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
