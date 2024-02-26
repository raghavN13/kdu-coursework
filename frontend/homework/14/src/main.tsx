
import ReactDOM from "react-dom/client";
import "./index.css";
import { Provider } from "react-redux";
import App from "./App";
import { store } from "./redux/Store";
import React from "react";
ReactDOM.createRoot(document.getElementById("root")!).render(

    <Provider store={store}>
      <App />
    </Provider>

);