package cn.edu.jxau.dbn.util.token.impl;

import cn.edu.jxau.dbn.config.TokenConfig;
import cn.edu.jxau.dbn.util.token.TokenUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtilImpl implements TokenUtil {

//    第一部分我们称它为头部（header),第二部分我们称其为载荷（payload)，第三部分是签证（signature)。

    @Override
    public String getToken(Map<String, String> map) {
        if (map.size() > 10) {
            throw new RuntimeException("参数超出限制");
        }
        Date date = new Date(System.currentTimeMillis() + Long.parseLong(map.get(TokenUtil.TOKEN_VALID_TIME_NAME)) * 1000);
        Algorithm algorithm = Algorithm.HMAC256(map.get(TokenUtil.SALT_NAME));
        Map<String, Object> head = new HashMap<>(2);
        //输入参数
        map.put(TokenUtil.TOKEN_TYPE, map.get(TokenUtil.TOKEN_TYPE));
        map.put(TokenUtil.ALGORITHMS_NAME, map.get(TokenUtil.ALGORITHMS_NAME));
        JWTCreator.Builder build = JWT.create().withHeader(head);
        //加参数
        for (Map.Entry<String, String> entry : map.entrySet()) {
            build.withClaim(entry.getKey(), entry.getValue());
        }
        return build.withExpiresAt(date).sign(algorithm);
    }

    @Override
    public Map<String, String> parseToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        Map<String, Claim> claimMap = decodedJWT.getClaims();
        Map<String, String> result = new HashMap<>(claimMap.size());
        for (Map.Entry<String, Claim> entry : claimMap.entrySet()) {
            result.put(entry.getKey(), entry.getValue().asString());
        }
        return result;
    }

    @Override
    public String getParamByToken(String token, String key) {
        Algorithm algorithm = Algorithm.HMAC256(TokenConfig.TOKEN_SALT);
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim(key).asString();
    }

    @Override
    public boolean isValid(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TokenConfig.TOKEN_SALT);
        try {
            JWT.require(algorithm).build().verify(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
