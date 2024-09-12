import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { hideMessage, setMessage } from "./SlackSlice";
import { AppDispatch } from "./Store";

const BASE_URL = "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json";

export interface IRoom {
  id: number;
  name: string;
  costPerNight: string;
  currency: string;
  addOns: {
    name: string;
    cost: string;
    currency: string;
  }[];
}

interface RoomState {
  rooms: IRoom[];
  state: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: RoomState = {
  rooms: [],
  state: "loading",
  error: null,
};

export const fetchRooms = createAsyncThunk("fetchRooms", async () => {
  const response = await axios.get(BASE_URL);
  return response.data;
});

const roomSlice = createSlice({
  name: "rooms",
  initialState,
  reducers: {},
  extraReducers(builder) {
    builder
      .addCase(fetchRooms.pending, (state) => {
        state.state = "loading";
      })
      .addCase(fetchRooms.fulfilled, (state, action) => {
        state.state = "succeeded";
        state.rooms = action.payload;
      })
      .addCase(fetchRooms.rejected, (state, action) => {
        state.state = "failed";
        state.error = action.error.message || null;
      });
  },
});

export const loadRoomsAndNotify = () => async (dispatch: AppDispatch) => {
  try {
    const response = await dispatch(fetchRooms());
    console.log(response);
    if (response.payload && Array.isArray(response.payload) && response.payload.length > 0) {
      dispatch(setMessage("Rooms loaded successfully!"));
      setTimeout(() => {
        dispatch(hideMessage());
      }, 2000);
      return response;
    } else {
      dispatch(setMessage("Failed to load rooms. No rooms found."));
      setTimeout(() => {
        dispatch(hideMessage());
      }, 2000);
      throw new Error("Failed to load rooms. No rooms found.");
    }
  } catch (error) {
    console.error("Error:", error);
    dispatch(setMessage("Failed to load rooms. Error: "));
    setTimeout(() => {
      dispatch(hideMessage());
    }, 2000);
    throw error;
  }
};

export default roomSlice.reducer;
