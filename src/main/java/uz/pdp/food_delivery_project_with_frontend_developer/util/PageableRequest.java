package uz.pdp.food_delivery_project_with_frontend_developer.util;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageableRequest {

    private int perPage;

    private int page;
}