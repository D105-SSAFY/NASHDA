package com.ssafy.nashda.statistic.dto.response;

import lombok.*;

public interface PhonemeInterface {
    Integer getIncorrect();
    String getLetter();
    Integer getTotal();
    Integer getType();
}
