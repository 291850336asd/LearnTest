package com.meng.jwt;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JWTTest {

    // The secret key. This should be in a property file NOT under source
    // control and not hard coded in real life. We're putting it here for
    // simplicity.
    private static String SECRET_KEY = "435%$43$#Using JWT, noticed my log file is growing really big recently. I noticed that everytime we are connectiUsing JWT, noticed my log file is growing really big recently. I noticed that everytime we are connectiUsing JWT, noticed my log file is growing really big recently. I noticed that everytime we are connectiUsing JWT, noticed my log file is growing really big recently. I noticed that everytime we are connectiUsing JWT, noticed my log file is growing really big recently. I noticed that everytime we are connecti";


    public static void main(String[] args) {
        String sss = createJWT("1", "www", "hello", 10000);
        System.out.println(sss);
        parseJWT(sss);
    }

    //Sample method to construct a JWT
    private static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    private static void parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }


    public static void simpleJwt(){
        // We need a signing key, so we'll create one just for this lamda1. Usually
        // the key would be read from your application configuration instead.
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
        System.out.println(jws);
        System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject());

        try {

            Jwts.parser().setSigningKey(key).parseClaimsJws(jws);

            //OK, we can trust this JWT

        } catch (JwtException e) {

            //don't trust the JWT!
        }
    }
}
