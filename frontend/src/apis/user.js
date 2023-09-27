import eetch from "apis/eetch";

export const valid = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/valid`;
        const options = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (error) {
        throw new Error(error);
    }
};

export const refresh = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/refresh`;

        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.refreshToken}`
            },
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const signin = async ({ email, password }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/signin`;

        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password })
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const sendCode = async ({ email }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/sendcode`;

        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email })
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const signup = async ({ email, password, name, nickname, age = null, job = null, hobby = null }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/signup`;

        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password, name, nickname, age, job, hobby }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const signout = async ({ email, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/signout`;

        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const unregist = async ({ email, password, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/unregist`;

        const options = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, password }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const updatePw = async ({ email, password, newpassword, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/updatepw`;

        const options = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, password, newpassword }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const resetPw = async ({ email, password, code }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/resetpw`;

        const options = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password, code })
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const updateProfile = async ({ name, nickname, age = null, job = null, hobby = null, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/updateprofile`;

        const options = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ name, nickname, age, job, hobby }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const mypage = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/mypage`;

        const options = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const checkEmail = async ({ email }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/checkemail`;

        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email })
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const checkCode = async ({ email, code }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/checkcode`;

        const options = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, code })
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const checkNickname = async ({ nickname }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/checknickname/${nickname}`;

        const options = {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const reset = async ({ nickname, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/reset/${nickname}`;
        const options = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (error) {
        console.log(error);
    }
};

export const domain = async () => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/user/domain`;
        const options = {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (error) {
        console.log(error);
    }
};
