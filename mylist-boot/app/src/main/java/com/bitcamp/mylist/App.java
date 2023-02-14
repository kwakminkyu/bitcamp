package com.bitcamp.mylist;

import javax.sql.DataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  public static DataSource dataSource;

  @Bean
  public DataSource createDataSource() {
    System.out.println("createDataSource() 호출됨");
    try {
      DriverManagerDataSource connectionPool = new DriverManagerDataSource();
      connectionPool.setDriverClassName("org.mariadb.jdbc.Driver");
      connectionPool.setUrl("jdbc:mariadb://localhost:3306/mylist_db");
      connectionPool.setUsername("study");
      connectionPool.setPassword("1111");

      return connectionPool;

    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  //  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext beanContainer) {
    return args -> {

      System.out.println("빈 컨테이너가 생성한 객체( 빈 컨테이너에 들어있는 객체 ):");

      String[] beanNames = beanContainer.getBeanDefinitionNames();
      for (int i = 0; i < beanNames.length; i++) {
        Object bean = beanContainer.getBean(beanNames[i]);
        System.out.printf("----> %03d: %s\n", i + 1, bean.getClass().getName());
      }

    };
  }
}
