/**
 * 유효 AccesToken 획득 ( user )
 * > accessToken
 * */
export const refresh = async (user) => {
    try {
        const response = await fetch(`${process.env.REACT_APP_API_URL}/user/refresh`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.refreshToken}`
            },
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 로그인 ( String email | String password )
 * > { accessToken | refreshToken | progress }
 */
export const signin = async ({ email, password }) => {
    try {
        const response = await fetch(`${process.env.REACT_APP_API_URL}/user/signin`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password })
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 회원가입시 이메일 인증 번호 전송 ( String email )
 */
export const sendCode = async (email) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/user/sendcode`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email })
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 회원가입 ( String email | String password | String name | String nickname
 * ? Int age ? String job ? String hobby )
 * */
export const signup = async ({ email, password, name, nickname, age = null, job = null, hobby = null }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/user/signup`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                email,
                password,
                name,
                nickname,
                age,
                job,
                hobby
            }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 로그아웃 ( String email | user )
 * */
export const signout = async ({ email, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/user/signout`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 회원탈퇴 ( String email | String password | user )
 * */
// export const signOut = async ({ email, password, user }) => {
//     try {
//         const response = fetch(`${process.env.REACT_APP_API_URL}/user/signout`, {
//             method: "PUT",
//             headers: {
//                 "Content-Type": "application/json",
//                 Authorization: `Bearer ${user.accessToken}`
//             },
//             body: JSON.stringify({ email, password }),
//             credentials: "include"
//         });

//         const result = await response.json();

//         return result;
//     } catch (error) {
//         console.log(error);
//     }
// };

/**
 * 비밀번호 변경 ( String email | String password | String newpassword | user)
 * */
export const updatePw = async ({ email, password, newpassword, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/user/updatepw`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, password, newpassword }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 비밀번호 찾기 ( String email | String password | String code)
 * */
export const resetPw = async ({ email, password, code }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/user/resetpw`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password, code })
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 프로필 상세 정보 변경 ( String email | String password | String name | String nickname | user
 * ? Int age ? String job ? String hobby)
 * */
export const updateProfile = async ({ email, password, name, nickname, age = null, job = null, hobby = null, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/user/updateprofile`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({
                email,
                password,
                name,
                nickname,
                age,
                job,
                hobby
            }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 회원정보 조회 ( String nickname | user )
 * > { nickname | name | email | age | job | hobby | progress | start_week | now_week }
 * */
export const mypage = async (user) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/user/mypage`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 이메일 중복 체크 ( String email )
 * */
export const checkEmail = async (email) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/user/checkemail`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email })
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 코드 일치 체크 ( String email | String code )
 * */
export const checkCode = async ({ email, code }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/user/checkcode`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, code })
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 닉네임 중복 체크 ( String nickname )
 * */
export const checkNickname = async (nickname) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/user/checknickname/${nickname}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

/**
 * 데이터 삭제 ( String nickname | refreshToken )
 * */
export const reset = async ({ nickname, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/user/reset/${nickname}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};
