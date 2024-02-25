interface IListitems{
    id : number;
    name : string;
}

export interface IState{
    listitems : IListitems[];
    state : React.Dispatch<React.SetStateAction<{
        id: number;
        name: string;
    }[]>>;
}