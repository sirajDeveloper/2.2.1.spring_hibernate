package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      /*User user = userService.getUserByCar(19, "MERCEDES-BENZ E63 AMG");
      System.out.println(user);*/

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car bimmer = new Car("BMW M5");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car lupatiy = new Car("Mercedes-Benz E63 AMG");
      user2.setCar(lupatiy);
      user1.setCar(bimmer);
      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar().getName());
         System.out.println();
      }

      context.close();
   }
}
