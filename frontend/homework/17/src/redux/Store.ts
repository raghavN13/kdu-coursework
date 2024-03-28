import { configureStore } from "@reduxjs/toolkit";
import productsReducer from "./ProductSlice";
import slackReducer from "./SlackSlice"

export const store = configureStore({
    reducer: {
        products:productsReducer,
        slack : slackReducer,
    },
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;