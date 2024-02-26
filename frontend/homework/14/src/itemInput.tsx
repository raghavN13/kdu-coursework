import { useState } from "react";
import { addItem } from "./redux/TodoSlice";
import { useDispatch } from "react-redux";

export function ItemInput(){
    const [inputValue,setInputValue] = useState('');
    const dispatch = useDispatch();

    const add = () => {
        if(inputValue.trim() !== ''){
            dispatch(addItem(inputValue.trim()));
            setInputValue('');
        }
    }

    return (

        <div className="itemInputText">
            <input className="enter-text" value={inputValue} onChange={(e) => setInputValue(e.target.value)}
            />
            <button className="item-btn" onClick={add}>Submit</button>
        </div>
    )
    
}