/**
 *
 */
package eapli.base.infrastructure.persistence;

import eapli.base.clientusermanagement.repositories.ClientUserRepository;

import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.depositsmanagement.repositories.DepositRepository;
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

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

	/**
	 * factory method to create a transactional context to use in the repositories
	 *
	 * @return
	 */
	TransactionalContext newTransactionalContext();

	/**
	 *
	 * @param autoTx the transactional context to enrol
	 * @return
	 */
	UserRepository users(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	UserRepository users();

	/**
	 *
	 * @param autoTx the transactional context to enroll
	 * @return
	 */
	ClientUserRepository clientUsers(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	ClientUserRepository clientUsers();

	/**
	 *
	 * @param autoTx the transactional context to enroll
	 * @return
	 */
	SignupRequestRepository signupRequests(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	SignupRequestRepository signupRequests();

        RawMaterialCategoryRepository rawMaterialCategories();

	RawMaterialRepository rawMaterials();

        ProductRepository productRepository();

	ProductionLineRepository productionLineRepository();

	MachineRepository machineRepository();
        
        DepositRepository depositRepository();

        ProductionOrderRepository productionOrderRepository();
    
        NotificationRepository notificationRepository();
        
        MessageRepository messageRepository();
        
        LotRepository lotRepository();
        
        DetailedMachineTimesRepository detailedMachineTimesRepository();

}
