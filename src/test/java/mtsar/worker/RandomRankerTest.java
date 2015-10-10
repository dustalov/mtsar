/*
 * Copyright 2015 Dmitry Ustalov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mtsar.worker;

import mtsar.api.Process;
import mtsar.api.Worker;
import mtsar.processors.WorkerRanker;
import mtsar.processors.worker.RandomRanker;
import org.junit.Before;
import org.junit.Test;

import static mtsar.TestHelper.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomRankerTest {
    private static final Process.Definition process = mock(Process.Definition.class);
    private static final Worker worker = fixture("worker1.json", Worker.class);
    private static final WorkerRanker ranker = new RandomRanker();

    @Before
    public void setup() {
        when(process.getId()).thenReturn("1");
    }

    @Test
    public void testRanking() {
        assertThat(ranker.rank(worker).isPresent()).isTrue();
        assertThat(ranker.rank(worker).get().getReputation()).isBetween(0.0, 1.0);
    }
}
