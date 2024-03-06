// StockSlice.ts

import { createSlice, PayloadAction } from '@reduxjs/toolkit';

export interface IHistory {
    id:string;
    stock_name: string;
    stock_symbol: string;
    transaction_price: number;
    timestamp: string;
    status: string;
}

interface StockState {
  history :IHistory[];
}

const initialState: StockState = {
  history:[],
};

const stockSlice = createSlice({
  name: 'stock',
  initialState,
  reducers: {

    addHistory(state,action:PayloadAction<IHistory>){
      state.history.push(action.payload);
    }
  },
});

export const {addHistory } = stockSlice.actions;

export default stockSlice.reducer;
