import { ListItem } from "./ListItem";
import './ItemList.css';
import { useItemContext } from "./ItemProvider";

export function List(){

    const {filteredItems} = useItemContext();

    return (
        <div className="itemList">
            <div className="item-list-header">Items</div>
            <ul id="list">
            {filteredItems.map(item => (
                    <ListItem key={item.id} item={item} />
                ))}
            </ul>
        </div>
    )

}