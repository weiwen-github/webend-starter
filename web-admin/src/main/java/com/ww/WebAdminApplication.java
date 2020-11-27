package com.ww;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author ww
 * @date 2020/11/10
 */
@SpringBootApplication(scanBasePackages = {"com.ww"})
@MapperScan("com.ww.**.dao")
@EnableSwagger2
@EnableScheduling
public class WebAdminApplication {
  public static void main(String[] args) {
    // 数据库密码解密的密钥
    System.setProperty("jasypt.encryptor.password", "AFCWEIWEN");
    SpringApplication.run(WebAdminApplication.class, args);
  }
}
