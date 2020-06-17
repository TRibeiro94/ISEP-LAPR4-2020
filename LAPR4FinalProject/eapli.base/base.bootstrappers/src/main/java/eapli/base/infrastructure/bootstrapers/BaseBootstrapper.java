/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.infrastructure.bootstrapers;

import eapli.base.infrastructure.bootstrapers.demo.DepositBootstrapper;
import eapli.base.infrastructure.bootstrapers.demo.MachineBootstrapper;
import eapli.base.infrastructure.bootstrapers.demo.NotificationBootstrapper;
import eapli.base.infrastructure.bootstrapers.demo.ProductBootstrapper;
import eapli.base.infrastructure.bootstrapers.demo.ProductionLineBootstrapper;
import eapli.base.infrastructure.bootstrapers.demo.RawMaterialBootstrapper;
import eapli.base.infrastructure.bootstrapers.demo.RawMaterialCategoryBootstrapper;
import eapli.base.infrastructure.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.util.Strings;
import eapli.framework.validations.Invariants;

/**
 * Base Bootstrapping data app
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings("squid:S106")
public class BaseBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            BaseBootstrapper.class);

    private static final String POWERUSER_A1 = "poweruserA1";
    private static final String POWERUSER = "poweruser";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private final UserRepository userRepository = PersistenceContext.repositories().users();

    @Override
    public boolean execute() {
        // declare bootstrap actions
        final Action[] actions = { new MasterUsersBootstrapper(), new RawMaterialCategoryBootstrapper(), new RawMaterialBootstrapper(), new ProductBootstrapper(), new ProductionLineBootstrapper(), new DepositBootstrapper(), new MachineBootstrapper(), new NotificationBootstrapper()};

        registerPowerUser();
        registerFloorManager();
        registerProductManager();
        registerSMM();
        authenticateForBootstrapping();

        // execute all bootstrapping
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    private boolean registerProductManager() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername("productMan").withPassword("productMan1").withName("Tio", "Product")
                .withEmail("product@email.org").withRoles(BaseRoles.PRODUCT_MANAGER);
        final SystemUser newUser = userBuilder.build();

        SystemUser productUser;
        try {
            productUser = userRepository.save(newUser);
            assert productUser != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerFloorManager() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername("floorMan").withPassword("floorMan1").withName("Tio", "Floor")
                .withEmail("tio@email.org").withRoles(BaseRoles.FACTORY_FLOOR_MANAGER);
        final SystemUser newUser = userBuilder.build();

        SystemUser floorUser;
        try {
            floorUser = userRepository.save(newUser);
            assert floorUser != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }
    
    private boolean registerSMM() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername("monitorsys").withPassword("monitorsysA1").withName("Monitorization System", "App")
                .withEmail("smm@email.org").withRoles(BaseRoles.SMM);
        final SystemUser newUser = userBuilder.build();

        SystemUser smmUser;
        try {
            smmUser = userRepository.save(newUser);
            assert smmUser != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    /**
     * register a power user directly in the persistence layer as we need to
     * circumvent authorisations in the Application Layer
     */
    private boolean registerPowerUser() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(POWERUSER).withPassword(POWERUSER_A1).withName("joe", "power")
                .withEmail("joe@email.org").withRoles(BaseRoles.POWER_USER);
        final SystemUser newUser = userBuilder.build();

        SystemUser poweruser;
        try {
            poweruser = userRepository.save(newUser);
            assert poweruser != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    /**
     * authenticate a super user to be able to register new users
     *
     */
    protected void authenticateForBootstrapping() {
        authenticationService.authenticate(POWERUSER, POWERUSER_A1);
        Invariants.ensure(authz.hasSession());
    }

    private String nameOfEntity(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}
