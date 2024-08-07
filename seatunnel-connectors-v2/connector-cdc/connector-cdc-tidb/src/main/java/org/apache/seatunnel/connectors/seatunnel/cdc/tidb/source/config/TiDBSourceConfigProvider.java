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

import static org.apache.seatunnel.shade.com.google.common.base.Preconditions.checkNotNull;

public class TiDBSourceConfigProvider {
    private TiDBSourceConfigProvider() {}

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder implements SourceConfig.Factory<TiDBSourceConfig> {
        private String databaseName;
        private String tableName;
        private StartupConfig startupConfig;
        private StopConfig stopConfig;
        private int parallelism;
        private TiConfiguration tiConfiguration;

        public Builder databaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        public Builder tableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public Builder startupConfig(StartupConfig startupConfig) {
            this.startupConfig = startupConfig;
            return this;
        }

        public Builder stopConfig(StopConfig stopConfig) {
            this.stopConfig = stopConfig;
            return this;
        }

        public Builder parallelism(int parallelism) {
            this.parallelism = parallelism;
            return this;
        }

        public Builder tiConfiguration(TiConfiguration tiConfiguration) {
            this.tiConfiguration = tiConfiguration;
            return this;
        }

        public Builder validate() {
            checkNotNull(databaseName, "databaseName must be provided");
            checkNotNull(tableName, "tableName must be provided");
            checkNotNull(tiConfiguration, "tiConfiguration must be provided");
            return this;
        }

        @Override
        public TiDBSourceConfig create(int subtask) {
            return new TiDBSourceConfig(
                    databaseName,
                    tableName,
                    startupConfig,
                    stopConfig,
                    parallelism,
                    tiConfiguration);
        }
    }
}
