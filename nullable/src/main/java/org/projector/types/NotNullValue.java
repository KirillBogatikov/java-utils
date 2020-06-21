package org.projector.types;

import static org.projector.utils.Nullable.checkNotNull;
import static org.projector.utils.Nullable.ifNullOrNot;

public class NotNullValue<T> {
	private String name;
	private T item;
	
	public NotNullValue(String name, T item) {
		this.name = ifNullOrNot(name, () -> { return "Value"; }, (n) -> n);
		checkNotNull(item, this.name, null);
		this.item = item;
	}
	
	public NotNullValue(T item) {
		this(null, item);
	}
	
	public boolean hasValue() {
		return item != null;
	}
	
	public T get() {
		if (!hasValue()) {
			throw new NullPointerException(String.format("%s is null", name));
		}
		return item;
	}
	
	public T update(T item) {
		if (!hasValue()) {
			throw new NullPointerException(String.format("%s is null", name));
		}
		T last = this.item;
		this.item = item;
		return last;
	}
	
	public void set(T item) {
		if (!hasValue()) {
			throw new NullPointerException(String.format("%s is null", name));
		}
		this.item = item;
	}
}
