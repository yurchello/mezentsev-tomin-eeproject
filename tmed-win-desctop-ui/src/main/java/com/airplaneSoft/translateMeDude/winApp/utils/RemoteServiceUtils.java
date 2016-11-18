package com.airplaneSoft.translateMeDude.winApp.utils;

import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.winApp.App;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.apache.log4j.Logger;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides to control vocabulary from remote personal account by REST full api
 */
public class RemoteServiceUtils {
    private static final Logger LOGGER = Logger.getLogger(RemoteServiceUtils.class);
    //main site url
    private String url;
    //user ssoid
    private String ssoId;
    //user password
    private String password;

    public RemoteServiceUtils(String url, String ssoId, String password) {
        this.url = url;
        this.ssoId = ssoId;
        this.password = password;
    }

    /**
     * Get full vocabulary
     * @return
     */
    public List<WordsGroup> getWordGroupsList(){
        final String URL_FULL_PATH = url + AppUtils.getStringProperty("rest.vocabulary.update");
        User user = new User();
        user.setSsoId(ssoId);
        user.setPassword(password);
        int status = 0;
        try {
            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);
            WebResource webResource = client.resource(URL_FULL_PATH);
            ClientResponse response = webResource.accept("application/json")
                    .type("application/json").post(ClientResponse.class, user);
            List<WordsGroup> wordsGroups;
            status = response.getStatus();
            if (status == Response.Status.OK.getStatusCode()) {
                LOGGER.info("URL: " + URL_FULL_PATH + " " + user + " http status = OK");
                wordsGroups = response.getEntity(new GenericType<List<WordsGroup>>(){});
                return wordsGroups;
            }
            if (status == Response.Status.BAD_REQUEST.getStatusCode()) {
                LOGGER.info("URL: " + URL_FULL_PATH + " " + user + " http status = BAD_REQUEST");
                return null;
            }
            if (status == Response.Status.NOT_FOUND.getStatusCode()){
                LOGGER.info("URL: " + URL_FULL_PATH + " "  + user + " http status = NOT_FOUND");
                return null;
            }
        }catch (Exception e){
            LOGGER.error("URL connection: " + URL_FULL_PATH + " " + user + " Error.", e);
        }
        LOGGER.info("URL: " + URL_FULL_PATH + " "  + user + "Bad http status = " + status);
        return null;
    }

    /**
     * Provides to test connection with personal account
     * @return
     */
    public boolean testConnection(){
        final String URL_FULL_PATH = url + AppUtils.getStringProperty("rest.vocabulary.update");
        User user = new User();
        user.setSsoId(ssoId);
        user.setPassword(password);
        try {
            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);
            WebResource webResource = client.resource(URL_FULL_PATH);
            ClientResponse response = webResource.accept("application/json")
                    .type("application/json").post(ClientResponse.class, user);

            int status = response.getStatus();
            if (status == Response.Status.OK.getStatusCode()) {
                System.out.println("URL test connection: " + URL_FULL_PATH + " " + user + " http status = OK");
                return true;
            }
        }catch (Exception e){
            System.out.println("URL test connection: " + URL_FULL_PATH + " " + user + " Error.");
            e.printStackTrace();
        }

        return false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
