import eetch from "apis/eetch";

export const game = async ({ formData, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/game`;
        const options = {
            method: "POST",
            headers: {
                Authorization: `Bearer ${user.accessToken}`
            },
            body: formData,
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (error) {
        console.log(error);
    }
};

export const speed = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/game/speed`;
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

export const speedOne = async ({ index, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/game/speed/1/${index}`;
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

export const speedTwo = async ({ index, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/game/speed/2/${index}`;
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

export const speedResult = async ({ total, score, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/game/speed/result`;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ total, score }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const blank = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/game/blank`;
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

export const blankResult = async ({ total, score, level, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/game/blank/result`;

        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ total, score, level }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};
