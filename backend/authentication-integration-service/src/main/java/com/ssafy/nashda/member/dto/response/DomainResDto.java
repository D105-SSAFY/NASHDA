package com.ssafy.nashda.member.dto.response;

import com.ssafy.nashda.member.entity.Hobby;
import com.ssafy.nashda.member.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DomainResDto {
    private List<Job> jobList;
    private List<Hobby> hobbyList;
}
