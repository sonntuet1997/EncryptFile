package Module.KeyShare;

import com.codahale.shamir.Scheme;
import org.hibernate.SessionFactory;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Son on 6/15/2017.
 */
public class KeyShareService {
    private static SessionFactory factory;
    private static int currentActive;

    public KeyShareService(SessionFactory factory) {
        this.factory = factory;
    }

    public KeyShareService() {
//        if (factory == null || currentActive != DatabaseEntity.Active) {
//            IDatabaseService databaseService = new DatabaseService();
//            IDatabaseControllService databaseControllService = new DatabaseControllService();
//            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
//            currentActive = DatabaseEntity.Active;
//        }
    }

    public static void setFactory(SessionFactory factory) {
        KeyShareService.factory = factory;
    }


//    public KeyShareEntity get(int id) {
//        Session session = factory.openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<FileModel> criteria = builder.createQuery(FileModel.class);
//        Root<FileModel> FileModels = criteria.from(FileModel.class);
//        criteria.where(builder.equal(FileModels.get("id"), id));
//        try {
//            FileModel fileModel = session.createQuery(criteria).getSingleResult();
//            return new KeyShareEntity(fileModel);
//        } catch (NoResultException e) {
//            return null;
//        } finally {
//            session.close();
//        }
//    }

    public List<KeyShareEntity> encode(RequestKeyShare requestKeyShare) {
        ArrayList<KeyShareEntity> result = new ArrayList();
        if (requestKeyShare.required == 1 && requestKeyShare.optional == 0) {
            KeyShareEntity a = new KeyShareEntity();
            a.id = -1;
            a.code = requestKeyShare.input;
            a.isRequired = true;
            result.add(a);
            return result;
        }
        if (requestKeyShare.required == 0 && requestKeyShare.optional > 0) {
            if (requestKeyShare.optional == 1) {
                KeyShareEntity a = new KeyShareEntity();
                a.id = -1;
                a.code = requestKeyShare.input;
                a.isRequired = false;
                result.add(a);
                return result;
            }
            if (requestKeyShare.threshHold == 1) {
                for (int i = 1; i <= requestKeyShare.optional; i++) {
                    KeyShareEntity a = new KeyShareEntity();
                    a.id = i;
                    a.code = Base64.getEncoder().encodeToString(requestKeyShare.input.getBytes());
                    a.isRequired = true;
                    result.add(a);
                }
                return result;
            }
            Scheme scheme = new Scheme(new SecureRandom(), requestKeyShare.optional, requestKeyShare.threshHold);
            byte[] secret = requestKeyShare.input.getBytes(StandardCharsets.UTF_8);
            Map<Integer, byte[]> parts = scheme.split(secret);
            for (int i = 1; i <= requestKeyShare.optional; i++) {
                KeyShareEntity a = new KeyShareEntity();
                a.id = i;
                a.code = Base64.getEncoder().encodeToString(parts.get(i));
                a.isRequired = false;
                result.add(a);
            }
            return result;
        }
        int total = requestKeyShare.optional > 0 ? requestKeyShare.required + 1 : requestKeyShare.required;
        if (total < 0) return null;
        Scheme scheme = new Scheme(new SecureRandom(), total, total);
        byte[] secret = requestKeyShare.input.getBytes(StandardCharsets.UTF_8);
        Map<Integer, byte[]> parts = scheme.split(secret);
        for (int i = 1; i < total; i++) {
            KeyShareEntity a = new KeyShareEntity();
            a.id = i;
            a.code = Base64.getEncoder().encodeToString(parts.get(i));
            a.isRequired = true;
            result.add(a);
        }
        if (requestKeyShare.optional == 0 || requestKeyShare.optional == 1) {
            KeyShareEntity a = new KeyShareEntity();
            a.id = total;
            a.code = Base64.getEncoder().encodeToString(parts.get(total));
            a.isRequired = true;
            result.add(a);
        } else {
            if (requestKeyShare.threshHold == 1) {
                for (int i = 1; i <= requestKeyShare.optional; i++) {
                    KeyShareEntity a = new KeyShareEntity();
                    a.id = total;
                    a.code = Base64.getEncoder().encodeToString(parts.get(total));
                    a.isRequired = true;
                    result.add(a);
                }
                return result;
            }
            scheme = new Scheme(new SecureRandom(), requestKeyShare.optional, requestKeyShare.threshHold);
            secret = Base64.getEncoder().encode(parts.get(total));
            parts = scheme.split(secret);
            for (int i = 1; i <= requestKeyShare.optional; i++) {
                KeyShareEntity a = new KeyShareEntity();
                a.id = i;
                a.code = Base64.getEncoder().encodeToString(parts.get(i));
                a.isRequired = false;
                result.add(a);
            }
        }
        return result;
    }

    public String decode(List<KeyShareEntity> keyShareEntities) {
        for (KeyShareEntity key : keyShareEntities) {
            if (key.id == -1) return key.code;
        }
        List<KeyShareEntity> optionalKeyShareEntities = keyShareEntities.parallelStream().filter(keyShareEntity -> !keyShareEntity.isRequired).collect(Collectors.toList());
        List<KeyShareEntity> requiredKeyShareEntities = keyShareEntities.parallelStream().filter(keyShareEntity -> keyShareEntity.isRequired).collect(Collectors.toList());
        Map<Integer, byte[]> map = new HashMap<>();
        if (optionalKeyShareEntities.size() > 0) {
            for (KeyShareEntity keyShareEntity : optionalKeyShareEntities) {
                map.put(keyShareEntity.id, Base64.getDecoder().decode(keyShareEntity.code));
            }
            Scheme scheme = new Scheme(new SecureRandom(), keyShareEntities.size(), keyShareEntities.size());
            byte[] recovered = scheme.join(map);
            if (requiredKeyShareEntities.size() == 0) {
                return new String(recovered, StandardCharsets.UTF_8);
            }
            map = new HashMap<>();
            map.put(requiredKeyShareEntities.size() + 1, Base64.getDecoder().decode(recovered));
        }

        for (KeyShareEntity keyShareEntity : requiredKeyShareEntities) {
            map.put(keyShareEntity.id, Base64.getDecoder().decode(keyShareEntity.code));
        }
        Scheme scheme = new Scheme(new SecureRandom(), keyShareEntities.size(), keyShareEntities.size());
        byte[] recovered = scheme.join(map);
        return new String(recovered, StandardCharsets.UTF_8);
    }
//
//    public KeyShareEntity encryptAndHash(KeyShareEntity fileEntity) {
//
//        final Scheme scheme = new Scheme(new SecureRandom(), threshHold, optional);
//        final byte[] secret = "VTUTI47OOGJAryEqHTD4nw==".getBytes(StandardCharsets.UTF_8);
//        final Map<Integer, byte[]> parts = scheme.split(secret);
//        StringBuilder out = new StringBuilder();
//        for (int i = 1; i <= threshHold; i++) {
//            out.append(i).append(";").append(Arrays.toString(parts.get(i))).append("\threshHold");
//        }
//        System.out.println(out.toString());
//        String input = "1;[98, -126, 51, -31, 104, -105, -61, -94, -51, -116, 3, 82, 29, -39, 6, -29, 52, -128, -99, 61, -104, -87, -122, -99]\threshHold" +
//                "3;[-111, -12, 42, 48, 29, -45, -28, -8, -40, -26, 15, -12, -60, -36, 97, -104, -53, 49, 0, 55, 11, 63, -76, 89]\threshHold" +
//                "5;[-59, 71, 120, -18, 94, 123, -16, 76, -49, -23, 85, 51, -88, -80, 97, 30, -51, -15, 108, 73, 46, 90, -123, 68]\threshHold";
//        Map<Integer, byte[]> map = new HashMap<>();
//        String[] a = input.split("\threshHold");
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//            String[]c = a[i].split(";");
//            int number = Integer.parseInt(c[0]);
//            map.put(number,parse(c[1]));
//        }
//        final byte[] recovered = scheme.join(map);
//        System.out.println(new String(recovered, StandardCharsets.UTF_8));
//    }
//
//    public static byte[] parse(String input){
//        input = input.replace("[","");
//        input = input.replace("]","");
//// The string you want to be an integer array.
//        String[] integerStrings = input.split(", ");
//// Splits each spaced integer into a String array.
//        byte[] integers = new byte[integerStrings.length];
//// Creates the integer array.
//        for (int i = 0; i < integers.length; i++){
//            integers[i] = Byte.parseByte(integerStrings[i]);
////Parses the integer for each string.
//        }
//        return integers;
//
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            FileModel fileModel = fileEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(fileModel)));
//            tx.commit();
//            KeyShareEntity result = new KeyShareEntity(fileModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public KeyShareEntity update(int fileId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            KeyShareEntity fileEntity = new KeyShareEntity(fileId, startX, startY, endX, endY, shapeId);
//            session.update(fileEntity.toModel());
//            tx.commit();
//            KeyShareEntity result = get(fileId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public KeyShareEntity update(int fileId, KeyShareEntity fileEntity) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            session.update(fileEntity.toModel());
//            tx.commit();
//            KeyShareEntity result = get(fileId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public boolean delete(int id) {
//        Transaction tx = null;
//        try (Session session = factory.openSession()) {
//            tx = session.beginTransaction();
//            FileModel fileModel = new FileModel();
//            fileModel.setId(id);
//            session.delete(fileModel);
//            tx.commit();
//            return true;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public List<KeyShareEntity> get(SearchFileModel searchFileModel) {
//        Session session = factory.openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<FileModel> criteria = builder.createQuery(FileModel.class);
//        Root<FileModel> FileModels = criteria.from(FileModel.class);
//        try {
//            List<FileModel> fileList = session.createQuery(criteria).getResultList();
//            return fileList.stream()
//                    .map(s -> new KeyShareEntity(s)).collect(Collectors.toList());
//        } catch (NoResultException e) {
//            return null;
//        } finally {
//            session.close();
//        }
//    }
}
