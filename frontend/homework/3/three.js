const shoes = [{type:"nike",color:"blue",size:"5",price:"1100"},
{type:"adidas",color:"red",size:"6",price:"1200"}]

const shirts = [{type:"usp",color:"blue",size:"L",price:"1100"},
{type:"hnm",color:"red",size:"L",price:"1200"}]

const warehouse = [...shoes,...shirts];
console.log(warehouse);
let totalPrice = 0;

warehouse.forEach(element => {
    totalPrice+=parseInt(element.price);
});

warehouse.sort((a,b) => b.price-a.price);
console.log("Sorted Warehouse On the Basis Of Price is ", warehouse);


const spare = warehouse.filter((item) => item.color==='blue');
console.log("The Warehouse element with the color blue is ", spare);



