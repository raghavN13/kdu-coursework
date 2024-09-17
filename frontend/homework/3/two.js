let myFunction = (item,index,arr) => {
    let trimmedString = item.trim();
    let result = trimmedString.slice(0,3).toUpperCase();
    finalArray.push(result);

}

let encoderFunction = (item) =>{
    let trimmedString = item.trim();
    let newString = trimmedString.replace(/a/g,"4").replace(/e/g,"3").replace(/o/g,"0").replace(/s/g,"5");
    return newString;
    
}
const days = ["Sunday   ","   Monday  ",

"  Tuesday","Wednesday  ","  Thursday   ","   Friday",

"Saturday    "]



const finalArray = [];
days.forEach(myFunction);
console.log(finalArray);
console.log(encoderFunction("javascript is cool"));
console.log(encoderFunction("pr0gr4mm1ng 15 fun"));
console.log(encoderFunction("b3c0m3 4 c0d3r"));



