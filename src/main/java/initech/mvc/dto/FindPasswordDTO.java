package initech.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class FindPasswordDTO {

    // 이름
    private String memberName;

    // 생년월일
    private String memberBirth;

    // 이메일
    private String memberEmail;

    // 인증코드
    private String verifyCode;



}
