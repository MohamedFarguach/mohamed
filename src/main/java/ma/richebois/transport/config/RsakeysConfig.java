package ma.richebois.transport.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 

@ConfigurationProperties(prefix = "rsa")
@AllArgsConstructor @Data @NoArgsConstructor
public class RsakeysConfig {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
    
    

    
}
