import eetch from "apis/eetch";

export const wordList = async ({ email, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/test/word`;
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

export const wordSubmit = async ({ index, sound, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/test/word/user`;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "mulitpart/form-data",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ index, sound }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const wordResult = async ({ index, score, pron, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/test/word/result`;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ index, score, pron }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const sentenceList = async ({ email, order, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/test/sentence`;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, order }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const sentenceSubmit = async ({ index, order, sound, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/test/sentence/user`;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "mulitpart/form-data",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ index, order, sound }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const sentenceResult = async ({ index, score, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/test/sentence/result`;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ index, score }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const weekList = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/test/week`;
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

export const weekSubmit = async ({ formData, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/test/week/user`;
        const options = {
            method: "POST",
            headers: {
                Authorization: `Bearer ${user.accessToken}`
            },
            body: formData,
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const weekResult = async ({ index, idxImg, score, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/test/week/result`;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ index, idxImg, score }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};
