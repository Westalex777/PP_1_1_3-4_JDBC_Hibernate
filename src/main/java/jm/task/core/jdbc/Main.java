package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("name1","lastname1", (byte) 20);
        userService.saveUser("name2","lastname2", (byte) 30);
        userService.saveUser("name3","lastname3", (byte) 40);
        userService.saveUser("name4","lastname4", (byte) 50);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
