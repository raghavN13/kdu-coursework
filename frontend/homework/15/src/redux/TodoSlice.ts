import { PayloadAction, createSlice, isAction } from "@reduxjs/toolkit";
import { v4 as uuidv4 } from 'uuid';

interface Item {
    id: string;
    name: string;
}

interface ItemContextType {
    items: Item[];
    filteredItems: Item[];
    searchText:string;
}

const initialState : ItemContextType = {
    items : [
        {
            id:"1",
            name:"Item 1",
        },
        {
            id:"2",
            name:"Item 2",
        }
    ],

    filteredItems : [
        {
            id:"1",
            name:"Item 1",
        },
        {
            id:"1",
            name:"Item 2",
        }
    ],
    searchText:"",

};

const itemSlice = createSlice({

    name:"items",
    initialState,
    reducers:{
        addItem:(state,action:PayloadAction<string>) => {
            const newItem:Item = {
                id:uuidv4(), 
                name:action.payload,
            };
            state.items.push(newItem);
            state.filteredItems.push(newItem);

        },

        deleteItem:(state,action:PayloadAction<string>) => {
            state.items = state.items.filter((item)=>item.id !== action.payload);
            state.filteredItems = state.items;
            
        },
        setSearchText: (state, action: PayloadAction<string>) => {
            state.searchText = action.payload;
            if (action.payload === "") {
                // If search text is empty, reset filteredItems to include all items
                console.log("entering in the search list", state.items);
                state.filteredItems = [...state.items];
            } else {
                // Otherwise, filter items based on the search text
                state.filteredItems = state.items.filter(item =>
                    item.name.toLowerCase().startsWith(action.payload.toLowerCase())
                );
            }
        },
        
          clearSearchText: (state) => {
            state.searchText = "";
            state.filteredItems = state.items;
          },
    },
});

export const {addItem,deleteItem,setSearchText,clearSearchText} = itemSlice.actions;
export default itemSlice.reducer;



