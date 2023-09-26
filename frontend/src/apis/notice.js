import eetch from "apis/eetch";

export const noticeSend = async ({ title, content, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/notice`;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ title, content }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (error) {
        console.log(error);
    }
};

export const noticeList = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/notice`;
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

export const noticeEdit = async ({ index, title, content, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/notice/${index}`;
        const options = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ title, content }),
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};

export const noticeGet = async ({ index, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/notice/${index}`;
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

export const noticeDelete = async ({ index, user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/notice/${index}`;
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
