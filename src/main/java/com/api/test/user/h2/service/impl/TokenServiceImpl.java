package com.api.test.user.h2.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.api.test.user.h2.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService{
	
	@Autowired
    private TokenStore tokenStore;
	
    @Value("${security.jwt.client-id}")
    private String clientID;

	@Override
    public String getTokens(String email) {
    	
        Collection<OAuth2AccessToken>  listtok = tokenStore.findTokensByClientId(clientID);
        Collection<OAuth2AccessToken>  colttok = tokenStore.findTokensByClientIdAndUserName(clientID, email);
        listtok.forEach(lt ->{
        	System.out.println(lt.getValue());
        	System.out.println(lt.getTokenType());
        	
        });
        
        String tokenS = "";
        ArrayList<OAuth2AccessToken> listoken = new ArrayList<>(colttok);
        tokenS = listoken.get(0).toString();
        return tokenS;
            
    }

}
