import { useParams, useNavigate } from "react-router-dom";
import { useItemContext } from "../ItemContext";
import "./Itemdetails.css"
import SearchIcon from '@mui/icons-material/Search';

export const ItemDetails = () => {
  const { id } = useParams();
  const { items } = useItemContext();
  const navigate = useNavigate();

  if (!id) {
    return <div>No ID provided</div>;
  }

  const itemId = parseInt(id);

  const item = items.find((item) => item.id === itemId);

  if (!item) {
    return <div>Item not found</div>;
  }

  const handleNavigateBack = () => {
    navigate("/home");
  };

  return (
    <div>
              <div className="navbar">
            <div className="search-box">
                <input
                    type="search"
                    name="search"
                    id="search"
                />
                <div className="search-icon">
                    <SearchIcon className='search-icon'></SearchIcon>
                </div>
            </div>
            <div className="left-side-navbar">

          
            </div>
        </div>
      <div className="heading">

      <p>{item.title}</p>
      </div>
      <div className="item-details">
        <img src={item.image} alt={item.title} />
        <div className="item-info">
        <p className="title-info">Model:{item.title}</p>
        <p className="price-info">Price: ${item.price}</p>
        <p className="product-description">Product Description:</p>
        <p className="description-info">{item.description}</p>
        <p className="ratings">Rating: {item.rating.rate}</p>
        <p className="category-info">Category: {item.category}</p>
      <button className="home-button" onClick={handleNavigateBack}>Back to Products</button>
        </div>
      </div>
    </div>
  );
};
