package com.example.crawling.service;

import com.example.crawling.common.AreaCode;
import com.example.crawling.common.ReservationType;
import com.example.crawling.common.SubAreaCode;
import com.example.crawling.constants.ReservationConstants;
import com.example.crawling.dto.ReservationInfo;
import com.example.crawling.model.Reservation;
import com.example.crawling.model.YaUser;
import com.example.crawling.repository.ReservationRepository;
import com.example.crawling.util.RandomNumberUtil;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private WebDriver driver;
    private WebDriverWait wait;

    public void setupWebDriver() {
        System.setProperty("webdriver.chrome.driver", ReservationConstants.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void closeWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void handleAllRegionsAccommodations() {
        for (AreaCode areaCode : AreaCode.values()) {
            for (SubAreaCode subAreaCode : areaCode.getSubAreas()) {
                String regionAccommodationListUrl = buildRegionAccommodationListUrl(subAreaCode.getCode());
                driver.get(regionAccommodationListUrl);

                List<String> accommodationUrlList = getAccommodationUrlList();

                ReservationInfo reservationInfo = ReservationInfo.builder()
                        .areaCode(areaCode)
                        .build();

                handleAccommodationList(accommodationUrlList, reservationInfo);
            }
        }
    }

    private String buildRegionAccommodationListUrl(int subAreaCode) {
        return String.format("https://www.yanolja.com/area/a-%d", subAreaCode);
    }

    private List<String> getAccommodationUrlList() {
        List<String> accommodationUrlList = new ArrayList<>();
        List<WebElement> accommodationUrlElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("div.PlaceListItemBanner_container__ARsIm.banner-unit.PlaceListItemBanner_large__1KiY8 a")
        ));

        for (int i = 0; i < accommodationUrlElements.size(); i++) {
            WebElement accommodationUrlElement = accommodationUrlElements.get(i);
            accommodationUrlList.add(accommodationUrlElement.getAttribute("href"));
        }
        return accommodationUrlList;
    }

    @Transactional
    private void handleAccommodationList(List<String> accommodationUrlList, ReservationInfo reservationInfo) {
        for (String accommodationUrl : accommodationUrlList) {
            driver.get(accommodationUrl);

            // 숙소 이름
            WebElement accommodationNameElement = driver.findElement(By.cssSelector("div .css-1g3ik0v"));
            String accommodationName = accommodationNameElement.getText();

            // 숙소 이미지
            String accommodationImage = getAccommodationImage();

            // 숙소 주소
            WebElement accommodationAddressElement = driver.findElement(By.cssSelector("div.address span"));
            String accommodationAddress =  accommodationAddressElement.getText();

            List<WebElement> roomElementList = driver.findElements(By.cssSelector("div.css-1nnj57j"));
            for (WebElement roomElement : roomElementList) {

                // 객실 이름
                WebElement roomNameElement = roomElement.findElement(By.cssSelector("div.css-1rr4h0w"));
                String roomName = roomNameElement.getText();

                // 수용 인원
                WebElement roomCapacityElement = roomElement.findElement(By.cssSelector("div.css-18j6obq"));
                String roomCapacity = roomCapacityElement.getText().replaceAll("[^0-9]", "");

                int standardNumber = Character.getNumericValue(roomCapacity.charAt(0));
                int maximumNumber = Character.getNumericValue(roomCapacity.charAt(1));

                List<WebElement> roomDetailsElementList = roomElement.findElements(By.cssSelector(".css-1qwzivr .css-1w7jlh2"));

                WebElement roomDetailsElement = roomDetailsElementList.size() == 1 ? roomDetailsElementList.get(0) : roomDetailsElementList.get(1);

                // 숙박 유형
                WebElement accommodationTypeElement = roomDetailsElement.findElement(By.cssSelector(".css-dh6x9t div, .css-12w41re div"));
                String accommodationType = accommodationTypeElement.getText();

                // 1박 가격
                WebElement roomPriceElement = roomDetailsElement.findElement(By.cssSelector("div.price"));
                String roomPriceText = roomPriceElement.getText().contains("%") ? roomPriceElement.getText().split("%")[1] : roomPriceElement.getText();
                int roomPrice = Integer.parseInt(roomPriceText.replaceAll("[^0-9]", ""));

                reservationInfo = reservationInfo.toBuilder()
                        .accommodationName(accommodationName)
                        .accommodationImage(accommodationImage)
                        .accommodationAddress(accommodationAddress)
                        .roomName(roomName)
                        .standardNumber(standardNumber)
                        .maximumNumber(maximumNumber)
                        .roomPrice(roomPrice)
                        .build();

                int numberOfReservations = RandomNumberUtil.getRandomReservationNumber();
                List<Reservation> reservationList = new ArrayList<>();

                for (int i = 0; i < numberOfReservations; i++) {
                    if ("숙박".equals(accommodationType)) {
                        reservationInfo = handleStayAccommodation(roomDetailsElement, reservationInfo);
                    } else {
                        reservationInfo = handleDayUseAccommodation(reservationInfo);
                    }

                    Reservation reservation = reservationInfo.fromEntity(reservationInfo);
                    reservationList.add(reservation);
                }

                reservationRepository.saveAll(reservationList);
            }
        }
    }

    private String getAccommodationImage() {
        WebElement swiperElement = driver.findElement(By.cssSelector("div.swiper.swiper-virtual.swiper-initialized.swiper-horizontal.swiper-watch-progress"));
        swiperElement.click();

        // 대표 이미지 하나만 추출
        WebElement mainImageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img.css-w9k1o7")));
        String accommodationImage = mainImageElement.getAttribute("src");

        driver.navigate().back();
        return accommodationImage;
    }

    private ReservationInfo handleStayAccommodation(WebElement roomDetailsElement, ReservationInfo reservationInfo) {

        // 숙박 체크인 및 체크아웃 시간
        WebElement checkInOutSection = roomDetailsElement.findElement(By.cssSelector(".css-1kjfa47, .css-1qxtkjb"));
        List<WebElement> checkInOutElements = checkInOutSection.findElements(By.cssSelector(":nth-child(2), :nth-child(4)"));

        LocalTime checkInTime = LocalTime.parse(checkInOutElements.get(0).getText());
        LocalTime checkOutTime = LocalTime.parse(checkInOutElements.get(1).getText());

        // 숙박 체크인 및 체크아웃 날짜
        LocalDate checkInDate = LocalDate.now().plusDays(RandomNumberUtil.getRandomCheckInDateDuration());
        LocalDate checkOutDate = checkInDate.plusDays(RandomNumberUtil.getRandomStayCheckOutDateDuration());

        // 예약 날짜
        LocalDate reservationDate = checkInDate.minusDays(RandomNumberUtil.getRandomReservationDateDuration());

        // 구매 가격
        int duration = (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        int purchasePrice = reservationInfo.getRoomPrice() * duration;

        // 현재 판매 가격
        int currentSellingPrice = (int) (Math.round(purchasePrice * RandomNumberUtil.getRandomSellingPriceMultiplier()) / 100) * 100;

        return setReservationInfo(reservationInfo, checkInTime, checkOutTime, checkInDate, checkOutDate, reservationDate, purchasePrice, currentSellingPrice, ReservationType.STAY);
    }

    private ReservationInfo handleDayUseAccommodation(ReservationInfo reservationInfo) {

        // 대실 체크인 및 체크아웃 시간
        LocalTime checkInTime = LocalTime.of(ReservationConstants.DAY_USE_CHECK_IN_HOUR, ReservationConstants.DAY_USE_CHECK_IN_MINUTE).plusHours(RandomNumberUtil.getRandomDayUseCheckInTimeDuration());
        LocalTime checkOutTime = checkInTime.plusHours(ReservationConstants.DAY_USE_CHECK_OUT_TIME_DURATION);

        // 대실 체크인 및 체크아웃 날짜
        LocalDate checkInDate = LocalDate.now().plusDays(RandomNumberUtil.getRandomCheckInDateDuration());
        LocalDate checkOutDate = checkInDate;

        // 예약 날짜
        LocalDate reservationDate = checkInDate.minusDays(RandomNumberUtil.getRandomReservationDateDuration());

        // 구매 가격 및 현재 판매가
        int purchasePrice = reservationInfo.getRoomPrice();
        int currentSellingPrice = (int) (Math.round(purchasePrice * RandomNumberUtil.getRandomSellingPriceMultiplier()) / 100) * 100;

        return setReservationInfo(reservationInfo, checkInTime, checkOutTime, checkInDate, checkOutDate, reservationDate, purchasePrice, currentSellingPrice, ReservationType.DAY_USE);
    }

    private ReservationInfo setReservationInfo(ReservationInfo reservationInfo, LocalTime checkInTime, LocalTime checkOutTime, LocalDate checkInDate, LocalDate checkOutDate, LocalDate reservationDate, int purchasePrice, int currentSellingPrice, ReservationType reservationType) {
        return reservationInfo.toBuilder()
                .checkInTime(checkInTime)
                .checkOutTime(checkOutTime)
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .reservationDate(reservationDate)
                .purchasePrice(purchasePrice)
                .currentSellingPrice(currentSellingPrice)
                .reservationType(reservationType)
                .build();
    }

    public int getReservationCount() { return (int) reservationRepository.count(); }

    public void connectMembersToReservations(List<YaUser> memberList, int createMemberCount) {
        List<Reservation> reservationList = reservationRepository.findAll();
        int reservationCount = getReservationCount();

        for (int i = 0; i < reservationCount; i++) {
            YaUser yaUser = memberList.get(i % createMemberCount);
            reservationList.get(i).setYaMemberId(yaUser.getId());
        }

        reservationRepository.saveAll(reservationList);
    }
}
