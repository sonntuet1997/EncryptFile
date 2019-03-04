package Module.KeyShare;


import java.io.Serializable;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class KeyShareEntity implements Serializable {
    public int id;
    public String code;
    public boolean isRequired;
    public KeyShareEntity() {
    }
}
