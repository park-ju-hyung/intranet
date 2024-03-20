package initech.mvc.vo;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StaffVO {
    private Long id;

    private int orderNumber;
    @NotBlank(message = "아이디는 필수 항목입니다.")
    @Size(min=4, max=20, message = "아이디는 최소 4~20자리여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "특수문자는 _만 가능합니다.")
    private String memberid;
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min=8, max=16, message = "비밀번호는 최소 8~16자리여야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호는 영문 대소문자, 숫자, 특수문자를 모두 포함해야 합니다.")
    private String member_password;
    @NotBlank(message = "비밀번호 확인은 필수 입력 항목입니다.")
    private String confirmPassword;
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String memberName;
    @NotBlank(message = "부서는 필수 입력 항목입니다.")
    private String memberDepartment;
    @NotBlank(message = "직급은 필수 입력 항목입니다.")
    private String memberPosition;
    @NotBlank(message = "입사일자는 필수 입력 항목입니다.")
    @Pattern(regexp = "^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$", message = "올바른 형식이 아닙니다.")
    private String member_EmploymentDate;
    @Column(name = "member_birth")
    @NotBlank(message = "생년월일은 필수 입력 항목입니다.")
    @Pattern(regexp = "^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$", message = "올바른 형식이 아닙니다.")
    private String memberBirth;
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email
    private String memberEmail;
    @NotBlank(message = "인증코드는 필수 입력 항목입니다.")
    private String email_verifycode;
    private boolean TermsAgreed;

    // 신청일자 (게시물 신청일자)
    @CreationTimestamp // 엔티티를 저장될때 현재 일시를 자동으로 설정
    private LocalDateTime applicationDate;
    // 업데이트 일자
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // 게시물 검색조건 시작날짜
    private LocalDateTime searchStartDate;

    // 게시물 검색조건 끝날짜
    private LocalDateTime searchEndDate;


    // 상태
    private String employmentStatus = "재직";
}
