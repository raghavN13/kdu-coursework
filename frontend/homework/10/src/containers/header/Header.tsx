import { IState } from "../State";
import { Search } from "./Search";
import './Header.css';

interface IHeaderProps{
    header : IState;
}
export function Header({header}:IHeaderProps){
 return (
    <div className="header-items">
        <p>Item Lister</p>
        <Search search = {header}/>

    </div>
    )
}