import "./StockWindow.scss"
import { Stocks } from './Stocks'
import { WatchlistStocks } from "./WatchlistStocks";
interface StockWindowProps {
    isWatchlistSelected: boolean;
  }
  export default function StockWindow({ isWatchlistSelected }: StockWindowProps) {

    return (
      <div className='stock-item-main'>

  
        {isWatchlistSelected ? <WatchlistStocks /> : <Stocks />}
  
      </div>
    )
  }
  