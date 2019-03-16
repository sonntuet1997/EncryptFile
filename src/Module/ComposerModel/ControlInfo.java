package Module.ComposerModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ControlInfo {
    public String[] required_list;
    public String[] optional_list;
    public Integer thresh_hold;
}
