package com.ww.admin.config;

import com.ww.framework.shiro.fiter.OnlineSessionFilter;
import com.ww.framework.shiro.fiter.SyncOnlineSessionFilter;
import com.ww.framework.shiro.reaml.UserRealm;
import com.ww.framework.shiro.session.OnlineSessionDAO;
import com.ww.framework.shiro.session.OnlineSessionFactory;
import com.ww.framework.shiro.session.OnlineWebSessionManager;
import com.ww.framework.shiro.session.SpringSessionValidationScheduler;
import com.ww.system.utils.SpringContextUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro框架配置类
 *
 * @author weiwen
 * @date 2020/11/10
 */
@Configuration
public class ShiroConfig {

  /** 自定义Realm */
  @Bean
  public UserRealm userRealm() {
    UserRealm customRealm = new UserRealm();
    return customRealm;
  }

  /** 自定义sessionDAO会话 */
  @Bean
  public OnlineSessionDAO sessionDAO() {
    OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
    return sessionDAO;
  }

  /** 自定义sessionFactory会话 */
  @Bean
  public OnlineSessionFactory sessionFactory() {
    OnlineSessionFactory sessionFactory = new OnlineSessionFactory();
    return sessionFactory;
  }

  /** 会话管理器 */
  @Bean
  public OnlineWebSessionManager sessionManager() {
    OnlineWebSessionManager sessionManager = new OnlineWebSessionManager();
    // 删除过期的session
    sessionManager.setDeleteInvalidSessions(true);
    // 设置全局session超时时间
    sessionManager.setGlobalSessionTimeout(3600000);
    // 去掉 JSESSIONID
    sessionManager.setSessionIdUrlRewritingEnabled(false);
    // 定义要使用的无效的Session定时调度器
    sessionManager.setSessionValidationScheduler(
        SpringContextUtils.getBean(SpringSessionValidationScheduler.class));
    // 是否定时检查session
    sessionManager.setSessionValidationSchedulerEnabled(true);
    // 自定义SessionDao
    sessionManager.setSessionDAO(sessionDAO());
    // 自定义sessionFactory
    sessionManager.setSessionFactory(sessionFactory());
    return sessionManager;
  }

  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  /** 设置自定义的单Realm应用 */
  @Bean
  public DefaultWebSecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(userRealm());
    securityManager.setSessionManager(sessionManager());
    return securityManager;
  }

  /**
   * Shiro主过滤器本身功能十分强大,其强大之处就在于它支持任何基于URL路径表达式的、自定义的过滤器的执行
   * Web应用中,Shiro可控制的Web请求必须经过Shiro主过滤器的拦截,Shiro对基于Spring的Web应用提供了完美的支持
   */
  @Bean
  public ShiroFilterFactoryBean shiroFilter() {
    // Shiro的核心安全接口,这个属性是必须的
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    shiroFilter.setSecurityManager(securityManager());
    // 要求登录时的链接(可根据项目的URL进行替换),非必须的属性,默认会自动寻找Web工程根目录下的"/index.html"页面
    shiroFilter.setLoginUrl("/api/v1/sysAuth/noAuth");
    // 登录成功后要跳转的连接
    shiroFilter.setSuccessUrl("/");
    shiroFilter.setUnauthorizedUrl("/api/v1/sysAuth/noAuth");
    Map<String, String> map = new LinkedHashMap<>();
    // anon 放行，不需要验证
    map.put("/api/v1/sysAuth/noAuth", "anon");
    // 登录接口
    map.put("/api/v1/user/login", "anon");

    // swagger2免拦截
    map.put("/swagger-ui.html", "anon");
    map.put("/doc.html", "anon");
    map.put("/webjars/**", "anon");
    map.put("/swagger-resources/**", "anon");
    map.put("/v2/**", "anon");

    Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
    filters.put("onlineSession", onlineSessionFilter());
    filters.put("syncOnlineSession", syncOnlineSessionFilter());
    shiroFilter.setFilters(filters);

    // 所有请求需要认证
    map.put("/**", "user,onlineSession,syncOnlineSession");
    shiroFilter.setFilterChainDefinitionMap(map);
    return shiroFilter;
  }

  /** 自定义在线用户处理过滤器 */
  @Bean
  public OnlineSessionFilter onlineSessionFilter() {
    OnlineSessionFilter onlineSessionFilter = new OnlineSessionFilter();
    onlineSessionFilter.setLoginUrl("/api/v1/sys/loginAdapter");
    return onlineSessionFilter;
  }

  /** 自定义在线用户同步过滤器 */
  @Bean
  public SyncOnlineSessionFilter syncOnlineSessionFilter() {
    SyncOnlineSessionFilter syncOnlineSessionFilter = new SyncOnlineSessionFilter();
    return syncOnlineSessionFilter;
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      @Qualifier("securityManager") SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
        new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }
}
