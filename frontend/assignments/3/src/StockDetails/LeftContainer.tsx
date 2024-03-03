import React, { useState, useEffect, useRef } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/Store';
import { useParams } from 'react-router-dom';
import "./StockPage.scss";
import { IStock, IStockTransaction, addStockTransaction, deleteStockTransaction } from '../redux/StockSlice';
import { io } from 'socket.io-client';
import { addHistoryLocal, addTransaction } from '../redux/transactions';
import { IHistory, addHistory } from '../redux/HistorySlice';

export default function LeftContainer() {
    const stocks = useSelector((state: RootState) => state.stocks.stocks);
    const { stockNameFromUrl } = useParams<{ stockNameFromUrl: string }>();
    console.log("hello buddy")
    console.log(stockNameFromUrl);
    const [selectedStock, setSelectedStock] = useState("");
    const [selectedStockData, setSelectedStockData] = useState<IStock | null>(null);
    const [showDropdown, setShowDropdown] = useState(false);
    const [quantity, setQuantity] = useState("0");
    const dispatch = useDispatch();
    const [userBudget, setUserBudget] = useState(5000);
    const transactions = useSelector((state: RootState) => state.transaction.transactions);
    const stockTransaction = useSelector((state: RootState) => state.stocks.stocksTransaction);
    const [randomNumbers, setRandomNumbers] = useState<number[]>([]);
    const [flag, setFlag] = useState<number>(0);
    const barColors = ['red', 'blue'];
    const containerRef = useRef<HTMLDivElement>(null);

    useEffect(() => {
        const socket = io('http://localhost:5000');
        socket.on('transaction', (transactionData) => {
            console.log('Received transaction:', transactionData);
            dispatch(addTransaction(transactionData));
        });
        socket.on('newRandomNumber', (randomNumber) => {
            setRandomNumbers(prevNumbers => [...prevNumbers, randomNumber]);
        });
        return () => {
            socket.disconnect();
        };
    }, []);

    useEffect(() => {
        const updatedBarsColors = randomNumbers.map((randomNumber, index) => {
            let color = 'blue'; // Default color
            setFlag(0);
            if (index > 0) {
                // Compare the current bar's height with the previous bar's height
                if (randomNumber < randomNumbers[index - 1]) {
                    color = 'red';
                    setFlag(1); // Change color to red if current bar's height is less than previous bar's height
                }
            }
            return color;
        });
    }, [randomNumbers]);

    const handleBuy = () => {
        console.log("iitial", stockTransaction);
        const totalPrice = selectedStockData?.base_price ? selectedStockData.base_price * parseInt(quantity) : 0;

        const buyDataTransaction: IStockTransaction = {
            stock_name: selectedStock,
            quantity: parseInt(quantity)
        }
        const buyData = {
            stock: selectedStock,
            quantity: parseInt(quantity),
            date: new Date().toISOString(),
            type: "buy"
        };
        const historyBuyData: IHistory = {
            id: "1",
            stock_name: selectedStock,
            stock_symbol: selectedStockData?.stock_symbol ?? '',
            transaction_price: selectedStockData?.base_price ?? 0,
            timestamp: new Date().toISOString(),
            status: "Passed",
        }

        const socket = io('http://localhost:5000');
        if (totalPrice && totalPrice <= userBudget) {
            dispatch(addStockTransaction(buyDataTransaction));
            dispatch(addHistoryLocal(buyData));
            dispatch(addHistory(historyBuyData));
            setUserBudget(prevBudget => prevBudget - totalPrice);
            socket.emit('buyRequest', buyData);
        }
        else {
            historyBuyData.status = "Failed";
            dispatch(addHistory(historyBuyData));
            console.log("The use dosent have enough budget");
        }
    }

    const handleSell = () => {
        const stockTransactionData = stockTransaction.find(stock => stock.stock_name === selectedStock);
        console.log("the stock data is ", stockTransaction);

        const sellDataTransaction = {
            stock_name: selectedStock,
            quantity: parseInt(quantity),
        };
        const sellData = {
            stock: selectedStock,
            quantity: parseInt(quantity),
            date: new Date().toISOString(),
            type: "sell"
        };
        const historySellData: IHistory = {
            id: "1",
            stock_name: selectedStock,
            stock_symbol: selectedStockData?.stock_symbol ?? '',
            transaction_price: selectedStockData?.base_price ?? 0,
            timestamp: new Date().toISOString(),
            status: "Passed",
        };

        const socket = io('http://localhost:5000');
        if (stockTransactionData && stockTransactionData.quantity >= parseInt(quantity)) {
            dispatch(deleteStockTransaction(sellDataTransaction));
            setUserBudget(prevBudget => prevBudget + (selectedStockData?.base_price || 0) * parseInt(quantity));
            dispatch(addHistoryLocal(sellData));
            dispatch(addHistory(historySellData));
            socket.emit('sellRequest', sellData);
        }
        else {
            console.log("The User cannot sell the given stock ");
            historySellData.status = "Failed";
            dispatch(addHistory(historySellData));
        }
    }

    useEffect(() => {
        if (stocks.length > 0) {
            setSelectedStock(stockNameFromUrl || stocks[0].stock_name);
        }
    }, [stocks, stockNameFromUrl]);

    useEffect(() => {
        const stock = stocks.find(stock => stock.stock_name === selectedStock);
        setSelectedStockData(stock || null);
    }, [selectedStock, stocks]);

    const handleStockSelect = (stockName: string) => {
        setSelectedStock(stockName);
        setShowDropdown(false);
    };

    useEffect(() => {
        if (containerRef.current) {
            const containerWidth = containerRef.current.offsetWidth;
            let totalBarWidth = 0;
            const barsToDelete: number[] = [];
            randomNumbers.forEach((randomNumber, index) => {
                totalBarWidth += 30; // Assuming each bar has a width of 30px
                if (totalBarWidth > containerWidth) {
                    barsToDelete.push(index);
                }
            });
            if (barsToDelete.length > 0) {
                setRandomNumbers(prevNumbers => prevNumbers.slice(barsToDelete.length));
            }
        }
    }, [randomNumbers]);

    return (
        <>
            <div className="stock-container-left">
                <div className="stock-container-left-header">
                    <div className={`stock-container-left-stock-name ${showDropdown ? 'show-dropdown' : ''}`} onClick={() => setShowDropdown(!showDropdown)}>
                        {selectedStock} <i className="arrow-icon">&#9660;</i>
                        <div className="options-dropdown">
                            {stocks.map((stock, index) => (
                                <div key={`${stock.stock_symbol}-${index}`} className="option-item" onClick={() => handleStockSelect(stock.stock_name)}>
                                    {stock.stock_name}
                                </div>
                            ))}
                        </div>
                    </div>
                    <div className="stock-container-left-price">
                        {selectedStockData ? selectedStockData.base_price : '-'}
                    </div>
                    <div className="stock-container-left-quantity">
                        <input
                            type="string"
                            value={quantity}
                            onChange={(e) => setQuantity(e.target.value)}
                        />
                    </div>
                    <div className="stock-container-left-buy" onClick={handleBuy}>
                        Buy
                    </div>
                    <div className="stock-container-left-sell" onClick={handleSell}>
                        Sell
                    </div>
                </div>

                <div className="stock-container-left-graph" ref={containerRef}>
                    <div className="grid-background">
                        
                        {[...Array(3)].map((_, index) => (
                            <div className="horizontal-line" style={{ top: `${(index + 1) * 33.33}%` }} key={index}></div>
                        ))}
                        {/* Vertical lines */}
                        {[...Array(5)].map((_, index) => (
                            <div className="vertical-line" style={{ left: `${(index + 1) * 20}%` }} key={index}></div>
                        ))}
                    </div>

                    {randomNumbers.map((randomNumber, index) => {
                        const barClassName = `bar-${index}`; 
                        const barStyle: React.CSSProperties = {
                            height: `${randomNumber}px`, 
                            width: '30px', 
                            backgroundColor: index > 0 && randomNumber >= randomNumbers[index - 1] ? '#B2F2BB' : '#FFC9C9', 
                            border: index > 0 && randomNumber >= randomNumbers[index - 1] ? '1px solid #2F9E44' : '1px solid #E03131',
                        };

                        return (
                            <div
                                className={barClassName}
                                style={barStyle}
                                key={index}
                            ></div>
                        );
                    })}
                </div>
            </div>
            <div></div>
        </>
    )
}
