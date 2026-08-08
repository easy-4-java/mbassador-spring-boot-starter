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
package net.engio.mbassy.spring.boot.event;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EventObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MBassadorEvent}.
 *
 * <p>Uses a minimal concrete subclass to exercise the abstract base class
 * behaviour: constructor, timestamp, route expression, source override, field
 * getters/setters and {@code toString}.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("MBassadorEvent Tests")
class MBassadorEventTest {

    private static final String SOURCE = "origin";

    private TestEvent event;

    @BeforeEach
    void setUp() {
        event = new TestEvent(SOURCE);
    }

    @Test
    @DisplayName("Constructor stores the source and initialises a non-zero timestamp")
    void testConstructor() {
        assertThat(event.getSource()).isEqualTo(SOURCE);
        assertThat(event.getTimestamp()).isPositive();
        assertThat(event.getTimestamp()).isLessThanOrEqualTo(System.currentTimeMillis());
    }

    @Test
    @DisplayName("Event/tag/key getters and setters round-trip")
    void testEventTagKey() {
        event.setEvent("login");
        event.setTag("user");
        event.setKey("session");

        assertThat(event.getEvent()).isEqualTo("login");
        assertThat(event.getTag()).isEqualTo("user");
        assertThat(event.getKey()).isEqualTo("session");
    }

    @Test
    @DisplayName("Body getter/setter round-trip")
    void testBody() {
        event.setBody(42);
        assertThat(event.getBody()).isEqualTo(42);
    }

    @Test
    @DisplayName("getRouteExpression joins event, tag and key")
    void testRouteExpression() {
        event.setEvent("login");
        event.setTag("user");
        event.setKey("session");

        assertThat(event.getRouteExpression()).isEqualTo("/login/user/session");
    }

    @Test
    @DisplayName("setSource overrides the EventObject source")
    void testSetSource() {
        event.setSource("new-origin");
        assertThat(event.getSource()).isEqualTo("new-origin");
    }

    @Test
    @DisplayName("toString contains event, tag and key")
    void testToString() {
        event.setEvent("login");
        event.setTag("user");
        event.setKey("session");

        String text = event.toString();
        assertThat(text).contains("login").contains("user").contains("session");
    }

    @Test
    @DisplayName("Event extends EventObject")
    void testIsEventObject() {
        assertThat(event).isInstanceOf(EventObject.class);
    }

    /** Minimal concrete {@link MBassadorEvent} used as a test fixture. */
    static final class TestEvent extends MBassadorEvent {
        TestEvent(Object source) {
            super(source);
        }
    }
}
