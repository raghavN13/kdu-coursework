import { DeleteItem } from "./DeleteItem";
import { IState } from "./State";


interface IItemProps{
    id : number;
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