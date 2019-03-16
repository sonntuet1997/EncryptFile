package Module.ComposerModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Identity {
    public String identityId;
    public String name;
    public String certificate;
    public String issuer;
    public String state;
    public String participant;
}
