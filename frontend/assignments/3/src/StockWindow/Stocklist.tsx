import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { IStock } from "../redux/StockSlice";
import { addToWishlist, removeFromWishList } from "../redux/Watchlist";
import { useNavigate } from "react-router-dom"; // Import the useNavigate hook

export const Stocklist = () => {
  const stocks = useSelector((state: RootState) => state.stocks.stocks);
  const wishlistStocks = useSelector((state: RootState) => state.wishlist.WishlistStocks);
  const dispatch = useDispatch();
  const itemsPerPage = 10;
  const [currentPage, setCurrentPage] = useState(1);
  const navigate = useNavigate(); // Initialize the navigate function

  const handleNavigateToDetailPage = (stockName: string) => {
    navigate(`/stock/${stockName}`);
  };

  const handleAddToWatchlist = (item: IStock) => {
    dispatch(addToWishlist(item));
  };

  const handleRemoveFromWatchlist = (item: IStock) => {
    dispatch(removeFromWishList(item));
  };

  const isInWishlist = (stock: IStock) => {
    return wishlistStocks.some(wishlistItem => wishlistItem.stock_name === stock.stock_name);
  };

  const handleAddToWatchlistButtonClick = (item: IStock) => {
    if (isInWishlist(item)) {
      handleRemoveFromWatchlist(item);
    } else {
      handleAddToWatchlist(item);
    }
  };

  const totalPages = Math.ceil(stocks.length / itemsPerPage);
  const paginatedStocks = stocks.slice((currentPage - 1) * itemsPerPage, currentPage * itemsPerPage);

  const handlePageChange = (pageNumber: number) => {
    setCurrentPage(pageNumber);
  };

  const renderPaginationButtons = () => {
    const buttons = [];
    const maxButtons = 5; // Maximum number of pagination buttons to show

    // Logic for rendering pagination buttons
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
    <div className="main-container">
      <div className="items-container">
        <div className="stock-item-container">
          <div className="stock-item-header stock-item-header-title">
            <div className="company"><p>Company</p></div>
            <div className="base-price"><p>Base Price</p></div>
            <div className="watchlist-stocks"><p>Watchlist</p></div>
          </div>
          <div className="item-body" >
            {paginatedStocks.map((item) => (
              <div key={item.stock_name} className="item stock-item-header" >
                <div className="show">
                  <p className="stock-symbol company" onClick={() => handleNavigateToDetailPage(item.stock_name)}>
                    ${item.stock_symbol}
                  </p>
                </div>
                <p className="stock-price base-price">{item.base_price}</p>
                <button
                  className={`watchlist ${isInWishlist(item) ? "correct-icon" : ""}`}
                  onClick={() => handleAddToWatchlistButtonClick(item)}
                >
                  {isInWishlist(item) ? (
                    <i className="fi fi-rr-check"></i>
                  ) : (
                    <i className="fi fi-rr-plus"></i>
                  )}
                </button>
              </div>
            ))}
          </div>
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

export default Stocklist;
