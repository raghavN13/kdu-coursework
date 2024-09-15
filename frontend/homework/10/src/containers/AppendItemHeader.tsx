import { ItemInput } from "../itemInput";
import { IState } from "./State";

interface IAppendItemHeaderProps{
    input : IState
}

export function AppendItemHeader({input}: IAppendItemHeaderProps){
    return (
        <div className="item-header-container">
        <div className="add-header-text">ADD ITEM</div>
        <div className="text-input">
            <ItemInput itemInput = {input}/>
        </div>

        </div>
        
    )
}