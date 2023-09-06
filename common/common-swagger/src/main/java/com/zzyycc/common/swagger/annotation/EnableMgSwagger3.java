package com.zzyycc.common.swagger.annotation;

import com.zzyycc.common.swagger.config.SwaggerConfig;
import org.springframework.context.annotation.Import;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.lang.annotation.*;

/**
 * @author zhuyuechao
 * @version 1.0.0
 * @className EnableMgSwagger2
 * @createTime 2022/2/23 9:27
 * @description 开启mgl swagger
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableOpenApi
@Import({ SwaggerConfig.class})
public @interface EnableMgSwagger3 {
}
