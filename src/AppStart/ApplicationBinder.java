package AppStart;

import Manager.Interface.IDatabaseControllService;
import Manager.Interface.IDatabaseService;
import Manager.Service.DatabaseControllService;
import Manager.Service.DatabaseService;
import Module.EncryptFile.EncryptFileService;
import Module.EncryptKey.EncryptKeyService;
import Module.KeyShare.KeyShareService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(new DatabaseService()).to(IDatabaseService.class);
        bind(new DatabaseControllService()).to(IDatabaseControllService.class);
        bind(new KeyShareService()).to(KeyShareService.class);
        bind(new EncryptFileService()).to(EncryptFileService.class);
        bind(new EncryptKeyService()).to(EncryptKeyService.class);
    }
}