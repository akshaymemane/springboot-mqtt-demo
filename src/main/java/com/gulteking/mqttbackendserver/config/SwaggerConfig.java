package com.gulteking.mqttbackendserver.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@EnableJpaAuditing
public class SwaggerConfig {

    @Configuration
    @EnableSwagger2
    public class SpringFoxConfig {
        @Bean
        public Docket apiDocket() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build();
        }
    }

    private Predicate<String> postPaths() {
        return or(regex("/*.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("All JSG APIs")
                .description("REST APIs reference for developers")
                //.termsOfServiceUrl("http://test.com")
                .contact("akshay.memane.official@gmail.com").license("JSG")
                .licenseUrl("http://licence-url.com").version("1.0").build();
    }

}