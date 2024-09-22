package uz.pdp.food_delivery_project_with_frontend_developer.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageableResponse<T> {
    private Long total;
    private Integer current;
    private Integer totalPages;
    private Integer perPages;
    private List<T> data;
}

