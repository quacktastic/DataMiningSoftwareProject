package org.example.access.validate;

public interface Validator<T> {

    boolean isValid(T input);
}
