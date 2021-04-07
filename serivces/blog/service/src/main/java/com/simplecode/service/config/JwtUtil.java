package com.simplecode.service.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class JwtUtil {

    /**
     * 校验 token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.info("token is invalid{}", e.getMessage());
            return false;
        }
    }

    public static String getUsername(HttpServletRequest request) {
        // 取token
        String token = request.getHeader("Authorization");
        return getUsername(token);
    }
    /**
     * 从 token中获取用户名
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }

    public static Integer getUserId(HttpServletRequest request) {
        // 取token
        String token = request.getHeader("Authorization");
        return getUserId(token);
    }
    /**
     * 从 token中获取用户ID
     * @return token中包含的ID
     */
    public static Integer getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return Integer.valueOf(jwt.getSubject());
        } catch (JWTDecodeException e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }


    /**
     * 生成 token
     * @param username 用户名
     * @param secret   用户的密码
     * @return token 加密的token
     */
    public static String sign(String username, String secret, Integer userId) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            username = StringUtils.lowerCase(username);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withHeader(map)
                    .withClaim("username", username)
                    .withSubject(String.valueOf(userId))
                    .withIssuedAt(new Date())
//                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("error：{}", e);
            return null;
        }
    }
}
