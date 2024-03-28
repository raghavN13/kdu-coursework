import { useSelector } from "react-redux";
import { RootState } from "./redux/Store";
import { Home } from "./Home";

export function Products(){
    const state = useSelector((state:RootState)=>state.products.state);
    const productError = useSelector((state:RootState)=>state.products.error);

    if(state === "pending"){
        return <div>Loading...</div>
    }

    if(state==="fulfilled"){
        return <Home />
    }

    if(state==="error"){
        return <div>Error</div>
    }
}