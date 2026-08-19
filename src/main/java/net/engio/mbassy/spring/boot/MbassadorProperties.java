package net.engio.mbassy.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(MbassadorProperties.PREFIX)
/**
 * <p>Configuration properties for Mbassador.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MbassadorProperties {

	public static final String PREFIX = "spring.mbassador";

	/** Enable Disruptor. */
	private boolean enabled = false;
	/** 是否自动创建RingBuffer对象 */
	private boolean ringBuffer = false;
	/** RingBuffer缓冲区大小, 默认 1024 */
	private int ringBufferSize = 1024;
	/** 消息消费线程池大小, 默认 4 */
	private int ringThreadNumbers = 4;
	/** 是否对生产者，如果是则通过 RingBuffer.createMultiProducer创建一个多生产者的RingBuffer，否则通过RingBuffer.createSingleProducer创建一个单生产者的RingBuffer */
	private boolean multiProducer = false;
	/**
	 * <p>Is enabled.</p>
	 * @return the boolean
	 */

	public boolean isEnabled() {
		return enabled;
	}
	/** Sets the enabled. */

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * <p>Is ring buffer.</p>
	 * @return the boolean
	 */
	
	public boolean isRingBuffer() {
		return ringBuffer;
	}
	/** Sets the ring buffer. */

	public void setRingBuffer(boolean ringBuffer) {
		this.ringBuffer = ringBuffer;
	}
	/**
	 * <p>Is multi producer.</p>
	 * @return the boolean
	 */

	public boolean isMultiProducer() {
		return multiProducer;
	}
	/** Sets the multi producer. */

	public void setMultiProducer(boolean multiProducer) {
		this.multiProducer = multiProducer;
	}
	/** Gets the ring buffer size. */

	public int getRingBufferSize() {
		return ringBufferSize;
	}
	/** Sets the ring buffer size. */

	public void setRingBufferSize(int ringBufferSize) {
		this.ringBufferSize = ringBufferSize;
	}
	/** Gets the ring thread numbers. */

	public int getRingThreadNumbers() {
		return ringThreadNumbers;
	}
	/** Sets the ring thread numbers. */

	public void setRingThreadNumbers(int ringThreadNumbers) {
		this.ringThreadNumbers = ringThreadNumbers;
	}

}