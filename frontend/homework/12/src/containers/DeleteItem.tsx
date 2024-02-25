import { useItemContext } from "./ItemProvider";

interface IDeletetItemsProps{
    itemId : number;
}

export function DeleteItem({itemId}: IDeletetItemsProps){

    const {deleteItem} = useItemContext();
    function handleDelete(){
        deleteItem(itemId);
    }

    return(
        <button className="del-btn" onClick={handleDelete}>X</button>
    )
}