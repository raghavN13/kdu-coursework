import React, { useState } from 'react';

interface BottomNavbarProps {
    onTabChange: (isWatchlist: boolean) => void;
}

export default function BottomNavbar({ onTabChange }: BottomNavbarProps) {
    const [activeTab, setActiveTab] = useState<boolean>(false); 

    const handleTabChange = (isWatchlist: boolean) => {
        onTabChange(isWatchlist);
        setActiveTab(isWatchlist); 
    }

    return (
        <div>
            <div className="dashboard-option">
                <div className="explore" onClick={() => handleTabChange(false)}>
                    <p className={activeTab ? '' : 'active'}>Explore</p>
                </div>
                <div className="my-watchlist" onClick={() => handleTabChange(true)}>
                    <p className={activeTab ? 'active' : ''}>My Watchlist</p>
                </div>
            </div>
            <div className="bottom-line"></div>
        </div>
    )
}
