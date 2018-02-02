package com.example.sample;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@EnableSwagger2
@RefreshScope
@RequestMapping("/")
@EnableAutoConfiguration
public class RestService {

    private static final Logger LOG = LoggerFactory.getLogger(RestService.class);

    @Autowired
    private LogDetailService detailService;

    @RequestMapping(method = RequestMethod.GET)
    public void swaggerUi(final HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html");
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value = "Greetings", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "World is created"),
            @ApiResponse(code = 204, message = "No greetings at all",
                    responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Boolean.class)),
            @ApiResponse(code = 401, message = "World is unauthorized"),
            @ApiResponse(code = 403, message = "World is forbidden"),
            @ApiResponse(code = 404, message = "World not found")})
    public String home() {
        return "Hello world";
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Primary
    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }
}
