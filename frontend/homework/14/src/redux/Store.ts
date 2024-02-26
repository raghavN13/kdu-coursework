import { configureStore } from "@reduxjs/toolkit";
import itemReducer from "./TodoSlice";

export const store = configureStore({
    reducer: {
        item:itemReducer,
    }
});

export type RootState = ReturnType<typeof store.getState>