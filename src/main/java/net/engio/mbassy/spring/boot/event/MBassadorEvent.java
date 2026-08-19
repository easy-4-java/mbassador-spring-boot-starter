package net.engio.mbassy.spring.boot.event;

import java.util.EventObject;

/**
 * 事件(Event) 就是通过 Disruptor 进行交换的数据类型。
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public abstract class MBassadorEvent extends EventObject {

	/** System time when the event happened */
	private final long timestamp;
	/** Event Name */
	private String event;
	/** Event Tag */
	private String tag;
	/** Event Keys */
	private String key;
	/** Event body */
	private Object body;
	
	/**
	 * Create a new ConsumeEvent.
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public MBassadorEvent(Object source) {
		super(source);
		this.timestamp = System.currentTimeMillis();
	}

	/**
	 * Return the system time in milliseconds when the event happened.
	 */
	public final long getTimestamp() {
		return this.timestamp;
	}
	/** Gets the route expression. */
	
	public String getRouteExpression() {
		return new StringBuilder("/").append(getEvent()).append("/").append(getTag()).append("/")
				.append(getKey()).toString();
		
	}
	/** Sets the source. */
	
	public void setSource(Object source){
		this.source = source;
	}
	/** Gets the event. */

	public String getEvent() {
		return event;
	}
	/** Sets the event. */

	public void setEvent(String event) {
		this.event = event;
	}
	/** Gets the tag. */

	public String getTag() {
		return tag;
	}
	/** Sets the tag. */

	public void setTag(String tag) {
		this.tag = tag;
	}
	/** Gets the key. */

	public String getKey() {
		return key;
	}
	/** Sets the key. */

	public void setKey(String key) {
		this.key = key;
	}
	/** Gets the body. */

	public Object getBody() {
		return body;
	}
	/** Sets the body. */

	public void setBody(Object body) {
		this.body = body;
	}
	/**
	 * <p>To string.</p>
	 * @return the string
	 */
	
	@Override
	public String toString() {
		return new StringBuilder("DisruptorEvent [event :").append(getEvent()).append(",tag :").append(getTag()).append(", key :")
				.append(getKey()).append("]").toString();
	}
	
}
