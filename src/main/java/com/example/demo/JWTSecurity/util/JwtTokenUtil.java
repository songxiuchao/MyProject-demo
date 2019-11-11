package com.example.demo.JWTSecurity.util;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.example.demo.JWTSecurity.constants.ConstantsContext;
import com.example.demo.JWTSecurity.po.JwtPayLoad;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

/**
 * @program: demo
 * @description: jwt
 * @author: xiuchao Song
 * @create: 2019-11-05 17:21
 **/
public class JwtTokenUtil {

    /**
     * 生成token,根据userId和默认过期时间
     */
    public static String generateToken(JwtPayLoad jwtPayLoad) {
        Long expiredSeconds = getExpireSeconds();//获取token的header标识
        final Date expirationDate = new Date(System.currentTimeMillis() + expiredSeconds * 1000);
        return generateToken(String.valueOf(jwtPayLoad.getUserId()), expirationDate, jwtPayLoad.toMap());
    }

    /**
     * 获取jwt的payload部分
     */
    public static JwtPayLoad getJwtPayLoad(String token) {
        Claims claimFromToken = getClaimFromToken(token);
        return JwtPayLoad.toBean(claimFromToken);
    }

    /**
     * 解析token是否正确(true-正确, false-错误)
     */
    public static Boolean checkToken(String token) {
        try {
            String jwtSecret = getJwtSecret();
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * 验证token是否失效
     */
    public static Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 生成token,根据userId和过期时间
     */
    public static String generateToken(String userId, Date exppiredDate, Map<String, Object> claims) {

        final Date createdDate = new Date();
        //获取系统地密钥
        String secret = getJwtSecret();

        if (claims == null) {
            return Jwts.builder()
                    .setSubject(userId)
                    .setIssuedAt(createdDate)
                    .setExpiration(exppiredDate)
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        } else {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userId)
                    .setIssuedAt(createdDate)
                    .setExpiration(exppiredDate)
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        }
    }

    /**
     * 获取jwt的payload部分
     */
    public static Claims getClaimFromToken(String token) {

        if (ToolUtil.isEmpty(token)) {
            throw new IllegalArgumentException("token参数为空！");
        }

        String jwtSecret = getJwtSecret();
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    private static String getJwtSecret() {
        return ConstantsContext.getJwtSecret();
    }

    private static Long getExpireSeconds() {
        return ConstantsContext.getJwtSecretExpireSec();
    }
}
