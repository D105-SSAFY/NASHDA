import eetch from "apis/eetch";

export const questionWrite = async ({ formData, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/question`;
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

export const questionList = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/question`;
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

export const questionEdit = async ({ index, email, title, content, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/question/${index}`;
        const options = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, title, content }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const questionGet = async ({ index, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/question/${index}`;
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

export const questionDelete = async ({ index, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/question/${index}`;
        const options = {
            method: "DELETE",
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

export const replyWrite = async ({ index, email, content, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/question/reply/${index}`;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, content }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const replyEdit = async ({ index, email, content, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/question/reply/${index}`;
        const options = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, content }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const replyDelete = async ({ index, email, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/question/reply/${index}`;
        const options = {
            method: "DELETE",
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
