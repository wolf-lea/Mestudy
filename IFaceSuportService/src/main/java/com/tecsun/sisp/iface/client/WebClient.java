package com.tecsun.sisp.iface.client;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

/**
 * Created by Sandwich on 2015/12/13.
 */
public class WebClient {
    public static Object getWebClient(String url, JsonObject json, Class resultClass){
        Client client = new Client();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class,json.toString());
        return response.getEntity(resultClass);
    }
    public static Object getWebClient_get(String url,Class resultClass){
    	Client client = new Client();
    	WebResource webResource = client.resource(url);
    	ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
    	return response.getEntity(resultClass);
    }
}
