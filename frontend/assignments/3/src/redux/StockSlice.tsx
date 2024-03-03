import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit"
import axios from "axios";
import { hideMessage, setMessage } from "./SlackSlice";
import { AppDispatch } from "./Store";
const BASE_URL = "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json"

export interface IStock{
    stock_name:string;
    stock_symbol:string;
    base_price:number;
}
export interface IStockTransaction{
    stock_name:string;
    quantity:number;
}
interface StockState{
    stocks:IStock[];
    stocksTransaction : IStockTransaction[],
    state:"pending"|"fulfilled"|"error",
    error?:string;
}

const initialState:StockState = {
    stocksTransaction:[],
    stocks:[],
    state:"pending"
}

export const fetchStocks = createAsyncThunk("fetch" ,async () => {
    const response = await axios.get(BASE_URL);
    console.log(response.data);

    return response?.data;
})

const StockSlice = createSlice({
    name:"stocks",
    initialState,
    reducers:{

        addStockTransaction(state, action: PayloadAction<IStockTransaction>) {
            const { stock_name, quantity } = action.payload;
            const existingStockIndex = state.stocksTransaction.findIndex(stock => stock.stock_name === stock_name);

            if (existingStockIndex !== -1) {
                state.stocksTransaction[existingStockIndex].quantity += quantity;
            } else {
                state.stocksTransaction.push(action.payload);
            }
        },
        deleteStockTransaction(state, action: PayloadAction<IStockTransaction>) {
            const { stock_name, quantity } = action.payload;
            const existingStockIndex = state.stocksTransaction.findIndex(stock => stock.stock_name === stock_name);

            if (existingStockIndex !== -1) {
                if (state.stocksTransaction[existingStockIndex].quantity >= quantity) {
                    state.stocksTransaction[existingStockIndex].quantity -= quantity;
                    if (state.stocksTransaction[existingStockIndex].quantity === 0) {
                        state.stocksTransaction.splice(existingStockIndex, 1);
                    }
                } else {
                    console.error("Insufficient quantity to sell.");
                }
            } else {
                console.error("Stock not found in transaction history.");
            }
        }
    },

    

    extraReducers(builder){
        builder.addCase(fetchStocks.pending,(state)=> {
            state.state = "pending"
        })
        .addCase(fetchStocks.fulfilled,(state,action)=>{
            state.state = "fulfilled"
            state.stocks = action.payload;
            
        })
        .addCase(fetchStocks.rejected,(state,action)=>{
            state.state = "error"
            state.error = action.error.message;
        })
    }
});

export const loadStocksAndNotify = () => async (dispatch: AppDispatch) => {
    try {
        const response = await dispatch(fetchStocks());
        if (response.payload && Array.isArray(response.payload) && response.payload.length > 0) {
           
            dispatch(setMessage("Products loaded successfully!"));
            setTimeout(() => {
                dispatch(hideMessage());
            }, 2000);
            return response;
        } else {
            dispatch(setMessage("Failed to load products. No products found."));
            setTimeout(() => {
                dispatch(hideMessage());
            }, 2000);
            throw new Error("Failed to load products. No products found.");
        }
    } catch (error) {
        console.error("Error:", error);
        dispatch(setMessage("Failed to load products. Error: "));
        setTimeout(() => {
            dispatch(hideMessage());
        }, 2000);
        throw error;
    }
};


export const { addStockTransaction, deleteStockTransaction } = StockSlice.actions;

export default StockSlice.reducer;
