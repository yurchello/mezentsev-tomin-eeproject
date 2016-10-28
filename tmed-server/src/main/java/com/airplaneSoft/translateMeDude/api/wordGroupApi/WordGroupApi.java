package com.airplaneSoft.translateMeDude.api.wordGroupApi;

import com.airplaneSoft.translateMeDude.core.Utils;
import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.service.UserService;
import com.airplaneSoft.translateMeDude.service.WordGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Base REST API for desctop and mobile client
 */
@RestController
@RequestMapping(value = "/api")
public class WordGroupApi {
    @Autowired
    WordGroupService wordGroupService;
    @Autowired
    UserService userService;

    /**
     *
     * @param user may contains only ssoId and password
     * @return Response with  List<WordsGroup>, where user is owner.
     * returns HttpStatus.BAD_REQUEST if user not found.
     * returns HttpStatus.NOT_FOUND if user  was found, but no one word group was created
     */
    @Transactional
    @RequestMapping(value="/getGroupsList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WordsGroup>> getGroupsList(@RequestBody User user){
        User ownerUser = userService.findBySSO(user.getSsoId());
        if (!Utils.getPasswordEncoder().matches(user.getPassword(), ownerUser.getPassword()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<WordsGroup> list = wordGroupService.findAllUserGroups(ownerUser);
        if (list == null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Allows to test connection from client.
     * @param user may contains only ssoId and password
     * @return HttpStatus.BAD_REQUEST if user not found
     * HttpStatus.OK if user was found
     */
    @RequestMapping(value="/testConnection", method = RequestMethod.POST)
    ResponseEntity<Void> testConnection(@RequestBody User user){
        User ownerUser = userService.findBySSO(user.getSsoId());
        if (!Utils.getPasswordEncoder().matches(user.getPassword(), ownerUser.getPassword()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(HttpStatus.OK);
    }

}
