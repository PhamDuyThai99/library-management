package library_management.spring.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "secret key secret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret keysecret key";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    public static String generateToken(String email, String roleName) throws JOSEException {
        // jwt header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        // claim set
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("domain service")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(EXPIRATION_TIME, ChronoUnit.MILLIS).toEpochMilli()))
                .claim("role", roleName)
                .build();

        // payload
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
        return jwsObject.serialize();
    }

    public static boolean validateToken(String token, UserDetails userDetails) throws JOSEException, ParseException {
        JWSVerifier jwsVerifier = new MACVerifier(SECRET_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiredTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(jwsVerifier);

        return verified && expiredTime.after(new Date());
    }

    @Bean
    public static JwtDecoder jwtDecoder() {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes() ,"HS512");
        return NimbusJwtDecoder
                .withSecretKey(keySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}
