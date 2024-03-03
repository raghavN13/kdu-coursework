import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { IStock } from "../redux/StockSlice";
import { removeFromWishList } from "../redux/Watchlist";

// WatchlistStocks component
export const WatchlistStocks = () => {
  const watchlist = useSelector((state: RootState) => state.wishlist.WishlistStocks);
  const dispatch = useDispatch();

  const handleRemoveFromWatchlist = (item: IStock) => {
    dispatch(removeFromWishList(item));
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
        {watchlist.map((item) => (
          <div key={item.stock_name} className="item stock-item-header">
            <div className="show">
              <p className="stock-symbol company">${item.stock_symbol}</p>
            </div>
            <p className="stock-price base-price">{item.base_price}</p>
            <button className="watchlist" onClick={() => handleRemoveFromWatchlist(item)}>
              <i className="fi fi-rr-cross"></i>
            </button>
          </div>
        ))}

        </div>

    </div>
      
      </div>
    
  );
};
