package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = Util.getConnection()) {
            if (connection != null) {
                System.out.println("Подключение к базе установлено!");
            } else {
                System.out.println("Не удалось подключиться к базе.");
                return;
            }

            UserService userService = new UserServiceImpl();

            userService.createUsersTable();

            userService.saveUser("Name1", "LastName1", (byte) 20);
            userService.saveUser("Name2", "LastName2", (byte) 30);
            userService.saveUser("Name3", "LastName3", (byte) 25);
            userService.saveUser("Name4", "LastName4", (byte) 35);

            List<User> users = userService.getAllUsers();
            System.out.println("\nСписок всех пользователей:");
            users.forEach(System.out::println);

            userService.cleanUsersTable();
            System.out.println("\nТаблица очищена.");

            userService.dropUsersTable();
            System.out.println("Таблица удалена.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
