package initech.mvc.vo;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmailVO {
    // id
    private Long id;
    // 이메일
    private String verifyEmail;
    // 인증코드
    private String verifyCode;
    // 만료시간
    private LocalDateTime expireTime;



}
