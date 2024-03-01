import { useEffect} from 'react'
import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './Home/Home';

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
    </Routes>

    </BrowserRouter>
  )
}

export default App
