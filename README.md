# Bring Framework Playground

## Getting Started

Demo features:
 - List injection
 - PostConstruct
 - SingletonBean and PrototypeBean
 - Properties injection via Value
 - ScheduledTask
 - Configuration (Bean), Service annotation usage
 - Qualifier
 - Embedded Server Tomcat 
 - Dispatcher Servlet
 - REST API
 - Static Content Serving
 - Exception handler
 - Actuator
 - Banner

Please take into account that this is not a full supported features it is just for demo set. 
If you need more please take to look into example of repo [bring](https://github.com/BlyznytsiaOrg/bring).

## Usage

To run the `bring Playground` application:

```bash
   git clone https://github.com/YevgenDemoTestOrganization/bring-playground.git
```  

and run the 

```
com.levik.bringplayground.BringPlaygroundApplication
```  

## Let's go feature by feature

- List Injection Overview

```java
import io.github.blyznytsiaorg.bring.core.annotation.Autowired;
import io.github.blyznytsiaorg.bring.core.annotation.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Barista {
    private final List<Drink> drinks;
    
    public Barista(List<Drink> drinks) {
        this.drinks = drinks;
    }

```

List injection, a feature in Bring, involves the injection of a collection of objects into a class or component. 
In our project, the Barista class is a prime example that demonstrates this functionality.
Go to class Barista. Within this example, the Barista class receives a list of Drink instances via its constructor. 
These instances might be defined elsewhere in the project, and Bring automatically handles their injection into the Barista class upon initialization.

See the result of the logs:

```
[INFO ] 23-12-01 14:43:39.838 [main] c.l.b.BringPlaygroundApplication - bring - Barista is Barista is preparing a drink: Brewing a strong espresso!, Making a delicious latte!
```

- PostConstruct

```java

    @PostConstruct
    public void onInit() {
        log.info("PostContract demo!!!");
    }
```

- SingletonBean and PrototypeBean

```java

import io.github.blyznytsiaorg.bring.core.annotation.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Component
public class SingletonBean {

    @Getter
    private final PrototypeBean prototypeBean;

}

import io.github.blyznytsiaorg.bring.core.annotation.Scope;
import io.github.blyznytsiaorg.bring.core.annotation.Service;
import io.github.blyznytsiaorg.bring.core.domain.BeanScope;
import io.github.blyznytsiaorg.bring.core.domain.ProxyMode;

@Service
@Scope(name = BeanScope.PROTOTYPE, proxyMode = ProxyMode.ON)
public class PrototypeBean {
}


  private final SingletonBean singletonBean;
  @ScheduledTask(value = "myTask", initialDelay = 1, period = 20, timeUnit = TimeUnit.SECONDS)
  public void scheduledMethod1() {
    log.info(Thread.currentThread().getName() + " scheduledMethod1 " + LocalDateTime.now());

    log.info("singletonBean -> " + singletonBean + " PrototypeBean ->" + singletonBean.getPrototypeBean());
  }
}

```

and from logs we could see that Prototype Bean has different identity

```
[INFO ] 23-12-01 20:41:11.969 [pool-1-thread-1] c.l.b.feature.di.MyScheduledTasks - bring - pool-1-thread-1 scheduledMethod1 2023-12-01T20:41:11.969047
[INFO ] 23-12-01 20:41:11.969 [pool-1-thread-1] c.l.b.feature.di.MyScheduledTasks - bring - singletonBean -> com.levik.bringplayground.feature.di.SingletonBean@365695eb PrototypeBean ->com.levik.bringplayground.feature.di.PrototypeBean@1dcd751f
[INFO ] 23-12-01 20:41:31.974 [pool-1-thread-1] c.l.b.feature.di.MyScheduledTasks - bring - pool-1-thread-1 scheduledMethod1 2023-12-01T20:41:31.974337
[INFO ] 23-12-01 20:41:31.975 [pool-1-thread-1] c.l.b.feature.di.MyScheduledTasks - bring - singletonBean -> com.levik.bringplayground.feature.di.SingletonBean@365695eb PrototypeBean ->com.levik.bringplayground.feature.di.PrototypeBean@740d3425
```

- Properties injection via Value

We have default values that come to start the tomcat for instance server.port etc. If you want you could override them and use your port of use default.

But else you could create you class to consumer you properties for instance ShortenProperties it has serverUrl

```java
import io.github.blyznytsiaorg.bring.core.annotation.Component;
import io.github.blyznytsiaorg.bring.core.annotation.Value;
import lombok.Data;

@Data
@Component
public class ShortenProperties {

    @Value("shortenServerUrl")
    private String serverUrl;
}
```  

application.properties

```
shortenServerUrl = http://localhost:9000/short/
```

- ScheduledTask


This code defines a class MyScheduledTasks that contains a method scheduledMethod1(), annotated as a scheduled task. 
This method is set to run periodically with a specific initial delay and time period. 
When executed, it logs information using the logger just for demo purpose.
```java
import io.github.blyznytsiaorg.bring.core.annotation.Component;
import io.github.blyznytsiaorg.bring.core.annotation.ScheduledTask;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyScheduledTasks {
    @ScheduledTask(value = "myTask", initialDelay = 1, period = 20, timeUnit = TimeUnit.SECONDS)
    public void scheduledMethod1() {
        log.info(Thread.currentThread().getName() + " scheduledMethod1 " + LocalDateTime.now());
    }
}
```

log result:

```
[INFO ] 23-12-01 15:10:21.241 [pool-1-thread-1] c.l.b.feature.di.MyScheduledTasks - bring - pool-1-thread-1 scheduledMethod1 2023-12-01T15:10:21.241206
[INFO ] 23-12-01 15:10:41.242 [pool-1-thread-1] c.l.b.feature.di.MyScheduledTasks - bring - pool-1-thread-1 scheduledMethod1 2023-12-01T15:10:41.242068
```

- Configuration (Bean), Service annotation usage

Within our project, we have the flexibility to configure classes using annotations such as @Component or @Service,
enabling them to be recognized as Bring-managed components automatically. Additionally, we can create a separate configuration class,
annotate it with @Configuration, and employ the @Bean annotation on its methods.

```java
import io.github.blyznytsiaorg.bring.core.annotation.Service;


@Service
public class Md5HashGenerator implements HashGenerator {
    ...
}
```


```java
import io.github.blyznytsiaorg.bring.core.annotation.Bean;
import io.github.blyznytsiaorg.bring.core.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public HashGenerator incrementHashGenerator() {
        return new IncrementHashGenerator();
    }
}
```

- Qualifier

We need two classes for hash generation: one for testing and another for production. To ensure functionality, we require a Qualifier or a name that can be resolved by its name.

```java
    private final HashGenerator hashGenerator;

    public ShortenService(ShortenProperties shortenProperties,
                          @Qualifier("md5HashGenerator") HashGenerator hashGenerator) {
        ...
        }

```

- Embedded Server Tomcat, Dispatcher Servlet, Rest API, Web annotations like PostMapping, ResponseEntity, RequestBody etc.


To simplify and expedite our web development process, it would be beneficial to incorporate an embedded Tomcat that encompasses all the essential functionalities for developing and resetting APIs seamlessly.

```java
public class ShortenController implements BringServlet {

    private static final String LOCATION = "location";
    private final ShortenService shortenService;

    @SneakyThrows
    @PostMapping(path = "/api/shorten")
    public ResponseEntity<UserResponse> createShortUrl(@RequestBody UserRequest userRequest) {
        String shortUrl = shortenService.createShortUrl(userRequest.getOriginalUrl());
        return new ResponseEntity<>(new UserResponse(shortUrl), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping(path = "/short/{hash}")
    public ResponseEntity<Void> getLongUrl(@PathVariable String hash) {
        String longUrl = shortenService.resolveHash(hash);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(LOCATION, longUrl);

        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }
}

```

- Static Content Serving

Typically, we require serving static content such as HTML, CSS, and JS files on a server. 
With Bring, you can automate this process using out of the box, allowing for seamless hosting and browsing of these files

![Bring DI diagram](https://github.com/YevgenDemoTestOrganization/bring/assets/73576438/289a774e-dce4-4057-8506-ecaefb2a2ec3)

UI

<img width="1434" alt="image" src="https://github.com/YevgenDemoTestOrganization/bring/assets/73576438/4f8e9696-9b63-4ff6-a890-0bfa5edad3d6">

- Exception handler
  ![Bring DI diagram](https://github.com/YevgenDemoTestOrganization/bring/assets/73576438/f18327da-bfb3-4e73-b93e-3208229462e8)

  - Custom exception
```java
import io.github.blyznytsiaorg.bring.web.servlet.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HashNotFoundException extends RuntimeException {

    public HashNotFoundException(String message) {
        super(message);
    }
}
```


- Actuator

We offer support for health checks, info retrieval, environment details, and logging functionalities. 
Our standout feature allows changing log levels in real-time without the need for system restarts, 
facilitated through a dedicated API.

```java
    /**
     * Endpoint for dynamically changing the log level of specific loggers based on package name.
     *
     * @param packageName The name of the logger's package to change the level.
     * @param newLevel    The new log level for the specified logger's package.
     */
    @PostMapping(path = "/loggers")
    public void logger(@RequestParam String packageName, @RequestParam String newLevel) {
        LogLevelChangerUtils.changeLogLevel(packageName, newLevel);
    }
```

- The last one Banner


  if you want you could use the default one add your own or disable it. 
  Let's try with new one:
 - add banner.txt to resources
 - add VM options add bring.main.banner.file

<img width="1272" alt="image" src="https://github.com/YevgenDemoTestOrganization/bring/assets/73576438/87af8f33-00d5-43a8-9872-2de678598b77">

logs from application:

<img width="1688" alt="image" src="https://github.com/YevgenDemoTestOrganization/bring/assets/73576438/2c231fa9-ed34-4aca-8d1f-98c54d94431b">


Furthermore, Bring accommodates a plethora of additional features.:
- [Core](https://github.com/YevgenDemoTestOrganization/bring/blob/main/features/Core.md)
- [Web](https://github.com/YevgenDemoTestOrganization/bring/blob/main/features/Web.md)


