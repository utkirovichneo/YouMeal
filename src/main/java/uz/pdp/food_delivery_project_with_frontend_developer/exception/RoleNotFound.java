package uz.pdp.food_delivery_project_with_frontend_developer.exception;

import java.text.MessageFormat;

public class RoleNotFound extends RuntimeException {

    public RoleNotFound(String message, Object ... args) {
        super(MessageFormat.format(message, args));
    }
}