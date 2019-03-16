package Module.ComposerModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)

public class ProposeFile {
    public FileAction file_action;
    public FileEncrypted proposing_file;
    public Date timestamp;
}
