// StockSlice.ts

import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface Transaction {
  stock: string;
  quantity: number;
  date: string;
  type: string;
}

interface StockState {
  transactions: Transaction[];
  history :Transaction[];
}
const initialState: StockState = {
  transactions: [],
  history:[],
};

const stockSlice = createSlice({
  name: 'stock',
  initialState,
  reducers: {
    addTransaction(state, action: PayloadAction<Transaction>) {
      state.transactions.push(action.payload);
    },
    addHistoryLocal(state,action:PayloadAction<Transaction>){
      state.history.push(action.payload);
    }
  },
});

export const { addTransaction, addHistoryLocal} = stockSlice.actions;

export default stockSlice.reducer;
