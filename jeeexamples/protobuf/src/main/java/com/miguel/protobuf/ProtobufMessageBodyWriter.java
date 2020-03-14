package com.miguel.protobuf;

import com.google.protobuf.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces("application/x-protobuf")
@Consumes("application/x-protobuf")
public class ProtobufMessageBodyWriter
    implements MessageBodyWriter<Message>, MessageBodyReader<Message> {

    private final Map<Class<Message>, Method> cache = new ConcurrentHashMap<>();

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Message.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(Message message, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        message.writeTo(entityStream);
    }

    @Override
    public boolean isReadable(Class<?> type, java.lang.reflect.Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Message.class.isAssignableFrom(type);
    }

    @Override
    public Message readFrom(Class<Message> type, java.lang.reflect.Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        final Method newBuilder = cache.computeIfAbsent(type, this::newBuilder);
        final Message.Builder builder = builder(newBuilder, genericType);
        return builder.mergeFrom(entityStream).build();
    }

    private Method newBuilder(final Class<Message> msg) {
        try {
            return msg.getMethod("newBuilder");
        } catch (Exception e) {
            return null;
        }
    }

    private Message.Builder builder(Method newBuilder, Type genericType) {
        try {
            return  (Message.Builder) newBuilder.invoke(genericType);
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
    }
}
