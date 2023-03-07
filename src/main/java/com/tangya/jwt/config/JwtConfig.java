package com.tangya.jwt.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@ConfigurationProperties(prefix = "config.jwt")
@Component
@Data
public class JwtConfig {

    private String secret;
    private long expire;
    private String header;

    /**
     * 生成token
     * @param subject
     * @return
     */
    public String createToken(String subject){

        Date nowDate  = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);//过期时间
        //long l = System.currentTimeMillis();

        return Jwts.builder().setHeaderParam("type","JWT")  //头
                .setSubject(subject)  //体
                .setIssuedAt(nowDate)  // 生成时间
                .setExpiration(expireDate)  // 过期时间
                .signWith(SignatureAlgorithm.HS512,secret) // 盐
                .compact();
    }

    /**
     * 获取token中注册信息
     * @param token
     * @return
     */
    public Claims getTokenClaim(String token){
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
            /*  catch (ExpiredJwtException e){
                    return e.getClaims(); //防止jwt过期解析报错
                }
            */
        }
    }


    /**
     * token 是否过期
     * @param expirationTime
     * @return
     */
    public boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * 获取token失效时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }


    /**
     * 获取token 里面的用户名
     * @param token
     * @return
     */
    public String getUserNameFromToke(String token){
        return getTokenClaim(token).getSubject();
    }

    /**
     * 获取token 的发布时间
     * @param token
     * @return
     */
    public Date getIssuedAtDateFromToken(String token){
         return getTokenClaim(token).getIssuedAt();
    }


}
