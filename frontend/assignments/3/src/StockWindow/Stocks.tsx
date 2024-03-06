import { useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { Stocklist } from "./Stocklist";
import { SyncLoader } from "react-spinners"; // Import SyncLoader spinner component

export function Stocks(){
    const state = useSelector((state:RootState)=>state.stocks.state);
    const productError = useSelector((state:RootState)=>state.stocks.error);

    if(state === "pending"){
        return (
            <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <SyncLoader color={"#36D7B7"} loading={true} size={15} /> {/* Render SyncLoader spinner */}
            </div>
        );
    }

    if(state === "fulfilled"){
        return <Stocklist />;
    }

    if(state === "error"){
        return <div>Error</div>;
    }
}
