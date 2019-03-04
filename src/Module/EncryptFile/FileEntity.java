package Module.EncryptFile;


import javax.ws.rs.core.StreamingOutput;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class FileEntity implements Serializable {
    public String key;
    public StreamingOutput data;
    public String hash;
    public FileEntity() {
    }
}
