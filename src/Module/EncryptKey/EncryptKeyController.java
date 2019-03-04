package Module.EncryptKey;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/encrypt-key")
public class EncryptKeyController {
    @Inject
    private EncryptKeyService encryptKeyService;

    public EncryptKeyController() {
    }

    @POST
    @Path("encrypt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CryptoEntity encrypt(CryptoEntity cryptoEntity) {
        return encryptKeyService.encrypt(cryptoEntity);
    }

    @POST
    @Path("encrypt-all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CryptoEntity[] encryptAll(CryptoEntity[] cryptoEntities) {
        return encryptKeyService.encryptAll(cryptoEntities);
    }

    @POST
    @Path("decrypt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CryptoEntity decrypt(CryptoEntity cryptoEntity) {
        return encryptKeyService.decrypt(cryptoEntity);
    }
}