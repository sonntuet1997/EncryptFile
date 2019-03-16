package Module.ComposerModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Log {
    public String uid;
    public String user;
    public Date timestamp;
    public Action action;
    public String file;
}
