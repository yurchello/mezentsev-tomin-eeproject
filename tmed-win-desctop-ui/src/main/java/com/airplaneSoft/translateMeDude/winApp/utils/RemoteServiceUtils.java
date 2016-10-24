package com.airplaneSoft.translateMeDude.winApp.utils;

import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.testng.Assert;

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


    public boolean updateVocabularyFile(){

        return false;
    }

    public boolean testConnection(){
        return false;
    }



    public void wordGroupClientTest(){
        final String URL = "http://localhost:8080/api/getGroupsList";
        User user = new User();
        user.setId(46);
        user.setSsoId("qqq");
        user.setFirstName("ddd");
        user.setPassword("qqq");
        try {
            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);
            WebResource webResource = client.resource(URL);
            ClientResponse response = webResource.accept("application/json")
                    .type("application/json").post(ClientResponse.class, user);
            List<WordsGroup> wordsGroups = null;
            int status = response.getStatus();
            if (status == Response.Status.OK.getStatusCode()) {
                System.out.println("http status = OK");
                wordsGroups = response.getEntity(List.class);
            }
            if (status == Response.Status.BAD_REQUEST.getStatusCode()) System.out.println("http status = BAD_REQUEST");
            if (status == Response.Status.NOT_FOUND.getStatusCode()) System.out.println("http status = NOT_FOUND");
            Assert.assertNotNull(wordsGroups);

        } catch (Exception e) {

            e.printStackTrace();

        }
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
