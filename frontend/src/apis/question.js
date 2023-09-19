export const questionWrite = async ({ email, title, content, writer, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/question`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, title, content, writer }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const questionList = async ({ user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/question`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const questionEdit = async ({ idx, email, title, content, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/question/${idx}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, title, content }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const questionGet = async ({ idx, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/question/${idx}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const questionDelete = async ({ idx, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/question/${idx}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const replyWrite = async ({ idx, email, content, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/question/reply/${idx}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, content }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const replyEdit = async ({ idx, email, content, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/question/reply/${idx}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, content }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const replyDelete = async ({ idx, email, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/question/reply/${idx}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
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
