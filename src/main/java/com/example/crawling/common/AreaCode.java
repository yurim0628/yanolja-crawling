package com.example.crawling.common;

import lombok.Getter;

@Getter
public enum AreaCode {

    SEOUL(7, "서울",
            SubAreaCode.GANGNAM, SubAreaCode.SEOCHO, SubAreaCode.JAMSIL, SubAreaCode.YEONGDEUNGPO, SubAreaCode.SINRIM,
            SubAreaCode.CHEONHO, SubAreaCode.HWAGOK, SubAreaCode.GURO, SubAreaCode.SINCHON, SubAreaCode.YEONSINNAE,
            SubAreaCode.JONGNO, SubAreaCode.SUNGSHIN, SubAreaCode.ITAEWON, SubAreaCode.DONGDAEMUN, SubAreaCode.HUIGI,
            SubAreaCode.JANGAN, SubAreaCode.GEONDAE, SubAreaCode.WANGSHIMRI, SubAreaCode.SUYU, SubAreaCode.SANGBONG, SubAreaCode.TAERUNG),

    GYEONGGI(8, "경기도",
            SubAreaCode.SUWON_INGI, SubAreaCode.SUWON_STATION, SubAreaCode.SUWON_CITY_HALL, SubAreaCode.ANYANG, SubAreaCode.SEONGNAM,
            SubAreaCode.YONGIN, SubAreaCode.DONGTAN, SubAreaCode.HANAM, SubAreaCode.YEOJU, SubAreaCode.ANSAN_CENTRAL_STATION,
            SubAreaCode.ANSAN_GOJAN, SubAreaCode.GUNPO, SubAreaCode.SIHEUNG, SubAreaCode.GWANGMYEONG, SubAreaCode.PYEONGTAEK,
            SubAreaCode.BUCHEON, SubAreaCode.ILSAN, SubAreaCode.PAJU, SubAreaCode.GIMPO, SubAreaCode.UIJEONGBU, SubAreaCode.GURI, SubAreaCode.NAMYANGJU_DASAN,
            SubAreaCode.NAMYANGJU_ONAM, SubAreaCode.POCHEON, SubAreaCode.YANGJU, SubAreaCode.YANGPYEONG, SubAreaCode.GAPYEONG, SubAreaCode.JEBUDO),

    INCHEON(9, "인천",
            SubAreaCode.BUPIYEONG, SubAreaCode.GUWOL, SubAreaCode.SEOGU_SEOKNAM, SubAreaCode.GYEYANG_JAKJEON, SubAreaCode.JUAN, SubAreaCode.SONGDO,
            SubAreaCode.INCHEON_AIRPORT, SubAreaCode.DONGAM, SubAreaCode.YONGHYEON, SubAreaCode.GANGHWA),

    GANGWON(11, "강원",
            SubAreaCode.CHUNCHEON, SubAreaCode.WONJU, SubAreaCode.GYEONGPODAE, SubAreaCode.GANGNEUNG_STATION, SubAreaCode.YEONGWOL,
            SubAreaCode.SOKCHO, SubAreaCode.YANGYANG, SubAreaCode.DONGHAE, SubAreaCode.PYEONGCHANG, SubAreaCode.HONGCHEON, SubAreaCode.HWACHEON),

    JEJU(6601, "제주",
            SubAreaCode.JEJU_AIRPORT_WEST, SubAreaCode.JEJU_AIRPORT_EAST, SubAreaCode.SEOGWIPO, SubAreaCode.IHOTEU, SubAreaCode.HAMDEOK, SubAreaCode.NAMWON_PYOSEON),

    DAEJEON(10, "대전",
            SubAreaCode.YUSEONG_GU, SubAreaCode.JUNG_GU, SubAreaCode.DONG_GU, SubAreaCode.SEO_GU, SubAreaCode.DAEDEOK_GU),

    CHUNGBUK(50, "충북",
            SubAreaCode.CHEONGJU_HEUNGDEOK, SubAreaCode.CHEONGJU_SANGDANG, SubAreaCode.CHUNGJU, SubAreaCode.JECHON, SubAreaCode.JINCHEON, SubAreaCode.BOEUN),

    CHUNGNAM(13, "충남/세종",
            SubAreaCode.CHEONAN_SEOBUK, SubAreaCode.CHEONAN_DONGNAM, SubAreaCode.ASAN, SubAreaCode.GONGJU, SubAreaCode.GYERYONG,
            SubAreaCode.YESAN, SubAreaCode.TAAN, SubAreaCode.SEOSAN, SubAreaCode.DANGJIN, SubAreaCode.BORYEONG, SubAreaCode.SEOCHUN),

    BUSAN(14, "부산",
            SubAreaCode.HAEUNDAE, SubAreaCode.SONGJEONG, SubAreaCode.GWANGANLI, SubAreaCode.KYUNGSEONGDAE, SubAreaCode.SEOMYUN,
            SubAreaCode.NAMPO, SubAreaCode.BUSAN_STATION, SubAreaCode.YEONSAN, SubAreaCode.DONGRAE, SubAreaCode.SASANG, SubAreaCode.DEOKCHEON, SubAreaCode.HADAN),

    ULSAN(6602, "울산",
            SubAreaCode.NAM_GU_JUNG_GU, SubAreaCode.DONG_GU_BUK_GU_ULJU),

    GYEONGNAM(6596, "경남",
            SubAreaCode.CHANGWON_SANGNAM, SubAreaCode.CHANGWON_MYUNGSEO, SubAreaCode.MASAN, SubAreaCode.JINHAE, SubAreaCode.KIMHAE,
            SubAreaCode.YANGSAN, SubAreaCode.JINJU, SubAreaCode.GEOJE, SubAreaCode.SACHUN, SubAreaCode.HADONG, SubAreaCode.GEOCHANG),

    DAEGU(6597, "대구",
            SubAreaCode.DONGSEONG_RO, SubAreaCode.DONGDAEGU_STATION, SubAreaCode.DAEGU_AIRPORT, SubAreaCode.SEODAEGU_STATION, SubAreaCode.DURYU, SubAreaCode.SEONGSEO),

    GYEONGBUK(6598, "경북",
            SubAreaCode.POHANG_NAM_GU, SubAreaCode.POHANG_BUK_GU, SubAreaCode.GYEONGJU, SubAreaCode.GUMI, SubAreaCode.GYEONGSAN,
            SubAreaCode.ANDONG, SubAreaCode.YEONGCHUN, SubAreaCode.GIMCHEON, SubAreaCode.MUNGYEONG, SubAreaCode.ULJIN, SubAreaCode.ULLEUNGDO),

    GWANGJU(100056, "광주",
            SubAreaCode.SANGMU, SubAreaCode.CHUNGJANG_RO, SubAreaCode.CHEOMDAN, SubAreaCode.GWANGJUYEODAE, SubAreaCode.GWANGJU_STATION),

    JEONNAM(100064, "전남",
            SubAreaCode.YEOSU, SubAreaCode.SUNGCHEON, SubAreaCode.GWANGYANG, SubAreaCode.MOKPO, SubAreaCode.MUAN, SubAreaCode.NAJU, SubAreaCode.DAMYANG, SubAreaCode.HAENAM),

    JEONBUK(6600, "전북",
            SubAreaCode.JEONJU_DEOKJIN, SubAreaCode.JEONJU_WANSAN, SubAreaCode.GUNSAN, SubAreaCode.IKSAN, SubAreaCode.NAMWON_IMSIL, SubAreaCode.JEONGEUP);

    private final int areaCode;
    private final String areaName;
    private final SubAreaCode[] subAreas;

    AreaCode(int areaCode, String areaName, SubAreaCode... subAreas) {
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.subAreas = subAreas;
    }
}
