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
package net.engio.mbassy.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.engio.mbassy.bus.BusRuntime;
import net.engio.mbassy.bus.IMessagePublication;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.bus.publication.SyncAsyncPostCommand;
import net.engio.mbassy.spring.boot.event.MBassadorEvent;

/**
 * Unit tests for {@link MbassadorTemplate}.
 *
 * <p>Every public method is exercised by stubbing the wrapped
 * {@link MBassador} and verifying the template delegates correctly.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("MbassadorTemplate Tests")
class MbassadorTemplateTest {

    private MbassadorTemplate template;
    private MBassador<MBassadorEvent> mbassador;
    private MBassadorEvent event;

    @BeforeEach
    void setUp() {
        template = new MbassadorTemplate();
        mbassador = mock(MBassador.class);
        template.setMbassador(mbassador);
        event = new TestEvent("source");
    }

    @Test
    @DisplayName("setMbassador/getMbassador round-trip the bus instance")
    void testGetterSetter() {
        MBassador<MBassadorEvent> another = mock(MBassador.class);
        template.setMbassador(another);
        assertThat(template.getMbassador()).isSameAs(another);
    }

    @Test
    @DisplayName("post delegates to MBassador.post")
    void testPost() {
        SyncAsyncPostCommand<MBassadorEvent> command = mock(SyncAsyncPostCommand.class);
        when(mbassador.post(event)).thenReturn(command);

        SyncAsyncPostCommand<MBassadorEvent> result = template.post(event);

        assertThat(result).isSameAs(command);
        verify(mbassador).post(event);
    }

    @Test
    @DisplayName("publish delegates to MBassador.publish")
    void testPublish() {
        IMessagePublication publication = mock(IMessagePublication.class);
        when(mbassador.publish(event)).thenReturn(publication);

        IMessagePublication result = template.publish(event);

        assertThat(result).isSameAs(publication);
        verify(mbassador).publish(event);
    }

    @Test
    @DisplayName("publishAsync delegates to MBassador.publishAsync")
    void testPublishAsync() {
        IMessagePublication publication = mock(IMessagePublication.class);
        when(mbassador.publishAsync(event)).thenReturn(publication);

        IMessagePublication result = template.publishAsync(event);

        assertThat(result).isSameAs(publication);
        verify(mbassador).publishAsync(event);
    }

    @Test
    @DisplayName("publishAsync with timeout delegates to MBassador.publishAsync(message, timeout, unit)")
    void testPublishAsyncWithTimeout() {
        IMessagePublication publication = mock(IMessagePublication.class);
        when(mbassador.publishAsync(event, 5L, TimeUnit.SECONDS)).thenReturn(publication);

        IMessagePublication result = template.publishAsync(event, 5L, TimeUnit.SECONDS);

        assertThat(result).isSameAs(publication);
        verify(mbassador).publishAsync(event, 5L, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("getRegisteredErrorHandlers delegates to the bus")
    void testGetRegisteredErrorHandlers() {
        Collection<IPublicationErrorHandler> handlers = Collections.emptyList();
        when(mbassador.getRegisteredErrorHandlers()).thenReturn(handlers);

        assertThat(template.getRegisteredErrorHandlers()).isSameAs(handlers);
        verify(mbassador).getRegisteredErrorHandlers();
    }

    @Test
    @DisplayName("hasPendingMessages delegates to the bus")
    void testHasPendingMessages() {
        when(mbassador.hasPendingMessages()).thenReturn(true);

        assertThat(template.hasPendingMessages()).isTrue();
        verify(mbassador).hasPendingMessages();
    }

    @Test
    @DisplayName("getRuntime delegates to the bus")
    void testGetRuntime() {
        BusRuntime runtime = mock(BusRuntime.class);
        when(mbassador.getRuntime()).thenReturn(runtime);

        assertThat(template.getRuntime()).isSameAs(runtime);
        verify(mbassador).getRuntime();
    }

    /** Minimal concrete {@link MBassadorEvent} used as a test fixture. */
    static final class TestEvent extends MBassadorEvent {
        TestEvent(Object source) {
            super(source);
        }
    }
}
