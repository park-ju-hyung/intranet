package initech.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class FindEmailDTO {

    // 이름
    private String memberName;

    // 생년월일
    private String memberBirth;

}
