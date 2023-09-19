package com.ssafy.nashda.practice.service;

public interface SequenceGeneratorService {

    public long generateSequence(String seqName) throws Exception;

    public long getSequenceNum(String seqName);
}
