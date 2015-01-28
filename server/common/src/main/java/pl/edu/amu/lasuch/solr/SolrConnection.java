/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.lasuch.solr;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import pl.edu.amu.lasuch.model.Product;

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
        result.setTitle(Strings.PRODUCT_NOT_FOUND);
        
        if (responseObject.getResponse().getDocs().size() != 0)
            result = responseObject.getResponse().getDocs().get(0);
        
        return result;
    }
    
    private String getRawResponse() throws UnsupportedEncodingException, MalformedURLException, IOException {
        //String parametersString = "http://localhost:8983/solr/collection1/select?q=*:*&wt=json&indent=true";
    	String parametersString = Strings.URL_SELECT_ALL;
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
       //String parametersString = "http://localhost:8983/solr/collection1/select?q=" + searchField + ":".concat(searchText.concat("&wt=json&indent=true"));
        String parametersString = String.format(Strings.URL_SELECT2, searchField, searchText);
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
        String parametersString = String.format(Strings.URL_SELECT4, firstSearchField, searchText1, secondSearchField, searchText2);
        //String parametersString = "http://localhost:8983/solr/collection1/select?q=" + firstSearchField + ":" + searchText1 + "%20" + secondSearchField + ":" + searchText2 + "&wt=json&indent=true";
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
    
    public void addProduct(Product product) {
		Doc document = Doc.fromProduct(product);
        Gson gson = new Gson();

        String dataJson = String.format(Strings.ADD_DOCUMENT, gson.toJson(document));
        
        try {
	        URL url = new URL(Strings.URL_UPDATE);
	
	        
	        HttpURLConnection httpcon = (HttpURLConnection) ((url.openConnection()));
	        httpcon.setDoOutput(true);
	        httpcon.setRequestProperty("Content-Type", "application/json");
	        httpcon.setRequestProperty("Accept", "application/json");
	        
				httpcon.setRequestMethod("POST");
			
	        httpcon.connect();
	
	        byte[] outputBytes = dataJson.getBytes("UTF-8");
	        OutputStream os = httpcon.getOutputStream();
	        os.write(outputBytes);
	        os.close();
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	        		httpcon.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {} //z jakiegos powodu musze pobrac input, bo inaczej nie dziala
			in.close();
			
        } catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
