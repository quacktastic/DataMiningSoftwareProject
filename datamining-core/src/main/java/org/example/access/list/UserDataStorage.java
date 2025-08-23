package org.example.access.list;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class helps with storing, adding, removing, and checking the user data
 */
public class UserDataStorage {

    // Storing the data with mapping
    private static Map<String, String> userDataMap = new HashMap<>();

    // Removing user from the dataset
    public static void removeUser(String id) {
        userDataMap.remove(id);
    }

    public static void findUserID(String id) {
        if (userDataMap.containsKey(id)) {
            System.out.println(id);
        } else {
            System.out.println("id not found.");
        }
    }

    public static void findUserPassword(String password) {
        if (userDataMap.containsValue(password)) {
            System.out.println(password);
        } else {
            System.out.println("password not found");
        }
    }

    public static boolean existsById(String id) {
        return userDataMap.containsKey(id);
    }

    public static String getPasswordById(String id) {
        return userDataMap.get(id);
    }

    public static void saveUserToCSV(String id, String password, String filepath) {

        try (FileWriter writer = new FileWriter(filepath, true)) {
            writer.append(id).append(",").append(password).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean updatePasswordInMap(String id, String newPassword) {

        if (!userDataMap.containsKey(id)) {
            return false;
        }
        userDataMap.put(id, newPassword);
        return true;
    }

    public static void clearAllForTest() {
        userDataMap.clear();
    }



}
