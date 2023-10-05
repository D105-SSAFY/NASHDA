import eetch from "apis/eetch";

export const problemNum = async ({ type, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/practice/pron/` + type;
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

export const getProblem = async ({ type, number, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/practice/pron/` + type + "/" + number;
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

export const getProblemSTT = async ({ formData, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/practice/pron/result`;
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

export const initSimulation = async ({ background, message, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/simul/` + background;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ message }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (error) {
        console.log(error);
    }
};

export const getSimulationSTT = async ({ formData, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/simul/stt`;
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

export const nextSimulation = async ({ background, id, message, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/simul/` + background;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ id, message }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (error) {
        console.log(error);
    }
};
