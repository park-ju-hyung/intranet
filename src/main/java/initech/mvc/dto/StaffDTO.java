package initech.mvc.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class StaffDTO{
    // id
    @Column(name = "reg_id")
    private Long regId;

    // 데이터 순번
    @Column(name = "order_number")
    private int orderNumber;

    // 아이디
    @NotBlank(message = "아이디는 필수 항목입니다.")
    @Size(min=4, max=20, message = "아이디는 최소 4~20자리여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "특수문자는 _만 가능합니다.")
    @Column(name = "member_id")
    private String memberId;

    // 비밀번호
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min=8, max=16, message = "비밀번호는 최소 8~16자리여야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호는 영문 대소문자, 숫자, 특수문자를 모두 포함해야 합니다.")
    @Column(name = "member_password")
    private String memberPassword;

    // 비밀번호 재확인
    @NotBlank(message = "비밀번호 확인은 필수 입력 항목입니다.")
    private String confirmPassword;

    // 이름
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    @Column(name = "member_name")
    private String memberName;

    // 부서
    @NotBlank(message = "부서는 필수 입력 항목입니다.")
    @Column(name = "member_department")
    private String memberDepartment;

    // 직급
    @NotBlank(message = "직급은 필수 입력 항목입니다.")
    @Column(name = "member_position")
    private String memberPosition;

    // 입사일자
    @NotBlank(message = "입사일자는 필수 입력 항목입니다.")
    @Pattern(regexp = "^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$", message = "올바른 형식이 아닙니다.")
    @Column(name="member_employmentdate")
    private String memberEmploymentDate;

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


    // 신청일자 (게시물 신청일자)
    @CreationTimestamp // 엔티티를 저장될때 현재 일시를 자동으로 설정
    @Column(name = "application_date")
    private LocalDateTime applicationDate;

    // 업데이트 일자
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 게시물 검색조건 시작날짜
    @Column(name = "search_startdate")
    private LocalDateTime searchStartDate;

    // 게시물 검색조건 끝날짜
    @Column(name = "search_endate")
    private LocalDateTime searchEndDate;

    // 근무 상태
    @Column(name="employment_status")
    private String employmentStatus = "재직";

    // 권한승인여부
    @Column(name="member_permission")
    private String memberPermission = "신청";

    // 사유
    private String memberReason;
}
