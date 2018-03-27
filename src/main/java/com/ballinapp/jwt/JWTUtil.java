package com.ballinapp.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ballinapp.service.TeamService;
import com.ballinapp.util.SystemVariables;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.StringUtils;

/**
 * User: dusan <br/> Date: 3/20/18
 */
public class JWTUtil {

    private static TeamService teamService = TeamService.getInstance();

    public static String createJWT(int teamId, String secret, Date expirationDate) {
        try {
            return JWT.create()
                    .withIssuer(SystemVariables.ISSUER)
                    .withClaim("id", teamId)
                    .withExpiresAt(expirationDate)
                    .sign(Algorithm.HMAC256(secret));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Map<Boolean, String> verifyJWT(String token) {
        Map<Boolean, String> map = new HashMap<>();
        try {
            int teamId = getTeamIdFromToken(token);
            Algorithm algorithm = Algorithm.HMAC256(teamService.getActiveSecret(teamId));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(SystemVariables.ISSUER)
                    .withClaim("id", teamId)
                    .build();
            verifier.verify(token);
            map.put(true, "");
            return map;
        } catch (UnsupportedEncodingException exception){
            exception.fillInStackTrace();
            return map;
        } catch (JWTVerificationException exception){
            map.put(false, exception.toString());
            return map;
        }
    }

    private static int getTeamIdFromToken(String token) {
        String payload = StringUtils.newStringUtf8(Base64.getDecoder().decode(JWT.decode(token).getPayload()));
        Gson g = new Gson();
        JsonJWT jwt = g.fromJson(payload, JsonJWT.class);
        return jwt.getId();
    }

}
