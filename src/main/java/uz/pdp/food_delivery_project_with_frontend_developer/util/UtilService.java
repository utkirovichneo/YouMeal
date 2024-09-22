package uz.pdp.food_delivery_project_with_frontend_developer.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class UtilService {

    public static <T> Page<T> toPageable(List<T> list, PageableRequest pageable) {
        return new PageImpl<T>(list, PageRequest.of(pageable.getPage(), pageable.getPerPage()), list.size());
    }
}