import React, { useContext } from 'react';
import './Navbar.css';
import { useItemContext } from '../ItemContext';

export function Navbar() {
    const { setSearchTerm, setFilterCategory, setSortBy } = useItemContext();

    const handleSearchChange = (e: { target: { value: string; }; }) => {
        setSearchTerm(e.target.value);
    };

    const handleFilterChange = (e: { target: { value: string; }; }) => {
        setFilterCategory(e.target.value);
    };

    const handleSortChange = (e: { target: { value: string; }; }) => {
        setSortBy(e.target.value);
    };

    return (
        <div className="navbar">
            Search:
            <div className="search-box">
                <input
                    type="search"
                    name="search"
                    id="search"
                    onChange={handleSearchChange}
                />
            </div>
            <div className="filter">
                <p>Filter:</p>
                <select
                    name="filter"
                    id="filter"
                    onChange={handleFilterChange}
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
                    onChange={handleSortChange}
                >
                    <option value=""></option>
                    <option value="asc">Ascending</option>
                    <option value="desc">Descending</option>
                </select>
            </div>
        </div>
    );
}
