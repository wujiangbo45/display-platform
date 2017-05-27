package com.mapbar.common.serialization;

public interface Serializer {

	byte[] serialize(Object obj);

	Object deserialize(byte[] bytes);

}
