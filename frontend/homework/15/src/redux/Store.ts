import { configureStore, combineReducers } from "@reduxjs/toolkit";
import itemReducer from "./TodoSlice";
import { persistReducer, persistStore } from "redux-persist";
import storage from "redux-persist/lib/storage";

const persistConfig = {
    key: 'root', // key for the persist storage
    storage,
};

const rootReducer = combineReducers({
    item: itemReducer, // Make sure itemReducer is included here
    
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
    reducer: persistedReducer,
});

export const persistor = persistStore(store);

// Adjust RootState type
export type RootState = ReturnType<typeof rootReducer>;