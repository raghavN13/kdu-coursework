import React from 'react';
import { useSelector } from 'react-redux';
import './StockPage.scss';
import { RootState } from '../redux/Store';

export default function RightContainer() {
  // Get transactions from Redux store
  const transactions = useSelector((state: RootState) => state.transaction.transactions);
  const history = useSelector((state: RootState) => state.transaction.history);

  return (
    <>
      <div className="stock-container-right">
        <div className="user-history">
          <h2>User History</h2>
          <ul>
            {history.map((transaction, index) => (
              <li key={index}>
                <div>
                  <strong>Stock:</strong> {transaction.stock}
                </div>
                <div>
                  <strong>Quantity:</strong> {transaction.quantity}
                </div>
                <div>
                  <strong>Date:</strong> {transaction.date}
                </div>
                <div>
                  <strong>Type:</strong> {transaction.type}
                </div>
              </li>
            ))}
          </ul>
        </div>
        <div className="global-history">
          <h2>Global History</h2>
          <ul>
            {transactions.map((transaction, index) => (
              <li key={index}>
                <div>
                  <strong>Stock:</strong> {transaction.stock}
                </div>
                <div>
                  <strong>Quantity:</strong> {transaction.quantity}
                </div>
                <div>
                  <strong>Date:</strong> {transaction.date}
                </div>
                <div>
                  <strong>Type:</strong> {transaction.type}
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
