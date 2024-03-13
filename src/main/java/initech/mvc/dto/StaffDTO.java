package initech.mvc.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class StaffDTO{
    private Long bt_idm;
    // @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    // @Pattern(regexp = "^[a-zA-Z0-9_]{4,20}$")
    private String memberid;
    // @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "Password must be 8 to 16 characters long and include at least one letter, one number, and one special character.")
    private String member_password;
    // @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String member_name;
    // @NotBlank(message = "부서는 필수 입력 항목입니다.")
    private String member_department;
    //@NotBlank(message = "직급은 필수 입력 항목입니다.")
    private String member_position;
    // @NotBlank(message = "입사일자는 필수 입력 항목입니다.")
    // @Pattern(regexp = "^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$", message = "올바른 형식이 아닙니다.")
    private String member_EmploymentDate;
    // @NotBlank(message = "생년월일은 필수 입력 항목입니다.")
    // @Pattern(regexp = "^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$", message = "올바른 형식이 아닙니다.")
    private String member_birth;
    //@NotBlank(message = "이메일은 필수 입력 항목입니다.")
    // @Email
    private String member_email;
    // @NotBlank(message = "인증코드는 필수 입력 항목입니다.")
    private String email_verifycode;
    private boolean TermsAgreed;
}
