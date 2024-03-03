import React from 'react'
interface BottomNavbarProps {
    onTabChange: (isWatchlist: boolean) => void;
}
export default function BottomNavbar({ onTabChange }:BottomNavbarProps) {

    const handleTabChange = (isWatchlist:boolean) => {
        onTabChange(isWatchlist);
    }
  return (
    <div>
                  <div className="dashboard-option">
                <div className="explore" onClick={()=>handleTabChange(false)}>
                    <p>Explore</p>
                </div>
                <div className="my-watchlist" onClick={()=>handleTabChange(true)}>
                    <p>My Watchlist</p>
                </div>
            </div>
    </div>
  )
}
