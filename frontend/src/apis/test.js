export const wordList = async ({ email, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/test/word`, {
            method: 'POST',
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

export const wordSubmit = async ({ index, sound, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/test/word/user`, {
            method: 'POST',
            headers: {
                'Content-Type': 'mulitpart/form-data',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ index, sound }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const wordResult = async ({ index, score, pron, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/test/word/result`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ index, score, pron }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const sentenceList = async ({ email, order, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/test/sentence`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, order }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const sentenceSubmit = async ({ index, order, sound, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/test/sentence/user`, {
            method: 'POST',
            headers: {
                'Content-Type': 'mulitpart/form-data',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ index, order, sound }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const sentenceResult = async ({ index, score, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/test/sentence/result`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ index, score }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const weekList = async ({ email, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/test/week`, {
            method: 'POST',
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

export const weekSubmit = async ({ index, order, sound, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/test/week/user`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ index, order, sound }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const weekResult = async ({ index, idxImg, score, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/test/week/result`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ index, idxImg, score }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};
