package uz.pdp.food_delivery_project_with_frontend_developer.dto.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class ErrorResDTO {
    private String errorMessage;
    private String errorPath;
    private Integer errorCode;
    private LocalDateTime errorTime;

    public ErrorResDTO(Integer errorCode, String errorPath, String errorMessage) {
        this.errorCode = errorCode;
        this.errorPath = errorPath;
        this.errorMessage = errorMessage;
        this.errorTime = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Tashkent")));
    }
}
