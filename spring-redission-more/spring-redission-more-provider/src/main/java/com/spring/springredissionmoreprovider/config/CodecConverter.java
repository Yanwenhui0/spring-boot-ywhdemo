package com.spring.springredissionmoreprovider.config;

import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/11/9
 */
public class CodecConverter implements Converter<String, Codec> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecConverter.class);
    private final ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

    public CodecConverter() {
    }

    @Override
    public Codec convert(String source) {
        if (StringUtils.hasText(source)) {
            try {
                return (Codec)ClassUtils.resolveClassName(source.trim(), this.classLoader).newInstance();
            } catch (Exception var3) {
                LOGGER.error("convert to Codec failed, use patsnap JsonJacksonCodec as default.", var3);
            }
        }

        return new JsonJacksonCodec();
    }
}
