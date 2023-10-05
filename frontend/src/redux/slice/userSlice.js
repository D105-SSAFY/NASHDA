import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

export const loginUser = createAsyncThunk(`user/loginUser`, async (tokens) => {
    return tokens;
});

export const clearUser = createAsyncThunk("user/clearUser", async () => {
    return {};
});

export const userSlice = createSlice({
    name: "user",
    initialState: {
        accessToken: "",
        refreshToken: ""
    },
    reducers: {
        updateRefresh(state, action) {
            state.accessToken = action.payload.accessToken;

            return state;
        },
        clearUser(state) {
            state.accessToken = "";
            state.refreshToken = "";

            return state;
        }
    },
    extraReducers(builder) {
        builder.addCase(loginUser.fulfilled, (state, action) => {
            state.accessToken = action.payload.accessToken;
            state.refreshToken = action.payload.refreshToken;
        });

        builder.addCase(clearUser.fulfilled, (state) => {
            state.accessToken = "";
            state.refreshToken = "";
        });
    }
});

export const { updateRefresh } = userSlice.actions;
export default userSlice.reducer;
