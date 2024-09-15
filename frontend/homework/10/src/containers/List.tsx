import { ListItem } from "./ListItem";
import { IState } from "./State";
import './ItemList.css'

interface IListProps{
    listItems : IState;
}
export function List({listItems}:IListProps){

    return (
        <div className="itemList">
            <div className="item-list-header">Items</div>
            <ul id="list">
                {
                    listItems.listitems.map((item)=>{
                        const pair = {
                            item : item,
                            itemState : listItems
                        }

                        return (
                            <ListItem key={item.id} getPair={pair} />
                        )
                    })
                }
            </ul>
        </div>
    )

}