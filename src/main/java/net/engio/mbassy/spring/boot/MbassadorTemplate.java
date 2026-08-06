/*
 * Copyright (c) 2018, hiwepy (https://github.com/easy-4-java).
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

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import net.engio.mbassy.bus.BusRuntime;
import net.engio.mbassy.bus.IMessagePublication;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.bus.publication.SyncAsyncPostCommand;
import net.engio.mbassy.spring.boot.event.MBassadorEvent;

public class MbassadorTemplate {
	
	@Autowired	
	private MBassador<MBassadorEvent> mbassador;
	
	public SyncAsyncPostCommand<MBassadorEvent> post(MBassadorEvent message) {
		return mbassador.post(message);
	}
	
	public IMessagePublication publish(MBassadorEvent message) {
		return mbassador.publish(message);
	}
	
	public IMessagePublication publishAsync(MBassadorEvent message) {
		return mbassador.publishAsync(message);
	}
	
	public IMessagePublication publishAsync(MBassadorEvent message, long timeout, TimeUnit unit) {
		return mbassador.publishAsync(message, timeout, unit);
	}
	
	public Collection<IPublicationErrorHandler> getRegisteredErrorHandlers() {
		return mbassador.getRegisteredErrorHandlers();
	}
	
	public boolean hasPendingMessages() {
		return mbassador.hasPendingMessages();
	}
	
	public BusRuntime getRuntime() {
		return mbassador.getRuntime();
	}

	public MBassador<MBassadorEvent> getMbassador() {
		return mbassador;
	}

	public void setMbassador(MBassador<MBassadorEvent> mbassador) {
		this.mbassador = mbassador;
	}
	
}
