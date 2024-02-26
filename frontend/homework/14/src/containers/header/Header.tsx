import { Search } from "./Search";
import './Header.css';

export function Header(){
 return (
    <div className="header-items">
        <p>Item Lister</p>
        <Search/>

    </div>
    )
}