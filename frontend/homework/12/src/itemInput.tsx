import { useState } from "react";
import { useItemContext } from "./containers/ItemProvider";

export function ItemInput(){
    const {addItem , items} = useItemContext();
    const [inputValue,setInputValue] = useState('');

    const add = () => {
        if(inputValue.trim() !== ''){
            addItem(inputValue.trim());
            setInputValue('');
        }
    }

    console.log(items);

    return (

        <div className="itemInputText">
            <input className="enter-text" value={inputValue} onChange={(e) => setInputValue(e.target.value)}
            />
            <button className="item-btn" onClick={add}>Submit</button>
        </div>
    )
    
}