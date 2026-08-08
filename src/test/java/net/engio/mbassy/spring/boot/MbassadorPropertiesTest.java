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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link MbassadorProperties}.
 *
 * <p>Verifies the configuration prefix, the default values and every
 * getter/setter.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("MbassadorProperties Tests")
class MbassadorPropertiesTest {

    private MbassadorProperties properties;

    @BeforeEach
    void setUp() {
        properties = new MbassadorProperties();
    }

    @Test
    @DisplayName("Configuration prefix is 'spring.mbassador'")
    void testPrefix() {
        assertThat(MbassadorProperties.PREFIX).isEqualTo("spring.mbassador");
    }

    @Test
    @DisplayName("Default enabled is false")
    void testDefaultEnabled() {
        assertThat(properties.isEnabled()).isFalse();
    }

    @Test
    @DisplayName("Setter for enabled updates the value")
    void testSetEnabled() {
        properties.setEnabled(true);
        assertThat(properties.isEnabled()).isTrue();
    }

    @Test
    @DisplayName("Default ringBuffer is false")
    void testDefaultRingBuffer() {
        assertThat(properties.isRingBuffer()).isFalse();
    }

    @Test
    @DisplayName("Setter for ringBuffer updates the value")
    void testSetRingBuffer() {
        properties.setRingBuffer(true);
        assertThat(properties.isRingBuffer()).isTrue();
    }

    @Test
    @DisplayName("Default ringBufferSize is 1024")
    void testDefaultRingBufferSize() {
        assertThat(properties.getRingBufferSize()).isEqualTo(1024);
    }

    @Test
    @DisplayName("Setter for ringBufferSize updates the value")
    void testSetRingBufferSize() {
        properties.setRingBufferSize(2048);
        assertThat(properties.getRingBufferSize()).isEqualTo(2048);
    }

    @Test
    @DisplayName("Default ringThreadNumbers is 4")
    void testDefaultRingThreadNumbers() {
        assertThat(properties.getRingThreadNumbers()).isEqualTo(4);
    }

    @Test
    @DisplayName("Setter for ringThreadNumbers updates the value")
    void testSetRingThreadNumbers() {
        properties.setRingThreadNumbers(8);
        assertThat(properties.getRingThreadNumbers()).isEqualTo(8);
    }

    @Test
    @DisplayName("Default multiProducer is false")
    void testDefaultMultiProducer() {
        assertThat(properties.isMultiProducer()).isFalse();
    }

    @Test
    @DisplayName("Setter for multiProducer updates the value")
    void testSetMultiProducer() {
        properties.setMultiProducer(true);
        assertThat(properties.isMultiProducer()).isTrue();
    }
}
