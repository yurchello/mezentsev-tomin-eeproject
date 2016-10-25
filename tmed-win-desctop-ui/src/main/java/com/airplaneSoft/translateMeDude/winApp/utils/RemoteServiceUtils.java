package com.airplaneSoft.translateMeDude.winApp.utils;

import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Mezentsev.Y on 10/24/2016.
 */
public class RemoteServiceUtils {
    private String url;
    private String ssoId;
    private String password;



    public RemoteServiceUtils(String url, String ssoId, String password) {
        this.url = url;
        this.ssoId = ssoId;
        this.password = password;
    }


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
                System.out.println("URL: " + URL_FULL_PATH + " " + user + " http status = OK");
                wordsGroups = response.getEntity(List.class);
                return wordsGroups;
            }
            if (status == Response.Status.BAD_REQUEST.getStatusCode()) {
                System.out.println("URL: " + URL_FULL_PATH + " " + user + " http status = BAD_REQUEST");
                return null;
            }
            if (status == Response.Status.NOT_FOUND.getStatusCode()){
                System.out.println("URL: " + URL_FULL_PATH + " "  + user + " http status = NOT_FOUND");
                return null;
            }
        }catch (Exception e){
            System.out.println("URL connection: " + URL_FULL_PATH + " " + user + " Error.");
            e.printStackTrace();
        }
        System.out.println("URL: " + URL_FULL_PATH + " "  + user + "Bad http status = " + status);
        return null;
    }

    public boolean testConnection(){
        final String URL_FULL_PATH = url + AppUtils.getStringProperty("rest.vocabulary.update");
        User user = new User();
        user.setId(46);
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
