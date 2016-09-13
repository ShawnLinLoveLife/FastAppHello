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

public class HelloMain extends FastMainBase {

    private static final Logger LOG = LoggerFactory.getLogger(HelloMain.class);

    /* the ID for the first function instance */
    private String fid;

    public void onCreate() {
        LOG.info("HelloMain Session Initiated");

        /*
         * This is usually where you launch the first FAST function instance.
         *
         * But you can just get the FastSystem instance and submit function instances later.
         * */
        fid = this.fast.submit(new HelloFunction());

        LOG.info("HelloMain initialized successfully");
    }

    @Override
    public void close() throws Exception {
        this.fast.delete(fid);

        LOG.info("HelloLauncher Closed");
    }

    @Override
    public void onPacketIn(PacketReceived packetIn) {
        LOG.info("Received one packet");
        /*
         * This is the handler of the triggered event.
         *
         * You can launch function instances here.
         * */
        HelloFunction f = new HelloFunction();

        /* You can set the precedence to make sure the first function instance is always executed first */
        String[] precedences = { fid };
        this.fast.submit(f, new UserHints(precedences));
    }
}
