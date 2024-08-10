package initech.mvc.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VerifyCodeDTO {
    private Long id;
    private String VerifyEmail;
    private String VerifyCode;
}
