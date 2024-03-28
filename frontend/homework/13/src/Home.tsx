import { ItemProvider } from "./ItemContext";
import { ItemList } from "./ItemList/ItemList";
import { Navbar } from "./Navbar/Navbar";

export function Home(){
    return(
        <div>
            
            <Navbar/>
            <ItemList/>

           

            
        </div>
    )
}