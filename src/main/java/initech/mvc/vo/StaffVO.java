package initech.mvc.vo;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StaffVO {
    private Long bt_idm;
    // @NotBlank(message = "아이디는 필수 항목입니다.")
    // @Size(min=4, max=20, message = "아이디는 최소 4~20자리여야 합니다.")
    // @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "특수문자는 _만 가능합니다.")
    private String memberid;
    // @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    // @Size(min=8, max=16, message = "비밀번호는 최소 8~16자리여야 합니다.")
    // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호는 영문 대소문자, 숫자, 특수문자를 모두 포함해야 합니다.")
    private String member_password;
    // @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String member_name;
    // @NotBlank(message = "부서는 필수 입력 항목입니다.")
    private String member_department;
    // @NotBlank(message = "직급은 필수 입력 항목입니다.")
    private String member_position;
    // @NotBlank(message = "입사일자는 필수 입력 항목입니다.")
    // @Pattern(regexp = "^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$", message = "올바른 형식이 아닙니다.")
    private String member_EmploymentDate;
    // @NotBlank(message = "생년월일은 필수 입력 항목입니다.")
    // @Pattern(regexp = "^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$", message = "올바른 형식이 아닙니다.")
    private String member_birth;
    // @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    // @Email
    private String member_email;
    // @NotBlank(message = "인증코드는 필수 입력 항목입니다.")
    private String email_verifycode;
    private boolean TermsAgreed;
}
