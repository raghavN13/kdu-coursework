import { ItemInput } from "../itemInput";

export function AppendItemHeader(){
    return (
        <div className="item-header-container">
        <div className="add-header-text">ADD ITEM</div>
        <div className="text-input">
            <ItemInput/>
        </div>

        </div>
        
    )
}