package com.songspasssta.reportservice.domain;

import com.songspasssta.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE bookmark SET status = 'DELETED' where id = ?")
@SQLRestriction("status = 'ACTIVE'")
@Table(name = "bookmark", indexes = {
        @Index(name = "idx_bookmark_reportId", columnList = "report_id")
})
public class Bookmark extends BaseEntity {

    public static final String STATUS_DELETED = "DELETED";
    public static final String STATUS_ACTIVE = "ACTIVE";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Boolean bookmarked;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    public static Bookmark createBookmark(Long memberId, Report report, Boolean bookmarked) {
        Bookmark bookmark = new Bookmark();
        bookmark.memberId = memberId;
        bookmark.bookmarked = bookmarked;
        bookmark.addReport(report);
        return bookmark;
    }

    private void addReport(Report report) {
        this.report = report;
        report.getBookmarks().add(this);
    }

    public void toggleBookmarkStatus(Boolean bookmarked) {
        this.bookmarked = bookmarked;
    }
}
