const String = '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}'
console.log(String);

const obj = JSON.parse(String);
// converting the names into uppercase
obj.firstName = obj.firstName.toUpperCase();
obj.lastName = obj.lastName.toUpperCase();
obj.city = obj.city.toUpperCase();
obj.country = obj.country.toUpperCase();
console.log("The Object after the conversion is ",obj);
delete obj.email;
console.log(obj);

const newString = JSON.stringify(obj);
console.log(newString);