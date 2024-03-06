import React, { useState } from 'react';
import './DashboardNavbar.scss';
import { useNavigate } from 'react-router-dom';



export function DashboardNavbar() {
    const navigate = useNavigate();
    const handleNavigateHome = () => {
      navigate('/home');
    };
    const handleNavigatePortfolio = () => {
        console.log("entering here ");
        navigate('/portfolio');
      };
    const [isMenuOpen, setIsMenuOpen] = useState(false);


    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    return (
        <div className="dashboard">
            <div className="dashboard-navbar">
                <div className="dashboard-navbar-left">
                    <i className="fi fi-ss-chart-histogram" onClick={handleNavigateHome}></i>
                    <p className="dashboard-name">KDU STOCKMARKET</p>
                </div>
                <div className="dashboard-navbar-right">
                    <p className="dashboard-summarizer">Summarizer</p>
                    <p className="my-portfolio" onClick={handleNavigatePortfolio}>My Portfolio</p>
    
                </div>
                <div className="hamburger-icon-mobile">
                <button onClick={toggleMenu}><i className="fi fi-sr-menu-burger"></i></button> 

                </div>
            </div>
            <div className={`show-class ${isMenuOpen ? 'show' : ''}`}>
                    <p className="dashboard-summarizer">Summarizer</p>
                    <div className="portfolio" >

                    portfolio
                    </div>
                </div>
        </div>
    );
}
