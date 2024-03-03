import React, { useEffect, useState } from 'react'
import { DashboardNavbar } from '../Dashboard/Navbar/DashboardNavbar'
import StockWindow from '../StockWindow/StockWindow'
import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch, RootState } from '../redux/Store';
import { loadStocksAndNotify } from '../redux/StockSlice';
import BottomNavbar from '../Dashboard/Navbar/BottomNavbar';

export default function Home() {
    const reduxDispatch: AppDispatch = useDispatch();
    const slackMessage = useSelector((state:RootState) => state.slack.message);
    const isVisible = useSelector((state:RootState) => state.slack.isVisible);

    /**
     * A state is defined to see if the watchlist will be displayed or the explore page will be displayed
     */
    const [isWatchlistSelected,setIsWatchlistSelected] =  useState(false);

    const handleTabChange = (isWatchlist:boolean)=>{
        setIsWatchlistSelected(isWatchlist);
    }

    useEffect(() => {
        
        reduxDispatch(loadStocksAndNotify());
    }, [reduxDispatch]); 
  return (
    <div>
      <DashboardNavbar/>
      <BottomNavbar onTabChange={handleTabChange}/>
      <StockWindow isWatchlistSelected={isWatchlistSelected}/>
      {isVisible && <div>{slackMessage}</div>}

    </div>
  )
}
