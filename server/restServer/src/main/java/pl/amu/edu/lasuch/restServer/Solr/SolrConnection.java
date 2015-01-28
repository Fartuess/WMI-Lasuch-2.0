/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.amu.edu.lasuch.restServer.Solr;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Uczelnia
 */
public class SolrConnection {
    private String originalText;

    public SolrConnection() {
    }
    
    public List<Doc> GetAll() throws MalformedURLException, URISyntaxException, IOException {
        String connectionResult = getRawResponse();
        
        Gson gson = new Gson();
        SolrResponse responseObject = gson.fromJson(connectionResult, SolrResponse.class);
        List<Doc> result = responseObject.getResponse().getDocs();
        
        return result;
    }
    
    public List<Doc> searchProducts(String searchField, String searchText) throws MalformedURLException, URISyntaxException, IOException {
        String connectionResult = getRawResponse(searchField, searchText);
        
        Gson gson = new Gson();
        SolrResponse responseObject = gson.fromJson(connectionResult, SolrResponse.class);
        List<Doc> result = responseObject.getResponse().getDocs();
        
        return result;
    }
    
    public List<Doc> searchProducts(String firstSearchField, String firstAttribute,
            String secondSearchField, String secondAttribute) throws MalformedURLException, URISyntaxException, IOException {
        String connectionResult = getRawResponse(firstSearchField, firstAttribute, secondSearchField, secondAttribute);
        
        Gson gson = new Gson();
        SolrResponse responseObject = gson.fromJson(connectionResult, SolrResponse.class);
        List<Doc> result = responseObject.getResponse().getDocs();
        
        return result;
    }
    
    public Doc findOneProduct(String searchField, String searchText) throws UnsupportedEncodingException, MalformedURLException, IOException {
        String connectionResult = getRawResponse(searchField, searchText);
        
        Gson gson = new Gson();
        SolrResponse responseObject = gson.fromJson(connectionResult, SolrResponse.class);
        Doc result = new Doc();
        result.setTitle("Nie znaleziono produktu");
        
        if (responseObject.getResponse().getDocs().size() != 0)
            result = responseObject.getResponse().getDocs().get(0);
        
        return result;
    }
    
    private String getRawResponse() throws UnsupportedEncodingException, MalformedURLException, IOException {
        String parametersString = "http://localhost:8983/solr/collection1/select?q=*:*&wt=json&indent=true";

        URL url = new URL(parametersString);
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                connection.getInputStream()));
        
        String line;    
        String connectionResult = "";
        
        while ((line = in.readLine()) != null) 
            connectionResult += line;
        
        in.close();
        
        return connectionResult;
    }
    
    private String getRawResponse(String searchField, String attribute) throws UnsupportedEncodingException, MalformedURLException, IOException {
        String searchText = URLEncoder.encode(attribute.replace(" ", "\\ "), "UTF-8");
        String parametersString = "http://localhost:8983/solr/collection1/select?q=" + searchField + ":".concat(searchText.concat("&wt=json&indent=true"));

        URL url = new URL(parametersString);
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                connection.getInputStream()));
        
        String line;    
        String connectionResult = "";
        
        while ((line = in.readLine()) != null) 
            connectionResult += line;
        
        in.close();
        
        return connectionResult;
    }
    
    private String getRawResponse(String firstSearchField, String firstAttribute,
            String secondSearchField, String secondAttribute) throws UnsupportedEncodingException, MalformedURLException, IOException {
        String searchText1 = URLEncoder.encode(firstAttribute.replace(" ", "\\ "), "UTF-8");
        String searchText2 = URLEncoder.encode(secondAttribute.replace(" ", "\\ "), "UTF-8");
        String parametersString = "http://localhost:8983/solr/collection1/select?q=" + firstSearchField + ":" + searchText1 + "%20" + secondSearchField + ":" + searchText2 + "&wt=json&indent=true";
        parametersString = parametersString.replace("%3F", "");

        URL url = new URL(parametersString);
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                connection.getInputStream()));
        
        String line;    
        String connectionResult = "";
        
        while ((line = in.readLine()) != null) 
            connectionResult += line;
        
        in.close();
        
        return connectionResult;
    }
}
