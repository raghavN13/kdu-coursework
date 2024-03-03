import { useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { Stocklist } from "./Stocklist";
// import { RootState } from "./redux/Store";
// import { Home } from "./Home";

export function Stocks(){
    const state = useSelector((state:RootState)=>state.stocks.state);
    const productError = useSelector((state:RootState)=>state.stocks.error);

    if(state === "pending"){
        return <div>Loading...</div>
    }

    if(state==="fulfilled"){
        return <Stocklist />
    }

    if(state==="error"){
        return <div>Error</div>
    }
}