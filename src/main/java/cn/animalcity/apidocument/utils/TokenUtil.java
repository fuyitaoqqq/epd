package cn.animalcity.apidocument.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author: welcome
 * @date: 2019/03/29
 */

public class TokenUtil {

    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "JwtSecret";

    /**
     * 签发者
     */
    private static final String ISS = "EPD";

    /**
     * 过期时间是3600秒，既是1个小时
     */
    private static final long EXPIRATION = 60 * 60L;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 7 * 24 * 60 * 60L;

    /**
     * 创建token
     * @param username
     * @param isRememberMe
     * @return
     */
    public static String createToken(String username, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();

        return token;
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token){

        if (getTokenBody(token) == null) {
            return null;
        } else {
            return getTokenBody(token).getSubject();
        }

    }

    /**
     * 判断是否已过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token){
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            return body;
        } catch (Exception e) {
            return null;
        }
    }

}
