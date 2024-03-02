
import { useSelector } from "react-redux";
import "./Items.css"
import { RootState } from "../redux/Store";

export const ItemList = () => {
  const products = useSelector((state:RootState)=>state.products.products);

  return (
    <div className="main-container">
    <div className="items-container">
      {products.map((item) => (
        <div key={item.id} className="item">
          <img src={item.image} alt={item.title} />
          <div className="show">
          <h3 className="title">{item.title}</h3>
          <p className="price">${item.price}</p>
          </div>
          <p className="description">{item.description}</p>
          <p className="category">Category: {item.category}</p>
        </div>
      ))}
    </div>
    </div>
  );
};
