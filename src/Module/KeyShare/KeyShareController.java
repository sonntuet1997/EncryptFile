package Module.KeyShare;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/key-share")
public class KeyShareController {
    @Inject
    private KeyShareService keyShareService;

    public KeyShareController() {
    }

    @POST
    @Path("encrypt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<KeyShareEntity> encode(RequestKeyShare requestKeyShare) {
        return keyShareService.encode(requestKeyShare);
    }

    @POST
    @Path("decrypt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String decode(List<KeyShareEntity> keyShareEntities) {
        return keyShareService.decode(keyShareEntities);
    }
}