package dev.mvc.menu;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MenuVO {
    /** 메뉴 번호 */
    private Integer menuno = 0;

    /** 메뉴 이름 */
    @NotEmpty(message = "메뉴 이름은 필수 항목입니다.")
    @Size(min = 1, max = 100, message = "메뉴 이름은 최소 1자에서 최대 100자까지 가능합니다.")
    private String name;

    /** 메뉴 설명 */
    @NotEmpty(message = "메뉴 설명은 필수 항목입니다.")
    @Size(min = 2, max = 500, message = "메뉴 설명은 최소 2자에서 최대 500자까지 가능합니다.")
    private String explanation;

    /** 가격 */
    @NotNull(message = "가격은 필수 입력 항목입니다.")
    @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
    @Max(value = 1000000, message = "가격은 1,000,000원 이하이어야 합니다.")
    private Integer price;
    
    /** 업로드된 파일 객체 */
    private MultipartFile photoMF;

    /** 업로드된 파일 이름 */
    private String photo;

    /** 식당 번호 */
    @NotNull(message = "식당 번호는 필수 입력 항목입니다.")
    private Integer storeno;
    
    /** 추천 수 */
    private Integer recom = 0;
}
