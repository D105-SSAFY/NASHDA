import { configureStore, combineReducers } from "@reduxjs/toolkit";
import { createStateSyncMiddleware, initMessageListener } from "redux-state-sync";
import { persistReducer, persistStore } from "redux-persist";
import storageSession from "redux-persist/lib/storage/session";
import userSlice from "redux/slice/userSlice";

const persistConfig = {
    key: "root",
    storage: storageSession
};

const rootReducer = combineReducers({
    user: userSlice
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

// Replace createStore with configureStore
const store = configureStore({
    reducer: persistedReducer,
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware({
            serializableCheck: false
        }).concat(
            createStateSyncMiddleware({
                blacklist: ["persist/PERSIST", "persist/REHYDRATE"]
            })
        )
});

initMessageListener(store);

export const persistor = persistStore(store);
export default store;
