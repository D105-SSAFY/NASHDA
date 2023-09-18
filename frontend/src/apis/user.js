// `/user/login`로 `email, password`를 request body로 포함하는 POST 요청 fetch 사용
export const refresh = () => {
    return fetch(`${process.env.API_URL}/user/refresh`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        credentials: "include",
    }).then((res) => res.json());
};

export const login = ({ email, password }) => {
    return fetch(`${process.env.API_URL}/user/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
    }).then((res) => res.json());
};

export const sendcode = ({ email }) => {
    return fetch(`${process.env.API_URL}/user/sendcode`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ email }),
    }).then((res) => res.json());
};

export const signup = ({ email, password, name, nickname, age = null, job = null, hobby = null }) => {
    return fetch(`${process.env.API_URL}/user/signup`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            email,
            password,
            name,
            nickname,
            age,
            job,
            hobby,
        }),
    }).then((res) => res.json());
};

export const logout = ({ email }) => {
    return fetch(`${process.env.API_URL}/user/logout`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email }),
    }).then((res) => res.json());
};

export const signout = ({ email, password }) => {
    return fetch(`${process.env.API_URL}/user/signout`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
    }).then((res) => res.json());
};

export const updatepw = ({ email, password, newpassword }) => {
    return fetch(`${process.env.API_URL}/user/updatepw`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password, newpassword }),
    }).then((res) => res.json());
};

export const resetpw = ({ email, password, code }) => {
    return fetch(`${process.env.API_URL}/user/resetpw`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password, code }),
    }).then((res) => res.json());
};

export const updateprofile = ({ email, password, name, nickname, age = null, job = null, hobby = null }) => {
    return fetch(`${process.env.API_URL}/user/updateprofile`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            email,
            password,
            name,
            nickname,
            age,
            job,
            hobby,
        }),
    }).then((res) => res.json());
};
