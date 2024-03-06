import React from 'react';
import { useSelector } from 'react-redux';
import './StockPage.scss';
import { RootState } from '../redux/Store';
import { useParams } from 'react-router-dom';

export default function RightContainer() {
    const transactions = useSelector((state: RootState) => state.transaction.transactions);
    const history = useSelector((state: RootState) => state.transaction.history);
    const { stockNameFromUrl } = useParams<{ stockNameFromUrl: string }>();
    console.log(stockNameFromUrl);
    const randomNames = ["John", "Emma", "Michael", "Sophia", "James"];

    const randomName = randomNames[Math.floor(Math.random() * randomNames.length)];

    return (
        <>
            <div className="stock-container-right">
                <div className="user-history">
                    <h2>History</h2>
                    <ul>
                        {history.map((transaction, index) => (
                            <li key={index}>
                                <div className="user-history-container">
                                    <div className="left-side-user-history">
                                        <p className='quantity'>{transaction.quantity} Stocks</p>
                                        <p className='date'>{new Date(transaction.date).toLocaleString(undefined, { month: 'long', day: 'numeric', year: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: true })}</p>

                                    </div>
                                    <div className="right-side-user-history">
                                        <p className={`transaction ${transaction.type}`}>{transaction.type}</p>

                                    </div>

                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
                <div className="global-history">
                    <ul>
                        {transactions
                            .filter(transaction => transaction.stock === stockNameFromUrl)
                            .map((transaction, index) => (
                                <li key={index}>
                                    <div className="global-history-container">
                                        <div className="info">
                                            <p>
                                                {randomName} {transaction.type === 'buy' ? 'bought' : 'sold'} {transaction.quantity} {transaction.stock}
                                            </p>
                                        </div>
                                        <div className="time">
                                            <p>{new Date(transaction.date).toLocaleTimeString()}</p>
                                        </div>
                                    </div>
                                </li>
                            ))}
                    </ul>
                </div>

            </div>
            <div></div>
        </>
    );
}
