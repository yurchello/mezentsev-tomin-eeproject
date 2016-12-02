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
import org.testng.annotations.Test;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 12/1/2016.
 */
public class RemoteServiceUtilsTest {
    private final String URL = "http://localhost:8080/";
    private final String ssoid = "qqq";
    private final String password = "qqq";


    @Test(enabled=false)
    public void wordGroupClientTest(){
        final String URL = "http://localhost:8080/api/getGroupsList";
        User user = new User();
        user.setSsoId("qqq");
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

    @Test
    public void testConnectionTest(){
        RemoteServiceUtils serviceUtils = new RemoteServiceUtils(URL, ssoid, password);
        Assert.assertTrue(serviceUtils.testConnection());
    }

    @Test
    public void getWordGroupsListTest(){
        RemoteServiceUtils serviceUtils = new RemoteServiceUtils(URL, ssoid, password);
        Assert.assertNotNull(serviceUtils.getWordGroupsList());
        Assert.assertTrue(serviceUtils.getWordGroupsList().size()>0);
    }

}