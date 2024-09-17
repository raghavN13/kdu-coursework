const socket = io();
let lastPrice = 501; 
socket.on('get-stock-prices', data => {
 console.log(data);
 const price = data[0].price;

 console.log(data[0].price);

 const barChart = document.getElementById('bar-chart');
 const priceTag = document.getElementById('price');
 priceTag.innerText = `${price}`;

 const bar = document.createElement('div');
 

 bar.classList.add('actual-bar');
 bar.style.height = `${price}px`;
 bar.style.width = '35px';
 bar.style.border = '1px solid black'

 if(lastPrice>price){
 bar.style.background = 'red';
 lastPrice = price;
 
 }
 else{
   bar.style.background = 'green'
   lastPrice = price;
 }





 console.log("hello man how are you");


 barChart.appendChild(bar);



});

document.getElementById('buy').addEventListener('click' , () =>{

 const amount = document.getElementById('input-stock').value.trim();
 

 const secondContainer = document.getElementById('second-container');
 const newBuyContainer = document.createElement('div');

 const newBuy = document.createElement('div');
 const newBuyOption= document.createElement('div');
 
 newBuyOption.innerText = 'Buy';
 newBuy.classList.add('new-buy');
 newBuy.classList.add('new-buy-option');
 newBuy.innerText = `${amount}`;

 newBuyContainer.appendChild(newBuy);
 newBuyContainer.appendChild(newBuyOption);
 newBuyContainer.style.display = 'flex';
 newBuyContainer.style.justifyContent = 'space-between'
 newBuyContainer.style.border = '1px solid black';

 secondContainer.appendChild(newBuyContainer);
   
})

document.getElementById('sell').addEventListener('click' , () =>{

 const amount = document.getElementById('input-stock').value.trim();
 

 const secondContainer = document.getElementById('second-container');
 const newSellContainer = document.createElement('div');
 const newSell = document.createElement('div');
 const newSellOption= document.createElement('div');
 
 newSellOption.innerText = 'Sell';
 newSell.classList.add('new-buy');
 newSell.classList.add('new-buy-option');
 newSell.innerText = `${amount}`;

 newSellContainer.appendChild(newSell);
 newSellContainer.appendChild(newSellOption);

 newSellContainer.style.display = 'flex';
 newSellContainer.style.justifyContent = 'space-between'
 newSellContainer.style.border = '1px solid black';
 
 secondContainer.appendChild(newSellContainer);
 
 
 
})





