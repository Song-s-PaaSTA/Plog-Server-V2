package com.songspasssta.memberservice.domain.repository;

import com.songspasssta.memberservice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findBySocialLoginId(final String socialLoginId);
}
