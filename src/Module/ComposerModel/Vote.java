package Module.ComposerModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Vote {
    public String user;
    public boolean is_accept;
}
