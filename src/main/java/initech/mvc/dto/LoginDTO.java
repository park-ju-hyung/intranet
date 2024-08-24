package initech.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class LoginDTO {

    // 비밀번호
    private String memberPassword;

    // 이메일
    private String memberEmail;

}
