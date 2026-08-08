/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.engio.mbassy.spring.boot.hooks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.spring.boot.event.MBassadorEvent;

/**
 * Unit tests for {@link MBassadorShutdownHook}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("MBassadorShutdownHook Tests")
class MBassadorShutdownHookTest {

    @Test
    @DisplayName("Constructor accepts a null bus")
    void testConstructorWithNull() {
        MBassadorShutdownHook hook = new MBassadorShutdownHook(null);
        assertThat(hook).isNotNull();
    }

    @Test
    @DisplayName("Constructor stores the provided bus")
    void testConstructorWithBus() {
        MBassador<MBassadorEvent> bus = mock(MBassador.class);
        MBassadorShutdownHook hook = new MBassadorShutdownHook(bus);
        assertThat(hook).isNotNull();
        assertThat(hook).isInstanceOf(Thread.class);
    }

    @Test
    @DisplayName("run() shuts the bus down")
    void testRunShutsDown() {
        MBassador<MBassadorEvent> bus = mock(MBassador.class);
        MBassadorShutdownHook hook = new MBassadorShutdownHook(bus);

        hook.run();

        verify(bus).shutdown();
    }
}
