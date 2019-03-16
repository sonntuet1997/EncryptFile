package Module.ComposerModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class User {
    public String uid;
    public String name;
    public String identify;
    public String issuer;
    public String admin;
    public boolean manager;
}
