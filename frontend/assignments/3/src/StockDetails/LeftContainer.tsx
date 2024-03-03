import React, { useState, useEffect, useRef } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/Store';
import { useNavigate, useParams } from 'react-router-dom';
import "./StockPage.scss";
import { IStock, IStockTransaction, addStockTransaction, deleteStockTransaction } from '../redux/StockSlice';
import { io } from 'socket.io-client';
import { addHistoryLocal, addTransaction } from '../redux/transactions';
import { IHistory, addHistory } from '../redux/HistorySlice';

export default function LeftContainer() {
    const stocks = useSelector((state: RootState) => state.stocks.stocks);
    const { stockNameFromUrl } = useParams<{ stockNameFromUrl: string }>();
    console.log(stockNameFromUrl);
    const [selectedStock, setSelectedStock] = useState("");
    const [selectedStockData, setSelectedStockData] = useState<IStock | null>(null);
    const [showDropdown, setShowDropdown] = useState(false);
    const [quantity, setQuantity] = useState("");
    const dispatch = useDispatch();
    const [userBudget, setUserBudget] = useState(5000);
    const stockTransaction = useSelector((state: RootState) => state.stocks.stocksTransaction);
    const [randomNumbers, setRandomNumbers] = useState<number[]>([]);
    const containerRef = useRef<HTMLDivElement>(null);
    const [newPrice, setNewPrice] = useState();
    
    useEffect(() => {

        setSelectedStock(stockNameFromUrl || stocks[0].stock_name);
    }, [stockNameFromUrl]);

    useEffect(() => {
        const stock = stocks.find(stock => stock.stock_name === selectedStock);
        setSelectedStockData(stock || null);
    }, [selectedStock, stocks]);


    console.log("the url here is " , stockNameFromUrl)
    useEffect(() => {
        const socket = io('http://localhost:5000');
        socket.on('transaction', (transactionData) => {
            console.log('Received transaction:', transactionData);
            dispatch(addTransaction(transactionData));
        });
        socket.on('newRandomNumber', (randomNumber) => {
            setRandomNumbers(prevNumbers => [...prevNumbers, randomNumber]);
            console.log("the selected stockdata is ",selectedStockData);
            console.log("the selected stock data base price is " , selectedStockData?.base_price);
            setNewPrice(selectedStockData?.base_price);
        });
        return () => {
            socket.disconnect();
        };
    }, []);

    useEffect(() => {
        const updatedBarsColors = randomNumbers.map((randomNumber, index) => {
            let color = 'blue';
            if (index > 0) {
                if (randomNumber < randomNumbers[index - 1]) {
                    color = 'red';
                }
            }
            return color;
        });
    }, [randomNumbers]);

    const handleBuy = () => {
        console.log("intial", stockTransaction);
        const totalPrice = newPrice ? newPrice * parseInt(quantity) : 0;

        const buyDataTransaction: IStockTransaction = {
            stock_name: selectedStock,
            quantity: parseInt(quantity)
        }
        const buyData = {
            stock: selectedStock,
            quantity: parseInt(quantity),
            date: new Date().toISOString(),
            type: "Buy"
        };
        const historyBuyData: IHistory = {
            id: "1",
            stock_name: selectedStock,
            stock_symbol: selectedStockData?.stock_symbol ?? '',
            transaction_price: newPrice ?? 0,
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
            type: "Sell"
        };
        const historySellData: IHistory = {
            id: "1",
            stock_name: selectedStock,
            stock_symbol: selectedStockData?.stock_symbol ?? '',
            transaction_price: newPrice ?? 0,
            timestamp: new Date().toISOString(),
            status: "Passed",
        };

        const socket = io('http://localhost:5000');
        if (stockTransactionData && stockTransactionData.quantity >= parseInt(quantity)) {
            dispatch(deleteStockTransaction(sellDataTransaction));
            setUserBudget(prevBudget => prevBudget + (newPrice || 0) * parseInt(quantity));
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


    const navigate = useNavigate(); // Initialize the navigate function

    const handleStockSelect = (stockName: string) => {
        setSelectedStock(stockName);
        setShowDropdown(false);
        navigate(`/stock/${stockName}`);
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
    
    console.log("the new price", newPrice)
    useEffect(() => {
        if (stocks.length > 0) {
            setSelectedStock(stockNameFromUrl || stocks[0].stock_name);
            setRandomNumbers([]); // Reset random numbers when the selected stock changes
        }
    }, [stocks, stockNameFromUrl]);


    return (
        <>
            <div className="stock-container-left">
                <div className="stock-container-left-header">
                    <div className={`stock-container-left-stock-name ${showDropdown ? 'show-dropdown' : ''}`} onClick={() => setShowDropdown(!showDropdown)}>
                        <div className="symbol">{selectedStock.substring(0, 3).toUpperCase()}</div>

                        <div className="selected-stock"> {selectedStock} </div><i className="arrow-icon">&#9660;</i>
                        <div className="options-dropdown">
                            {stocks.map((stock, index) => (
                                <div key={`${stock.stock_symbol}-${index}`} className="option-item" onClick={() => handleStockSelect(stock.stock_name)}>
                                    <div className="stock-name">
                                        {stock.stock_name}
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                    <div className="stock-container-left-price">

                        {newPrice}
                    </div>
                    <div className="stock-container-left-quantity">
                        <input
                            type="string"
                            value={quantity}
                            placeholder='Enter QTY'
                            onChange={(e) => setQuantity(e.target.value)}
                        />
                    </div>
                    <div className="stock-container-left-buy" onClick={handleBuy}>
                        <p>Buy</p>
                    </div>
                    <div className="stock-container-left-sell" onClick={handleSell}>
                        <p>Sell</p>
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
