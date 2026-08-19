package net.engio.mbassy.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.spring.boot.event.MBassadorEvent;
import net.engio.mbassy.spring.boot.handler.PublicationErrorLoggedHandler;
import net.engio.mbassy.spring.boot.hooks.MBassadorShutdownHook;

@Configuration
@ConditionalOnClass({ MBassador.class })
@ConditionalOnProperty(prefix = MbassadorProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ MbassadorProperties.class })
/**
 * <p>Auto-configuration for Mbassador integration.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MbassadorAutoConfiguration implements ApplicationContextAware {

	private static final Logger LOG = LoggerFactory.getLogger(MbassadorAutoConfiguration.class);
	private ApplicationContext applicationContext;
	/**
	 * <p>Error handler.</p>
	 * @return the i publication error handler
	 */
	
	@Bean
	@ConditionalOnMissingBean
	public IPublicationErrorHandler errorHandler() {
		return new PublicationErrorLoggedHandler();
	}
	
	/**
	 */
	@Bean
	@ConditionalOnClass({ MBassador.class })
	@ConditionalOnProperty(prefix = MbassadorProperties.PREFIX, value = "enabled", havingValue = "true")
	public MBassador<MBassadorEvent> mbassador(MbassadorProperties properties,
			@Autowired(required = false) IPublicationErrorHandler errorHandler) {

		MBassador<MBassadorEvent> mbassador = new MBassador<MBassadorEvent>(errorHandler);
		if (errorHandler == null) {
			mbassador = new MBassador<MBassadorEvent>();
		} else {
			mbassador = new MBassador<MBassadorEvent>(errorHandler);
		}

		//mbassador.subscribe(listener);
		
		/**
		 * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
		 * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
		 */
		Runtime.getRuntime().addShutdownHook(new MBassadorShutdownHook(mbassador));

		return mbassador;

	}
	/**
	 * <p>Disruptor template.</p>
	 * @return the mbassador template
	 */
	
	@Bean
	public MbassadorTemplate disruptorTemplate() {
		return new MbassadorTemplate();
	}
	/** Sets the application context. */
 
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	/** Gets the application context. */

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
