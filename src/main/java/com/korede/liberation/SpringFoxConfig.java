package com.korede.liberation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
//@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("**/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("**/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
//
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
//        registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
//        registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
//        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.korede.liberation"))
                .build()
                .pathMapping("/")
                .apiInfo(apiDetails());
    }




    private ApiInfo apiDetails(){
        return new ApiInfo(
                "KOREDE LIBERATION NOTES API",
                "Set of Apis for KOREDE LIBERATION NOTES",
                "1.0",
                "For KOREDE LIBERATION NOTES only",
                new Contact("KOREDE LIBERATION NOTES","KOREDE LIBERATION NOTES","KOREDE LIBERATION NOTES"),
                "KOREDE LIBERATION NOTES License",
                "KOREDE LIBERATION NOTES",
                Collections.emptyList()
        );
        //link address http://localhost:8080/v2/api-docs
        //final link address http://localhost:8080/swagger-ui.html#/
        //http://localhost:8080/api/swagger-ui.html
    }
}
