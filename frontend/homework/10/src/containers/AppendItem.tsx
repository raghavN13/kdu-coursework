import { AppendItemHeader } from "./AppendItemHeader";
import { List } from "./List";
import { IState } from "./State";
import './AppendItemHeader.css'

interface IAppendItemProp{
    item : IState;
}
export function AppendItem({item}:IAppendItemProp){
    return(
        <>
        <AppendItemHeader input = {item}/>
        <List listItems = {item}/>
        </>
    )
}