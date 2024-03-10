package com.example.crawling.dto;

import com.example.crawling.common.AreaCode;
import com.example.crawling.common.ReservationType;
import com.example.crawling.model.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInfo {
    private AreaCode areaCode;
    private String accommodationImage;
    private String accommodationName;
    private String accommodationAddress;
    private ReservationType reservationType;
    private String roomName;
    private int standardNumber;
    private int maximumNumber;
    private int roomPrice;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate reservationDate;
    private int purchasePrice;
    private int currentSellingPrice;

    public Reservation fromEntity(ReservationInfo reservationInfo) {
        return Reservation.builder()
                .areaCode(reservationInfo.areaCode)
                .accommodationImage(reservationInfo.accommodationImage)
                .accommodationName(reservationInfo.accommodationName)
                .accommodationAddress(reservationInfo.accommodationAddress)
                .reservationType(reservationType)
                .roomName(reservationInfo.roomName)
                .standardNumber(reservationInfo.standardNumber)
                .maximumNumber(reservationInfo.maximumNumber)
                .checkInTime(reservationInfo.checkInTime)
                .checkOutTime(reservationInfo.checkOutTime)
                .checkInDate(reservationInfo.checkInDate)
                .checkOutDate(reservationInfo.checkOutDate)
                .reservationDate(reservationInfo.reservationDate)
                .purchasePrice(reservationInfo.purchasePrice)
                .currentSellingPrice(reservationInfo.currentSellingPrice)
                .build();
    }
}
