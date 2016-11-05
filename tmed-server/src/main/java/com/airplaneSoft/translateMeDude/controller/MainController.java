package com.airplaneSoft.translateMeDude.controller;

import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.UserProfile;
import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.utils.FileUtilities;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class MainController extends BaseController{

    /**
     *This method provide save user avatar photo do drive
     */
    //@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/avatarUpload", method = RequestMethod.POST)
    public void addAdvert(@RequestParam(name = "mainImage", required = false) MultipartFile mainImage) throws IOException {
        String ssoId = getSSOIdifAuthenticated();
        String fileName = ssoId + "." + FilenameUtils.getExtension(mainImage.getOriginalFilename());
        String path = FileUtilities.saveFile(mainImage, UPLOAD_LOCATION, fileName);
        User user = userService.findBySSO(ssoId);
        user.setPhoto(path);
        userService.updateUser(user);
        logger.info("Avatar path saved: ", path);
    }
//
//    private String saveFile(MultipartFile multipartFile, String dirName, String fileName) throws IOException {
//        File file = new File(dirName, fileName);
//        FileCopyUtils.copy(multipartFile.getBytes(), file);
//        return file.getPath();
//    }

    /**
     * This method provide checking password length
     * @return length status to view
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

    /**
     * This method provide to add a new user.
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        //model.addAttribute("loggedinuser", getPrincipal());
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
            FieldError ssoError =new FieldError("user","ssoId",
                    messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }
        userService.saveUser(user);
        //model.addAttribute("loggedinuser", getPrincipal());
        return "registrationSuccess";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again,
     * will be redirected to personal page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "home";
        } else {
            String ssoId = getSSOIdifAuthenticated();
            return "redirect:/user-" + ssoId;
        }

    }

    /**
     *
     * This method handles login GET requests.
     * If user is logged in, it will be redirected to personal page.
     */
    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public String personalPage(ModelMap model) {
        String ssoId = getSSOIdifAuthenticated();
        return "redirect:/user-" + ssoId;
    }

    /**
     * This method handles GET requests.
     * It provides personal data to account page view
     */
    @RequestMapping(value = { "/user-{ssoId}" }, method = RequestMethod.GET)
    public String customAccount(@PathVariable String ssoId, ModelMap model){
        if (!isLoggedInUser()) return "accessDeniedUnregistered";
        User user = userService.findBySSO(ssoId);
        if (user == null) return "page404";
        model.addAttribute("user", user);
       // model.addAttribute("loggedinuser", getPrincipal());
        String image = null;
        try {
            image = FileUtilities.getRawFileFromDrive(user.getPhoto());
        } catch (IOException e) {
            logger.debug("File not found",e);
        }
        model.addAttribute("photoPath", image);
        return "account";
    }

    /**
     * This method provide personal data to edit
     */
    @RequestMapping(value = {"/editUser-{ssoId}"}, method = RequestMethod.GET)
    public String editProfile(@PathVariable String ssoId, ModelMap model){
        if (!isAccountOwner(ssoId)) return "accessDeniedHard";
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        String image = null;
        try {
            image = FileUtilities.getRawFileFromDrive(user.getPhoto());
        } catch (IOException e) {
            logger.debug("File not found",e);
        }
        model.addAttribute("photoPath", image);
        //model.addAttribute("loggedinuser", getPrincipal());
        return "editProfile";
    }

    /**
     * This method be will be called on form submission, handling POST request for
     * edit personal user data and save it to database. It also validates the user input
     */
    @RequestMapping(value = {"/editUser-{ssoId}"}, method = RequestMethod.POST)
    public String updateProfile(@Valid User user, BindingResult result,
                                ModelMap model, HttpServletRequest request){
        if (result.hasErrors()){
            return "editProfile";
        }
        if (user.getUserProfiles() == null || user.getUserProfiles().size() == 0){
            setUserRoles(user, (List<UserProfile>) model.get("roles"));
        }

        userService.updateUser(user);
        return "redirect:/user-{ssoId}";
    }

    /**
     * This method handles GET requests.
     * It provides words groups list to view
     */
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

    /**
     * This method provides to delete selected words group
     */
    @RequestMapping(value = {"delete-group"}, method = RequestMethod.GET)
    public String deleteGroup(@RequestParam Integer wordsGroupId, @RequestParam String ssoId, ModelMap model){
        if (!isAccountOwner(ssoId)) return "accessDeniedHard";
        WordsGroup wordsGroup = wordGroupService.findById(wordsGroupId);
        wordGroupService.deleteGroup(wordsGroup);
        return "redirect:/groupsList-" + ssoId;
    }

    /**
     * This method provides to view words group and all words are there
     */
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

    /**
     * This method provides to create a new words group from view
     */
    @RequestMapping(value = {"/newGroup"}, method = RequestMethod.GET)
    public String createGroup(@RequestParam String ssoId, ModelMap model){
        if (!isAccountOwner(ssoId)) return "accessDeniedHard";
        model.addAttribute("ssoId", ssoId);
        model.addAttribute("wordsGroup", new WordsGroup());
        return "newGroup";
    }

    /**
     * This method handling POST request for creating a new words group
     * and save it in database
     */
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

    /**
     * This method provides to create a new word from view
     */
    @RequestMapping(value = {"newWord"}, method = RequestMethod.GET)
    public String newWord(@RequestParam Integer wordsGroupId, @RequestParam String ssoId, ModelMap model){
        if (!isAccountOwner(ssoId)) return "accessDeniedHard";
        model.addAttribute("ssoId", ssoId);
        WordsGroup wordsGroup = wordGroupService.findById(wordsGroupId);
        model.addAttribute("wordsGroup",wordsGroup);
        Word word = new Word();
        model.addAttribute("word",word);
        return "newWord";
    }

    /**
     * This method handling POST request for creating a new word
     * and save it in database
     */
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

    /**
     * This method provides to edit word of selected words group from view
     */
    @RequestMapping(value = {"/editWord"}, method = RequestMethod.GET)
    public String editWord(@RequestParam Integer wordId, @RequestParam Integer wordsGroupId,@RequestParam String ssoId, ModelMap model){
        if (!isAccountOwner(ssoId)) return "accessDeniedHard";
        Word word = wordService.findById(wordId);
        WordsGroup wordsGroup = wordGroupService.findById(wordsGroupId);
        model.addAttribute("ssoId", ssoId);
        model.addAttribute("wordsGroup",wordsGroup);
        model.addAttribute("word",word);
        return "editWord";
    }

    /**
     * This method handling POST request for editing word
     * and update it in database
     */
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

    /**
     * This method provides to delete word from selected words group from view
     */
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

    /**
     *This method handles Access Denied request
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        //model.addAttribute("loggedinuser", getPrincipal());
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

    /**
     *This method provides to get users list to view
     */
    @RequestMapping(value="/usersList", method = RequestMethod.GET)
    public String userList(ModelMap model){
        if (!isLoggedInUser()) return "accessDeniedUnregistered";
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        List<String> images = new ArrayList<>();
        for (User user: users){
            String image = null;
            try {
                image = FileUtilities.getRawFileFromDrive(user.getPhoto());
            } catch (IOException e) {
                logger.debug("File not found",e);
            }
            images.add(image);
        }
        model.addAttribute("images", images);
        return "usersList";
    }

    /**
     * This method will delete an user by SSOID value.
     */
    @RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/usersList";
    }


    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    private boolean isAccountOwner(String ssoId){
        if (ssoId == null) return false;
        return ssoId.equals(getSSOIdifAuthenticated());
    }

    /**
     *This method provides to set user roles to current user
     */
    private void setUserRoles(User user, List<UserProfile> userProfiles){
        Set<UserProfile> set = new HashSet<>();
        for (UserProfile userProfile: userProfiles){
            if (userProfile != null){
                set.add(userProfile);
            }
        }
        user.setUserProfiles(set);
    }

    /**
     *This method provides to set user roles to current user
     */
    private void setUserRoles(User user, UserProfile... userProfiles){
        Set<UserProfile> set = new HashSet<>();
        for (UserProfile userProfile: userProfiles){
            if (userProfile != null){
                set.add(userProfile);
            }
        }
        user.setUserProfiles(set);
    }

}
