package Module.KeyShare;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class RequestKeyShare implements Serializable {
    public int threshHold;
    public int optional;
    public int required;
    public String input;
}
