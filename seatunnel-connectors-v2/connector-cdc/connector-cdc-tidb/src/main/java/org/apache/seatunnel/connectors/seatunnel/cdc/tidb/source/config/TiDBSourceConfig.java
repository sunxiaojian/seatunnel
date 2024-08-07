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

package org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.config;

import org.apache.seatunnel.connectors.cdc.base.config.SourceConfig;
import org.apache.seatunnel.connectors.cdc.base.config.StartupConfig;
import org.apache.seatunnel.connectors.cdc.base.config.StopConfig;

import org.tikv.common.TiConfiguration;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class TiDBSourceConfig implements SourceConfig {

    public TiDBSourceConfig(
            String databaseName,
            String tableName,
            String pdAddresses,
            StartupConfig startupConfig,
            StopConfig stopConfig,
            TiConfiguration tiConfiguration) {

        this.databaseName = databaseName;
        this.tableName = tableName;
        this.pdAddresses = pdAddresses;
        this.startupConfig = startupConfig;
        this.stopConfig = stopConfig;
        this.tiConfiguration = tiConfiguration;
    }

    private String databaseName;
    private String tableName;
    private String pdAddresses;
    private StartupConfig startupConfig;
    private StopConfig stopConfig;
    private TiConfiguration tiConfiguration;

    @Override
    public StartupConfig getStartupConfig() {
        return startupConfig;
    }

    @Override
    public StopConfig getStopConfig() {
        return stopConfig;
    }

    @Override
    public int getSplitSize() {
        return 0;
    }

    @Override
    public boolean isExactlyOnce() {
        return false;
    }
}
