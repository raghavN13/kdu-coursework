import { AppendItem } from "./AppendItem";
import { Header } from "./header/Header";


export function ItemList(){

    return (
        <div className="item-list">
            <div className="body">
            <Header/>
            <AppendItem/>

            </div>
        </div>
    )
}