import { IState } from "./State";

interface IItemProps{
    id : number;
    name : string;
}
interface IItemPairProps{
    item : IItemProps;
    itemState : IState
}

interface IDeletetItemsProps{
    getPair : IItemPairProps;
}

export function DeleteItem({getPair}: IDeletetItemsProps){
    function deleteItem(){
        const list = getPair.itemState.listitems.filter(item => item.id!==getPair.item.id);
        getPair.itemState.state([...list]);
    }

    return(
        <button className="del-btn" onClick={deleteItem}>X</button>
    )
}