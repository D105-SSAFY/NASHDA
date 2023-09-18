package com.ssafy.nashda.practice.service;

import com.ssafy.nashda.practice.entity.PronWordSet;

public interface PracticePronService {
    PronWordSet getPronWordSets(int index) throws Exception;
    PronWordSet savePronWordSet() throws Exception;
}
