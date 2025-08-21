package org.example.access.validate;

import org.example.access.list.UserDataStorage;

public class ID_Validator implements Validator<String>{


    @Override
    public boolean isValid(String id) {
        return ID_Is_Integer(id) && validLength(id)
                && !id.isEmpty();
    }

    private boolean ID_Is_Integer(String id) {
       return getIDLength(id) == countIntegerDigits(id);
    }



    private int countIntegerDigits(String id) {

        if (id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }

        int count = 0;
        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            if (!Character.isDigit(c)) {
               continue;
            }
            count++;
        }

        return count;
    }

    private int getIDLength(String id) {
        return id.length();
    }

    private boolean validLength(String id) {
        int length = getIDLength(id);
        return length >= 5 && length <= 10;
    }

    private boolean isUniqueID(String id) {
        // Placeholder for uniqueness check logic
        // This could involve checking against a database or a list of existing IDs
        return !UserDataStorage.existsById(id);
    }

}
