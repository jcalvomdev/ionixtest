package cl.ionix.test.restApi.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.ionix.test.restApi.external.model.SearchResult;

@Service
public class SearchService {
	
	private final RestTemplate restTemplate;
	private static final String ERROR_TOKEN = "ERROR##";

    public SearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public SearchResult getSearchResult(String param) {
    	SearchResult searchResult = null;
    	String encryptedParam = encryptParam(param);
    	
    	if(encryptedParam.contains(ERROR_TOKEN)) {
    		searchResult = new SearchResult();
            searchResult.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            searchResult.setDescription(encryptedParam.replace(ERROR_TOKEN,""));
            searchResult.setElapsedTime(0L);
            return searchResult;
    	}
        
    	String url = "https://sandbox.ionix.cl/test-tecnico/search?rut="+encryptedParam;
        Instant starts = Instant.now();
        ResponseEntity<SearchResult> response = this.restTemplate.getForEntity(url, SearchResult.class);
        Instant end = Instant.now();
        if(response.getStatusCode() == HttpStatus.OK) {
        	searchResult = response.getBody();
        } else {
            searchResult = new SearchResult();
            searchResult.setResponseCode(response.getStatusCodeValue());
            searchResult.setDescription("Not Ok");
        }
        searchResult.setElapsedTime(Duration.between(starts, end).toMillis());
        return searchResult;
    }
    
    private String encryptParam(String param) {
    	String encryptedParam = "";
    	try {
			DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes("UTF8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey ks = keyFactory.generateSecret(keySpec);
			
    		byte[] cleartext = param.getBytes("UTF8");
    		Cipher cipher = Cipher.getInstance("DES");
    		cipher.init(Cipher.ENCRYPT_MODE, ks);
    		encryptedParam = Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));
    		
    	} catch (InvalidKeyException e) {
    		encryptedParam = ERROR_TOKEN+e.getMessage();
		} catch (UnsupportedEncodingException e) {
			encryptedParam = ERROR_TOKEN+e.getMessage();
		} catch (IllegalBlockSizeException e) {
			encryptedParam = ERROR_TOKEN+e.getMessage();
		} catch (BadPaddingException e) {
			encryptedParam = ERROR_TOKEN+e.getMessage();
		} catch (NoSuchAlgorithmException e) {
			encryptedParam = ERROR_TOKEN+e.getMessage();
		} catch (NoSuchPaddingException e) {
			encryptedParam = ERROR_TOKEN+e.getMessage();
		} catch (InvalidKeySpecException e) {
			encryptedParam = ERROR_TOKEN+e.getMessage();
		}
    	return encryptedParam;
    }
}
