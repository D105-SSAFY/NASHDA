import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
    name: 'user',
    initialState: {
<<<<<<< HEAD
<<<<<<< HEAD
        accessToken: ""
=======
        accessToken: ''
>>>>>>> 39c5709 (Feat : 회원가입 페이지 및 비밀번호 찾기 페이지 구현)
=======
        accessToken: ''
>>>>>>> 39c5709713575e0e8f5ab802ab0481614199bf8b
    },
    reducers: {
        loginUser(state, action) {
            state.accessToken = action.payload.accessToken;

            return state;
        },
        clearUser(state) {
            state.accessToken = '';

            return state;
        }
    }
});

export const { loginUser, clearUser } = userSlice.actions;
export default userSlice.reducer;
