package uz.pdp.food_delivery_project_with_frontend_developer.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageableRequest {
    @Schema(defaultValue = "10")
    private int perPage;

    @Schema(defaultValue = "0")
    private int page;
}