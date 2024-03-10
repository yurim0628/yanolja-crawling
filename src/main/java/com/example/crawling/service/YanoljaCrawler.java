package com.example.crawling.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class YanoljaCrawler implements ApplicationRunner {

    private final ReservationService reservationService;
    private final MemberService memberService;

    @Override
    public void run(ApplicationArguments args) {
        reservationService.setupWebDriver();
        reservationService.handleAllRegionsAccommodations();
        reservationService.closeWebDriver();
        memberService.createAndConnectMembersToReservations();
    }
}
