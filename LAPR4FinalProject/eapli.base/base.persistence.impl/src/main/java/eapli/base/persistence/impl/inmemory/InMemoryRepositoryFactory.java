package eapli.base.persistence.impl.inmemory;

import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.depositsmanagement.repositories.DepositRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.base.messagemanagement.repositories.MessageRepository;
import eapli.base.notificationmanagement.repositories.NotificationRepository;
import eapli.base.productionlinemanagement.repositories.ProductionLineRepository;
import eapli.base.productionordermanagement.repositories.DetailedMachineTimesRepository;
import eapli.base.productionordermanagement.repositories.LotRepository;
import eapli.base.productionordermanagement.repositories.ProductionOrderRepository;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialCategoryRepository;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.InMemoryUserRepository;

/**
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public ClientUserRepository clientUsers(final TransactionalContext tx) {

        return new InMemoryClientUserRepository();
    }

    @Override
    public ClientUserRepository clientUsers() {
        return clientUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public RawMaterialCategoryRepository rawMaterialCategories() {
        return new InMemoryRawMaterialCategoriesRepository();
    }

    @Override
    public RawMaterialRepository rawMaterials() {
        return new InMemoryRawMaterialsRepository();
    }

    @Override
    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }

    @Override
    public ProductionLineRepository productionLineRepository() {
        return new InMemoryProductionLineRepository();
    }

    @Override
    public MachineRepository machineRepository() {
        return new InMemoryMachineRepository();
    }

    @Override
    public DepositRepository depositRepository() {
        return new InMemoryDepositRepository();
    }

    @Override
    public ProductionOrderRepository productionOrderRepository() {
        return new InMemoryProductionOrderRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

    @Override
    public NotificationRepository notificationRepository() {
        return new InMemoryNotificationRepository();
    }

    @Override
    public MessageRepository messageRepository() {
        return new InMemoryMessageRepository();
    }

    @Override
    public LotRepository lotRepository() {
        return new InMemoryLotRepository();
    }
    
    @Override
    public DetailedMachineTimesRepository detailedMachineTimesRepository() {
        return new InMemoryDetailedMachineTimesRepository();
    }

}
