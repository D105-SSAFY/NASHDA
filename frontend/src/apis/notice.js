export const noticeSend = async ({ title, content, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/notice`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ title, content }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const noticeList = async ({ user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/notice`, {
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

export const noticeEdit = async ({ idx, title, content, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/notice/${idx}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ title, content }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const noticeGet = async ({ idx, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/notice/${idx}`, {
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

export const noticeDelete = async ({ idx, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/notice/${idx}`, {
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
