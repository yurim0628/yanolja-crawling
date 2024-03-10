package com.example.crawling.model;

import com.example.crawling.common.AreaCode;
import com.example.crawling.common.ReservationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
public class Reservation {
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long yaMemberId;

    @Enumerated(EnumType.STRING)
    private AreaCode areaCode;
    private String accommodationImage;
    private String accommodationName;
    private String accommodationAddress;
    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;
    private String roomName;
    private int standardNumber;
    private int maximumNumber;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate reservationDate;
    private int purchasePrice;
    private int currentSellingPrice;

    @Builder
    public Reservation(
            AreaCode areaCode, String accommodationImage, String accommodationName, String accommodationAddress,
            ReservationType reservationType, String roomName, int standardNumber, int maximumNumber, LocalTime checkInTime, LocalTime checkOutTime,
            LocalDate checkInDate, LocalDate checkOutDate, LocalDate reservationDate, int purchasePrice, int currentSellingPrice
    ) {
        this.areaCode = areaCode;
        this.accommodationImage = accommodationImage;
        this.accommodationName = accommodationName;
        this.accommodationAddress = accommodationAddress;
        this.reservationType = reservationType;
        this.roomName = roomName;
        this.standardNumber = standardNumber;
        this.maximumNumber = maximumNumber;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationDate = reservationDate;
        this.purchasePrice = purchasePrice;
        this.currentSellingPrice = currentSellingPrice;
    }

    public void setYaMemberId(Long yaMemberId) {
        this.yaMemberId = yaMemberId;
    }
}
