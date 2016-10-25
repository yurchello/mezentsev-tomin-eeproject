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
 * Created by Mezentsev.Y on 9/30/2016.
 */
@RestController
@RequestMapping(value = "/api")
public class WordGroupApi {
    @Autowired
    WordGroupService wordGroupService;
    @Autowired
    UserService userService;

    //{"id":46,"ssoId":null,"password":"$2a$10$SJ2PcT/U4LvpypN76WYfkemo1C39tEf77W5IQpD9LQOhGvJ8l0PYG","firstName":null,"lastName":null,"email":null,"description":null,"photo":null,"userProfiles":[]}
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

    @RequestMapping(value="/testConnection", method = RequestMethod.POST)
    ResponseEntity<Void> testConnection(@RequestBody User user){
        User ownerUser = userService.findBySSO(user.getSsoId());
        if (!Utils.getPasswordEncoder().matches(user.getPassword(), ownerUser.getPassword()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(HttpStatus.OK);
    }

}
