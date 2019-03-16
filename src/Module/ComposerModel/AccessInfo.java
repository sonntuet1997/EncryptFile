package Module.ComposerModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessInfo {
    public String user;
    public Crypto[] crypto_list;
}
