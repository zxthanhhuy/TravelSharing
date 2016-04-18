package vn.edu.iuh.fit.travelsharing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vn.edu.iuh.fit.travelsharing.helper.MyDialect;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {
  final static Logger logger = LoggerFactory.getLogger(App.class);

  public static void main( String[] args ) {
	  SpringApplication.run(App.class, args);
  }

  //THYMELEAF Utility Object 
  @Bean 
  public MyDialect myDialect() {
	  return  new  MyDialect();
  }
}
