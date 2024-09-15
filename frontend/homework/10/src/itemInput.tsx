import { useState } from "react";
import { IState } from "./containers/State";
import { text } from "stream/consumers";

interface IItemInputProps{
    itemInput : IState;
}
export function ItemInput({itemInput}:IItemInputProps){
    const [inputValue,setInputValue] = useState('');

    function add() {
        const items = itemInput.listitems;
        const newItem = {
            id : itemInput.listitems.length+1,
            name:inputValue
        }

        itemInput.state([...itemInput.listitems,newItem]);
        console.log(itemInput.listitems);
        setInputValue('');

    }

    return (

        <div className="itemInputText">
            <input className="enter-text" value={inputValue} onChange={(e) => setInputValue(e.target.value)}
            />
            <button className="item-btn" onClick={add}>Submit</button>
        </div>
    )
    
}