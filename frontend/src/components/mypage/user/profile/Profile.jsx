import React, { useState, useEffect } from "react";
import * as p from "./style";
import { MoreButton, CloseButton } from "components/mypage/user/style";
import SigninInput from "components/input/FormInputCol";
import SelectInput from "components/input/FormSelectCol";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

import eetch from "apis/eetch";

export default function Profile({ userInfo, more, getMore }) {
    const [lists, setLists] = useState({ jobList: [], hobbyList: [] });
    const [inputs, setInputs] = useState({
        email: "",
        password: ""
    });

    const handleChange = (e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value
        });
    };

    const detailText = (str) => {
        const result = [];
        for (let i = 0; i < str.length; i++) {
            result.push(<span key={i}>{str.charAt(i)}</span>);
        }

        return result;
    };

    const switchedHandler = () => {
        if (more === 4) getMore(0);
        else getMore(4);
    };

    const getJob = (code = 0) => {
        if (!code) return "-";
        if (lists.jobList.length > 0) {
            console.log(lists.jobList.length);
            return lists.jobList.find((job) => {
                return job.jobIdx === code;
            }).job;
        }

        return "리스트 없음";
    };

    const getHobby = (code) => {
        if (!code) return "-";
        if (lists.hobbyList.length > 0) {
            return lists.hobbyList.find((hobby) => {
                return hobby.hobbyIdx === code;
            }).hobby;
        }

        return "리스트 없음";
    };

    useEffect(() => {
        eetch.domain().then((res) => {
            setLists({ jobList: res.data.jobList, hobbyList: res.data.hobbyList });
        });
    }, []);

    return (
        <>
            <p.DetailTypeBox>
                <p.DetailTypeText>{detailText("이메일")}</p.DetailTypeText>
                <p.DetailTypeText>{detailText("본명")}</p.DetailTypeText>
                <br />
                <p.DetailTypeText>{detailText(`나이`)}</p.DetailTypeText>
                <p.DetailTypeText>{detailText("직업")}</p.DetailTypeText>
                <p.DetailTypeText>{detailText("취미")}</p.DetailTypeText>
            </p.DetailTypeBox>
            <p.DivideLine />
            <p.DetailContentBox>
                <p.DetailContentText>{userInfo.email}</p.DetailContentText>
                <p.DetailContentText>{userInfo.name}</p.DetailContentText>
                <br />
                <p.DetailContentText>{userInfo.age}</p.DetailContentText>
                <p.DetailContentText>{getJob(userInfo.job)}</p.DetailContentText>
                <p.DetailContentText>{getHobby(userInfo.hobby)}</p.DetailContentText>
            </p.DetailContentBox>

            <p.InputBox>
                <SigninInput
                    data={{
                        text: "이메일",
                        id: "email",
                        name: "email",
                        type: "text",
                        onChangeFunc: handleChange,
                        value: inputs.email
                    }}
                />
                <SelectInput
                    data={{
                        text: "직업",
                        list: lists
                    }}
                />
                <SelectInput
                    data={{
                        text: "취미",
                        list: lists
                    }}
                />
            </p.InputBox>

            <MoreButton onClick={() => switchedHandler()}>
                {more === 4 ? "수정 완료" : "상세 수정"}
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
            <CloseButton onClick={() => getMore(0)} toggle={more !== 4} />
        </>
    );
}
