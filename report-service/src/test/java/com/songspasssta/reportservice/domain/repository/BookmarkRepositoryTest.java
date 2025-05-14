package com.songspasssta.reportservice.domain.repository;

import com.songspasssta.reportservice.adapter.out.persistence.BookmarkRepository;
import com.songspasssta.reportservice.domain.Bookmark;
import com.songspasssta.reportservice.domain.Report;
import com.songspasssta.reportservice.dto.response.BookmarkReports;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static com.songspasssta.reportservice.domain.Bookmark.createBookmark;
import static com.songspasssta.reportservice.domain.Report.createReport;
import static com.songspasssta.reportservice.domain.type.ReportType.*;
import static com.songspasssta.reportservice.domain.type.RegionType.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookmarkRepositoryTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @BeforeEach
    public void setUp() {
        Long memberId = 1L;

        for (int i = 0; i < 50000; i++) {
            Report report = createReport(
                    memberId,
                    "http://example.com/" + i,
                    "쓰레기 설명 " + i,
                    IN_PROGRESS,
                    "서울시 어딘가 " + i,
                    SEOUL
            );
            em.persist(report);

            boolean isBookmarked = i % 2 == 0;
            Bookmark bookmark = createBookmark(memberId, report, isBookmarked);
            em.persist(bookmark);
        }

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("특정 사용자가 북마크한 신고물 리스트 조회 테스트")
    public void findBookmarks() throws Exception {
        //given
        Long memberId = 1L;

        //when
        long start2 = System.currentTimeMillis();

        List<BookmarkReports> results = bookmarkRepository.findAllByMemberIdAndBookmarked(memberId);

        long end2 = System.currentTimeMillis();
        System.out.println("Query 실행 시간1: " + (end2 - start2) + "ms");

        // then
        assertThat(results).hasSize(25000);

//        assertThat(results)
//                .extracting(BookmarkReports::getReportImgUrl)
//                .containsExactlyInAnyOrder("http://example1.com", "http://example2.com");
//
//        assertThat(results)
//                .extracting(BookmarkReports::getReportType)
//                .containsExactlyInAnyOrder(IN_PROGRESS.name(), NOT_STARTED.name());
    }
}