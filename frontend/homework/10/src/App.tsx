import React, { useState } from "react";
import "./App.css";
import "./child"
import {Child} from "./child"; 
import { ItemList } from "./containers/ItemList";
function App() {

  const [items,setState] = useState(
    [
      {
        id:1,
        name:"Item 1"
      },
      {
        id:2,
        name:'Item 2'
      }
    ]
  );

  const flow = {
    listitems:items,
    state:setState
  }

  return (
    <div className="App">
      <ItemList itemsInfo = {flow} />
    </div>
  );
}

export default App;
