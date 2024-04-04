package initech.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class LoginDTO {

    // 비밀번호
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min=8, max=16, message = "비밀번호는 최소 8~16자리여야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호는 영문 대소문자, 숫자, 특수문자를 모두 포함해야 합니다.")
    @Column(name = "member_password")
    private String memberPassword;

    // 이메일
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @Column(name = "member_email")
    private String memberEmail;

}
