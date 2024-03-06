import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { IStock } from "../redux/StockSlice";
import { removeFromWishList } from "../redux/Watchlist";
import { useState } from "react";
import "./StockWindow.scss"

export const WatchlistStocks = () => {
  const watchlist = useSelector((state: RootState) => state.wishlist.WishlistStocks);
  const dispatch = useDispatch();
  const itemsPerPage = 10;
  const [currentPage, setCurrentPage] = useState(1);

  const handleRemoveFromWatchlist = (item: IStock) => {
    dispatch(removeFromWishList(item));
  };

  const totalPages = Math.ceil(watchlist.length / itemsPerPage);
  const paginatedWatchlist = watchlist.slice((currentPage - 1) * itemsPerPage, currentPage * itemsPerPage);

  const handlePageChange = (pageNumber: number) => {
    setCurrentPage(pageNumber);
  };

  const renderPaginationButtons = () => {
    const buttons = [];
    const maxButtons = 5;

    if (totalPages <= maxButtons) {
      for (let i = 1; i <= totalPages; i++) {
        buttons.push(
          <button
            key={i}
            onClick={() => handlePageChange(i)}
            className={i === currentPage ? "active" : ""}
          >
            {i}
          </button>
        );
      }
    } else {
      let firstButton = currentPage - Math.floor(maxButtons / 2);
      let lastButton = currentPage + Math.floor(maxButtons / 2);

      if (currentPage < Math.ceil(maxButtons / 2)) {
        firstButton = 1;
        lastButton = maxButtons;
      } else if (currentPage > totalPages - Math.floor(maxButtons / 2)) {
        firstButton = totalPages - maxButtons + 1;
        lastButton = totalPages;
      }

      if (firstButton !== 1) {
        buttons.push(
          <button key={1} onClick={() => handlePageChange(1)}>
            1
          </button>
        );
        if (firstButton !== 2) {
          buttons.push(<span key="ellipsis-start">...</span>);
        }
      }

      for (let i = firstButton; i <= lastButton; i++) {
        buttons.push(
          <button
            key={i}
            onClick={() => handlePageChange(i)}
            className={i === currentPage ? "active" : ""}
          >
            {i}
          </button>
        );
      }

      if (lastButton !== totalPages) {
        if (lastButton !== totalPages - 1) {
          buttons.push(<span key="ellipsis-end">...</span>);
        }
        buttons.push(
          <button key={totalPages} onClick={() => handlePageChange(totalPages)}>
            {totalPages}
          </button>
        );
      }
    }

    return buttons;
  };

  return (
    <div className="main-container watchlist-container">
      <div className="stock-item-container">
        <div className="stock-item-header stock-item-header-title">
          <div className="company"><p>Company</p></div>
          <div className="base-price"><p>Base Price</p></div>
          <div className="watchlist-stocks"><p>Watchlist</p></div>
        </div>
        <div className="item-body">
          {paginatedWatchlist.map((item) => (
            <div key={item.stock_name} className="item stock-item-header">
              <div className="show">
                <p className="stock-symbol company">${item.stock_symbol}</p>
              </div>
              <p className="stock-price base-price">{item.base_price}</p>
              <button className="watchlist" onClick={() => handleRemoveFromWatchlist(item)}>
                <i className="fi fi-rr-check"></i>
              </button>
            </div>
          ))}
      <div className="pagination">
        {currentPage > 1 && (
          <button onClick={() => handlePageChange(currentPage - 1)}>
            &lt;
          </button>
        )}
        {renderPaginationButtons()}
        {currentPage < totalPages && (
          <button onClick={() => handlePageChange(currentPage + 1)}>
            &gt;
          </button>
        )}
      </div>
        </div>
      </div>
    </div>
  );
};

export default WatchlistStocks;
