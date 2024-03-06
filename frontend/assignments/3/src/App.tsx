import { useEffect} from 'react'
import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './Home/Home';
import { StockDetail } from './StockDetails/StockDetails';
import Portfolio from './PortfoilioTransactions/portfolioTransaction';

function App() {
  useEffect(() => {
    if (window.location.pathname !== '/home') {
      window.location.href = '/home';
    }
  }, []);



  return (
    <BrowserRouter>
    <Routes>
      <Route path="/home" element={<Home />}/>
      <Route path="/stock/:stockNameFromUrl" element={<StockDetail />} /> 
      <Route path="/portfolio" element={<Portfolio />} /> 

    </Routes>

    </BrowserRouter>
  )
}

export default App
