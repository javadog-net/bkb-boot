package net.javadog.bkb.common.shiro.util;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Description: JWT工具类
 * @Author: hdx
 * @Date: 2022/1/13 15:38
 * @Version: 1.0
 */
public class JwtUtil {
    /**
     * 过期时间 30天
     */
    private static final int EXPIRE_DAY = 30;

    /**
     * 密钥
     */
    private static final String SECRET = "javadog";

    /**
     * 生成 token
     */
    public static String createToken(String openId) {
        try {
            Date date = DateUtil.offsetDay(new Date(), EXPIRE_DAY);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("openId", openId)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验 token 是否正确
     */
    public static boolean verify(String token, String openId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了openId信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("openId", openId)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     */
    public static String getOpenId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("openId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
