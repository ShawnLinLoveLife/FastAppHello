
package org.opendaylight.yang.gen.v1.urn.fast.app.hello.impl.rev160902;

import fast.api.FastSystem;

import fast.hello.HelloExternalEventTrigger;
import fast.hello.HelloMain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloModule extends org.opendaylight.yang.gen.v1.urn.fast.app.hello.impl.rev160902.AbstractHelloModule {
    private static final Logger LOG = LoggerFactory.getLogger(HelloMain.class);

    public HelloModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public HelloModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.fast.app.hello.impl.rev160902.HelloModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        FastSystem fast = getFastSystemDependency();
        if (fast == null) {
            LOG.error("Error loading HelloMain: No FAST system found.");
            return null;
        }

        final HelloExternalEventTrigger trigger = new HelloExternalEventTrigger();
        try {
            getBrokerDependency().registerProvider(trigger);
        } catch (Exception e) {
            LOG.error("Error loading HelloMain: Cannot register external event trigger");
            return null;
        }

        final HelloMain main = new HelloMain();
        main.init(fast, trigger);
        main.onCreate();

        return new AutoCloseable() {
            @Override
            public void close() {
                try {
                    trigger.close();
                } catch (Exception e) {
                }
                try {
                    main.close();
                } catch (Exception e) {
                }
            }
        };
    }

}
