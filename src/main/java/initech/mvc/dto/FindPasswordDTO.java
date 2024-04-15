package initech.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class FindPasswordDTO {

    // 이름
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    @Column(name = "member_name")
    private String memberName;

    // 생년월일
    @NotBlank(message = "생년월일은 필수 입력 항목입니다.")
    @Pattern(regexp = "^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$", message = "올바른 형식이 아닙니다.")
    @Column(name = "member_birth")
    private String memberBirth;

    // 이메일
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email
    @Column(name = "member_email")
    private String memberEmail;

    // 인증코드
    @NotBlank(message = "인증코드는 필수 입력 항목입니다.")
    @Column(name = "verify_code")
    private String verifyCode;



}
