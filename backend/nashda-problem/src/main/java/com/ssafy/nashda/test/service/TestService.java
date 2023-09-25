package com.ssafy.nashda.test.service;


import com.ssafy.nashda.game.entity.ImgWordSet;

import java.util.List;

public interface TestService {

        List<ImgWordSet> getSpeed1Sets() throws Exception;
        List<Integer> generateRandomNum();
}
