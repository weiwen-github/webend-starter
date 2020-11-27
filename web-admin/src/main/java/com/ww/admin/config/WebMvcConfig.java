package com.ww.admin.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author weiwen
 * @date 2020/11/10
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
  /**
   * 配置静态资源路径
   * @param registry
   * @return void
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
    registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    registry.addResourceHandler("/view/**").addResourceLocations("classpath:/templates/");
    registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/");

    registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    super.addResourceHandlers(registry);
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    super.configureMessageConverters(converters);
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures(
            // 是否格式化输出，默认为false
            SerializerFeature.PrettyFormat,
            // 禁止循环引用， 即出现$.data[0]
            SerializerFeature.DisableCircularReferenceDetect,
            // Map字段如果为空，输出为[]，而不是null
            SerializerFeature.WriteMapNullValue,
            // List字段如果为空，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 字符串字段如果为null，输出“”，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    );
    fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
    // 处理中文乱码
    List<MediaType> fastMediaType = new ArrayList<>();
    fastMediaType.add(MediaType.APPLICATION_JSON_UTF8);

    fastConverter.setSupportedMediaTypes(fastMediaType);
    fastConverter.setFastJsonConfig(fastJsonConfig);
    converters.add(fastConverter);
  }
}
