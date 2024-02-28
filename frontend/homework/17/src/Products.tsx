import { useSelector } from "react-redux";
import { RootState } from "./redux/Store";

export function Products(){
    const products = useSelector((state:RootState)=>state.products.products);
    const state = useSelector((state:RootState)=>state.products.state);
    const productError = useSelector((state:RootState)=>state.products.error);

    if(state === "pending"){
        return <div>Loading...</div>
    }

    if(state==="fulfilled"){
        return <div>${JSON.stringify(products)}</div>
    }

    if(state==="error"){
        return <div>Error</div>
    }
}