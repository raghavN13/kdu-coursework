import { ListItem } from "./ListItem";
import './ItemList.css';
import { RootState } from "../redux/Store";
import { useSelector } from "react-redux";
export function List(){

   const items =  useSelector((state:RootState) => state.item.filteredItems);

    return (
        <div className="itemList">
            <div className="item-list-header">Items</div>
            <ul id="list">
            {items.map(item => (
                    <ListItem key={item.id} item={item} />
                ))}
            </ul>
        </div>
    )

}