package initech.mvc.vo;

import lombok.*;

import javax.persistence.Column;
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
    @Column(name = "id_v")
    private Long id;
    // 이메일
    @Column(name = "verify_email")
    private String verifyEmail;
    // 인증코드
    @Column(name = "verify_code")
    private String verifyCode;
    // 만료시간
    @Column(name = "expire_time")
    private LocalDateTime expireTime;



}
