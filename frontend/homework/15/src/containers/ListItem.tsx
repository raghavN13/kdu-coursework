import { DeleteItem } from "./DeleteItem";

interface IItemProps{
    id : string;
    name : string;
}

interface IListItemsProps{
    item : IItemProps;
}

export function ListItem({item}:IListItemsProps){
    return (
        <li className="actual-list-item">
            <p>{item.name}</p>
            <DeleteItem itemId={item.id}/>
        </li>
    )
}