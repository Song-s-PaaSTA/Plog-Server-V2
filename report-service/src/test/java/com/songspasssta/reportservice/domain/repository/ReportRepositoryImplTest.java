package com.songspasssta.reportservice.domain.repository;

import com.songspasssta.reportservice.domain.Bookmark;
import com.songspasssta.reportservice.domain.Report;
import com.songspasssta.reportservice.domain.type.RegionType;
import com.songspasssta.reportservice.domain.type.ReportType;
import com.songspasssta.reportservice.dto.response.ReportList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.songspasssta.reportservice.domain.Bookmark.createBookmark;
import static com.songspasssta.reportservice.domain.Report.createReport;
import static com.songspasssta.reportservice.domain.type.RegionType.GYEONGGI;
import static com.songspasssta.reportservice.domain.type.RegionType.SEOUL;
import static com.songspasssta.reportservice.domain.type.ReportType.IN_PROGRESS;
import static com.songspasssta.reportservice.domain.type.ReportType.NOT_STARTED;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReportRepositoryImplTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private ReportRepository reportRepository;

    @BeforeEach
    public void setUp() {
        Long memberId = 1L;

        // 서울과 경기도의 신고글 생성
        for (int i = 0; i < 5; i++) {
            Report report = createReport(
                    memberId,
                    "http://example.com/report/" + i,
                    "신고글 설명 " + i,
                    i % 2 == 0 ? ReportType.IN_PROGRESS : NOT_STARTED,
                    "서울시 어딘가 " + i,
                    i % 2 == 0 ? RegionType.SEOUL : RegionType.GYEONGGI
            );
            em.persist(report);

            Bookmark bookmark = createBookmark(memberId, report, i % 2 == 0);
            em.persist(bookmark);
        }

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("신고글 필터 조회")
    public void findReportsWithFilter() throws Exception {
        //given
        Long memberId = 1L;
        PageRequest pageRequest = PageRequest.of(0, 3);
        List<RegionType> regionTypes = List.of(
                SEOUL,
                GYEONGGI
        );
        List<ReportType> reportTypes = List.of(
                IN_PROGRESS,
                NOT_STARTED
        );

        //when
        Page<ReportList> reports = reportRepository.findReportsWithFilter(
                memberId,
                regionTypes,
                reportTypes,
                "popularity",
                pageRequest
        );

        //then
        assertThat(reports.getContent()).hasSize(3);

        ReportList firstReport = reports.getContent().get(0);
        ReportList secondReport = reports.getContent().get(1);
        ReportList thirdReport = reports.getContent().get(2);

        assertThat(firstReport.getBookmarkCount()).isGreaterThanOrEqualTo(secondReport.getBookmarkCount());
        assertThat(secondReport.getBookmarkCount()).isGreaterThanOrEqualTo(thirdReport.getBookmarkCount());
    }

}