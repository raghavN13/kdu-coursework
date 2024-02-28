import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit"
import axios from "axios";
import { IProduct } from "../product.type";
import { hideMessage, setMessage } from "./SlackSlice";
import { AppDispatch } from "./Store";
const BASE_URL = "https://fakestoreapi.com/products"

interface ProductState{
    products:IProduct[];
    state:"pending"|"fulfilled"|"error",
    error?:string;
}

const initialState:ProductState = {
    products:[],
    state:"pending"
}

export const fetchProducts = createAsyncThunk("fetch" ,async () => {
    const response = await axios.get(BASE_URL);
    console.log(response.data);

    return response?.data;
})

const ProductSlice = createSlice({
    name:"products",
    initialState,
    reducers:{

    },

    extraReducers(builder){
        builder.addCase(fetchProducts.pending,(state,action)=> {
            state.state = "pending"
        })
        .addCase(fetchProducts.fulfilled,(state,action)=>{
            state.state = "fulfilled"
            state.products = action.payload;
            
        })
        .addCase(fetchProducts.rejected,(state,action)=>{
            state.state = "error"
            state.error = action.error.message;
        })
    }
});

export const loadProductsAndNotify = () => async (dispatch:AppDispatch) => {
    try {
        const response = await dispatch(fetchProducts());
        dispatch(setMessage("Products loaded successfully!"));
        setTimeout(() => {
            dispatch(hideMessage());
        }, 2000);
        return response;
    } catch (error) {
        dispatch(setMessage("Failed to load products. Error: "));
        setTimeout(() => {
            dispatch(hideMessage());
        }, 2000);
        throw error;
    }
};

export default ProductSlice.reducer;
