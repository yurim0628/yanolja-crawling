package com.example.crawling.service;

import com.example.crawling.constants.ReservationConstants;
import com.example.crawling.model.YaUser;
import com.example.crawling.repository.MemberRepository;
import com.example.crawling.util.RandomNumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ReservationService reservationService;

    @Transactional
    public void createAndConnectMembersToReservations() {
        int reservationCount = reservationService.getReservationCount();

        int createMemberCount = (int) Math.ceil(reservationCount / ReservationConstants.MEMBER_COUNT_DIVISOR);
        createAndSaveRandomMember(createMemberCount);

        List<YaUser> memberList = memberRepository.findAll();
        reservationService.connectMembersToReservations(memberList, createMemberCount);
    }

    private void createAndSaveRandomMember(int createMemberCount) {
        for (int i = 0; i < createMemberCount; i++) {
            String name = "yanolja" + String.format("%02d", i + 1);
            String email = name + "@email.com";
            String password = name;
            String phoneNumber = "010" + RandomNumberUtil.generateRandomDigits();

            YaUser member = YaUser.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .build();
            memberRepository.save(member);
        }
    }
}
