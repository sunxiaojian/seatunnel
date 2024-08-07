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

import org.apache.seatunnel.connectors.cdc.base.config.JdbcSourceConfig;
import org.apache.seatunnel.connectors.cdc.base.dialect.JdbcDataSourceDialect;
import org.apache.seatunnel.connectors.cdc.base.source.enumerator.splitter.ChunkSplitter;
import org.apache.seatunnel.connectors.cdc.base.source.reader.external.FetchTask;
import org.apache.seatunnel.connectors.cdc.base.source.reader.external.JdbcSourceFetchTaskContext;
import org.apache.seatunnel.connectors.cdc.base.source.split.SourceSplitBase;

import io.debezium.jdbc.JdbcConnection;
import io.debezium.relational.TableId;
import io.debezium.relational.history.TableChanges;

import java.util.List;

public class TiDBDialect implements JdbcDataSourceDialect {
    /** Get the name of dialect. */
    @Override
    public String getName() {
        return null;
    }

    /**
     * Check if the CollectionId is case-sensitive or not.
     *
     * @param sourceConfig
     */
    @Override
    public boolean isDataCollectionIdCaseSensitive(JdbcSourceConfig sourceConfig) {
        return false;
    }

    /**
     * Returns the {@link ChunkSplitter} which used to split collection to splits.
     *
     * @param sourceConfig
     */
    @Override
    public ChunkSplitter createChunkSplitter(JdbcSourceConfig sourceConfig) {
        return null;
    }

    /**
     * Discovers the list of table to capture.
     *
     * @param sourceConfig
     */
    @Override
    public List<TableId> discoverDataCollections(JdbcSourceConfig sourceConfig) {
        return null;
    }

    /**
     * Creates and opens a new {@link JdbcConnection} backing connection pool.
     *
     * @param sourceConfig a basic source configuration.
     * @return a utility that simplifies using a JDBC connection.
     */
    @Override
    public JdbcConnection openJdbcConnection(JdbcSourceConfig sourceConfig) {
        return null;
    }

    /**
     * Query and build the schema of table.
     *
     * @param jdbc
     * @param tableId
     */
    @Override
    public TableChanges.TableChange queryTableSchema(JdbcConnection jdbc, TableId tableId) {
        return null;
    }

    @Override
    public FetchTask<SourceSplitBase> createFetchTask(SourceSplitBase sourceSplitBase) {
        return null;
    }

    @Override
    public JdbcSourceFetchTaskContext createFetchTaskContext(
            SourceSplitBase sourceSplitBase, JdbcSourceConfig taskSourceConfig) {
        return null;
    }
}
