package Module.EncryptFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class GenerateBase64File {
    public static void main(String[] args) throws Exception{
        System.out.println(Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get("test"))));
        System.out.println(new String(Base64.getDecoder().decode("YXNkZnNmCmFmCmFmeApjdnhjCnZ4Y3YKY3YKZHNmCmRzZnNkCmZhCmZhCmVyCndyCndxZXIKZXF3cgpxd3J3ZXEKcndlCnIKd2VyCndlcndlCnJ3ZQpy".getBytes())));
    }
//
//    public static T mix(int a, int b) {
//        T result = new T();
//        result.a = a + b;
//        result.b = a + b;
//        return result;
//    }
}
//
//class T {
//    public int a;
//    public int b;
//}
