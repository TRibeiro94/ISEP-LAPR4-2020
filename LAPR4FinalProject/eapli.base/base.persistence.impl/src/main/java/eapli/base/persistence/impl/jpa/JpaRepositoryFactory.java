package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.depositsmanagement.repositories.DepositRepository;
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
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public RawMaterialCategoryRepository rawMaterialCategories() {
        return new JpaRawMaterialCategoriesRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public RawMaterialRepository rawMaterials() {
        return new JpaRawMaterialRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ProductRepository productRepository() {
        return new JpaProductRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ProductionLineRepository productionLineRepository() {
        return new JpaProductionLineRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MachineRepository machineRepository() {
        return new JpaMachineRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public DepositRepository depositRepository() {
        return new JpaDepositRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ProductionOrderRepository productionOrderRepository() {
        return new JpaProductionOrderRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public NotificationRepository notificationRepository() {
        return new JpaNotificationRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MessageRepository messageRepository() {
        return new JpaMessageRepository(Application.settings().getPersistenceUnitName());
    }
    
    @Override
    public LotRepository lotRepository() {
        return new JpaLotRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public DetailedMachineTimesRepository detailedMachineTimesRepository() {
        return new JpaDetailedMachineTimesRepository(Application.settings().getPersistenceUnitName());
    }
}
