package Module.ComposerModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Crypto {
    public String issuer;
    public String identity;
    public String public_key;
    public String encrypted_key;
}
