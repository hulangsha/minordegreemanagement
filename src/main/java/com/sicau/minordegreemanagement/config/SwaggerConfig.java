package com.sicau.minordegreemanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.basePackage}")
    private String basePackage;  //controller所在的包
    @Value("${swagger.title}")
    private String title;//当前文档的标题
    @Value("${swagger.description}")
    private String description;//当前文档的详细描述
    @Value("${swagger.version}")
    private String version; //当前文档的版本


    @Bean
    public Docket createRestApi(){

        // 设置请求头
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("Authorization") // 字段名
                .description("token") // 描述
                .modelRef(new ModelRef("string")) // 数据类型
                .parameterType("header") // 参数类型
                .defaultValue("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6IueOi-iAgeW4iCIsImV4cCI6MTcxMzQzOTI1OH0.y_8qFFaCqo4U_jsuyhyr-sNKIVCVqE7crl-wUZOB1Fo") // 默认值：可自己设置
                .hidden(true) // 是否隐藏
                .required(false) // 是否必须
                .build());
        
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("自定义接口") //自定义分组名称
                .apiInfo(apiInfo()) //指定构建api文档的详细信息的方法：apiInfo()
                .select()
                //指定要生成api接口的包路径，这里把controller作为包路径，生成controller中的所有接口
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(parameters);
    }
    /**
       *构建api文档的详细信息
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(title) //设置标题
                .description(description) //设置接口描述
                .version(version) //设置版本
                .build();
    }

}