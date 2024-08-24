package initech.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class RegiPasswordDTO {
    // id
    private Long regId;

    // 비밀번호
    private String memberPassword;

    // 비밀번호 재확인
    private String confirmPassword;
}
