//package Module.EncryptFile;
//
//import org.hibernate.SessionFactory;
//
//import javax.crypto.Cipher;
//import javax.crypto.CipherInputStream;
//import javax.crypto.CipherOutputStream;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import java.util.Random;
//
///**
// * Created by Son on 6/15/2017.
// */
//public class EncryptFileServicee {
//    private static SessionFactory factory;
//    private static int currentActive;
//    private String initVector = "RandomInitVector";
//
//    public EncryptFileServicee(SessionFactory factory) {
//        this.factory = factory;
//    }
//
//    public EncryptFileServicee() {
////        if (factory == null || currentActive != DatabaseEntity.Active) {
////            IDatabaseService databaseService = new DatabaseService();
////            IDatabaseControllService databaseControllService = new DatabaseControllService();
////            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
////            currentActive = DatabaseEntity.Active;
////        }
//    }
//
//    public static void setFactory(SessionFactory factory) {
//        EncryptFileServicee.factory = factory;
//    }
//
//    public static byte[] encryptAndHash(byte[] key, String initVector, byte[] value) {
//        try {
//            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
//            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//            byte[] result = new byte[value.length + 50];
//            int i = 0;
//            while (i * 8192 < value.length) {
//                int j = (i + 1) * 8192 < value.length ? 8192 : value.length - i * 8192;
//                cipher.doFinal(value, i * 8192, j, result, i * 8192);
//                i++;
//            }
//            return result;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static byte[] decrypt(byte[] key, String initVector, byte[] encrypted) {
//        try {
//            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
//            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//            return cipher.doFinal(encrypted);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static byte[] getRandomKey() {
//        byte[] array = new byte[32];
//        new Random().nextBytes(array);
//        return array;
//    }
//
//    public void encryptOrDecrypt(byte[] key, int mode, InputStream is,
//                                 OutputStream os) throws Exception {
//        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
//        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//        // SunJCE
//        if (mode == Cipher.ENCRYPT_MODE) {
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//            CipherInputStream cis = new CipherInputStream(is, cipher);
//            doCopy(cis, os);
//        } else if (mode == Cipher.DECRYPT_MODE) {
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//            CipherOutputStream cos = new CipherOutputStream(os, cipher);
//            doCopy(is, cos);
//        }
//    }
//
//    public static void doCopy(InputStream is, OutputStream os)
//            throws IOException {
//        byte[] bytes = new byte[64];
//        int numBytes;
//        while ((numBytes = is.read(bytes)) != -1) {
//            os.write(bytes, 0, numBytes);
//        }
//        os.flush();
//        os.close();
//        is.close();
//    }
//
//
////    public FileEntity encryptAndHash(InputStream fileEntity) {
////        byte[] key = getRandomKey();
////        byte[] data = Base64.getDecoder().decode(fileEntity.data);
////        FileEntity fileEntity1 =
////        fileEntity.data = Base64.getEncoder().encodeToString(encryptAndHash(key, initVector, data));
////        fileEntity.key = Base64.getEncoder().encodeToString(key);
////        hash(fileEntity, data);
////        return fileEntity;
////    }
////
////    public FileEntity decrypt(FileEntity fileEntity) {
////        byte[] key = Base64.getDecoder().decode(fileEntity.key.getBytes());
////        byte[] data = Base64.getDecoder().decode(fileEntity.data.getBytes());
////        byte[] raw = decrypt(key, initVector, data);
////        fileEntity.data = Base64.getEncoder().encodeToString(raw);
////        hash(fileEntity, raw);
////        return fileEntity;
////    }
//
//    private void hash(FileEntity fileEntity, byte[] raw) {
//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//            byte[] encodehash = messageDigest.digest(raw);
//            fileEntity.hash = Base64.getEncoder().encodeToString(encodehash);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
//}
