import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
    name: 'user',
    initialState: {
<<<<<<< HEAD
        accessToken: ""
=======
        accessToken: ''
>>>>>>> 39c5709 (Feat : 회원가입 페이지 및 비밀번호 찾기 페이지 구현)
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
