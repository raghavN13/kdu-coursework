import { BrowserRouter,Routes,Route } from "react-router-dom"
import { Home } from "./Home"
import { useEffect } from "react";
import { ItemDetails } from "./ItemDetails/ItemDetails";
import { ItemProvider } from "./ItemContext";


function App() {
  useEffect(() => {
    // Check if the current path is not '/home'
    if (window.location.pathname !== '/home') {
      // Redirect to '/home' only if not already there
      window.location.href = '/home';
    }
  }, []);
  return (
    <ItemProvider>

    <BrowserRouter>
    <Routes>
      <Route path="/home" element={<Home/>}/>
      <Route path="/item/:id" element={<ItemDetails />} />
     
    </Routes>

    </BrowserRouter>
    </ItemProvider>
  )
}

export default App
