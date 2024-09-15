import { AppendItem } from "./AppendItem";
import { IState } from "./State";
import { Header } from "./header/Header";


interface IItemListProps{
    itemsInfo : IState
}

export function ItemList({itemsInfo} : IItemListProps){

    return (
        <div className="item-list">
            <div className="body">
            <Header header={itemsInfo}/>
            <AppendItem item={itemsInfo} />

            </div>
        </div>
    )
}