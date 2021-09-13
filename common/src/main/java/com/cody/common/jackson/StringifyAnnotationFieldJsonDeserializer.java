package com.cody.common.jackson;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author Administrator
 * @desc StringifyAnnotationFieldJsonDeserializer
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
public class StringifyAnnotationFieldJsonDeserializer extends JsonDeserializer<Long> {

    private final String nullString = String.valueOf((Object) null);

    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (!StrUtil.isBlank(value) && !this.nullString.equals(value)) {
            if (!isNumeric(value)) {
                throw new RuntimeException(String.format("解析 \"%s\":\"%s\" 出现异常,请检查数据是否正确", p.getParsingContext().getCurrentName(), value));
            } else {
                try {
                    return Long.valueOf(value);
                } catch (NumberFormatException var5) {
                    throw new RuntimeException(String.format("解析 \"%s\":\"%s\" 出现异常,请检查数据是否正确", p.getParsingContext().getCurrentName(), value));
                }
            }
        } else {
            return null;
        }
    }

    private static boolean isNumeric(CharSequence cs) {
        if (StrUtil.isEmpty(cs)) {
            return false;
        } else {
            int sz = cs.length();
            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

}
