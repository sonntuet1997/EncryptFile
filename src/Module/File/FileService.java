//package Module.File;
//
//import Manager.Entity.DatabaseEntity;
//import Manager.Interface.IDatabaseControllService;
//import Manager.Interface.IDatabaseService;
//import Manager.Service.DatabaseControllService;
//import Manager.Service.DatabaseService;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import javax.persistence.NoResultException;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Created by Son on 6/15/2017.
// */
//public class FileService {
//    private static SessionFactory factory;
//    private static int currentActive;
//
//    public FileService(SessionFactory factory) {
//        this.factory = factory;
//    }
//
//    public FileService() {
////        if (factory == null || currentActive != DatabaseEntity.Active) {
////            IDatabaseService databaseService = new DatabaseService();
////            IDatabaseControllService databaseControllService = new DatabaseControllService();
////            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
////            currentActive = DatabaseEntity.Active;
////        }
//    }
//
//    public static void setFactory(SessionFactory factory) {
//        FileService.factory = factory;
//    }
//
//
////    public KeyShareEntity get(int id) {
////        Session session = factory.openSession();
////        CriteriaBuilder builder = session.getCriteriaBuilder();
////        CriteriaQuery<FileModel> criteria = builder.createQuery(FileModel.class);
////        Root<FileModel> FileModels = criteria.from(FileModel.class);
////        criteria.where(builder.equal(FileModels.get("id"), id));
////        try {
////            FileModel fileModel = session.createQuery(criteria).getSingleResult();
////            return new KeyShareEntity(fileModel);
////        } catch (NoResultException e) {
////            return null;
////        } finally {
////            session.close();
////        }
////    }
//
////    public KeyShareEntity encryptAndHash(int fileId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
////        Transaction tx = null;
////        try (Session session = factory.openSession()) {
////            tx = session.beginTransaction();
////            KeyShareEntity fileEntity = new KeyShareEntity(fileId, startX, startY, endX, endY, shapeId);
////            FileModel fileModel = fileEntity.toModel();
////            Integer.valueOf(String.valueOf(session.save(fileModel)));
////            tx.commit();
////            KeyShareEntity result = new KeyShareEntity(fileModel);
////            return result;
////        } catch (HibernateException e) {
////            if (tx != null) tx.rollback();
////            e.printStackTrace();
////        }
////        return null;
////    }
////
////    public KeyShareEntity encryptAndHash(KeyShareEntity fileEntity) {
////        Transaction tx = null;
////        try (Session session = factory.openSession()) {
////            tx = session.beginTransaction();
////            FileModel fileModel = fileEntity.toModel();
////            Integer.valueOf(String.valueOf(session.save(fileModel)));
////            tx.commit();
////            KeyShareEntity result = new KeyShareEntity(fileModel);
////            return result;
////        } catch (HibernateException e) {
////            if (tx != null) tx.rollback();
////            e.printStackTrace();
////        }
////        return null;
////    }
//
////    public KeyShareEntity update(int fileId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
////        Transaction tx = null;
////        try (Session session = factory.openSession()) {
////            tx = session.beginTransaction();
////            KeyShareEntity fileEntity = new KeyShareEntity(fileId, startX, startY, endX, endY, shapeId);
////            session.update(fileEntity.toModel());
////            tx.commit();
////            KeyShareEntity result = get(fileId);
////            return result;
////        } catch (HibernateException e) {
////            if (tx != null) tx.rollback();
////            e.printStackTrace();
////        }
////        return null;
////    }
//
////    public KeyShareEntity update(int fileId, KeyShareEntity fileEntity) {
////        Transaction tx = null;
////        try (Session session = factory.openSession()) {
////            tx = session.beginTransaction();
////            session.update(fileEntity.toModel());
////            tx.commit();
////            KeyShareEntity result = get(fileId);
////            return result;
////        } catch (HibernateException e) {
////            if (tx != null) tx.rollback();
////            e.printStackTrace();
////        }
////        return null;
////    }
////
////    public boolean delete(int id) {
////        Transaction tx = null;
////        try (Session session = factory.openSession()) {
////            tx = session.beginTransaction();
////            FileModel fileModel = new FileModel();
////            fileModel.setId(id);
////            session.delete(fileModel);
////            tx.commit();
////            return true;
////        } catch (HibernateException e) {
////            if (tx != null) tx.rollback();
////            e.printStackTrace();
////        }
////        return false;
////    }
////
////    public List<KeyShareEntity> get(SearchFileModel searchFileModel) {
////        Session session = factory.openSession();
////        CriteriaBuilder builder = session.getCriteriaBuilder();
////        CriteriaQuery<FileModel> criteria = builder.createQuery(FileModel.class);
////        Root<FileModel> FileModels = criteria.from(FileModel.class);
////        try {
////            List<FileModel> fileList = session.createQuery(criteria).getResultList();
////            return fileList.stream()
////                    .map(s -> new KeyShareEntity(s)).collect(Collectors.toList());
////        } catch (NoResultException e) {
////            return null;
////        } finally {
////            session.close();
////        }
////    }
//}
