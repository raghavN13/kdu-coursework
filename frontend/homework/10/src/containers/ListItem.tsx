import { DeleteItem } from "./DeleteItem";
import { IState } from "./State";

interface IItemProps{
    id : number;
    name : string;
}
interface IItemPairProps{
    item : IItemProps;
    itemState : IState
}

interface IListItemsProps{
    getPair : IItemPairProps;
}

export function ListItem({getPair}:IListItemsProps){
    return (
        <li className="actual-list-item">
            <p>{getPair.item.name}</p>
            <DeleteItem getPair = {getPair}/>
        </li>
    )
}