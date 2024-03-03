import { createSlice } from "@reduxjs/toolkit";
import { IStock } from "./StockSlice";

interface WishlistState{
    WishlistStocks : IStock[];
}
const initialState:WishlistState = {
    WishlistStocks:[],
}

const wishlistSlice = createSlice({
    name:"wishlist",
    initialState,
    reducers : {
        addToWishlist(state, action) {
            const existingStock = state.WishlistStocks.find(stock => stock.stock_name === action.payload.stock_name);
            if (!existingStock) {
                state.WishlistStocks.push(action.payload);
            }
        },
        removeFromWishList(state,action){
            state.WishlistStocks = state.WishlistStocks.filter((stock) => stock.stock_name!==action.payload.stock_name);
        },
    },
});

export const {addToWishlist,removeFromWishList} = wishlistSlice.actions;
export default wishlistSlice.reducer;
