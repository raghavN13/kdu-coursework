import { useNavigate } from 'react-router-dom';
import { DashboardNavbar } from '../Dashboard/Navbar/DashboardNavbar';
import "./StockDetails.scss"
import { StockPage } from './StockPage';
// import StockPage from './StockPage';


export const StockDetail = () => {

  return (
    <div>
      <DashboardNavbar/>
      <StockPage/>
      
    </div>
  );
}
