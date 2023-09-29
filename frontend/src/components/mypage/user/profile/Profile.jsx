import React, { useState, useEffect } from "react";
import * as p from "./style";
import { MoreButton, CloseButton } from "components/mypage/user/style";
import SigninInput from "components/input/FormInputCol";
import SelectInput from "components/input/FormSelectCol";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

import eetch from "apis/eetch";

export default function Profile({ userInfo, setUserInfo, more, setMore }) {
    const [lists, setLists] = useState({});
    const [inputs, setInputs] = useState([0, 0, 0]);

    const handleChange = (e) => {
        inputs[0] = e.target.value;
        setInputs(inputs);
    };

    const handleSubmit = () => {
        if (more === 4) {
            setMore(0);

            userInfo.age = inputs[0];
            userInfo.job = inputs[1];
            userInfo.hobby = inputs[2];

            eetch.updateProfile(userInfo).then((res) => {
                setUserInfo(res.data);
            });
        } else setMore(4);
    };

    const detailText = (str) => {
        const result = [];
        for (let i = 0; i < str.length; i++) {
            result.push(<span key={i}>{str.charAt(i)}</span>);
        }

        return result;
    };

    const getJob = (code) => {
        if (!code) return "-";
        if (Object.keys(lists).length > 0) {
            return lists.jobList.find((job) => {
                return job.jobIdx === code;
            }).job;
        }

        return "리스트 없음";
    };

    const getHobby = (code) => {
        if (!code) return "-";
        if (Object.keys(lists).length > 0) {
            return lists.hobbyList.find((hobby) => {
                return hobby.hobbyIdx === code;
            }).hobby;
        }

        return "리스트 없음";
    };

    const setJobHobby = (target, idx) => {
        if (target === "직업") inputs[1] = idx;
        else inputs[2] = idx;

        setInputs(inputs);
    };

    useEffect(() => {
        eetch.domain().then((res) => {
            setLists({ jobList: res.data.jobList, hobbyList: res.data.hobbyList });
        });
    }, []);

    useEffect(() => {
        setInputs([userInfo.age, userInfo.job, userInfo.hobby]);
    }, [userInfo]);

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
                        text: "나이",
                        id: "age",
                        name: "age",
                        type: "text",
                        onChangeFunc: handleChange,
                        value: inputs[0]
                    }}
                />
                <SelectInput
                    data={{
                        target: "직업",
                        list: lists.jobList,
                        callback: setJobHobby
                    }}
                />
                <SelectInput
                    data={{
                        target: "취미",
                        list: lists.hobbyList,
                        callback: setJobHobby
                    }}
                />
            </p.InputBox>

            <MoreButton onClick={() => handleSubmit()}>
                {more === 4 ? "수정 완료" : "상세 수정"}
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
            <CloseButton onClick={() => setMore(0)} toggle={more !== 4} />
        </>
    );
}
