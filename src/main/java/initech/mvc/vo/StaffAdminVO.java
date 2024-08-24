package initech.mvc.vo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StaffAdminVO {
    // id
    private Long regId;

    // 데이터 순번
    private int orderNumber;

    // 아이디
    private String memberId;

    // 비밀번호
    private String memberPassword;

    // 비밀번호 재확인
    private String confirmPassword;

    // 이름
    private String memberName;

    // 부서
    private String memberDepartment;

    // 직급
    private String memberPosition;

    // 입사일자
    private String memberEmploymentDate;

    // 생년월일
    private String memberBirth;

    // 이메일
    private String memberEmail;

    // 인증코드
    private String verifyCode;


    // 신청일자 (게시물 신청일자)
    private LocalDateTime applicationDate;

    // 업데이트 일자
    private LocalDateTime updatedAt;

    // 게시물 검색조건 시작날짜
    private LocalDateTime searchStartDate;

    // 게시물 검색조건 끝날짜
    private LocalDateTime searchEndDate;

    // 근무 상태
    private String employmentStatus = "재직";

    // 권한승인여부
    private String memberPermission = "신청";

    // 사유
    private String memberReason;
}
