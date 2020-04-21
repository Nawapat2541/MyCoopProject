package com.cubesofttech.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cubesofttech.model.LineIdToken;
import com.cubesofttech.model.lineAccessToken;
import com.cubesofttech.util.lineClient;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.cubesofttech.util.LineAPI;

import retrofit2.Call;

@Component
public class LineAPIService {
	
    private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";

    //TEST
//   
//    @Value("1641211709")
//    private String channelId;
//    @Value("724ad519a546e81a4430f659d6c04dd1")
//    private String channelSecret;
//    @Value("http://localhost:8080/lineAuth")
//    
    //PROD
    @Value("1650477965")
    private String channelId;
    @Value("60bd6c6f57bcdf2bf4948bcefdee6734")
    private String channelSecret;
    @Value("https://ts.cubesofttech.com/lineAuth")
    
    private String callbackUrl;
    
    public lineAccessToken accessToken(String code) {
        return getClient(t -> t.accessToken(
                GRANT_TYPE_AUTHORIZATION_CODE,
                channelId,
                channelSecret,
                callbackUrl,
                code));
    }
    
    public void revoke(final lineAccessToken accessToken) {
        getClient(t -> t.revoke(
                accessToken.access_token,
                channelId,
                channelSecret));
    }

    public LineIdToken idToken(String id_token) {
        try {
            DecodedJWT jwt = JWT.decode(id_token);
            return new LineIdToken(
                    jwt.getClaim("iss").asString(),
                    jwt.getClaim("sub").asString(),
                    jwt.getClaim("aud").asString(),
                    jwt.getClaim("ext").asLong(),
                    jwt.getClaim("iat").asLong(),
                    jwt.getClaim("nonce").asString(),
                    jwt.getClaim("name").asString(),
                    jwt.getClaim("picture").asString());
        } catch (JWTDecodeException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLineWebLoginUrl(String state, String nonce, List<String> list) {
        final String encodedCallbackUrl;
        final String scope = String.join("%20", list);

        try {
            encodedCallbackUrl = URLEncoder.encode(callbackUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return "https://access.line.me/oauth2/v2.1/authorize?response_type=code"
                + "&client_id=" + channelId
                + "&redirect_uri=" + encodedCallbackUrl
                + "&state=" + state
                + "&scope=" + scope
                + "&nonce=" + nonce;
    }
    
    private <R> R getClient(final Function<LineAPI, Call<R>> function) {
        return lineClient.getClient("https://api.line.me/", LineAPI.class, function);
    }
}
