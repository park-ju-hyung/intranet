package initech.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class FindEmailDTO {

    // 이름
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    @Column(name = "member_name")
    private String memberName;

    // 생년월일
    @NotBlank(message = "생년월일은 필수 입력 항목입니다.")
    @Pattern(regexp = "^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$", message = "올바른 형식이 아닙니다.")
    @Column(name = "member_birth")
    private String memberBirth;

}
