interface IUsers{
    id:number;
    name:string;
}

interface IListitem{
    list : IUsers[];
    state : React.Dispatch<React.SetStateAction<{
        id: number;
        name: string;
    }[]>>;
}

interface IListProp {
    listItem : IListitem;
}

export function Child({listItem}:IListProp){
    function clicked(){
        const user = {
            id:3,
            name:'ABC'
        }

        listItem.state([...listItem.list,user]);
    }
    return (
        <>
        <ul>
            {
                listItem.list.map((item)=>{
                    return <li>{item.name}</li>
                })
            }
        </ul>
        <button onClick={clicked}>Click</button>
        </>
    )
}
