package com.cody.common.aspect.annotation;

import com.cody.common.jackson.StringifyAnnotationFieldJsonDeserializer;
import com.cody.common.jackson.StringifyAnnotationFieldJsonSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 序列化注解
 *
 * @author wql
 * @date 2021/9/13
 * @lastUpdateUser wql
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@JacksonAnnotationsInside
@JsonSerialize(using = StringifyAnnotationFieldJsonSerializer.class)
@JsonDeserialize(using = StringifyAnnotationFieldJsonDeserializer.class)
public @interface Stringify {
}
