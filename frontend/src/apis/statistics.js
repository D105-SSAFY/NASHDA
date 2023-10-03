import eetch from "apis/eetch";

export const strick = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistic/strick`;
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

export const strickDetail = async ({ email, date, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistic/strick/detail`;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, date }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const achievement = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistic/achievement`;
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

export const games = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistic/game`;
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

export const gameBlankWeek = async ({ week, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistic/game/blank/${week}`;
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

export const gameSpeedWeek = async ({ week, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistic/game/speed/${week}`;
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

export const practiceWord = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistic/practice/word`;
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

export const practiceSimul = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistic/practice/simul`;
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

export const practiceSimulBackground = async ({ background, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistic/practice/simul/${background}`;
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

export const weekTest = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistic/test`;
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
