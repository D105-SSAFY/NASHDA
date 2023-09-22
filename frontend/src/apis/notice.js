export const noticeSend = async ({ title, content, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/notice`, {
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
        const response = fetch(`${process.env.REACT_APP_API_URL}/notice`, {
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

export const noticeEdit = async ({ index, title, content, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/notice/${index}`, {
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

export const noticeGet = async ({ index, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/notice/${index}`, {
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

export const noticeDelete = async ({ index, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/notice/${index}`, {
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
