import { UseDispatch, useDispatch } from "react-redux";
import { deleteItem } from "../redux/TodoSlice";
interface IDeletetItemsProps{
    itemId : number;
}

export function DeleteItem({itemId}: IDeletetItemsProps){

    const dispatch = useDispatch();
    function handleDelete(){
        dispatch(deleteItem(itemId));
    }

    return(
        <button className="del-btn" onClick={handleDelete}>X</button>
    )
}