import { createSlice } from "@reduxjs/toolkit";
interface ISlack{
    message:string;
    isVisible:boolean;
}

const initialState: ISlack= {
    message: "",
    isVisible: false,
};

const slackSlice = createSlice({
    name: "slack",
    initialState,
    reducers: {
        setMessage(state, action) {
            state.message = action.payload;
            state.isVisible = true;
        },
        hideMessage(state) {
            state.isVisible = false;
        },
    },
});

export const { setMessage, hideMessage } = slackSlice.actions;
export default slackSlice.reducer;
