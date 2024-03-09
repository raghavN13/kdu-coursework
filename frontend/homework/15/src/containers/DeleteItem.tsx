import { UseDispatch, useDispatch } from "react-redux";
import { deleteItem } from "../redux/TodoSlice";
interface IDeletetItemsProps{
    itemId : string;
}

export function DeleteItem({itemId}: IDeletetItemsProps){

    const dispatch = useDispatch();
    function handleDelete(){
        console.log("entering in the delete ");
        dispatch(deleteItem(itemId));
    }

    return(
        <button className="del-btn" onClick={handleDelete}>X</button>
    )
}