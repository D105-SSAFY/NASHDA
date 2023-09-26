import eetch from "apis/eetch";

export const strick = async ({ email, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistics/strick`;
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
    } catch (error) {
        console.log(error);
    }
};

export const strickDetail = async ({ email, date, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistics/strick/detail`;
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

export const achievement = async ({ email, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistics/achievement`;
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

export const gameBlankWeek = async ({ email, week, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistics/game/blank/${week}`;
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

export const gameSpeedWeek = async ({ email, week, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistics/game/speed/${week}`;
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

export const practiceWord = async ({ email, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistics/practice/word`;
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

export const practiceSimul = async ({ email, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistics/practice/simul`;
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

export const practiceSimulBackground = async ({ email, background, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/statistics/practice/simul/${background}`;
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
