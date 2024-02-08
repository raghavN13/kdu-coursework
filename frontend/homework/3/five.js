function converterFunction(obj) {
    for (let key in obj) {
        keys.push(key);
        if (typeof obj[key] === 'object') {
         converterFunction(obj[key]);
        }
        else {
            values.push(obj[key]);
        }
    }
}
const player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
        country: "Spain",
        city: "Barcelona",
    },
    careerInfo: {
        fcBarcelona: {
            appearances: 780,
            goals: {
                premierLeagueGoals: 590,
                championsLeagueGoals: 50,
            },
        },
    },
};
keys = [];
values = [];
 converterFunction(player);
console.log(keys);
console.log(values);