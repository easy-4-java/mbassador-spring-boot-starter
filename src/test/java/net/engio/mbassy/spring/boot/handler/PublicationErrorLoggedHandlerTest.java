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
package net.engio.mbassy.spring.boot.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.engio.mbassy.bus.error.PublicationError;

/**
 * Unit tests for {@link PublicationErrorLoggedHandler}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("PublicationErrorLoggedHandler Tests")
class PublicationErrorLoggedHandlerTest {

    @Test
    @DisplayName("Default constructor creates a non-null instance")
    void testDefaultConstructor() {
        PublicationErrorLoggedHandler handler = new PublicationErrorLoggedHandler();
        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("Constructor with printStackTrace=false creates a non-null instance")
    void testConstructorNoStackTrace() {
        PublicationErrorLoggedHandler handler = new PublicationErrorLoggedHandler(false);
        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("Constructor with printStackTrace=true creates a non-null instance")
    void testConstructorWithStackTrace() {
        PublicationErrorLoggedHandler handler = new PublicationErrorLoggedHandler(true);
        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("handleError logs the error without a stack trace by default")
    void testHandleErrorWithoutStackTrace() {
        PublicationErrorLoggedHandler handler = new PublicationErrorLoggedHandler();
        PublicationError error = mock(PublicationError.class);
        when(error.toString()).thenReturn("boom");
        when(error.getCause()).thenReturn(new RuntimeException("root"));

        handler.handleError(error);
    }

    @Test
    @DisplayName("handleError prints the cause stack trace when enabled and a cause is present")
    void testHandleErrorWithStackTraceAndCause() {
        PublicationErrorLoggedHandler handler = new PublicationErrorLoggedHandler(true);
        PublicationError error = mock(PublicationError.class);
        when(error.toString()).thenReturn("boom");
        when(error.getCause()).thenReturn(new IllegalStateException("root cause"));

        handler.handleError(error);
    }

    @Test
    @DisplayName("handleError with printStackTrace enabled but null cause does not fail")
    void testHandleErrorWithStackTraceAndNullCause() {
        PublicationErrorLoggedHandler handler = new PublicationErrorLoggedHandler(true);
        PublicationError error = mock(PublicationError.class);
        when(error.toString()).thenReturn("boom");
        when(error.getCause()).thenReturn(null);

        handler.handleError(error);
    }
}
