import { configureStore } from "@reduxjs/toolkit";
import roomReducer from "./RoomSlice"
import slackReducer from "./SlackSlice"

export const store = configureStore({
    reducer: {
        stocks:roomReducer,
        slack : slackReducer,
        
    },
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;