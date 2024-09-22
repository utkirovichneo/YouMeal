package uz.pdp.food_delivery_project_with_frontend_developer.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorDetailDTO{
    private String field;
    private String error;
}
