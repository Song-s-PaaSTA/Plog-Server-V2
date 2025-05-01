package com.songspasssta.memberservice.dto.response;

import com.songspasssta.memberservice.domain.Member;
import com.songspasssta.memberservice.domain.type.SocialLoginType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponse {

    private final String nickname;
    private final String email;
    private final String socialLoginId;
    private final SocialLoginType socialLoginType;
    private final String profileImageUrl;
    private final String accessToken;
    private final String refreshToken;
    private final Boolean isNewMember;

    public static LoginResponse of(
            final Member member,
            final String accessToken,
            final String refreshToken,
            final Boolean isNewMember
    ) {
        return new LoginResponse(
                member.getNickname(),
                member.getEmail(),
                member.getSocialLoginId(),
                member.getSocialLoginType(),
                member.getProfileImageUrl(),
                accessToken,
                refreshToken,
                isNewMember
        );
    }
}
