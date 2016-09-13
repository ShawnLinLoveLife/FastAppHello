/*
 * Copyright © 2016 SNLab and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package fast.hello;

import javax.annotation.Nonnull;
import fast.api.FastSystem;
import fast.api.UserHints;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketReceived;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FastMainBase implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(FastMainBase.class);

    protected FastSystem fast;

    public void init(@Nonnull FastSystem fast,
                     @Nonnull HelloExternalEventTrigger trigger) {
        LOG.info("HelloMainBase Session Initiated");
        this.fast = fast;
        trigger.bind(this);
        LOG.info("HelloMainBase initialized successfully");
    }

    public abstract void onCreate();

    @Override
    public abstract void close() throws Exception;

    public abstract void onPacketIn(PacketReceived packetIn);
}