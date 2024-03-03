import { configureStore } from "@reduxjs/toolkit";
import stocksReducer from "./StockSlice";
import slackReducer from "./SlackSlice"
import wishlistReducer from './Watchlist';
import transactionReducer from './transactions'
import portfolioTransactionReducer from "./PortfolioTransactions" 
import historyReducer from "./HistorySlice"


export const store = configureStore({
    reducer: {
        stocks:stocksReducer,
        slack : slackReducer,
        wishlist:wishlistReducer,
        transaction:transactionReducer,
        history:historyReducer,
        portfolioTransactions: portfolioTransactionReducer,
        
    },
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
