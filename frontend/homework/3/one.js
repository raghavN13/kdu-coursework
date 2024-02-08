let tipCalculator = (item,index,arr) => {
    let tipAmount = 0;
    if(item<50){
        tipAmount+=(0.2*item);
    }
    else if(item>=50 && item<=200){
        tipAmount+=(0.15*item);
    }
    else if(item>200){
        tipAmount+=(0.10*item);
    }
    tip.push(tipAmount);
    bill.push(tipAmount+item);
}
const billArray = [140,45,280];
const bill = [];
const tip = [];
billArray.forEach(tipCalculator);
console.log(tip);
console.log(bill);
