package com.ssafy.nashda.common.sequence.service;

public interface SequenceGeneratorService {

    public long generateSequence(String seqName) throws Exception;

    public long getSequenceNum(String seqName);
}
