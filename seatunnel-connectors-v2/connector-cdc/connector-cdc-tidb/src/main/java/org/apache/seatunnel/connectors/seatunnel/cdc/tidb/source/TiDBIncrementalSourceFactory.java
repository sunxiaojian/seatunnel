/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source;

import org.apache.seatunnel.api.configuration.util.OptionRule;
import org.apache.seatunnel.api.source.SeaTunnelSource;
import org.apache.seatunnel.api.table.factory.Factory;
import org.apache.seatunnel.api.table.factory.TableSourceFactory;

import com.google.auto.service.AutoService;

@AutoService(Factory.class)
public class TiDBIncrementalSourceFactory implements TableSourceFactory {
    /**
     * Returns a unique identifier among same factory interfaces.
     *
     * <p>For consistency, an identifier should be declared as one lower case word (e.g. {@code
     * kafka}). If multiple factories exist for different versions, a version should be appended
     * using "-" (e.g. {@code elasticsearch-7}).
     */
    @Override
    public String factoryIdentifier() {
        return TiDBIncrementalSource.IDENTIFIER;
    }

    /**
     * Returns the rule for options.
     *
     * <p>1. Used to verify whether the parameters configured by the user conform to the rules of
     * the options;
     *
     * <p>2. Used for Web-UI to prompt user to configure option value;
     */
    @Override
    public OptionRule optionRule() {
        return null;
    }

    /**
     * TODO: Implement SupportParallelism in the TableSourceFactory instead of the SeaTunnelSource,
     * Then deprecated the method
     */
    @Override
    public Class<? extends SeaTunnelSource> getSourceClass() {
        return null;
    }
}
