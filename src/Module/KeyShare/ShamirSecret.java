package Module.KeyShare;

import com.codahale.shamir.Scheme;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @version 1.00 2015/11/1
 * @(#)IDA.java
 */

public class ShamirSecret {
    public static void main(String[] args) throws IOException {
        int n = 5;
        int k = 3;
        final Scheme scheme = new Scheme(new SecureRandom(), n, k);
        final byte[] secret = "VTUTI47OOGJAryEqHTD4nw==".getBytes(StandardCharsets.UTF_8);
        final Map<Integer, byte[]> parts = scheme.split(secret);
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            out.append(i).append(";").append(Arrays.toString(parts.get(i))).append("\n");
        }
        System.out.println(out.toString());
        String input = "1;[98, -126, 51, -31, 104, -105, -61, -94, -51, -116, 3, 82, 29, -39, 6, -29, 52, -128, -99, 61, -104, -87, -122, -99]\n" +
                "3;[-111, -12, 42, 48, 29, -45, -28, -8, -40, -26, 15, -12, -60, -36, 97, -104, -53, 49, 0, 55, 11, 63, -76, 89]\n" +
                "5;[-59, 71, 120, -18, 94, 123, -16, 76, -49, -23, 85, 51, -88, -80, 97, 30, -51, -15, 108, 73, 46, 90, -123, 68]\n";
        Map<Integer, byte[]> map = new HashMap<>();
        String[] a = input.split("\n");
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
            String[]c = a[i].split(";");
            int number = Integer.parseInt(c[0]);
            map.put(number,parse(c[1]));
        }
        final byte[] recovered = scheme.join(map);
        System.out.println(new String(recovered, StandardCharsets.UTF_8));
    }

    public static byte[] parse(String input){
        input = input.replace("[","");
        input = input.replace("]","");
// The string you want to be an integer array.
        String[] integerStrings = input.split(", ");
// Splits each spaced integer into a String array.
        byte[] integers = new byte[integerStrings.length];
// Creates the integer array.
        for (int i = 0; i < integers.length; i++){
            integers[i] = Byte.parseByte(integerStrings[i]);
//Parses the integer for each string.
        }
        return integers;
    }
}
