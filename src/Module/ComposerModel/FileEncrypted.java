package Module.ComposerModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileEncrypted {
    public String uid;
    public AccessInfo[] access_info_list;
    public ControlInfo control_info;
    public String meta_data;
    public ProposeFile[] propose_list;
    public String checksum;
    public Vote[] vote_result_list;
}
