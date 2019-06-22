package restTemplatedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import restTemplatedemo.model.Coffee;

import java.math.BigDecimal;
import java.net.URI;

/**
 * use RestTemplate to access web resource
 */
@SpringBootApplication
@Slf4j
public class SpringRestTemplateDemoApplication implements ApplicationRunner {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {

        SpringApplication.run(SpringRestTemplateDemoApplication.class, args);

        new SpringApplicationBuilder()
                .sources(SpringRestTemplateDemoApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)//it means it will not start tomcat
                .run(args);
    }

    //create a restTemplate instance
    @Bean//must add it, or restTemplate can't be autowired
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        //anothor way
        //return new RestTemplate();
        return builder.build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //-1 get uri using UriComponentBuilder by passing uri string
        URI uri = UriComponentsBuilder
                //UnknownHostException if your localhost encounters typo
                .fromUriString("http://localhost:8080/coffee/{id}")
                .build(1);


        //-2 get the response entity obj by restTemplate.getForEntity
        ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);

        //-3 get response status and headers
        log.info("response status:{}, response headers:{}", c.getStatusCode(), c.getHeaders());

        //-4 get the entity obj
        log.info("Coffee:{}", c.getBody());

        String coffeeUri = "http://localhost:8080/coffee/";

        //-5 prepare the request obj
        Coffee request = Coffee.builder()
                .name("Americano")
                .price(new BigDecimal(25.00))
                .build();

        //-6 send request
        Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
        log.info("new Coffee:{}", response);

        String s = restTemplate.getForObject(coffeeUri, String.class);
        log.info("String :{}", s);



    }
}
