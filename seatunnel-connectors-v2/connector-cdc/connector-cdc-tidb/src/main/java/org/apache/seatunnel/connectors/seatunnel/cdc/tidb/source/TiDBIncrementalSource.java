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
import org.apache.seatunnel.api.source.SourceSplitEnumerator;
import org.apache.seatunnel.connectors.cdc.base.config.SourceConfig;
import org.apache.seatunnel.connectors.cdc.base.dialect.DataSourceDialect;
import org.apache.seatunnel.connectors.cdc.base.option.StartupMode;
import org.apache.seatunnel.connectors.cdc.base.option.StopMode;
import org.apache.seatunnel.connectors.cdc.base.source.IncrementalSource;
import org.apache.seatunnel.connectors.cdc.base.source.offset.OffsetFactory;
import org.apache.seatunnel.connectors.cdc.debezium.DebeziumDeserializationSchema;
import org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.dialect.TiDBDialect;

import java.io.Serializable;

public class TiDBIncrementalSource extends IncrementalSource {

    static final String IDENTIFIER = "TIDB-CDC";
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

    /**
     * Create source split enumerator, used to generate splits. This method will be called only once
     * when start a source.
     *
     * @param enumeratorContext enumerator context.
     * @return source split enumerator.
     * @throws Exception when create enumerator failed.
     */
    @Override
    public SourceSplitEnumerator createEnumerator(SourceSplitEnumerator.Context enumeratorContext)
            throws Exception {
        return null;
    }

    /**
     * Create source split enumerator, used to generate splits. This method will be called when
     * restore from checkpoint.
     *
     * @param enumeratorContext enumerator context.
     * @param checkpointState checkpoint state.
     * @return source split enumerator.
     * @throws Exception when create enumerator failed.
     */
    @Override
    public SourceSplitEnumerator restoreEnumerator(
            SourceSplitEnumerator.Context enumeratorContext, Serializable checkpointState)
            throws Exception {
        return null;
    }

    @Override
    public Option<StartupMode> getStartupModeOption() {
        return null;
    }

    @Override
    public Option<StopMode> getStopModeOption() {
        return null;
    }

    @Override
    public SourceConfig.Factory createSourceConfigFactory(ReadonlyConfig config) {
        return null;
    }

    @Override
    public DebeziumDeserializationSchema createDebeziumDeserializationSchema(
            ReadonlyConfig config) {
        return null;
    }

    @Override
    public DataSourceDialect createDataSourceDialect(ReadonlyConfig config) {
        return new TiDBDialect();
    }

    @Override
    public OffsetFactory createOffsetFactory(ReadonlyConfig config) {
        return null;
    }
}
