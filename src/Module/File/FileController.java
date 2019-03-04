//package Module.File;
//
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/files")
//public class FileController {
//    @Inject
//    private FileService fileService;
//
//    public FileController() {
//
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public FileEntity get() {
////    public List<KeyShareEntity> get(@BeanParam SearchFileModel searchFileModel) {
//        return new FileEntity();
////        return fileService.get(searchFileModel);
//    }
//
////    @GET
////    @Consumes(MediaType.APPLICATION_JSON)
////    @Path("/Count")
////    public int count(@BeanParam SearchFileModel searchFileModel) {
////        return 100;
////    }
////
////    @GET
////    @Produces(MediaType.APPLICATION_JSON)
////    @Consumes(MediaType.APPLICATION_JSON)
////    @Path("{fileId}")
////    public KeyShareEntity getId(@PathParam("fileId") int fileId) {
////        return fileService.get(fileId);
////    }
////
////    @POST
////    @Produces(MediaType.APPLICATION_JSON)
////    @Consumes(MediaType.APPLICATION_JSON)
////    public KeyShareEntity encryptAndHash(KeyShareEntity fileEntity) {
////        return fileService.encryptAndHash(fileEntity);
////    }
//
////    @PUT
////    @Produces(MediaType.APPLICATION_JSON)
////    @Consumes(MediaType.APPLICATION_JSON)
////    @Path("{fileId}")
////    public KeyShareEntity update(@PathParam("fileId") int fileId, KeyShareEntity fileEntity) {
////        return fileService.update(fileId, fileEntity);
////    }
////
////    @DELETE
////    @Produces(MediaType.APPLICATION_JSON)
////    @Consumes(MediaType.APPLICATION_JSON)
////    @Path("{fileId}")
////    public void delete(@PathParam("fileId") int fileId) {
////        fileService.delete(fileId);
////    }
//
//}