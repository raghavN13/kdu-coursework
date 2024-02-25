import "./App.css";
import { ItemList } from "./containers/ItemList";
import { ItemProvider } from "./containers/ItemProvider";
function App() {

  return (
    <ItemProvider>
    <div>
        <ItemList />
    </div>
    </ItemProvider>
  );
}

export default App;
