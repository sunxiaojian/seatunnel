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

import org.apache.seatunnel.connectors.cdc.base.source.reader.external.FetchTask;
import org.apache.seatunnel.connectors.cdc.base.source.split.SnapshotSplit;
import org.apache.seatunnel.connectors.cdc.base.source.split.SourceSplitBase;

public class TiDBScanFetchTask implements FetchTask<SourceSplitBase> {

    private final SnapshotSplit snapshotSplit;

    private volatile boolean taskRunning = false;

    public TiDBScanFetchTask(SnapshotSplit snapshotSplit) {
        this.snapshotSplit = snapshotSplit;
    }
    /**
     * Execute current task.
     *
     * @param context
     */
    @Override
    public void execute(Context context) throws Exception {
        TiDBFetchTaskContext taskContext = (TiDBFetchTaskContext) context;
    }

    /** Returns current task is running or not. */
    @Override
    public boolean isRunning() {
        return taskRunning;
    }

    /** Close this task */
    @Override
    public void shutdown() {}

    /** Returns the split that the task used. */
    @Override
    public SourceSplitBase getSplit() {
        return null;
    }
}
