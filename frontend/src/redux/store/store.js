import { createStore, applyMiddleware, combineReducers } from "redux";
import userSlice from 'redux/slice/userSlice';

import {
    createStateSyncMiddleware,
    initMessageListener,
} from "redux-state-sync";

import { persistStore, persistReducer } from "redux-persist";
// import storage from "redux-persist/lib/storage";
import storageSession from 'redux-persist/lib/storage/session';

const persistConfig = {
    key: "root",
    // storage,
    storage: storageSession,
};

const rootReducer = combineReducers({
    user: userSlice,
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = createStore(
    persistedReducer,
    applyMiddleware(
        createStateSyncMiddleware({
            blacklist: ["persist/PERSIST", "persist/REHYDRATE"],
        })
    )
);

initMessageListener(store);

export const persistor = persistStore(store);
export default store;