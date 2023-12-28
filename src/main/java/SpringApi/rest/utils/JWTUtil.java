package SpringApi.rest.utils;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
public class JWTUtil {
    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    private final Logger log = LoggerFactory.getLogger(JWTUtil.class);

    /**
     * Create a new token.
     *
     * @param id
     * @param subject
     * @return
     */
    public String create(String id, String subject) {
        String compactJws = Jwts.builder()
                .setId(id)
                .setIssuedAt(new Date())
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();

        return compactJws;
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public String getValue(String jwt) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(jwt);
        return claims.getBody().getSubject();
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public String getKey(String jwt) {
// Verificar si el token tiene exactamente dos períodos ('.')
        if (jwt != null && StringUtils.countMatches(jwt, ".") == 2) {
            try {
        Jws<Claims> claims = Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(jwt);
        return claims.getBody().getId();
            } catch (MalformedJwtException e) {
                // Manejar la excepción de token con formato incorrecto
                return "Token con formato incorrecto";
            }
        } else {
            // Manejar el caso de un token con formato incorrecto
            return "Token con formato incorrecto";
        }
    }

    private Key getSigningKey() {
        byte[] apiKeySecretBytes = key.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }
}
