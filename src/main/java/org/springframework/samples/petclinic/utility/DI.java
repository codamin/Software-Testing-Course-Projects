package org.springframework.samples.petclinic.utility;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class DI extends SimpleDI {
	private HashMap<Class<?>, Object> map;
	public DI() {
		map = new HashMap<Class<?>, Object>();
	}

	@Override
	public void provideByInstance(Class<?> typeClass, Object instanceOfType) {
		map.put(typeClass, instanceOfType);
	}

	@Override
	public void provideByAConstructorFunction(Class<?> typeClass, Callable<Object> providerFunction) {
		map.put(typeClass, providerFunction);
	}

	@Override
	public Object getInstanceOf(Class<?> requiredType) throws Exception {
		if(map.containsKey(requiredType)) {
			return map.get(requiredType);
		}
		return null;
	}
}
