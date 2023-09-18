// `/user/login`로 `email, password`를 request body로 포함하는 POST 요청 fetch 사용
export const refresh = async (refreshToken) => {
    try {
        const response = await fetch(`${process.env.API_URL}/user/refresh`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${refreshToken}`
            },
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const login = async ({ email, password }) => {
    try {
        const response = await fetch(`${process.env.API_URL}/user/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const sendcode = async ({ email }) => {
    try {
        const response = fetch(`${process.env.API_URL}/user/sendcode`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email })
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const signup = async ({ email, password, name, nickname, age = null, job = null, hobby = null }) => {
    try {
        const response = fetch(`${process.env.API_URL}/user/signup`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                email,
                password,
                name,
                nickname,
                age,
                job,
                hobby
            }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const logout = async ({ email }, refreshToken) => {
    try {
        const response = fetch(`${process.env.API_URL}/user/logout`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${refreshToken}`
            },
            body: JSON.stringify({ email }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const signout = async ({ email, password }, refreshToken) => {
    try {
        const response = fetch(`${process.env.API_URL}/user/signout`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${refreshToken}`
            },
            body: JSON.stringify({ email, password }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const updatepw = async ({ email, password, newpassword }, refreshToken) => {
    try {
        const response = fetch(`${process.env.API_URL}/user/updatepw`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${refreshToken}`
            },
            body: JSON.stringify({ email, password, newpassword }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const resetpw = async ({ email, password, code }) => {
    try {
        const response = fetch(`${process.env.API_URL}/user/resetpw`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password, code })
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const updateprofile = async ({ email, password, name, nickname, age = null, job = null, hobby = null }, refreshToken) => {
    try {
        const response = fetch(`${process.env.API_URL}/user/updateprofile`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${refreshToken}`
            },
            credentials: 'include',
            body: JSON.stringify({
                email,
                password,
                name,
                nickname,
                age,
                job,
                hobby
            })
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const mypageName = async (refreshToken) => {
    try {
        const response = fetch(`${process.env.API_URL}/user/mypage/name`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${refreshToken}`
            },
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};
