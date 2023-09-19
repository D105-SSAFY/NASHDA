export const strick = async ({ email, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/statistics/strick`, {
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

export const strickDetail = async ({ email, date, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/statistics/strick/detail`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${user.accessToken}`
            },
            body: JSON.stringify({ email, date }),
            credentials: 'include'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const achievement = async ({ email, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/statistics/achievement`, {
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

export const gameBlankWeek = async ({ email, week, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/statistics/game/blank/${week}`, {
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

export const gameSpeedWeek = async ({ email, week, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/statistics/game/speed/${week}`, {
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

export const practiceWord = async ({ email, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/statistics/practice/word`, {
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

export const practiceSimul = async ({ email, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/statistics/practice/simul`, {
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

export const practiceSimulBackground = async ({ email, background, user }) => {
    try {
        const response = fetch(`${process.env.API_URL}/statistics/practice/simul/${background}`, {
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
