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

package org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.fetch;

import org.apache.seatunnel.connectors.cdc.base.source.offset.Offset;
import org.apache.seatunnel.connectors.cdc.base.source.reader.external.FetchTask;
import org.apache.seatunnel.connectors.cdc.base.source.split.SourceSplitBase;

import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

import io.debezium.connector.base.ChangeEventQueue;
import io.debezium.pipeline.DataChangeEvent;
import io.debezium.relational.TableId;
import io.debezium.relational.Tables;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TiDBFetchTaskContext implements FetchTask.Context {

    @Override
    public void configure(SourceSplitBase sourceSplitBase) {}

    @Override
    public ChangeEventQueue<DataChangeEvent> getQueue() {
        return null;
    }

    @Override
    public TableId getTableId(SourceRecord record) {
        return null;
    }

    @Override
    public Tables.TableFilter getTableFilter() {
        return null;
    }

    @Override
    public boolean isExactlyOnce() {
        return false;
    }

    @Override
    public Offset getStreamOffset(SourceRecord record) {
        return null;
    }

    @Override
    public boolean isDataChangeRecord(SourceRecord record) {
        return false;
    }

    @Override
    public boolean isRecordBetween(SourceRecord record, Object[] splitStart, Object[] splitEnd) {
        return false;
    }

    @Override
    public void rewriteOutputBuffer(
            Map<Struct, SourceRecord> outputBuffer, SourceRecord changeRecord) {}

    @Override
    public List<SourceRecord> formatMessageTimestamp(Collection<SourceRecord> snapshotRecords) {
        return null;
    }

    @Override
    public void close() {}
}
