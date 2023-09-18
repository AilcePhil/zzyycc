package com.zzyycc.common.swagger.config;



import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

import static org.assertj.core.util.Sets.newHashSet;

/**
 * @author zhuyuechao
 * @version 1.0.0
 * @className SwaggerConfig
 * @createTime 2022/2/23 15:36
 * @description
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    private boolean enable;

    /**
     *
     * 访问路径
     * 美化  http://localhost:8080/doc.html
     * 原始
     *
     */


    /**
     * 默认的排除路径，排除Spring Boot默认的错误处理路径和端点
     */
    private static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList(
            "/error",
            "/actuator/**",
            "/actuator",
            "/actuator/health",
            "/actuator/health/**"
    );

    /**
     * 创建API
     * http:IP:端口号/swagger-ui/index.html 原生地址
     * http:IP:端口号/doc.html bootStrap-UI地址
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).pathMapping("/")
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                /*.enable(enable)*/
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                .paths(PathSelectors.regex("/.*/error").negate())
                .paths((s) -> {
                    for (String path : DEFAULT_EXCLUDE_PATH) {
                        if (StringUtils.startsWith(s, path)) {
                            return false;
                        }
                    }
                    return true;
                })
                // 扫描所有有注解的api，用这种方式更灵活
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                //.apis(RequestHandlerSelectors.basePackage("com.acerola.paymentdemo.controller"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.regex("(?!/ApiError.*).*"))
                //.paths(PathSelectors.any())
                .build()
                // 支持的通讯协议集合
                .protocols(newHashSet("https", "http"))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());

    }

    /**
     * 支持的通讯协议集合
     *
     * @param type1
     * @param type2
     * @return
     */
    private Set<String> newHashSet(String type1, String type2) {
        Set<String> set = new HashSet<>();
        set.add(type1);
        set.add(type2);
        return set;
    }

    /**
     * 认证的安全上下文
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add((SecurityScheme) new ApiKey("token", "token", "header"));
        return securitySchemes;
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any()).build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }


    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("zzyycc")
                // 描述
                .description("zzyycc")
                // 作者信息
                .contact(new Contact("zyc", null, null))
                // 版本
                .version("版本号:1.0.0")
                //协议
                //.license("The Apache License")
                //协议url
                //.licenseUrl("http://www.baidu.com")
                .build();
    }








    /*@Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier,
                                                                         ServletEndpointsSupplier servletEndpointsSupplier,
                                                                         ControllerEndpointsSupplier controllerEndpointsSupplier,
                                                                         EndpointMediaTypes endpointMediaTypes,
                                                                         CorsEndpointProperties corsProperties,
                                                                         WebEndpointProperties webEndpointProperties,
                                                                         Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(
                endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(),
                new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }


    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() &&
                (org.springframework.util.StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }*/


}
