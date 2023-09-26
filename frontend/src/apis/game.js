export const game = async ({ type, index, sound, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/game`, {
            method: "POST",
            headers: {
                "Content-Type": "multipart/form-data",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ type, index, sound }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const speed = async (user) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/game/speed`, {
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

export const speedOne = async ({ index, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/game/speed/1/${index}`, {
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

export const speedTwo = async ({ index, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/game/speed/2/${index}`, {
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

export const speedResult = async ({ total, score, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/game/speed/result`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ total, score }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const blank = async ({ level, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/game/blank`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ level }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const blankResult = async ({ total, score, level, user }) => {
    try {
        const response = fetch(`${process.env.REACT_APP_API_URL}/game/blank/result`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ total, score, level }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};
