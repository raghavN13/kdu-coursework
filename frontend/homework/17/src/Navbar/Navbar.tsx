import React, { useContext } from 'react';
import './Navbar.css';

export function Navbar() {


    return (
        <div className="main-navbar-container">

            <div className="navbar">
                <div className="search-box">
                    <input
                        type="search"
                        name="search"
                        id="search"
                    />
                    <div className="search-icon">
                    </div>

                </div>
                <div className="left-side-navbar">


                    <div className="filter">
                        <p>Filter:</p>
                        <select
                            name="filter"
                            id="filter"
                        >
                            <option value="">All Categories</option>
                            <option value="electronics">Electronics</option>
                            <option value="jewelery">Jewelery</option>
                            <option value="men's clothing">Men's Clothing</option>
                            <option value="women's clothing">Women's Clothing</option>
                        </select>
                    </div>
                    <div className="sort">
                        <p>Sort:</p>
                        <select
                            name="sort"
                            id="sort"
                        >
                            <option value=""></option>
                            <option value="asc">Ascending</option>
                            <option value="desc">Descending</option>
                        </select>
                    </div>
                </div>
            </div>
            <div className="kdu-store">
                <p>KDU MARKETPLACE</p>
            </div>
        </div>

    );
}
