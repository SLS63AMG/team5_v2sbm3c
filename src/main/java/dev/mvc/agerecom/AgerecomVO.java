package dev.mvc.agerecom;
import lombok.Getter;

public class AgerecomVO {
  private Long id;               // 고유 ID
  private Long contentId;        // 제품 ID (contents 테이블의 외래키)
  private String ageGroup;       // 연령대 (예: 10대, 20대 등)

  // Getters and Setters
  public Long getId() {
      return id;
  }

  public void setId(Long id) {
      this.id = id;
  }

  public Long getContentId() {
      return contentId;
  }

  public void setContentId(Long contentId) {
      this.contentId = contentId;
  }

  public String getAgeGroup() {
      return ageGroup;
  }

  public void setAgeGroup(String ageGroup) {
      this.ageGroup = ageGroup;
  }
}
