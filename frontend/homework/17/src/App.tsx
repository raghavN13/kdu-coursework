import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { loadProductsAndNotify } from './redux/ProductSlice'; // Import the thunk
import { AppDispatch, RootState } from './redux/Store';
import { Products } from './Products';
import { Navbar } from './Navbar/Navbar';

function App() {
    const reduxDispatch: AppDispatch = useDispatch();
    const slackMessage = useSelector((state:RootState) => state.slack.message);
    const isVisible = useSelector((state:RootState) => state.slack.isVisible);

    useEffect(() => {
        // Dispatch the thunk to load products and notify
        reduxDispatch(loadProductsAndNotify());
    }, [reduxDispatch]); // Make sure to include reduxDispatch in the dependency array

    return (
        <div>
            <Navbar/>
            <Products />
            {isVisible && <div>{slackMessage}</div>}
        </div>
    );
}

export default App;
