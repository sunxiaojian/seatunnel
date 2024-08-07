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

package org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.dialect;

import org.apache.seatunnel.connectors.cdc.base.dialect.DataSourceDialect;
import org.apache.seatunnel.connectors.cdc.base.source.enumerator.splitter.ChunkSplitter;
import org.apache.seatunnel.connectors.cdc.base.source.reader.external.FetchTask;
import org.apache.seatunnel.connectors.cdc.base.source.split.SourceSplitBase;
import org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.config.TiDBSourceConfig;
import org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.splitter.TiDBChunkSplitter;

import io.debezium.relational.TableId;

import java.util.List;

import static org.apache.seatunnel.connectors.seatunnel.jdbc.internal.dialect.DatabaseIdentifier.TIDB;

public class TiDBDialect implements DataSourceDialect<TiDBSourceConfig> {

    /** Get the name of dialect. */
    @Override
    public String getName() {
        return TIDB;
    }

    /**
     * Discovers the list of data collection to capture.
     *
     * @param sourceConfig
     */
    @Override
    public List<TableId> discoverDataCollections(TiDBSourceConfig sourceConfig) {
        return null;
    }

    /**
     * Check if the CollectionId is case-sensitive or not.
     *
     * @param sourceConfig
     */
    @Override
    public boolean isDataCollectionIdCaseSensitive(TiDBSourceConfig sourceConfig) {
        return true;
    }

    /**
     * Returns the {@link ChunkSplitter} which used to split collection to splits.
     *
     * @param sourceConfig
     */
    @Override
    public ChunkSplitter createChunkSplitter(TiDBSourceConfig sourceConfig) {
        return new TiDBChunkSplitter(sourceConfig);
    }

    /**
     * The fetch task used to fetch data of a snapshot split or incremental split.
     *
     * @param sourceSplitBase
     */
    @Override
    public FetchTask<SourceSplitBase> createFetchTask(SourceSplitBase sourceSplitBase) {
        return null;
    }

    /**
     * The task context used for fetch task to fetch data from external systems.
     *
     * @param sourceSplitBase
     * @param sourceConfig
     */
    @Override
    public FetchTask.Context createFetchTaskContext(
            SourceSplitBase sourceSplitBase, TiDBSourceConfig sourceConfig) {
        return null;
    }
}
