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

import org.apache.seatunnel.api.configuration.Option;
import org.apache.seatunnel.api.configuration.ReadonlyConfig;
import org.apache.seatunnel.api.source.SupportParallelism;
import org.apache.seatunnel.api.table.catalog.CatalogTable;
import org.apache.seatunnel.api.table.type.SeaTunnelDataType;
import org.apache.seatunnel.api.table.type.SeaTunnelRow;
import org.apache.seatunnel.connectors.cdc.base.config.SourceConfig;
import org.apache.seatunnel.connectors.cdc.base.dialect.DataSourceDialect;
import org.apache.seatunnel.connectors.cdc.base.option.StartupMode;
import org.apache.seatunnel.connectors.cdc.base.option.StopMode;
import org.apache.seatunnel.connectors.cdc.base.source.IncrementalSource;
import org.apache.seatunnel.connectors.cdc.base.source.offset.OffsetFactory;
import org.apache.seatunnel.connectors.cdc.debezium.DebeziumDeserializationSchema;
import org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.config.TiDBSourceConfig;
import org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.config.TiDBSourceConfigProvider;
import org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.dialect.TiDBDialect;

import org.tikv.common.TiConfiguration;

import javax.annotation.Nonnull;

import java.util.List;

public class TiDBIncrementalSource<T> extends IncrementalSource<T, TiDBSourceConfig>
        implements SupportParallelism {

    static final String IDENTIFIER = "TIDB-CDC";

    public TiDBIncrementalSource(
            ReadonlyConfig options,
            SeaTunnelDataType<SeaTunnelRow> dataType,
            List<CatalogTable> catalogTables) {
        super(options, dataType, catalogTables);
    }

    /**
     * Returns a unique identifier among same factory interfaces.
     *
     * <p>For consistency, an identifier should be declared as one lower case word (e.g. {@code
     * kafka}). If multiple factories exist for different versions, a version should be appended
     * using "-" (e.g. {@code elasticsearch-7}).
     */
    @Override
    public String getPluginName() {
        return IDENTIFIER;
    }

    @Override
    public Option<StartupMode> getStartupModeOption() {
        return TiDBSourceOptions.STARTUP_MODE;
    }

    @Override
    public Option<StopMode> getStopModeOption() {
        return TiDBSourceOptions.STOP_MODE;
    }

    @Override
    public SourceConfig.Factory<TiDBSourceConfig> createSourceConfigFactory(
            @Nonnull ReadonlyConfig config) {
        TiDBSourceConfigProvider.Builder builder = new TiDBSourceConfigProvider.Builder();
        builder.databaseName(config.get(TiDBSourceOptions.DATABASE_NAME));
        builder.tableName(config.get(TiDBSourceOptions.TABLE_NAME));
        builder.startupConfig(startupConfig);
        builder.stopConfig(stopConfig);
        TiConfiguration configuration =
                TiDBSourceOptions.getTiConfiguration(
                        config.get(TiDBSourceOptions.PD_ADDRESSES), config);

        builder.tiConfiguration(configuration);
        return builder;
    }

    @Override
    public DebeziumDeserializationSchema<T> createDebeziumDeserializationSchema(
            ReadonlyConfig config) {
        return null;
    }

    @Override
    public DataSourceDialect<TiDBSourceConfig> createDataSourceDialect(ReadonlyConfig config) {
        return new TiDBDialect();
    }

    @Override
    public OffsetFactory createOffsetFactory(ReadonlyConfig config) {
        return null;
    }
}
