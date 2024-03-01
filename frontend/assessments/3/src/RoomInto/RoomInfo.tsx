import { useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { RoomList } from "./RoomList";
// import { RootState } from "./redux/Store";
// import { Home } from "./Home";

export function RoomInfo(){
    const state = useSelector((state:RootState)=>state.stocks.state);
    const productError = useSelector((state:RootState)=>state.stocks.error);

    if(state === "loading"){
        return <div>Loading...</div>
    }

    if(state==="succeeded"){
        return <RoomList />
    }

    if(state==="failed"){
        return <div>Error</div>
    }
}