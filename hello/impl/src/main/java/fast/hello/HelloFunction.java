/*
 * Copyright © 2016 SNLab and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package fast.hello;

import fast.api.FastDataStore;
import fast.api.FastFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloFunction implements FastFunction {

    private static final Logger LOG = LoggerFactory.getLogger(HelloFunction.class);

    private FastDataStore datastore = null;

    public void init(FastDataStore datastore) {
        this.datastore = datastore;
    }

    public void run() {
        /*
         * Implement your control logic here.
         * */
        LOG.info("HelloFunction said, \"Hello world!\"");
    }
}
