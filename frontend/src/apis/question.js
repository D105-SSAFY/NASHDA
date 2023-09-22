export const questionWrite = async ({ email, title, content, writer, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/question`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, title, content, writer }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const questionList = async ({ user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/question`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const questionEdit = async ({ index, email, title, content, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/question/${index}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, title, content }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const questionGet = async ({ index, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/question/${index}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const questionDelete = async ({ index, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/question/${index}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const replyWrite = async ({ index, email, content, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/question/reply/${index}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, content }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const replyEdit = async ({ index, email, content, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/question/reply/${index}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, content }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const replyDelete = async ({ index, email, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/question/reply/${index}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};
