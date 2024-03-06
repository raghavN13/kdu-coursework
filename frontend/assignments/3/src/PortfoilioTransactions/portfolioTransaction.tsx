import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import "./PortfolioTransactions.scss"
import { AppDispatch, RootState } from '../redux/Store';
import { fetchPortfolioTransactions } from '../redux/PortfolioTransactions';
import { DashboardNavbar } from '../Dashboard/Navbar/DashboardNavbar';

interface Stock {
    id: string;
    stock_name: string;
    stock_symbol: string;
    transaction_price: number;
    timestamp: string;
    status: string;
}

export default function Portfolio() {
    const dispatch = useDispatch<AppDispatch>();
    const { transactions, status, error } = useSelector((state: RootState) => state.portfolioTransactions);
    const { stocks } = useSelector((state: RootState) => state.stocks);
    const history = useSelector((state: RootState) => state.history.history);
    const [selectedFilters, setSelectedFilters] = useState<string[]>([]);
    const [selectedStocks, setSelectedStocks] = useState<string[]>([]);
    const [startDate, setStartDate] = useState<string>('');
    const [endDate, setEndDate] = useState<string>('');
    const [search, setSearch] = useState<string>('');

   

    useEffect(() => {
        dispatch(fetchPortfolioTransactions());
    }, [dispatch]);



    const combinedTransactions = [...transactions, ...history];


    let filteredStocks = combinedTransactions;
    console.log(search);

    if (search) {
        filteredStocks = combinedTransactions.filter(stock => stock.stock_name.toLowerCase().includes(search.toLowerCase()));
    }

    if (selectedFilters.length > 0) {
        filteredStocks = filteredStocks.filter(stock => selectedFilters.includes(stock.status));
    }

    if (selectedStocks.length > 0) {
        filteredStocks = filteredStocks.filter(stock => selectedStocks.includes(stock.stock_symbol));
    }

    if (startDate) {
        filteredStocks = filteredStocks.filter(stock => {
            const stockDate = new Date(stock.timestamp).toISOString().split('T')[0];
            return stockDate >= startDate;
        });
    }

    if (endDate) {
        filteredStocks = filteredStocks.filter(stock => {
            const stockDate = new Date(stock.timestamp).toISOString().split('T')[0];
            return stockDate <= endDate;
        });
    }


    const sortedTransactions = filteredStocks.slice().sort((a, b) => {
        return new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime();
    });

    const groupedstocksTransactions = groupstocksTransactionsByDate(sortedTransactions);

    function groupstocksTransactionsByDate(stocksTransactions: Stock[]): { [date: string]: Stock[] } {
        const grouped: { [date: string]: Stock[] } = {};
        stocksTransactions.forEach((stock) => {
            const date = new Date(stock.timestamp).toLocaleDateString();
            if (!grouped[date]) {
                grouped[date] = [];
            }
            grouped[date].push(stock);
        });
        return grouped;
    }

    const handleSearch = (e) => {
        setSearch(e.target.value);
    }

    const handleFilterChange = (filter: string) => {
        if (selectedFilters.includes(filter)) {
            setSelectedFilters(selectedFilters.filter(f => f !== filter));
        } else {
            setSelectedFilters([...selectedFilters, filter]);
        }
    };

    const handleStockChange = (stock: string) => {
        if (selectedStocks.includes(stock)) {
            setSelectedStocks(selectedStocks.filter(s => s !== stock));
        } else {
            setSelectedStocks([...selectedStocks, stock]);
        }
    };

    const handleStartDateChange = (date: string) => {
        setStartDate(date);
    };

    const handleEndDateChange = (date: string) => {
        setEndDate(date);
    };

    const handleClearAll = () => {
        setSelectedFilters([]);
        setSelectedStocks([]);
        setStartDate('');
        setEndDate('');
        setSearch('');
    }

    if (status === 'loading') {
        return <div>Loading...</div>;
    }

    if (status === 'failed') {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="portfolio-wrapper">
            <DashboardNavbar />
            <div className="portfolio">
                <div className="filter">
                    <div className="filter-header">
                        <div className="filter-title">
                            Filters
                        </div>
                        <div className="clear-filter" onClick={handleClearAll}>
                            Clear All
                        </div>
                    </div>
                    <hr />
                    <input
                        type="text"
                        id='search-box'
                        placeholder='Search for a stock'
                        value={search}
                        onChange={handleSearch}
                    />
                    <hr />
                    <div className="search-by-date">
                        <input
                            type="date"
                            name=""
                            id=""
                            placeholder='Start Date'
                            value={startDate}
                            onChange={(e) => handleStartDateChange(e.target.value)}
                        />
                        <input
                            type="date"
                            name=""
                            id=""
                            placeholder='End Date'
                            value={endDate}
                            onChange={(e) => handleEndDateChange(e.target.value)}
                        />
                    </div>
                    <hr />
                    <div className="passed-failed">
                        <div>
                            <input
                                type="checkbox"
                                name="Passed"
                                id="passed"
                                checked={selectedFilters.includes('Passed')}
                                onChange={() => handleFilterChange('Passed')}
                            />
                            <label htmlFor="passed">Passed</label>
                        </div>
                        <div>
                            <input
                                type="checkbox"
                                name="Failed"
                                id="failed"
                                checked={selectedFilters.includes('Failed')}
                                onChange={() => handleFilterChange('Failed')}
                            />
                            <label htmlFor="failed">Failed</label>
                        </div>
                    </div>
                    <hr />
                    <div className="all-stocksTransactions">
                        {stocks.map(stock => (
                            <div key={stock.stock_name}>
                                <input
                                    type="checkbox"
                                    id={stock.stock_name}
                                    name={stock.stock_symbol}
                                    value={stock.stock_symbol}
                                    checked={selectedStocks.includes(stock.stock_symbol)}
                                    onChange={() => handleStockChange(stock.stock_symbol)}
                                />
                                <label htmlFor={stock.stock_name}>{stock.stock_name}</label>
                            </div>
                        ))}
                    </div>
                </div>
                <div className="history">
                    {Object.entries(groupedstocksTransactions).map(([date, stocksTransactions]) => (
                        <div key={date}>
                            <p className='portfolio-date'>{date}</p>
                            
                            {stocksTransactions.map((stock) => (
                                <div className="portfolio-elements" key={stock.id}>
                                    <div className='elements'>
                                        <div>{stock.stock_name}</div>
                                        <div>{stock.stock_symbol}</div>
                                        <div>{stock.transaction_price}</div>
                                        <div>{`${stock.status} ` === 'Passed ' ? <span className='green-dot'></span> : <span className='red-dot'></span>}</div>
                                        

                                    </div>
                                    
                                </div>
                            ))}
                            <br />
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}