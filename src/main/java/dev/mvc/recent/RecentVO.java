package dev.mvc.recent;

import java.time.LocalDateTime;
import lombok.Getter;

public class RecentVO {
    private Long id;               // 고유 ID
    private Long contentId;        // 제품 ID (contents 테이블의 외래키)
    private LocalDateTime viewedAt; // 최근 본 시간

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

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }
}