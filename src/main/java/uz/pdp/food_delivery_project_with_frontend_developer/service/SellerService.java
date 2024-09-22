package uz.pdp.food_delivery_project_with_frontend_developer.service;

import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerRequestDTO;

public interface SellerService {

    SellerDTO register(SellerRequestDTO dto);

}