package com.korit.servlet_study.security.jwt;

import com.korit.servlet_study.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtProvider {
    private Key key;

    private static JwtProvider instance;

    private JwtProvider() {
        final String SECRET = "a39dbfab197eeca04b33a519aa26c6776480bfd64fc8118389742f8c01c63ce4dd567db72a12cb4ea777790e5995fb9c46d5e2b8c3fc83e7e9f67bde7dece0cc09852a343292f95c2400055970eafafc44e18f25eba48cad9186013fad8b3cca26d324f73eb7a81384455587e387d3ef3bb05c7b5aa37f719d9102b4abcede7c57b1066a6409e33e822587f2211787616e73fa4d6a18aca291e373135f0c6b4b2e0b65be441303d8f023d631d0ebe2758c314d311304fca7f8e69c9968326744503ca857c6d76fe94b9e7827c62c6bc50d6f5070d8f7beaad636e73ecfbdc485bb7447939feb0a2b6a1232334c55c6183ec41c18670d7ae73203ab7c0250bce4";
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public static JwtProvider getInstance() {
        if (instance == null) {
            instance = new JwtProvider();
        }
        return instance;
    }

    private Date getExpireDate() {
        return new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 365));
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .claim("userId", user.getUserId())
                .setExpiration(getExpireDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

}
