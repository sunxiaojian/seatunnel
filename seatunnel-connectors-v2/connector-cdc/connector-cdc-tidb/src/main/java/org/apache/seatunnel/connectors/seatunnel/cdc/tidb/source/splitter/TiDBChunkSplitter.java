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

package org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.splitter;

import org.apache.seatunnel.shade.com.google.common.collect.Lists;

import org.apache.seatunnel.connectors.cdc.base.source.enumerator.splitter.ChunkSplitter;
import org.apache.seatunnel.connectors.cdc.base.source.split.SnapshotSplit;
import org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.config.TiDBSourceConfig;
import org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.utils.TableKeyRangeUtils;

import org.tikv.common.TiSession;
import org.tikv.common.meta.TiTableInfo;
import org.tikv.kvproto.Coprocessor;

import io.debezium.relational.TableId;

import javax.annotation.Nonnull;

import java.util.Collection;
import java.util.List;

public class TiDBChunkSplitter implements ChunkSplitter {

    private final TiDBSourceConfig sourceConfig;

    private transient TiSession session;

    public TiDBChunkSplitter(TiDBSourceConfig sourceConfig) {
        this.sourceConfig = sourceConfig;
        session = TiSession.create(sourceConfig.getTiConfiguration());
    }

    /**
     * Generates all snapshot splits (chunks) for the give data collection.
     *
     * @param tableId
     */
    @Override
    public Collection<SnapshotSplit> generateSplits(TableId tableId) {
        TiTableInfo tableInfo =
                session.getCatalog()
                        .getTable(sourceConfig.getDatabaseName(), sourceConfig.getTableName());
        List<Coprocessor.KeyRange> keyRanges =
                TableKeyRangeUtils.getTableKeyRanges(
                        tableInfo.getId(), sourceConfig.getParallelism());
        return generateSnapshotSplits(tableId, keyRanges);
    }

    private Collection<SnapshotSplit> generateSnapshotSplits(
            TableId tableId, List<Coprocessor.KeyRange> keyRanges) {
        List<SnapshotSplit> snapshotSplits = Lists.newArrayList();
        for (Coprocessor.KeyRange keyRange : keyRanges) {
            snapshotSplits.add(
                    new SnapshotSplit(
                            splitId(tableId, keyRange),
                            tableId,
                            null,
                            new Object[] {keyRange},
                            new Object[] {keyRange}));
        }
        return snapshotSplits;
    }

    private String splitId(@Nonnull TableId tableId, Coprocessor.KeyRange keyRange) {
        return String.format(
                "%s:%s:%s", tableId.identifier(), keyRange.getStart(), keyRange.getEnd());
    }
}
